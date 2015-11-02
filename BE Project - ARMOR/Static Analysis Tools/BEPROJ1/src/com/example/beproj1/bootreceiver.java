package com.example.beproj1;

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
		Log.d("beproj1","BootReceiver : Entered!!");
		Toast.makeText(context, "ARMOR STARTED... @JARS", Toast.LENGTH_LONG).show();
		Log.d("beproj1","BootReceiver : Exited !!");
	}
}
