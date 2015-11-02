package com.example.beproj1;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.beproj1.QueryServer.DoPOST3;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Service_intent extends Service
{
	String name;
	DatabaseHandler db;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	String server;
	BitSet bsold = new BitSet(160);
	BitSet bsnew = new BitSet(160);
	BitSet bscur=new BitSet(160);
	Set<Integer> setres = new HashSet<Integer>();
	public Service_intent() 
	{
	}

	@Override
	public IBinder onBind(Intent intent) 
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	@Override
	public void onCreate() 
	{
		Log.d("beproj1","Service_intent : Created");
		super.onCreate();
		db = new DatabaseHandler(this);
	}
	
	@Override
	public void onDestroy() 
	{
		Log.d("beproj1","Service_intent : Destroyed !!");
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		server="123.237.78.119";
		settings =getSharedPreferences("mysettings1", Context.MODE_PRIVATE);
		editor = settings.edit();
		Log.d("beproj1","Service_intent : OnStartCommand Startes");
		name=intent.getStringExtra("name");
		String type1=intent.getStringExtra("type");
		Log.d("beproj1","Type got as "+type1);
		boolean x=false;
		x= intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);
		Log.d("beproj1","@@@@@@@ "+String.valueOf(x));
		//Log.d("beproj1","jiiii "+String.valueOf(intent.EXTRA_REPLACING));
		String sub=null;
		sub=name.substring(8);
		Toast.makeText(getApplicationContext(), sub,1).show();
		if(type1.equalsIgnoreCase("remove"))
		{
			db.deleteapp(sub);
			Log.d("beproj1","Service_intent : Completed deletion of app...");
			Service_intent.this.stopSelf();
			return super.onStartCommand(intent, flags, startId);
		}
		try
		{
			extract_permission(sub,type1);
		} 
		catch (NameNotFoundException e) 
		{
			e.printStackTrace();
		}
		//check if update tag
		Log.d("beproj1","Type got as 2 "+type1);
		if(type1.equals("update"))
		{
			//check if record in the database
			Log.d("beproj1","*** Yes Update detected");
			AppEntry ae1 =db.searchapp(sub);
			if(ae1.equals(null))
			{
				Log.d("beproj1","*** !!! well this app is not in our database");
				//as no record in the database ... follow normal course of action
				Log.d("beproj1","*** as not in the db.... follow normal course of action .... calling category class");
				Intent in=new Intent().setClass(Service_intent.this,com.example.beproj1.Category.class);
				in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	
				startActivity(in);
			}
			else //record exists
			{
				 Log.d("beproj1","*** Record found in our database .... as .... "+ae1.appname+" " + ae1.bitstream+" "+ae1.category+" "+ae1.cat);
				 /*query the server to get the id of the permissions*/
				 bsold.clear();
				 for(int k=0;k<160;k++)
				 {
					 if(ae1.bitstream.charAt(k)=='1')
					 {
						 bsold.set(160-(k+1));
					 }
			
				 }
				 Log.d("beproj1","*** bsold has been formed ... it is "+bsold.toString());
				 Log.d("beproj1","*** So now query the server to get the new permission set... ");
				 DoPOST2 mDoPOST2 = new DoPOST2(Service_intent.this, "");
				 mDoPOST2.execute("");	
				 Log.d("beproj1","*** out of the aynch task....");
			}
			Log.d("beproj1","yupeeee");
		}
		else //type1 = install
		{
		Log.d("beproj1","hi ... here");
		Intent in=new Intent().setClass(Service_intent.this,com.example.beproj1.Category.class);
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	
		startActivity(in);
		}
		Service_intent.this.stopSelf();
		return super.onStartCommand(intent, flags, startId);
		
	}
	private void extract_permission(String nam,String typ) throws NameNotFoundException 
	{
		Log.d("beproj1","In Extract Permission");
		PackageManager pm = getPackageManager();
		//List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		//Log.d("testing",nam);
		
		editor.putString("appname",nam);
		editor.commit();
		editor.putString("type1",typ);
		editor.commit();
		try 
		{
		      PackageInfo packageInfo = pm.getPackageInfo(nam, PackageManager.GET_PERMISSIONS);
		      String[] requestedPermissions = packageInfo.requestedPermissions;
		      PermissionInfo[] definedPermissions = packageInfo.permissions;
		      
		     // Toast.makeText(getApplicationContext(), "hi "+definedPermissions,1).show();
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
		      //now also put 
		   //  int len= definedPermissions.length;
		     //now add to the shared preference
		    // editor.putInt("newlength",definedPermissions.length);
		    //  editor.commit();
		     // 
		     //Toast.makeText(getApplicationContext(),"Length = "+String.valueOf(len),1).show();
		    /* for(int i=0;i<len;i++)
		     {
		    	 String str1=definedPermissions[i].name;
		    	// Toast.makeText(getApplicationContext(), "permi is "+str1, 1).show();
		    	Log.d("beproj1","the per is : "+str1);
		    	 editor.putString("newper"+String.valueOf(i+1),str1);
		    	 editor.commit();
		     }
		    
		     */
		   } 
		catch (NameNotFoundException e) 
		{
		      e.printStackTrace();
		}
	}
	
	//a new sub-class
	public class DoPOST2 extends AsyncTask<String, Void, Boolean>
	{

		Context mContext = null;
		String strNameToSearch = "";
		//Result data
		int pid;
		Exception exception = null;
		DoPOST2(Context context, String nameToSearch)
		{
			mContext = context;
			strNameToSearch = nameToSearch;
		}
	
		protected void newfunc()
		{
				try{
				//Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				String new1="";
				new1=strNameToSearch.substring(19);
				Log.d("beproj1","Sending, : "+new1);
				nameValuePairs.add(new BasicNameValuePair("name", new1));
				//Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();
				//Setup timeouts
				HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);			
				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://"+server+"/be1.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				Log.d("beproj1","the server result is "+result);
				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);
				if(!jsonObject.isNull("id"))
				{
				//Retrieve the data from the JSON object
				pid= jsonObject.getInt("id");
				String bitmap = jsonObject.getString("name");
				Log.d("beproj1","pid is :"+String.valueOf(pid)+" "+bitmap);
				
				bsnew.set(pid);
				}
				else
				{
					Log.d("beproj1","Permission std unknown "+strNameToSearch);
					
				}
			}catch (Exception e){
				Log.e("ClientServerDemo", "Error:", e);
				exception = e;
			}
		}

		@Override
		protected Boolean doInBackground(String... arg0) 
		{
			bsnew.clear();
			for (int i = 0; i < settings.getLong("perlength", -1); i++) 
	        {
				String spn = settings.getString("per"+String.valueOf(i+1),"");
				strNameToSearch=spn;
				if(strNameToSearch.startsWith("android.permission."))
				{
					newfunc();
				}
				else
				{
					Log.d("beproj1","Unknown Permission "+strNameToSearch);
					
				}
					
	        }
			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid)
		{
			//Toast.makeText(mContext, "phey Server got "+String.valueOf(pid), Toast.LENGTH_LONG).show();
			if(exception != null)
			{
				Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
			}
			compare();
			//now display the extra permissions
			DoPOST3 mDoPOST3 = new DoPOST3(Service_intent.this, "");
			mDoPOST3.execute("");
			
	}
		void compare()
		{
			bscur=bsnew;
		BitSet all_one=new BitSet(160);
		all_one.set(0, 159);
		/* Negation of new bit string */
		BitSet neg_new=bsnew;
		neg_new.xor(all_one);
		/* or between neg_new and std_bit string  */
		neg_new.or(bsold);
		neg_new.xor(all_one);
		Log.d("beproj1","The final comp bit string is "+neg_new.toString());
		Log.d("beproj1","The final comp bit string is "+neg_new);
		/* now generate the result set */
		setres.clear();
		int i;
		int len=0;
		for(i=0;i<neg_new.size();i++)
		{
			if(neg_new.get(i)==true)
			{
				setres.add(i);
				len++;
			}
		}
		Log.d("beproj1","******The result set is of length "+setres.toString()+" "+String.valueOf(len));
		}
	}
	
	//a new sub-class
	public class DoPOST3 extends AsyncTask<String, Void, Boolean>
	{
		Context mContext = null;
		String strNameToSearch = "";
		//Result data
		int cid;
		String bitmap;
		Exception exception = null;
		DoPOST3(Context context, String nameToSearch)
		{
			mContext = context;
			strNameToSearch = nameToSearch;
		}

		@Override
		protected Boolean doInBackground(String... arg0)
		{
			int i;
			i=0;
			editor.putInt("comperleng",setres.size());
			editor.commit();
			for (Iterator<Integer> it = setres.iterator(); it.hasNext(); ) 
			{
		        int f = it.next();
		        func2(f,i);
				i++;
		    }
			
			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid)
		{
			
			if(exception != null)
			{
				Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
			}	
			editor.remove("newbitmap");
		    editor.commit();
		    int i;
		    String temp="";
		    bscur.or(bsold);
		    Log.d("beproj1","The new bscur is "+bscur.toString());
		    for(i=0;i<160;i++)
		    {
		    	if(bscur.get(160-(i+1))==true)
		    	{
		    		temp=temp+"1";
		    	}
		    	else
		    	{
		    		temp=temp+"0";
		    	}
		    		
		    }
		    //for rechecking....
		    
		
		    String mystr="";
		    for(i=temp.length()-1;i>=0;i=i-1)
			 {
				 if(temp.charAt(i)=='1')
				 {
				
					mystr=mystr+" "+String.valueOf(temp.length()-(i+1));
					
				 }
			 }
		    Log.d("beproj1","For rechecking ..... "+mystr);
		    //for rechecking
		    editor.putString("newbitmap",temp);
		    editor.commit();
			
			Intent in=new Intent().setClass(Service_intent.this,com.example.beproj1.OptionsHandler.class);
		    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	
			startActivity(in);
			
		}

	}
	void func2(int id,int loc) 
	{
		try{
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("id", String.valueOf(id)));	
		Log.d("beproj1","########## "+String.valueOf(id));
		//Create the HTTP request
		HttpParams httpParameters = new BasicHttpParams();
		//Setup timeouts
		HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
		HttpConnectionParams.setSoTimeout(httpParameters, 15000);			
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpPost httppost = new HttpPost("http://"+server+"/be3.php");
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity);
		Log.d("beproj1","**********Reply from server is : "+result);
		JSONObject jsonObject = new JSONObject(result);
		if(!jsonObject.isNull("name"))
		{
		//Retrieve the data from the JSON object
		String s1 = jsonObject.getString("name");
		String s2= jsonObject.getString("description");
		editor.putString("comper"+String.valueOf(loc),s1);
		editor.commit();
		editor.putString("comperdes"+String.valueOf(loc),s2);
		editor.commit();
		Log.d("beproj1","wopppp : "+s1+" "+s2);
		}
		else
		{
			Log.d("beproj1","Wrong Permission id!!");
		}
		
	}catch (Exception e){
		Log.e("ClientServerDemo", "Error:", e);
		
	}
	}
	

}
	

	

