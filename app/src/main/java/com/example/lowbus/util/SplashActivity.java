package com.example.lowbus.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.example.lowbus.R;

public class SplashActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    setContentView(R.layout.intro_splash);
	    
	    Handler handler = new Handler(){
	    	public void handleMessage(Message msg){
	    		finish();
	    	}
	    };
	    handler.sendEmptyMessageDelayed(0, 2000);
	
	    // TODO Auto-generated method stub
	}

}
