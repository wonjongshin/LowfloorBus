package com.example.lowbus.location;

import mysql.DBHelper.DatabaseHelper;
import openAPI.StationInfo.getStationsByPosList;
import openAPI.StationInfo.getStationsByPosList_Element;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lowbus.R;
import com.example.lowbus.num.num_2;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class location extends FragmentActivity implements LocationListener, OnClickListener {
    private GoogleMap mmap;
    private LocationManager locationManager;
    private String provider;
	ImageView btn_num,btn_lo,btn_book,btn_esp;

    double lat; // 위도
    double lng; // 경도
	
//	ArrayList<> stationlist;
	getStationsByPosList getstationsbyposlist;
	getStationsByPosList_Element stationsbyposlist;

	SQLiteDatabase DBRead;
	SQLiteDatabase DBWrite;
	DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
	    setContentView(R.layout.location);
		
		btn_num=(ImageView)findViewById(R.id.lo_btn_num);
		btn_lo=(ImageView)findViewById(R.id.lo_btn_location);
		btn_book=(ImageView)findViewById(R.id.lo_btn_bookmark);
		btn_esp=(ImageView)findViewById(R.id.lo_btn_esp);
		
		btn_num.setOnClickListener(this);
		btn_book.setOnClickListener(this);
		btn_esp.setOnClickListener(this);
        
      	GooglePlayServicesUtil.isGooglePlayServicesAvailable(location.this);
       	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        
        //위치정보 설정이 안 되어 있으면 설정하는 엑티비티로 이동
        if(provider==null) {
         	new AlertDialog.Builder(location.this)
	        .setTitle("위치 서비스 사용 필요")
	        .setMessage("'GPS 위성' 또는 '무선 네트워크 사용'에 체크해 주십시오.")
	        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
	        	@Override
	        	public void onClick(DialogInterface dialog, int which) {
	        		startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
				}
			}).setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					finish();
				}
			})
	        .show();
        }
        
        // 위치 정보 설정이 되어 있으면 현재위치를 받아옵니다
        else {
    		locationManager.requestLocationUpdates(provider, 1, 1, location.this);
        	setUpMapIfNeeded();
        }

		CreateOrOpenDB("history.db");
    }

	private void CreateOrOpenDB(String dbname) {
		int version = 1;
		
		dbhelper = new DatabaseHelper(getBaseContext(), dbname, null, version);
		DBRead = dbhelper.getReadableDatabase();
		DBWrite = dbhelper.getWritableDatabase();
	}
	
	private void InsertData(String tablename, int seq, String Data) {
		if(DBisExist("STATIONS_HISTORY", Data)) {
			return;
		}
		String sql = "insert into '" + tablename +
				"'(seq, Data) " +
				"values(" +	"'" + seq + "', " +
							"'" + Data + "')";
		DBWrite.execSQL(sql);
		
	}
	
	public boolean DBisExist(String tablename, String Data) {
		int count = 0;
		try {
			Cursor cursor = DBRead.rawQuery("select Data from '" + tablename + "'"
					+ "where Data = '" + Data + "'", null);
			if (cursor != null) {
				count = cursor.getCount();
			}
			
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		if(count != 0)
			return true;
		else
			return false;
		
	}
	
	public int DBgetCount(String tablename) {
		int count = 0;
		try {
			Cursor cursor = DBRead.rawQuery("select * from '" + tablename + "'", null);
			if (cursor != null) {
				count = cursor.getCount();
			}
			
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		return count;
		
	}
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {//위치설정 엑티비티 종료 후 
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		    Criteria criteria = new Criteria();
		    provider = locationManager.getBestProvider(criteria, true);
		        
		    //사용자가 위치기반 서비스에 동의 안 했을 때 - 종료 
		    if(provider == null) {
				finish();
		    }
		    //사용자가 위치설정 동의 했을 때 
		    else {
				locationManager.requestLocationUpdates(provider, 1L, 2F, location.this);
		        setUpMapIfNeeded();
		    }
		    
		break;
		}
	}
    
    @Override
	public void onBackPressed() {
		this.finish();
	}

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
    
    private void setUpMapIfNeeded() {
		if (mmap == null) {
			mmap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1)).getMap();
			if (mmap != null) {
				setUpMap();
			}
		}
	}
    
    private void setUpMap() {
    	mmap.setMyLocationEnabled(true);
    	mmap.getMyLocation();
	}

    boolean locationTag = true;
    
    @Override
    public void onLocationChanged(Location location) {
    	//한번만 위치를 가져오기 위해서 tag를 주었습니다
    	if(locationTag) {
    		Log.d("myLog"  , "onLocationChanged: !!"  + "onLocationChanged!!");
//	        double lat = location.getLatitude(); // 위도
//	        double lng = location.getLongitude(); // 경도
	        lat = location.getLatitude(); // 위도
	        lng = location.getLongitude(); // 경도

			getstationsbyposlist = new getStationsByPosList(
					apihandler,
					0,
					getString(R.string.openapi_key),
					""+lng,
					""+lat,
					"1000"
			);
			getstationsbyposlist.start(); // Data parsing 완료 시 전달 받은 핸들러로 sendemptyMessage(mwhat)을 호출한다.
	        
    	}
    }
    
//	void createAdapter(){
//		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, routelist);
//	}
	
	@SuppressLint("HandlerLeak")
	Handler apihandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 0:
				
				stationsbyposlist = getstationsbyposlist.getElement();
				
		        mmap.clear();
		        mmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));

		        for(int i = 0; i < stationsbyposlist.itemCount; i++) {
		        	String arsId = ""+stationsbyposlist.arsId.get(i);
		        	for(int i1 = 0; i1 < 5-arsId.length(); i1++) {
		        		arsId = "0"+arsId;
		        	}
		        	
			        mmap.addMarker(new MarkerOptions()
			        .icon(BitmapDescriptorFactory.fromResource(R.drawable.redpin))
			        .position(new LatLng(stationsbyposlist.gpsY.get(i), stationsbyposlist.gpsX.get(i)))
			        .title(stationsbyposlist.stationNm.get(i)).snippet(arsId));
			        
		        }
		        
		        locationTag = false;
		        mmap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					
					@Override
					public void onInfoWindowClick(Marker marker) {
						InsertData("STATIONS_HISTORY", DBgetCount("STATIONS_HISTORY"),
								marker.getTitle() + " (" + marker.getSnippet() + ")");
		        		
		        		Intent intent=new Intent(getApplicationContext(),num_2.class);
		        		intent.putExtra("value", marker.getTitle() + " (" + marker.getSnippet() + ")");
//		        		startActivity(intent);
		        		startActivityForResult(intent, 777);
		        		finish();
						
					}
				});
		        
				break;
				
			default:
				break;
			}
			
		}
		
	};

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
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_btn_num:
			setResult(1);
			break;
		case R.id.lo_btn_location:
			setResult(2);
			break;
		case R.id.lo_btn_bookmark:
			setResult(3);
			break;
		case R.id.lo_btn_esp:
			setResult(1324);
			break;
		}
		finish();	
	}
}

