package com.example.beproj2;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class Display extends Activity
{
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d("beproj2","Activity destroyed");
	}
	protected void onCreate(Bundle savedInstanceState)
	{	
		ArrayList<String> pvg=getIntent().getStringArrayListExtra("list1");
		Log.d("beproj2","In Display Activity");
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		final AlertDialog.Builder builder= new AlertDialog.Builder(this);
    	CharSequence[] Report_items = null;
    	List<String> strlist = new Vector<String>();
    	for(int i=0;i<pvg.size();i++)
		{
    		strlist.add(pvg.get(i));
		}
    	Report_items= strlist.toArray(new CharSequence[strlist.size()]);
    	builder.setTitle("Permission Escalator")
    	.setInverseBackgroundForced(true)
    	.setSingleChoiceItems(Report_items,-1,new DialogInterface.OnClickListener() 
    	{
    		public void onClick(DialogInterface dialog, int item) 
    		{
    		}
    	})	
    	.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() 
    	{
            @Override
            public void onClick(DialogInterface dialog, int id) 
            { 
               dialog.cancel();
               Display.this.finish();
            }
        });
      
    	AlertDialog alert_dialog = builder.create();
		alert_dialog.show();
    	
	}
	
}
