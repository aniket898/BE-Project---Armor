package com.example.beproj2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.IBinder;
import android.util.Log;

public class Service_2 extends Service
{
	String appname;
	List<String> appover=new LinkedList();
	List<String> intper=new LinkedList();
	List<String> pvg = new LinkedList(); 
	@Override
	public IBinder onBind(Intent arg0) 
	{
		return null;
	}
	@Override
	public void onCreate() 
	{
		appname="";
		Log.d("beproj2","Service_2 : Created");
		super.onCreate();
	}
	
	@Override
	public void onDestroy() 
	{
		Log.d("beproj2","Service_2 : Destroyed !!");
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		appname=intent.getStringExtra("name");
		Log.d("beproj2","Service_2 : onStartCommand !!");
		PackageManager pm = getPackageManager();
		PackageInfo packageInfo = null;
		try 
		{
			packageInfo = pm.getPackageInfo(appname, PackageManager.GET_PERMISSIONS);
		} 
		catch (NameNotFoundException e) 
		{
			e.printStackTrace();
		}
	    String[] requestedPermissions = packageInfo.requestedPermissions;
		Log.d("beproj2","Got permission length as "+requestedPermissions.length);
	    if(requestedPermissions != null) 
	    {
	         for (int i = 0; i < requestedPermissions.length; i++) 
	         {
	        	 String t1=requestedPermissions[i];
	        	 if(!t1.contains("android.permission"))
	        	 {
	        		 intper.add(t1);
	        	 }
	         }
	    }
	    //now for each of the current app's external permission
	    for(int i=0;i<intper.size();i++)
	    {
	    	Log.d("beproj2","#####Unknown... "+intper.get(i));
	    	next2(intper.get(i));
	    }
	    Log.d("beproj2","Calling the display Activity.....");
	    Intent in=new Intent().setClass(Service_2.this,com.example.beproj2.Display.class);
	    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	
		ArrayList<String> al=new ArrayList<String>();
		appover.add(appname);
		for(int k=0;k<pvg.size();k++)
		{
			al.add(pvg.get(k));
			Log.d("list",pvg.get(k));
		}
		in.putStringArrayListExtra("list1",al);
		startActivity(in);
	    Service_2.this.stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void next2(String x)
	{
		PackageManager pm = getPackageManager();
		PackageInfo packageInfo = null;
		Log.d("beproj1","####External Permission : "+x);
    	String pos = null;
		try 
		{
			pos = next1(x);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int flag=0;
		for(int k=0;k<appover.size();k++)
		{
			if(appover.get(k).equals(pos))
			flag=1;
		}
    	if(!pos.equals("") && flag==0)
    	{ 
    		appover.add(pos);
    		Log.d("beproj2",x+" Inherits from ..."+pos);
    		//now query the package to collect its permissions.....
    		try 
    		{
				packageInfo = pm.getPackageInfo(pos, PackageManager.GET_PERMISSIONS);
			} 
    		catch (NameNotFoundException e) 
    		{
				e.printStackTrace(); 
			}
    		String[] rp = packageInfo.requestedPermissions;
    		Log.d("beproj2","Got permission length as "+rp.length);
    	    if(rp != null) 
    	    {
    	         for (int k = 0; k < rp.length; k++) 
    	         {
    	        	 String t1=rp[k];
    	        	 Log.d("beproj2","inside .. "+t1);
    	        	 if(t1.contains("android.permission") && !pvg.contains(t1))
    	        	 {
    	        		 pvg.add(t1);
    	        		 Log.d("beproj2","NEWP"+t1);
    	        	 }
    	        	 else
    	        	 {
    	        		next2(t1);
    	        		Log.d("beproj2","%% Calling Recursively .... "+t1); 
    	        	 }
    	         }
    	    }
    		
    	}

	}
	
	private String next1(String search)
	{
		PackageManager pm = getPackageManager();
		PackageInfo packageInfo = null;	
		String t1="";
		List<PackageInfo> installedp = getPackageManager().getInstalledPackages(0);	
		for(int i=0;i<installedp.size();i++)
		{
			t1=installedp.get(i).packageName;
			Log.d("beproj2","Got"+t1);
			try {
				packageInfo = pm.getPackageInfo(t1, PackageManager.GET_PERMISSIONS);
				PermissionInfo[] definedPermissions = packageInfo.permissions;
				if(!(definedPermissions==null))
				{
					for (int j = 0; j < definedPermissions.length; j++) 
					{
			        	 String t2=definedPermissions[j].name;
			        	 if(t2.equals(search))
			        	 {
			        		 return t1;
			        	 }
			        	
					}
				}
			} 
			catch (NameNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return "";
		
		
	}

}
