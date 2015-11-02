package com.example.beproj1;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class OptionsHandler extends Activity
{
	SharedPreferences settings ;
	List<String> strlist;
	DatabaseHandler db;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d("beproj1","Yes destroyed");
	}
	
	
	protected void onCreate(Bundle savedInstanceState)
	{	
		 db = new DatabaseHandler(this);
		Log.d("beproj1","**********in activity");
		//Toast.makeText(getApplicationContext(), "hi beproj1 yardi",1).show();
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		final AlertDialog.Builder builder= new AlertDialog.Builder(this);
    	CharSequence[] Report_items = null;
    	strlist = new Vector<String>();
    	settings = getSharedPreferences("mysettings1", Context.MODE_PRIVATE);
    	editor = settings.edit();
    	/* get the comparison of permissions result */
    	int comperleng=settings.getInt("comperleng", 0);
    	Log.d("beproj1","got the comperleng "+String.valueOf(comperleng));
    	/* get all the comp per now */
    	int i;
    	String temp1="";
    	for(i=0;i<comperleng;i++)
    	{
    		temp1=settings.getString("comper"+String.valueOf(i), "");
    		strlist.add(temp1);
    	}
    	//convert strlist to charsequence
		/*get application name*/
    	final String sp1 = settings.getString("appname","");
    	Report_items= strlist.toArray(new CharSequence[strlist.size()]);
    	if(comperleng==0)
    	{
    		strlist.add("Standard Permission Abiding Application.... ");
    	}
    	builder.setTitle("Permission Analyser : "+sp1)
    	.setInverseBackgroundForced(true)
    	.setSingleChoiceItems(Report_items,-1,new DialogInterface.OnClickListener() 
    	{
    		public void onClick(DialogInterface dialog, int item) 
    		{
    			 //Log.d("beproj1","on click detected");
    			int position = item;
    			String  value = strlist.get(position);
    			Log.d("beproj1","new1 hey !!!"+value);
    			String temp1=settings.getString("comperdes"+String.valueOf(position), "");
    		    Log.d("beproj1","Got A click!!!!!! "+value+" "+temp1);
    		    Toast.makeText(getApplicationContext(), temp1,1).show();
    		    
    		 }
    	})	
    	.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() 
    	{
            @Override
            public void onClick(DialogInterface dialog, int id) 
            {
               //ADD THE NEW APPLICATIONS DATA IN THE LOCAL DATABASE
               
               String s1,s2,s3;
               int i1;
               //s1 is appname
               s1=settings.getString("appname", "");
               db.deleteapp(s1);
               i1=settings.getInt("catid", 0);
               s2=settings.getString("newbitmap", "");
               s3=settings.getString("category", "");
               Log.d("beproj1","Adding to th database");
               Log.d("beproj1",s1+" "+s2+" "+s3+" "+String.valueOf(i1));
               AppEntry ae=new AppEntry(s1,s2,s3,i1);
               db.addEntry(ae);
               Log.d("beproj1","done");
               Toast.makeText(getApplicationContext(),"Added Your Newly Installed Application In Our Records",1).show();
               db.getAllApps();
               dialog.cancel();
               OptionsHandler.this.finish();
         
             
            }
        })
        .setNegativeButton("UNINSTALL", new DialogInterface.OnClickListener() 
        {
            @Override
            public void onClick(DialogInterface dialog, int id) 
            {	
            	uninstallApplication(sp1);	
            	OptionsHandler.this.finish();
            }
            	
            
        });

    	AlertDialog alert_dialog = builder.create();
		alert_dialog.show();
    			
    			
    }
public void uninstallApplication(String applicationPackageName)
{
	if(applicationPackageName!=null)
	{
		try
		{
			Intent intent = new Intent(Intent.ACTION_DELETE);
			intent.setData(Uri.parse("package:"+applicationPackageName));
			startActivity(intent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
}
