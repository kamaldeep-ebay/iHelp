package com.android.bits;




import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.sql.*;



public class GdgHackActivity extends Activity {
    /** Called when the activity is first created. */
	
	LocationManager locMgr = null;
	LocationListener locListener = null;
	//static GpsStatus status;
	String str5= "edsfsdf";
	public static Double lat,lon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b1=(Button)findViewById(R.id.help);
        b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				/*Intent intent1 = new Intent(LocationUpdateDemoActivity.this,HttpPostDemo.class);
				intent1.putExtra(name, str);
				intent1.putExtra(loc, loc);
				
				startActivity(intent1);
				
				*/
				try
				{
				Intent intent2 = new Intent(Intent.ACTION_CALL,Uri.parse("tel:100"));
				startActivity(intent2);
				}
				catch(ActivityNotFoundException activityException)
				{
					Log.v("err", "call failed ",activityException);
				}
				
				/*
				SmsManager smsmgr = SmsManager.getDefault();
				smsmgr.sendTextMessage(destinationAddress, scAddress, text, null, null);
				*/
				
				
				
				
			}				
		});

        
        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new geoUpdate());
        Location lc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        /*if (lc==null)
        {
        	lc = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }*/
        
        
        //str5 = lc.getLatitude()+"aca";
        /*Double d_lon = lc.getLongitude();
        String str6 = d_lon.toString();
        Double d_lat = lc.getLatitude();
        String str7 = d_lat.toString();*/
        //Toast.makeText(getBaseContext(), str6, Toast.LENGTH_LONG).show();
        
        

        	
    }
    
    @Override
    public void onPause() {
    	//super.onPause();
    	//locMgr.removeUpdates(locListener);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
/*
        locMgr.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,			// minTime in ms
            0,			// minDistance in meters
            locListener);*/
    }

    public class geoUpdate implements LocationListener
    {

    	
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
			Double d_lat = location.getLongitude();
	        String str6 = d_lat.toString();
	        Double d_lon = location.getLongitude();
	        String str7 = d_lon.toString();
	        
	        
	        
	        String dbtime;
	        String dbUrl = "jdbc:mysql://10.0.2.2:3306/hi";
	        String dbClass = "com.mysql.jdbc.Driver";
	        String query = "Select * FROM gdg";
	        
	       
	        
	        try {
	        	 
	        	Class.forName(dbClass);
	        	Connection con = DriverManager.getConnection (dbUrl,"root","");
	        	Log.v("234", "conn");
	        	//Toast.makeText(getBaseContext(), "hufh", Toast.LENGTH_LONG).show();
	        	Statement stmt = con.createStatement();
	        	ResultSet rs = stmt.executeQuery(query);
	        	
	        	Statement s = con.createStatement();
	        	s.executeUpdate("INSERT INTO gdg " + "VALUES (744, 346, 33)");
	        	Toast.makeText(getBaseContext(), str6, Toast.LENGTH_LONG).show();
	        	
	        	while (rs.next()) {
	        	dbtime = rs.getString(3);
	        	System.out.println(dbtime);
	        	} //end while

	        	con.close();
	        	} //end try

	        	catch(ClassNotFoundException e) {
	        	e.printStackTrace();
	        	//Toast.makeText(getBaseContext(), "sdfsd", Toast.LENGTH_LONG).show();
	        	//Log.v("sdf", e.getMessage());
	        	}

	        	catch(SQLException e) {
	        	e.printStackTrace();
	        	//Toast.makeText(getBaseContext(), "s456dfsd", Toast.LENGTH_LONG).show();
	        	//Log.v("sdf", e.getMessage());
	        	}
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
		
			
			
		}
    	
    }


}