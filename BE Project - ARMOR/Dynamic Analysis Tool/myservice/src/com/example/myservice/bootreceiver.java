package com.example.myservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class bootreceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Log.d("juhi","BootReceiver : Entered!!");
		Toast.makeText(context, "Our service started", Toast.LENGTH_LONG).show();
		Intent startServiceIntent = new Intent(context,Myservice.class);
		context.startService(startServiceIntent);
		Log.d("juhi","BootReceiver : Exited !!");
	}
}
