package com.example.beproj1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RemovedPackageRec extends BroadcastReceiver
{


	@Override
	public void onReceive(Context context, Intent intent) 
	{
		boolean x=false;
		x= intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);
		Log.d("beproj1","%%%%%%%%% Removed Pack "+String.valueOf(x));
		if(x==false)
		{
		 Log.d("beproj1","RemovedPackageRec.onReceive : Entering");
		 Toast.makeText(context, "Application Removed "+intent.getDataString(), Toast.LENGTH_LONG).show();
		 Log.d("beproj1","RemovedPackageRec.onReceive : Got PAckage Name as : "+intent.getDataString());
		 //db.deleteapp(intent.getDataString());
		 String s1=intent.getDataString();
		 String s2="com.example.beproj1";
		 if(!s1.equalsIgnoreCase(s2))
		 {
		 Intent service = new Intent(context, Service_intent.class);
		 service.putExtra("name", intent.getDataString());
		 service.putExtra("type","remove");
		 Log.d("beproj1","RemovedPackageRec.onReceive : Called service Service_intent");
		 context.startService(service);
		 Log.d("beproj1","RemovedPackageRec.onReceive : Started another Service_intent...");
		 }
		Log.d("beproj1","RemovedPackageRec.onReceive : Returning");
		}
	}

}
