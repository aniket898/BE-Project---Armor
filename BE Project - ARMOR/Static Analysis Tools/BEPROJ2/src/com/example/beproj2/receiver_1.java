package com.example.beproj2;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class receiver_1 extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		String s1=intent.getDataString();
		Log.d("beproj2","Got the installed Package name as : "+s1);
		String s2="com.example.beproj2";
		 if(!s1.equalsIgnoreCase(s2))
		 {
			 s1=s1.substring(8);
		 Intent service = new Intent(context, Service_2.class);
		 service.putExtra("name",s1);
		 service.putExtra("type", "install");
		 context.startService(service);
		 }
		 else
		 {
			 Toast.makeText(context, "Newly App install detected... ", Toast.LENGTH_LONG).show();
		 }
		 
	}
}


