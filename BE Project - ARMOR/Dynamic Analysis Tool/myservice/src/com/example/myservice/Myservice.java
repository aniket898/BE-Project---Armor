package com.example.myservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


public class Myservice extends Service {
	
	ActivityManager am = null;
    Context context = null;
    int p_count=0;
    int n_count=0;
    String new_app=null;
    String last_app=new String("Hello");
    ActivityManager.RunningTaskInfo n_taskInfo=null;
    List< ActivityManager.RunningTaskInfo > taskInfo=null;
    Thread mythread;
    PackageManager pm;
    int counter=600;
    Map<String, Integer> myMap = new HashMap<String, Integer>();
    public Myservice() 
	{

		//new_activity =new RunningActivity(getApplicationContext());
		
	}
	public static native String netlinkconnect(int uid);

	   // Provide additional functionality, that "extends" the native method
	  

	   // Load library*/

	static {
	      System.loadLibrary("net_linksocket");
	   }
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
    public void onCreate() {
      //  Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();
		myMap.put("android.permission.ACCESS_COARSE_LOCATION", 0);
		myMap.put("android.permission.ACCESS_FINE_LOCATION", 0);
		myMap.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", 0);
		myMap.put("android.permission.ACCESS_MOCK_LOCATION", 0);
		myMap.put("android.permission.ACCESS_NETWORK_STATE", 0);
		myMap.put("android.permission.ACCESS_SURFACE_FLINGER", 0);
		myMap.put("android.permission.ACCESS_WIFI_STATE", 0);
		myMap.put("android.permission.ACCOUNT_MANAGER", 0);
		myMap.put("android.permission.ADD_VOICEMAIL", 0);
		myMap.put("android.permission.AUTHENTICATE_ACCOUNTS", 0);
		myMap.put("android.permission.BATTERY_STATS", 0);
		myMap.put("android.permission.BIND_ACCESSIBILITY_SERVICE", 0);
		myMap.put("android.permission.BIND_APPWIDGET", 0);
		myMap.put("android.permission.BIND_DEVICE_ADMIN", 0);
		myMap.put("android.permission.BIND_INPUT_METHOD", 0);
		myMap.put("android.permission.BIND_NFC_SERVICE", 0);
		myMap.put("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", 0);
		myMap.put("android.permission.BIND_PRINT_SERVICE", 0);
		myMap.put("android.permission.BIND_REMOTEVIEWS", 0);
		myMap.put("android.permission.BIND_TEXT_SERVICE", 0);
		myMap.put("android.permission.BIND_VPN_SERVICE", 0);
		myMap.put("android.permission.BIND_WALLPAPER", 0);
		myMap.put("android.permission.BLUETOOTH", 0);
		myMap.put("android.permission.BLUETOOTH_ADMIN", 0);
		myMap.put("android.permission.BLUETOOTH_PRIVILEGED", 0);
		myMap.put("android.permission.BRICK", 0);
		myMap.put("android.permission.BROADCAST_PACKAGE_REMOVED", 0);
		myMap.put("android.permission.BROADCAST_SMS", 0);
		myMap.put("android.permission.BROADCAST_STICKY", 0);
		myMap.put("android.permission.BROADCAST_WAP_PUSH", 0);
		myMap.put("android.permission.CALL_PHONE", 0);
		myMap.put("android.permission.CALL_PRIVILEGED", 0);
		myMap.put("android.permission.CAMERA", 0);
		myMap.put("android.permission.CAPTURE_AUDIO_OUTPUT", 0);
		myMap.put("android.permission.CAPTURE_SECURE_VIDEO_OUTPUT", 0);
		myMap.put("android.permission.CAPTURE_VIDEO_OUTPUT", 0);
		myMap.put("android.permission.CHANGE_COMPONENT_ENABLED_STATE", 0);
		myMap.put("android.permission.CHANGE_CONFIGURATION", 0);
		myMap.put("android.permission.CHANGE_NETWORK_STATE", 0);
		myMap.put("android.permission.CHANGE_WIFI_MULTICAST_STATE", 0);
		myMap.put("android.permission.CHANGE_WIFI_STATE", 0);
		myMap.put("android.permission.CLEAR_APP_CACHE", 0);
		myMap.put("android.permission.CLEAR_APP_USER_DATA", 0);
		myMap.put("android.permission.CONTROL_LOCATION_UPDATES", 0);
		myMap.put("android.permission.DELETE_CACHE_FILES", 0);
		myMap.put("android.permission.DELETE_PACKAGES", 0);
		myMap.put("android.permission.DEVICE_POWER", 0);
		myMap.put("android.permission.DIAGNOSTIC", 0);
		myMap.put("android.permission.DISABLE_KEYGUARD", 0);
		myMap.put("android.permission.DUMP", 0);
		myMap.put("android.permission.EXPAND_STATUS_BAR", 0);
		myMap.put("android.permission.FACTORY_TEST", 0);
		myMap.put("android.permission.FLASHLIGHT", 0);
		myMap.put("android.permission.FORCE_BACK", 0);
		myMap.put("android.permission.GET_ACCOUNTS", 0);
		myMap.put("android.permission.GET_PACKAGE_SIZE", 0);
		myMap.put("android.permission.GET_TASKS", 0);
		myMap.put("android.permission.GET_TOP_ACTIVITY_INFO", 0);
		myMap.put("android.permission.GLOBAL_SEARCH", 0);
		myMap.put("android.permission.HARDWARE_TEST", 0);
		myMap.put("android.permission.INJECT_EVENTS", 0);
		myMap.put("android.permission.INSTALL_LOCATION_PROVIDER", 0);
		myMap.put("android.permission.INSTALL_PACKAGES", 0);
		myMap.put("android.permission.INSTALL_SHORTCUT", 0);
		myMap.put("android.permission.INTERNAL_SYSTEM_WINDOW", 0);
		myMap.put("android.permission.INTERNET", 0);
		myMap.put("android.permission.KILL_BACKGROUND_PROCESSES", 0);
		myMap.put("android.permission.LOCATION_HARDWARE", 0);
		myMap.put("android.permission.MANAGE_ACCOUNTS", 0);
		myMap.put("android.permission.MANAGE_APP_TOKENS", 0);
		myMap.put("android.permission.MANAGE_DOCUMENTS", 0);
		myMap.put("android.permission.MASTER_CLEAR", 0);
		myMap.put("android.permission.MEDIA_CONTENT_CONTROL", 0);
		myMap.put("android.permission.MODIFY_AUDIO_SETTINGS", 0);
		myMap.put("android.permission.MODIFY_PHONE_STATE", 0);
		myMap.put("android.permission.MOUNT_FORMAT_FILESYSTEMS", 0);
		myMap.put("android.permission.MOUNT_UNMOUNT_FILESYSTEMS", 0);
		myMap.put("android.permission.NFC", 0);
		myMap.put("android.permission.PERSISTENT_ACTIVITY", 0);
		myMap.put("android.permission.PROCESS_OUTGOING_CALLS", 0);
		myMap.put("android.permission.READ_CALENDAR", 0);
		myMap.put("android.permission.READ_CALL_LOG", 0);
		myMap.put("android.permission.READ_CONTACTS", 0);
		myMap.put("android.permission.READ_EXTERNAL_STORAGE", 0);
		myMap.put("android.permission.READ_FRAME_BUFFER", 0);
		myMap.put("android.permission.READ_HISTORY_BOOKMARKS", 0);
		myMap.put("android.permission.READ_INPUT_STATE", 0);
		myMap.put("android.permission.READ_LOGS", 0);
		myMap.put("android.permission.READ_PHONE_STATE", 0);
		myMap.put("android.permission.READ_PROFILE", 0);
		myMap.put("android.permission.READ_SMS", 0);
		myMap.put("android.permission.READ_SOCIAL_STREAM", 0);
		myMap.put("android.permission.READ_SYNC_SETTINGS", 0);
		myMap.put("android.permission.READ_SYNC_STATS", 0);
		myMap.put("android.permission.READ_USER_DICTIONARY", 0);
		myMap.put("android.permission.REBOOT", 0);
		myMap.put("android.permission.RECEIVE_BOOT_COMPLETED", 0);
		myMap.put("android.permission.RECEIVE_MMS", 0);
		myMap.put("android.permission.RECEIVE_SMS", 0);
		myMap.put("android.permission.RECEIVE_WAP_PUSH", 0);
		myMap.put("android.permission.RECORD_AUDIO", 0);
		myMap.put("android.permission.REORDER_TASKS", 0);
		myMap.put("android.permission.RESTART_PACKAGES", 0);
		myMap.put("android.permission.SEND_RESPOND_VIA_MESSAGE", 0);
		myMap.put("android.permission.SEND_SMS", 0);
		myMap.put("android.permission.SET_ACTIVITY_WATCHER", 0);
		myMap.put("android.permission.SET_ALARM", 0);
		myMap.put("android.permission.SET_ALWAYS_FINISH", 0);
		myMap.put("android.permission.SET_ANIMATION_SCALE", 0);
		myMap.put("android.permission.SET_DEBUG_APP", 0);
		myMap.put("android.permission.SET_ORIENTATION", 0);
		myMap.put("android.permission.SET_POINTER_SPEED", 0);
		myMap.put("android.permission.SET_PREFERRED_APPLICATIONS", 0);
		myMap.put("android.permission.SET_PROCESS_LIMIT", 0);
		myMap.put("android.permission.SET_TIME", 0);
		myMap.put("android.permission.SET_TIME_ZONE", 0);
		myMap.put("android.permission.SET_WALLPAPER", 0);
		myMap.put("android.permission.SET_WALLPAPER_HINTS", 0);
		myMap.put("android.permission.SIGNAL_PERSISTENT_PROCESSES", 0);
		myMap.put("android.permission.STATUS_BAR", 0);
		myMap.put("android.permission.SUBSCRIBED_FEEDS_READ", 0);
		myMap.put("android.permission.SUBSCRIBED_FEEDS_WRITE", 0);
		myMap.put("android.permission.SYSTEM_ALERT_WINDOW", 0);
		myMap.put("android.permission.TRANSMIT_IR", 0);
		myMap.put("android.permission.UNINSTALL_SHORTCUT", 0);
		myMap.put("android.permission.UPDATE_DEVICE_STATS", 0);
		myMap.put("android.permission.USE_CREDENTIALS", 0);
		myMap.put("android.permission.USE_SIP", 0);
		myMap.put("android.permission.VIBRATE", 0);
		myMap.put("android.permission.WAKE_LOCK", 0);
		myMap.put("android.permission.WRITE_APN_SETTINGS", 0);
		myMap.put("android.permission.WRITE_CALENDAR", 0);
		myMap.put("android.permission.WRITE_CALL_LOG", 0);
		myMap.put("android.permission.WRITE_CONTACTS", 0);
		myMap.put("android.permission.WRITE_EXTERNAL_STORAGE", 0);
		myMap.put("android.permission.WRITE_GSERVICES", 0);
		myMap.put("android.permission.WRITE_HISTORY_BOOKMARKS", 0);
		myMap.put("android.permission.WRITE_PROFILE", 0);
		myMap.put("android.permission.WRITE_SECURE_SETTINGS", 0);
		myMap.put("android.permission.WRITE_SETTINGS", 0);
		myMap.put("android.permission.WRITE_SMS", 0);
		myMap.put("android.permission.WRITE_SOCIAL_STREAM", 0);
		myMap.put("android.permission.WRITE_SYNC_SETTINGS", 0);
		myMap.put("android.permission.WRITE_USER_DICTIONARY", 0);
		
    }

    @Override
    public void onStart(Intent intent, int startId) {
    	// For time consuming an long tasks you can launch a new thread here...
        //Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();
    	
        Runnable runnable = new Runnable() {
        	public void run() 
            {
                Looper.prepare();
                Log.d("SERVICE","SEARCHING FOR NEW APP");
                pm= getPackageManager();
                context=getApplicationContext();
                am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                int flag;
                try
                {
                while(true){
                	
                   taskInfo  = am.getRunningTasks(1);
                   n_taskInfo=taskInfo.get(0);
                   new_app=new String(n_taskInfo.topActivity.getPackageName());
                  
                 
                   if(!(new_app.equalsIgnoreCase(last_app)))
                   {
                	   Toast.makeText(getApplicationContext(), "Analysing your new app ! please keep your app running for atleast 1 minute !", Toast.LENGTH_SHORT).show();
                	   PackageInfo packageInfo = pm.getPackageInfo(new_app, PackageManager.GET_PERMISSIONS);
         		       Map<String, Integer> mapinstance = new HashMap<String, Integer>(myMap);
                	   String[] requestedPermissions = packageInfo.requestedPermissions;
         		       String buffer = new String();
         		       buffer += counter ;
         		       counter += 1;
         		       buffer += "," + new_app;
         		       if(requestedPermissions != null) {
         		         for (int i = 0; i < requestedPermissions.length; i++) {
         		        	if(mapinstance.containsKey(requestedPermissions[i]))
         		            {
         		            	mapinstance.put(requestedPermissions[i], 1);
         		            	Log.d("test", requestedPermissions[i]);
         			            
         		            }
         		         }
         		         for (String key : mapinstance.keySet()) {
         		        	    buffer += "," ;
         		        	 	buffer += mapinstance.get(key).toString();
         		        	}
         		       }
                	  //nw_app=getPackageName(new_app);
                	   Log.d("SERVICE","App LAUNCHED:"+new_app);
                	   ApplicationInfo app = pm.getApplicationInfo(new_app, 0);
                	   int uid = app.uid; 
                	   String data = "";
                	   data =new String(netlinkconnect(uid));
                	   //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                	   Log.d("MyApp", data);
                	   buffer += ",";
                	   buffer += data ;
                	   HttpClient client = new DefaultHttpClient();  
                	   HttpPost post = new HttpPost("http://localhost:9000/?body="+buffer);
                	   //HttpResponse response = client.execute(post);
                	   Log.d("MyApp",buffer);
                	   //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                   }
                   last_app=new String(new_app);
                    
                }
                }
                catch(Exception e)
                {
                	Log.d("SERVICE","Error "+e.getMessage());
                }
                
            }
      };
      mythread = new Thread(runnable);
	mythread.start();
       // Toast.makeText(this, " Running activity Started", Toast.LENGTH_LONG).show();

    }
    
    @Override
    public void onDestroy() 
    {
    	mythread.stop();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }
    
}
