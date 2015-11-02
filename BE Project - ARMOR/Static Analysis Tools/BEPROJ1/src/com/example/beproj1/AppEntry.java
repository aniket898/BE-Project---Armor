package com.example.beproj1;

public class AppEntry
{
	
	String appname;
	String bitstream;
	String category;
	int cat;
	
	 	public AppEntry()
	    {
	      appname="";
	      bitstream="";
	      category="";
	      cat=0;
	    }
	    public AppEntry(String appname,String bitstream,String category,int cat)
	    {
	    	 this.appname = appname;
	    	 this.category = category;
	    	 this.cat=cat;
	    	 this.bitstream=bitstream;
	    }
	    //appname
	    public String getappname()
	    {
	        return this.appname;
	    }
	    public void setappname(String appname)
	    {
	        this.appname = appname;
	    }
	    //category
	    public String getcategory()
	    {
	        return this.category;
	    }
	    public void setcategory(String category)
	    {
	        this.category = category;
	    }
	    //bitstream
	    public String getbitstream()
	    {
	        return this.bitstream;
	    }
	    public void setbitstream(String bitstream)
	    {
	        this.bitstream = bitstream;
	    }
	    //cat
	    public int getcat()
	    {
	        return this.cat;
	    }
	    public void setcat(int cat)
	    {
	        this.cat=cat;
	    }
}