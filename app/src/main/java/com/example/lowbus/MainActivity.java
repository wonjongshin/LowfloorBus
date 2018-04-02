package com.example.lowbus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lowbus.bookmark.bookmark;
import com.example.lowbus.location.location;
import com.example.lowbus.num.num;
import com.example.lowbus.util.SplashActivity;

public class MainActivity extends FragmentActivity implements OnClickListener {
	ImageView btn_num,btn_lo,btn_book,btn_esp;
	boolean key_check=false;
	Handler key_handler;
	Toast close_toast;
	LinearLayout ll;
	boolean check=false;

	static final int request_code = 1324;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, SplashActivity.class));
		setContentView(R.layout.activity_main);
		
		ll=(LinearLayout)findViewById(R.id.intro);
		
		btn_num=(ImageView)findViewById(R.id.menu_btn_num);
		btn_lo=(ImageView)findViewById(R.id.menu_btn_location);
		btn_book=(ImageView)findViewById(R.id.menu_btn_bookmark);
		btn_esp=(ImageView)findViewById(R.id.menu_btn_esp);
		
		btn_num.setOnClickListener(this);
		btn_lo.setOnClickListener(this);
		btn_book.setOnClickListener(this);
		btn_esp.setOnClickListener(this);
		
		setDoubleBack();
		

	}

	
	
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.menu_btn_num:
			intent.setClass(getApplicationContext(), num.class);
			startActivityForResult(intent, request_code);
			ll.setVisibility(View.INVISIBLE);
			break;
		case R.id.menu_btn_location:
			intent.setClass(getApplicationContext(), location.class);
			startActivityForResult(intent, request_code);
			ll.setVisibility(View.INVISIBLE);
			break;
		case R.id.menu_btn_bookmark:
			intent.setClass(getApplicationContext(), bookmark.class);
			startActivityForResult(intent, request_code);
			ll.setVisibility(View.INVISIBLE);
			break;
		case R.id.menu_btn_esp:
			check=true;
			btn_esp.setImageResource(R.drawable.esp);
			btn_book.setImageResource(R.drawable.inver_history);
			btn_lo.setImageResource(R.drawable.inver_map);
			btn_num.setImageResource(R.drawable.inver_bus);
			ll.setVisibility(View.VISIBLE);
			break;
		}
		
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		btn_esp.setImageResource(R.drawable.esp);
		btn_book.setImageResource(R.drawable.history);
		btn_lo.setImageResource(R.drawable.map);
		btn_num.setImageResource(R.drawable.bus);
		Intent intent2=new Intent();
		switch (resultCode) {
		case request_code:
			ll.setVisibility(View.VISIBLE);
			check=true;
			btn_esp.setImageResource(R.drawable.esp);
			btn_book.setImageResource(R.drawable.inver_history);
			btn_lo.setImageResource(R.drawable.inver_map);
			btn_num.setImageResource(R.drawable.inver_bus);
			break;
		case 1:
			ll.setVisibility(View.INVISIBLE);
			intent2.setClass(getApplicationContext(), num.class);
			startActivityForResult(intent2, request_code);
			break;
		case 2:
			ll.setVisibility(View.INVISIBLE);
			intent2.setClass(getApplicationContext(), location.class);
			startActivityForResult(intent2, request_code);
			
			break;
		case 3:
			ll.setVisibility(View.INVISIBLE);
			intent2.setClass(getApplicationContext(), bookmark.class);
			startActivityForResult(intent2, request_code);
			break;
		}
	}
	
	
	
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		super.onKeyDown(KeyCode, event);
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (KeyCode) {
			case KeyEvent.KEYCODE_BACK:
				if(check){
					btn_book.setImageResource(R.drawable.history);
					btn_lo.setImageResource(R.drawable.map);
					btn_num.setImageResource(R.drawable.bus);
					check=false;
					ll.setVisibility(View.INVISIBLE);
				}
				else if (!key_check) {
					key_check = true;

					key_handler.sendEmptyMessageDelayed(0, 2000);
					close_toast.show();
				} else {
					close_toast.cancel();

				
					finish();
				}
				break;
			}
			return false;
		}
		return true;
	}



	private void setDoubleBack() {
		close_toast = Toast.makeText(this, "'뒤로가기'를 한번 더 누르면 종료됩니다.",
				Toast.LENGTH_SHORT);
		// AttendanceCheck();
		key_handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					key_check = false;
				}
			}
		};
	}

}
