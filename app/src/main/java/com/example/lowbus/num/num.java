package com.example.lowbus.num;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import mysql.DBHelper.DatabaseHelper;

import openAPI.StationInfo.getLowStationByNameList;
import openAPI.StationInfo.getLowStationByNameList_Element;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lowbus.R;

public class num extends FragmentActivity  {
	ImageView btn_num,btn_lo,btn_book,btn_esp;
	EditText et;
	Button btn;
	ListView lv;
	ArrayList<String> stationlist;
	ArrayAdapter<String> adapter;
	boolean listempy = true;
	String[] array={"","","","","","",""};
	
	private ProgressDialog mPdProgress;
	
	getLowStationByNameList getlowstationbynamelist;
	getLowStationByNameList_Element lowstationbynamelist;

	SQLiteDatabase DBRead;
	SQLiteDatabase DBWrite;
	DatabaseHelper dbhelper;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.num);
		
		btn_num=(ImageView)findViewById(R.id.num_btn_num);
		btn_lo=(ImageView)findViewById(R.id.num_btn_location);
		btn_book=(ImageView)findViewById(R.id.num_btn_bookmark);
		btn_esp=(ImageView)findViewById(R.id.num_btn_esp);
		
//		btn_num.setOnClickListener(onclick);
		btn_lo.setOnClickListener(onclick);
		btn_book.setOnClickListener(onclick);
		btn_esp.setOnClickListener(onclick);
		
		lv=(ListView)findViewById(R.id.num_listview);
		
		et=(EditText)findViewById(R.id.num_et);
		btn=(Button)findViewById(R.id.num_btn);

		// 상태 표시용 프로그래스바
        mPdProgress = new ProgressDialog(this);
        mPdProgress.setMessage("불러오는 중...");
        mPdProgress.setIndeterminate(true);
        mPdProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mPdProgress.setCancelable(true);
        
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPdProgress.show(); // ProgressDialog 시작

				getlowstationbynamelist = new getLowStationByNameList( apihandler, 0, et.getText().toString() );
				getlowstationbynamelist.start(); // Data parsing 완료 시 전달 받은 핸들러로 sendemptyMessage(mwhat)을 호출한다.
			
			}
		});
		
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				
				if(!listempy) {
					InsertData("STATIONS_HISTORY", DBgetCount("STATIONS_HISTORY")+1, adapter.getItem(pos).toString());
					
					Intent intent=new Intent();
					
					intent.putExtra("value", adapter.getItem(pos).toString());
					intent.setClass(getApplicationContext(), num_2.class);
					startActivityForResult(intent, 1324);
				}
			}
		});

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
			case R.id.num_btn_num:
				setResult(1);
				break;
			case R.id.num_btn_location:
				setResult(2);
				finish();
				break;
			case R.id.num_btn_bookmark:
				setResult(3);
				finish();
				break;
			case R.id.num_btn_esp:
				setResult(1324);
				finish();
				break;
			}
			
		}
	};
	
	void createAdapter(){
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stationlist);
	}
	
	@SuppressLint("HandlerLeak")
	Handler apihandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 0:
				
				lowstationbynamelist = getlowstationbynamelist.getElement();
				stationlist = new ArrayList<String>();
				
				if(lowstationbynamelist.headerCd != 0) {
					stationlist.add(lowstationbynamelist.headerMsg);
					listempy = true;
					
				}
				else if(lowstationbynamelist.itemCount == 0) {
					stationlist.add("검색 결과가 없습니다.");
					listempy = true;
					
				}
				else {
					for(int i = 0; i < lowstationbynamelist.itemCount; i++) {
						
						String list = lowstationbynamelist.stNm.get(i) + " (";
						String arsid = Integer.toString( lowstationbynamelist.arsId.get(i) );
						for(int i1 = 0; i1 < 5-arsid.length(); i1++) {
							list += "0";
							
						}
						list += arsid + ")";
						
						stationlist.add(list);
						
					}
					listempy = false;
					
				}
				
				createAdapter();
				lv.setAdapter(adapter);
				mPdProgress.dismiss();
				break;
				
			default:
				break;
			}
			
			
		}
	};
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		Log.e("", resultCode+"");
		if(resultCode==1 || resultCode==2 || resultCode==3 || resultCode==1324){
			setResult(resultCode);
			finish();
		}
		else if(resultCode==4){
			Intent intent2=new Intent();
			intent.putExtra("value", getIntent().getStringExtra("value"));
			intent2.setClass(getApplicationContext(), num3.class);
			startActivityForResult(intent, 1324);
		}

		
	}
}
