package com.example.beproj1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class QueryServer extends Service
{
	
	String server="123.237.78.119";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	long cat;
	Set<Integer> setstd = new HashSet<Integer>();
	Set<Integer> setnew = new HashSet<Integer>();
	Set<Integer> setres = new HashSet<Integer>();
	Set<String> setper=new HashSet<String>(); //set of all unrecognized permissions
	Set<String> setper2=new HashSet<String>(); //set of defined permissions
	Set<String> setper3=new HashSet<String>(); //set of compared permissions
	BitSet bsstd = new BitSet(160);
	BitSet bsnew = new BitSet(160);
	int flag1,flag2;
	
	

	@Override
	public IBinder onBind(Intent arg0) 
	{
		return null;
	}
	@Override
	public void onCreate() 
	{
		super.onCreate();
	}
	
	@Override
	public void onDestroy() 
	{
		//Toast.makeText(getApplicationContext(), "Service Destroy",1).show();
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		display_all();
		//Log.d("beproj11","cuming here!!");
		//NextService.this.stopSelf();
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	void display_all()
	{	
				settings = getSharedPreferences("mysettings1", Context.MODE_PRIVATE);
				editor = settings.edit();
						/*get application name*/
				String sp1 = settings.getString("appname","");
						/*get length of permissions*/
				cat=settings.getLong("perlength", -1);
						/*get category of the application selected*/
				String sp3 = settings.getString("category", "null");
				//Toast.makeText(getApplicationContext(),"CATEGORY "+sp3,1).show();
				// Set<Integer> set2 = new HashSet<Integer>();
						/*query server to get the category id and category bit pattern*/
				
				//get the set of all defined permissions
				int perlen=settings.getInt("newlength", -1);
				//Toast.makeText(getApplicationContext(),"Mylength "+String.valueOf(perlen),1).show();
				setper2.clear();
				for(int i=0;i<perlen;i++)
				{
				String spn = settings.getString("newper"+String.valueOf(i+1),"");
				//Toast.makeText(getApplicationContext(), "mypermi is "+spn, 1).show();
				setper2.add(spn);
				}
				//
				flag1=0;
				flag2=0;
				DoPOST mDoPOST = new DoPOST(QueryServer.this, sp3);
				mDoPOST.execute("");
						/*query the server to get the id of the permissions*/
				DoPOST2 mDoPOST2 = new DoPOST2(QueryServer.this, "");
				mDoPOST2.execute("");	
				QueryServer.this.stopSelf();
				
	}//end of display all
	
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
	
	void nextstep() throws ClientProtocolException, IOException, JSONException
	{
		/* now the actual comparison begins */
		Log.d("beproj1","Starting the comparison");
		Log.d("beproj1","The std bit string is : "+bsstd.toString());
		Log.d("beproj1","The std num string is : "+setstd.toString());
		Log.d("beproj1","The new bit string is : "+bsnew.toString());
		Log.d("beproj1","The new num string is : "+setnew.toString());
		
		String temp="";
		for(int j=159;j>=0;j--)
		{
			if(bsnew.get(j)==true)
			{
				temp=temp+"1";
			}
			else
			{
				temp=temp+"0";
			}
					
		}
		editor.putString("newbitmap", temp);
	    editor.commit();
		/* bitset operations */
		/* All 1's bit string   */
		BitSet all_one=new BitSet(160);
		all_one.set(0, 159);
		/* Negation of new bit string */
		BitSet neg_new=bsnew;
		neg_new.xor(all_one);
		/* or between neg_new and std_bit string  */
		neg_new.or(bsstd);
		neg_new.xor(all_one);
		Log.d("beproj1","The final comp bit string is "+neg_new.toString());
		Log.d("beproj1","The final comp bit string is "+neg_new);
		/* now generate the result set */
		setres.clear();
		int i;
		for(i=0;i<neg_new.size();i++)
		{
			if(neg_new.get(i)==true)
			{
				setres.add(i);
				
			}
		}
		Log.d("beproj1","The result set is "+setres.toString());
		Log.d("beproj1", "Displaying all unrecognized permissions");
		Log.d("beproj1",setper.toString());
		Log.d("beproj1", "Displaying all defined permissions");
		Log.d("beproj1",setper2.toString());
		Log.d("beproj1","@@@@@@@@@@@@@@@@@@@@@@@");
		
		DoPOST3 mDoPOST3 = new DoPOST3(QueryServer.this, "");
		mDoPOST3.execute("");
		
	}
	
	//a new sub-class
	public class DoPOST extends AsyncTask<String, Void, Boolean>
	{
		Context mContext = null;
		String strNameToSearch = "";
		//Result data
		int cid;
		String bitmap;
		Exception exception = null;
		DoPOST(Context context, String nameToSearch)
		{
			mContext = context;
			strNameToSearch = nameToSearch;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try{
				//Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("name", strNameToSearch));	
				//Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();
				//Setup timeouts
				HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);			
				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://"+server+"/be2.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				Log.d("beproj1","Reply from server is : "+result);
				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);
				//Retrieve the data from the JSON object
				cid= jsonObject.getInt("id");
				bitmap=jsonObject.getString("bitmap");	
				editor.putInt("catid", cid);
				editor.putString("bitmap", bitmap);
				Log.d("beproj1","bitmap is : "+bitmap);
				 bsstd.clear();
				 int i;
				 String mystr="";
				 for(i=bitmap.length()-1;i>=0;i=i-1)
				 {
					 if(bitmap.charAt(i)=='1')
					 {
						setstd.add(bitmap.length()-i);
						mystr=mystr+" "+String.valueOf(bitmap.length()-i);
						bsstd.set(bitmap.length()-i);
					 }
				 }
				 //print the set of numbers
				 Log.d("beproj1","Got the set of num : "+mystr);
				 //so bs1 has the std bit pattern
				 
			}catch (Exception e){
				Log.e("ClientServerDemo", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid)
		{
			//Toast.makeText(mContext, "hey Server got "+String.valueOf(cid), Toast.LENGTH_LONG).show();
			//Toast.makeText(mContext, "2 hey Server got "+bitmap, Toast.LENGTH_LONG).show();
			if(exception != null)
			{
				Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
			}	
			flag1=1;
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
						setnew.add(pid);
						bsnew.set(pid);
						}
						else
						{
							Log.d("beproj1","Permission std unknown "+strNameToSearch);
							setper.add(strNameToSearch);
						}
					}catch (Exception e){
						Log.e("ClientServerDemo", "Error:", e);
						exception = e;
					}
				}

				@Override
				protected Boolean doInBackground(String... arg0) 
				{
					setnew.clear();
					bsnew.clear();
					for (int i = 0; i < cat; i++) 
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
							setper.add(strNameToSearch);
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
					flag2=1;
					try {
						nextstep();
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			
		//new subclass
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
					
					Intent in=new Intent().setClass(QueryServer.this,com.example.beproj1.OptionsHandler.class);
				    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);	
					startActivity(in);
					
				}

			}
			

	
	

}
