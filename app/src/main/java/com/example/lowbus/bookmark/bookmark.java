package com.example.lowbus.bookmark;

import java.util.ArrayList;
import java.util.Calendar;

import mysql.DBHelper.DatabaseHelper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lowbus.R;
import com.example.lowbus.num.num3;
import com.example.lowbus.num.num_2;

public class bookmark extends FragmentActivity implements OnClickListener {
	ImageView btn_num,btn_lo,btn_book,btn_esp;
	ListView list;
	Button btn1,btn2;
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter2;
	Intent intent;
	
	String[] data2={"mklmlkm","asldjf","qwpojf"};
	
	boolean check=false;

	SQLiteDatabase DBRead;
	SQLiteDatabase DBWrite;
	DatabaseHelper dbhelper;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.bookmark);
	
		btn_num=(ImageView)findViewById(R.id.book_btn_num);
		btn_lo=(ImageView)findViewById(R.id.book_btn_location);
		btn_book=(ImageView)findViewById(R.id.book_btn_bookmark);
		btn_esp=(ImageView)findViewById(R.id.book_btn_esp);
		
		
		btn_num.setOnClickListener(this);
		btn_lo.setOnClickListener(this);
		btn_esp.setOnClickListener(this);
		
		list=(ListView)findViewById(R.id.bookmark_listview);
	
		
		btn1=(Button)findViewById(R.id.btn_findbus);
		btn2=(Button)findViewById(R.id.btn_findstop);

		btn1.setBackgroundColor(Color.GRAY);
		btn2.setBackgroundColor(Color.LTGRAY);
		
//		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
//		adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data2);
		
//		list.setAdapter(adapter);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				intent=new Intent();
				intent.putExtra("value", adapter.getItem(arg2).toString());
				if(check){
					intent.setClass(getApplicationContext(), num_2.class);
				}
				else{
					intent.setClass(getApplicationContext(), num3.class);
				}		
				startActivity(intent);
			}
		});
		
		CreateOrOpenDB("history.db");
		InitList("BUS_HISTORY");
	}

	private void CreateOrOpenDB(String dbname) {
		int version = 1;
		
		dbhelper = new DatabaseHelper(getBaseContext(), dbname, null, version);
		DBRead = dbhelper.getReadableDatabase();
		DBWrite = dbhelper.getWritableDatabase();
	}

	private void InitList(String tablename) {
		ArrayList<String> arr_list = new ArrayList<String>();

		try {
			
			Cursor cursor = DBRead.rawQuery("select Data from '" + tablename + "'", null);
			if (cursor != null) {
				int count = cursor.getCount();
				
				for (int i = 0; i < count; i++) {
					cursor.moveToNext();
					arr_list.add(cursor.getString(0));
				}
			}
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_list);
		
		list.setAdapter(adapter);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.book_btn_num:
			setResult(1);
			finish();
			break;
		case R.id.book_btn_location:
			setResult(2);
			finish();
			break;
		case R.id.book_btn_bookmark:
			setResult(3);
			finish();
			break;
		case R.id.book_btn_esp:
			setResult(1324);
			finish();
			break;
		}
		if(v.getId()==R.id.btn_findbus){
			check=false;
//			list.setAdapter(adapter);
			InitList("BUS_HISTORY");
			adapter.notifyDataSetChanged();
			btn1.setBackgroundColor(Color.GRAY);
			btn2.setBackgroundColor(Color.LTGRAY);
			
		}
		else if(v.getId()==R.id.btn_findstop){
			check=true;
//			list.setAdapter(adapter2);
			InitList("STATIONS_HISTORY");
			adapter.notifyDataSetChanged();
			btn1.setBackgroundColor(Color.LTGRAY);
			btn2.setBackgroundColor(Color.GRAY);
			
			
		}
		
	}
	
}
