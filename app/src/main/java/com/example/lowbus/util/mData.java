/**
 * @fgetData.java
 * @author Yang-ho Kim
 * @date 2013. 5. 31.
 * @comment
 */
package com.example.lowbus.util;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class mData {
	ArrayList<user_Info> array=new ArrayList<user_Info>();

	public mData() {
		super();
		//setData();
	}
	
//	public void setData(){
////		array.add(new user_Info("냐옹",10000,"냐옹"));
//		array.add(new user_Info("개구리",true));
//		array.add(new user_Info("개구리",true));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		array.add(new user_Info("개구리",false));
//		
//	}

	public void addData(String stname) {
		addData(stname, "-", false);
		
	}
	public void addData(String stname, boolean imgvisibility) {
		addData(stname, "-", imgvisibility);
		
	}
	public void addData(String stname, String time, boolean imgvisibility) {
		array.add(new user_Info(stname, time, imgvisibility));
		
	}

	public ArrayList<user_Info> getArray() {
		return array;
	}

	public void setArray(ArrayList<user_Info> array) {
		this.array = array;
	}

}
