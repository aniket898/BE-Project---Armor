package com.example.beproj1;

import java.util.List;

import java.util.Vector;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.inputmethodservice.*;

public class MainActivity extends Activity 
{
	
	List<String> strlist;
	CharSequence[] Report_items;
	int position;
	int flag;
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	
	@Override
	public void onResume()
	{
		super.onResume();
		//EditText tt1=(EditText) findViewById(R.id.editText1);
	}
	//@Override
	/*public void onBackPressed() {

	       return;
	    }
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	
		settings = getSharedPreferences("mysettings1", Context.MODE_PRIVATE);
		editor = settings.edit();
		Log.d("beproj1","in main activity");
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		final Button button1 = (Button) findViewById(R.id.button1);
		final AlertDialog.Builder singlechoicedialog = new AlertDialog.Builder(this);
		Report_items=null;
		strlist = new Vector<String>();
        button1.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		flag=0;
        		Log.d("beproj1","mainactivity:button1 click detected");
        		final PackageManager packageManager = getPackageManager();
   		   		List<ApplicationInfo> installedApplications = 
   		   			packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
   		   		for (ApplicationInfo appInfo : installedApplications)
   		   		{
   		   			//Log.d("beproj1", "Package name : " + appInfo.packageName);
   		   			//Log.d("beproj1", "Name: " + appInfo.loadLabel(packageManager));
   		   			String s1=appInfo.packageName;
   		   			if(!s1.startsWith("com.android."))
   		   			strlist.add(appInfo.packageName);
   		   		}
   		   		singlechoicedialog.setTitle("Select One of the Installed Applications");
   		   		singlechoicedialog.setInverseBackgroundForced(false);
   		   		Report_items= strlist.toArray(new CharSequence[strlist.size()]);
   		   		singlechoicedialog.setSingleChoiceItems(Report_items, -1,new DialogInterface.OnClickListener() 
   		   		{
   		   			public void onClick(DialogInterface dialog, int item) 
   		   			{
   		   				//SharedPreferences preferences =getSharedPreferences("mysettings1", Context.MODE_PRIVATE);
   		   				//SharedPreferences.Editor editor = preferences.edit();
   		   				Log.d("beproj1","main activity: Application selected : "+Report_items[item]);
   		   				position = item;
   		   				set();
   		   				dialog.cancel();
   		   				EditText tt1=(EditText) findViewById(R.id.editText1);
   		   				//editor.putString("appname", Report_items[item].toString());
		   				//editor.commit();
   		   				try
   		   				{
   		   					extract_permission(Report_items[item].toString());
   		   				} 
   		   				catch (NameNotFoundException e) 
   		   				{
   		   					e.printStackTrace();
   		   				}
   		   				editor.putInt("wait_flag", 0);
   		   				editor.commit();
   		   				Log.d("beproj1","MainActivity : Starting Category Class intent");
   		   			    tt1.setText( Report_items[item] + "Please wait ......");
   		   				Intent in=new Intent().setClass(MainActivity.this,com.example.beproj1.Category.class);
   		   				in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	
   		   				startActivity(in);
   		   				MainActivity.this.onPause();
   		   			//	tt1.setText( Report_items[item] + "Has been analysed and added to our database...");
   		   				Log.d("beproj1","MainActivity : done with category class intent");
   		   			}
   	    		});
   	    		AlertDialog alert_dialog = singlechoicedialog.create();
   	    		alert_dialog.show();		  
        	}
        	});
        
        	final Button button2 = (Button) findViewById(R.id.button2);
        	button2.setOnClickListener(new View.OnClickListener() 
        	{
        		public void onClick(View v)
        		{
        			Log.d("beproj1","MainActivity : Activity has ended");
        			MainActivity.this.finish();
        		}
        	});
	
	}
	
	
	
	public void set()
	{
		flag=1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	private void extract_permission(String nam) throws NameNotFoundException 
	{
		PackageManager pm = getPackageManager();
		//List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		//Log.d("testing",nam);
		SharedPreferences preferences =getSharedPreferences("mysettings1", Context.MODE_PRIVATE);
		//SharedPreferences settings = getSharedPreferences("mysettings2", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("appname",nam);
		editor.commit();
		try 
		{
		      PackageInfo packageInfo = pm.getPackageInfo(nam, PackageManager.GET_PERMISSIONS);
		      String[] requestedPermissions = packageInfo.requestedPermissions;
		      PermissionInfo[] definedPermissions = packageInfo.permissions;
		      Log.d("beproj1","hiwiwiiw");
		     // Toast.makeText(getApplicationContext(), "hi "+definedPermissions,1).show();
		      if(requestedPermissions.equals(null))
		      {
		    	  Log.d("beproj1",".........yes null");
		      }
		      if(!requestedPermissions.equals(null))
		      {
		      Log.d("beproj1","perlength : "+String.valueOf(requestedPermissions.length));
		      editor.putLong("perlength",requestedPermissions.length);
		      editor.commit();
		      if(requestedPermissions != null) 
		      {
		         for (int i = 0; i < requestedPermissions.length; i++) 
		         {
		            //Toast.makeText(getApplicationContext(), requestedPermissions[i],1).show();
		            editor.putString("per"+String.valueOf(i+1),requestedPermissions[i]);
		            editor.commit();
		         }
		      }
		      }
		      //now also put 
		   //   Log.d("beproj1","is null ? "+definedPermissions.equals(null));
		     /* if(definedPermissions.)
		      {
		     int len= definedPermissions.length;
		     Log.d("beproj1","Defined permissions "+String.valueOf(len));
		     //now add to the shared preference
		     editor.putInt("newlength",definedPermissions.length);
		      editor.commit();
		      
		      
		     //Toast.makeText(getApplicationContext(),"Length = "+String.valueOf(len),1).show();
		     for(int i=0;i<len;i++)
		     {
		    	 String str1=definedPermissions[i].name;
		    	// Toast.makeText(getApplicationContext(), "permi is "+str1, 1).show();
		    	// Log.d("beproj1","the per is : "+str1);
		    	 editor.putString("newper"+String.valueOf(i+1),str1);
		    	 editor.commit();
		     }
		      }*/
		    
		     
		   } 
		catch (NameNotFoundException e) 
		{
		      e.printStackTrace();
		}
	}
}



