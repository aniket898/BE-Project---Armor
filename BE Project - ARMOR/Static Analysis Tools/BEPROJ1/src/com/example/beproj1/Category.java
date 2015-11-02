package com.example.beproj1;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import android.inputmethodservice.*;

public class Category extends Activity
{	
	
	
	 public AlertDialog mAlert;
	 SharedPreferences prefs;
	 int position=0;
	 public String value;
	 SharedPreferences settings;
	 AlertDialog alert_dialog ;
	 
	 @SuppressLint("NewApi")
	void set1()
	 {
		 InputMethodService obj=new InputMethodService();
		 obj.setBackDisposition(InputMethodService.BACK_DISPOSITION_WILL_NOT_DISMISS);
		 //setBackDisposition(InputMethodService.BACK_DISPOSITION_WILL_NOT_DISMISS);
	 }
		
	
	 @Override
		public void onBackPressed() {

		       return;
		    }
	
	protected void onCreate(Bundle savedInstanceState)
	{	
		
		set1();
		//Log.d("beproj1","in activity");
		//Toast.makeText(getApplicationContext(), "hi beproj1 yardi",1).show();
		 settings = getSharedPreferences("mysettings1",Context.MODE_PRIVATE);
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		final AlertDialog.Builder singlechoicedialog = new AlertDialog.Builder(this);
    	final CharSequence[] Report_items = { "Communications", "Photography", "News & Magazines","Productivity", "Social","Finance","Health & Fitness","Media & Video","Music & Audio","Books & Reference","Games","Unknown" };
    	singlechoicedialog.setTitle("Select The Category Of Installed Application :"+settings.getString("appname", "ApplicationName")+" "+settings.getString("type1", "type"));
    	singlechoicedialog.setInverseBackgroundForced(false);
    	singlechoicedialog.setSingleChoiceItems(Report_items, -1,new DialogInterface.OnClickListener() 
    	{
    		public void onClick(DialogInterface dialog, int item) 
    		{
    			 position = item;
    			 value = Report_items[item].toString();
    			set();
    			 //alert_dialog.dismiss();
    			 alert_dialog.cancel();
    			 Category.this.finish();
    			 }
    			});
    	        alert_dialog = singlechoicedialog.create();
    			alert_dialog.show();
    			alert_dialog.getListView().setItemChecked(position, true);			
    }
	
	void set()
	{
		 
		 SharedPreferences.Editor editor = settings.edit();
		 editor.putString("category", value);
		 editor.commit();
		 //Toast.makeText(getApplicationContext(), "category"+value,1).show();
		 
		//start a new service
		// Log.d("beproj1","start service3 next");
		// Toast.makeText(getBaseContext(), "Service : 3", Toast.LENGTH_LONG).show();
		 Intent service = new Intent(getBaseContext(), QueryServer.class);
		 this.startService(service);
	}
		
	}

