package com.example.beproj1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class PackageReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		boolean x=false;
		x= intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);
		Log.d("beproj1","%%%%%%% Package Receiver  "+String.valueOf(x));
		if(x==false)
		{
		 if (intent.getAction().equalsIgnoreCase(Intent.ACTION_PACKAGE_ADDED))
		 {
			 //service1
			 //Log.d("beproj1","in package receiver");
			 //Log.d("beproj1","start service1");
			
			 Log.d("beproj1","$$$$$$$$$$ IN Install Receiver $$$$$$$$$"+intent.getDataString());
			 String s1=intent.getDataString();
			 String s2="com.example.beproj1";
			 if(!s1.equalsIgnoreCase(s2))
			 {
			 Intent service = new Intent(context, Service_intent.class);
			 service.putExtra("name", intent.getDataString());
			 service.putExtra("type", "install");
			 context.startService(service);
			 }
			 else
			 {
				 Toast.makeText(context, "Newly Appdsdsds", Toast.LENGTH_LONG).show();
			 }
			 //Log.d("beproj1","ServiceTest ended... Back in PackageReceiver");
		 }
		}
		 else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_PACKAGE_REPLACED))
		 {
			 Log.d("beproj1","detected that package replaced");
			 Toast.makeText(context, "Application : UPDATED", Toast.LENGTH_LONG).show();
			 String s1=intent.getDataString();
			 Intent service = new Intent(context, Service_intent.class);
			 service.putExtra("name", intent.getDataString());
			 service.putExtra("type", "update");
			 context.startService(service);
			 
		 } 
		
	}
		 

}
