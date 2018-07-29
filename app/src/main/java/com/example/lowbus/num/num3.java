package com.example.lowbus.num;

import java.util.ArrayList;

import mysql.DBHelper.DatabaseHelper;

import openAPI.BusPos.getLowBusPosByRtidList;
import openAPI.BusPos.getLowBusPosByRtidList_Element;
import openAPI.busRouteInfo.getStationsByRouteList;
import openAPI.busRouteInfo.getStationsByRouteList_Element;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lowbus.R;
import com.example.lowbus.util.Custom_adapter2;
import com.example.lowbus.util.mData;
import com.example.lowbus.util.user_Info;

public class num3 extends FragmentActivity {
	Custom_adapter2 adapter;
    EditText et;
	ImageView btn_num,btn_lo,btn_book,btn_esp;
	ListView lv;
	
	String extravalue;
	String busrouteid;
	
	mData mdata;
	
	ArrayList<user_Info> infolist;
	
	boolean listempy = true;
	private ProgressDialog mPdProgress;
	getStationsByRouteList getstationsbyroutelist;
	getStationsByRouteList_Element stationsbyrutelist;
	getLowBusPosByRtidList getlowbuspobyrtidlist;
	getLowBusPosByRtidList_Element lowbuspobyrtidlist;

	SQLiteDatabase DBRead;
	SQLiteDatabase DBWrite;
	DatabaseHelper dbhelper;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.num3);
	    // TODO Auto-generated method stub
	    
	    et=(EditText)findViewById(R.id.num3_et);
	    extravalue = getIntent().getStringExtra("value");
	    busrouteid = extravalue.substring(extravalue.indexOf('(')+1, extravalue.indexOf(')'));
	    et.setText(extravalue.substring(0, extravalue.indexOf('(')));
	    
		btn_num=(ImageView)findViewById(R.id.num3_btn_num);
		btn_lo=(ImageView)findViewById(R.id.num3_btn_location);
		btn_book=(ImageView)findViewById(R.id.num3_btn_bookmark);
		btn_esp=(ImageView)findViewById(R.id.num3_btn_esp);
		
		// 상태 표시용 프로그래스바
        mPdProgress = new ProgressDialog(this);
        mPdProgress.setMessage("불러오는 중...");
        mPdProgress.setIndeterminate(true);
        mPdProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mPdProgress.setCancelable(true);
		
		btn_num.setOnClickListener(onclick);
		btn_lo.setOnClickListener(onclick);
		btn_book.setOnClickListener(onclick);
		btn_esp.setOnClickListener(onclick);
	    
	    lv=(ListView)findViewById(R.id.num3_listView);
	    
	    adapter=new Custom_adapter2(this, R.layout.custom_list_item, new mData().getArray());
	    lv.setAdapter(adapter);
	    
	    lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Intent intent=getIntent();
				
				String arsId = ""+stationsbyrutelist.stationNo.get(arg2);
				for(int i = 0; i < 5-arsId.length(); i++) {
					arsId = "0"+arsId;
				}

				InsertData("STATIONS_HISTORY", DBgetCount("STATIONS_HISTORY")+1,
						stationsbyrutelist.stationNm.get(arg2) + " (" + arsId + ")");
						
				intent.putExtra("return", stationsbyrutelist.stationNm.get(arg2)
						+ " (" + arsId + ")");
				Log.e("", getIntent().getStringExtra("return"));
				setResult(999,intent);
				finish();
			}
		});

	    initilizeList();
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
	
	OnClickListener onclick=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.num3_btn_num:
				setResult(1);
				break;
			case R.id.num3_btn_location:
				setResult(2);
				break;
			case R.id.num3_btn_bookmark:
				setResult(3);
				break;
			case R.id.num3_btn_esp:
				setResult(1324);
				break;
			}
			finish();	
			
		}
	};
	
	void initilizeList() {
		
		mPdProgress.show(); // ProgressDialog 시작
		
		getstationsbyroutelist = new getStationsByRouteList(
				apihandler,
				0,
				getString(R.string.openapi_key),
				busrouteid
		);
		getstationsbyroutelist.start(); // Data parsing 완료 시 전달 받은 핸들러로 sendemptyMessage(mwhat)을 호출한다.
	}
	
	void createAdapter(){
	    adapter=new Custom_adapter2(this, R.layout.custom_list_item, mdata.getArray());
	}
	
	@SuppressLint("HandlerLeak")
	Handler apihandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 0:
				
				stationsbyrutelist = getstationsbyroutelist.getElement();
				
				if(stationsbyrutelist.headerCd != 0) {
					mdata = new mData();
					mdata.addData(stationsbyrutelist.headerMsg);
					listempy = true;

					createAdapter();
					lv.setAdapter(adapter);
					mPdProgress.dismiss();
					
				}
				else if(stationsbyrutelist.itemCount == 0) {
					mdata.addData("검색 결과가 없습니다.");
					listempy = true;

					createAdapter();
					lv.setAdapter(adapter);
					mPdProgress.dismiss();
					
				}
				else {
					infolist = new ArrayList<user_Info>();
					for(int i = 0; i < stationsbyrutelist.itemCount; i++) {
						
						infolist.add(new user_Info(stationsbyrutelist.stationNm.get(i), "-", false));
						
					}
					listempy = false;
					
				}
				
				getlowbuspobyrtidlist = new getLowBusPosByRtidList(
				        apihandler,
                        1,
                        getString(R.string.openapi_key),
                        busrouteid
                );
				getlowbuspobyrtidlist.start();
				
				break;
				
			case 1:
				
				lowbuspobyrtidlist = getlowbuspobyrtidlist.getElement();
				mdata = new mData();
				
				if(lowbuspobyrtidlist.headerCd != 0) {
					
				}
				else if(lowbuspobyrtidlist.itemCount == 0) {
					
				}
				else {
					int i, j;
					for(i = 0; i < stationsbyrutelist.itemCount; i++) {
						for(j = 0; j < lowbuspobyrtidlist.itemCount; j++) {
							Log.e("",i+" "+j+" "+ stationsbyrutelist.section.get(i)+ " "+ lowbuspobyrtidlist.sectionId.get(j));
							if(stationsbyrutelist.section.get(i).intValue() == lowbuspobyrtidlist.sectionId.get(j).intValue()) {
								Log.e("", "찍힘");
								user_Info tmpInfo = infolist.get(i);
								tmpInfo.setTime( lowbuspobyrtidlist.nextStTm.get(j)/60 + "분전" );
								tmpInfo.setCheck(true);
								
								infolist.set(i, tmpInfo);
								
							}
							
						}
						
					}
					
				}
				mdata.setArray(infolist);
				
				createAdapter();
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPdProgress.dismiss();
				break;
				
			default:
				break;
			}
			
		}
		
	};
	
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		super.onKeyDown(KeyCode, event);
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (KeyCode) {
			case KeyEvent.KEYCODE_BACK:
				setResult(1024);
				finish();
				break;
			}
			return false;
		}
		return false;
	}



}
