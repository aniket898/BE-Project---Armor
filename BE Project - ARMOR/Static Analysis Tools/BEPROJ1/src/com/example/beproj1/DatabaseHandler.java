package com.example.beproj1;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

 
public class DatabaseHandler extends SQLiteOpenHelper 
{
	
	
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "BEPROJ_DB1";
    private static final String TABLE_NAME_1 = "Table_App";
    
    private static final String KEY_appname = "appname";
    private static final String KEY_bitstream = "bitstream";
    private static final String KEY_category="category";
    private static final String KEY_cat="cat";
    String s1="CREATE TABLE " + TABLE_NAME_1 + " ( " + KEY_appname + " VARCHAR(50) NOT NULL PRIMARY KEY , " + KEY_bitstream + " BLOB NOT NULL "+", ";
    String s2=KEY_category + " VARCHAR(45) NOT NULL , "+  KEY_cat +" NUMBER )";
    String CREATE_TABLE1 = s1+s2;
  
    
    public DatabaseHandler(Context context) 
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
    	Log.d("beproj1","******************* In Database Handler : onCreate ******************");
    	Log.d("beproj1","Database Handler : query exec is "+CREATE_TABLE1);
        db.execSQL(CREATE_TABLE1);
        Log.d("beproj1","Database Handler : Exiting from onCreate()");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {
    	Log.d("beproj1","Database Handler : In the onUpgrade()");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        onCreate(db);
        Log.d("beproj1","Database Handler : Exiting the onUpgrade()");    
    }
  
    void addEntry(AppEntry ae1) 
    {
    	Log.d("beproj1","Database Handler : in addEntry");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_appname, ae1.getappname()); 
        values.put(KEY_bitstream, ae1.getbitstream()); 
        values.put(KEY_category, ae1.getcategory()); 
        values.put(KEY_cat, ae1.getcat()); 
        Log.d("beproj1","Database Handler : Inserting into the database ");
        Log.d("beproj1",KEY_appname+" "+KEY_bitstream+" "+KEY_category+" "+String.valueOf(KEY_cat));
        db.insert(TABLE_NAME_1, null, values);
        db.close(); 
        Log.d("beproj1","Database Handler : Exiting AppEntry");
    }
    public List<AppEntry> getAllApps() 
    {
    	Log.d("beproj1","getAllApps : Entering");
        List<AppEntry> aeList = new ArrayList<AppEntry>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("beproj1","getAllApps : Printing all the values of getAllApps");
        if (cursor.moveToFirst()) 
        {
            do {
                AppEntry ae1 = new AppEntry();
                ae1.setappname(cursor.getString(0));
                ae1.setbitstream(cursor.getString(1));
                ae1.setcategory(cursor.getString(2));
                ae1.setcat(Integer.parseInt(cursor.getString(3)));
                aeList.add(ae1);
                Log.d("beproj1","getAllApps"+ae1.appname+" " + ae1.bitstream+" "+ae1.category+" "+ae1.cat);
            } while (cursor.moveToNext());
        }
        db.close();
        Log.d("beproj1","getAllApps : Exiting");
        return aeList;
        
    }
    
    // Deleting single contact
    public void deleteapp(String name) 
    {
    	Log.d("beproj1","deleteapp : Entering ...");
    	getAllApps();
        SQLiteDatabase db = this.getWritableDatabase();
     
        String query="Delete from "+TABLE_NAME_1+" WHERE APPNAME = '"+name+"'";
        Log.d("beproj1","deleteApp : query is "+query);
        db.execSQL(query);
        db.close();
        getAllApps();
        Log.d("beproj1","deleteApp : Exiting");
    }

	public AppEntry searchapp(String name) 
	{
		String selectQuery = "SELECT  * FROM " + TABLE_NAME_1 +" where appname = '"+name+"'";
		Log.d("beproj1","in searchapp...");
		Log.d("beproj1","query is "+selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) 
        {
       
                AppEntry ae1 = new AppEntry();
                ae1.setappname(cursor.getString(0));
                ae1.setbitstream(cursor.getString(1));
                ae1.setcategory(cursor.getString(2));
                ae1.setcat(Integer.parseInt(cursor.getString(3)));
                Log.d("beproj1","Search App :: "+ae1.appname+" " + ae1.bitstream+" "+ae1.category+" "+ae1.cat);
                db.close();
                return ae1;
          
        }
        else
        {
        	Log.d("beproj1","Cannot find record  "+name);
        }
        db.close();
        return null;
        
	}
  
    
    /***************************************************************************************/
 
  /*  public int updateContact(DBpermission permission) 
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }
 
    // Deleting single contact
    public void deleteContact(DBpermission contact) 
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
 
 
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }*/
    /* DBpermission getPermission(int id) 
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_PER_PER, new String[] {KEY_ID,KEY_NAME}, KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DBpermission contact = new DBpermission(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        return contact;
    }*/
 
}


