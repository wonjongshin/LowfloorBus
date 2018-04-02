package com.example.lowbus.num;

import java.util.ArrayList;

import mysql.DBHelper.DatabaseHelper;

import openAPI.StationInfo.getRouteByStationList;
import openAPI.StationInfo.getRouteByStationList_Element;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lowbus.R;

public class num_2 extends FragmentActivity {
	ListView lv;
	String[] array={"","","","","","",""};
	ArrayList<String> routelist;
	ArrayAdapter<String> adapter;
	ImageView btn_num,btn_lo,btn_book,btn_esp;
	EditText et;
	
	String extravalue;
	String arsId;
	
	boolean listempy = true;
	private ProgressDialog mPdProgress;
	getRouteByStationList getroutebystationlist;
	getRouteByStationList_Element routebystationlist;

	SQLiteDatabase DBRead;
	SQLiteDatabase DBWrite;
	DatabaseHelper dbhelper;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.num2);
	    
	    et=(EditText)findViewById(R.id.num2_et);
	    extravalue = getIntent().getStringExtra("value");
	    et.setText(extravalue);
	    
		btn_num=(ImageView)findViewById(R.id.num2_btn_num);
		btn_lo=(ImageView)findViewById(R.id.num2_btn_location);
		btn_book=(ImageView)findViewById(R.id.num2_btn_bookmark);
		btn_esp=(ImageView)findViewById(R.id.num2_btn_esp);
		
		routelist = new ArrayList<String>();
		
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
	    
	    lv=(ListView)findViewById(R.id.num2_listView);
	    adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, routelist);
	    lv.setAdapter(adapter);
	    
	    lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(!listempy) {
					InsertData("BUS_HISTORY", DBgetCount("BUS_HISTORY")+1,
							adapter.getItem(arg2).toString());
					
					Intent intent=new Intent();
					intent.putExtra("value", adapter.getItem(arg2).toString()
							+ "(" + routebystationlist.busRouteId.get(arg2) + ")");
					intent.setClass(getApplicationContext(), num3.class);
					startActivityForResult(intent, 1324);
				}
				
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
			case R.id.num2_btn_num:
				setResult(1);
				break;
			case R.id.num2_btn_location:
				setResult(2);
				break;
			case R.id.num2_btn_bookmark:
				setResult(3);
				break;
			case R.id.num2_btn_esp:
				setResult(1324);
				break;
			}
			finish();	
			
		}
	};

	void initilizeList() {
		
		mPdProgress.show(); // ProgressDialog 시작
		
		arsId = extravalue.substring(extravalue.indexOf('(')+1, extravalue.length()-1);

		getroutebystationlist = new getRouteByStationList( apihandler, 0, arsId );
		getroutebystationlist.start(); // Data parsing 완료 시 전달 받은 핸들러로 sendemptyMessage(mwhat)을 호출한다.
	}
	
	void createAdapter(){
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, routelist);
	}
	
	@SuppressLint("HandlerLeak")
	Handler apihandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 0:
				
				routebystationlist = getroutebystationlist.getElement();
				routelist = new ArrayList<String>();
				
				if(routebystationlist.headerCd != 0) {
					routelist.add(routebystationlist.headerMsg);
					listempy = true;
					
				}
				else if(routebystationlist.itemCount == 0) {
					routelist.add("검색 결과가 없습니다.");
					listempy = true;
					
				}
				else {
					for(int i = 0; i < routebystationlist.itemCount; i++) {
						
						String list = routebystationlist.busRouteNm.get(i);
						if(!list.contains("test")) {
							routelist.add(list);
						}
						
					}
					listempy = false;
					
				}
				createAdapter();
				Log.e("TT", ""+routelist.size());
				Log.e("TT", ""+adapter.getCount());
				lv.setAdapter(adapter);

				adapter.notifyDataSetChanged();
				mPdProgress.dismiss();
				break;
				
			default:
				break;
			}
			
		}
		
	};
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode==1 || resultCode==2 || resultCode==3 || resultCode==1324){
			setResult(resultCode);
			finish();
		}
		else if(resultCode==999){
//			Log.e("1", intent.getStringExtra("return"));
		    this.extravalue = intent.getStringExtra("return");
		    this.et.setText(extravalue);
		    initilizeList();
		}
	}
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		super.onKeyDown(KeyCode, event);
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (KeyCode) {
			case KeyEvent.KEYCODE_BACK:
				setResult(999);
				finish();
				break;
			}
			return false;
		}
		return false;
	}	

}
