package com.example.lowbus.util;


	import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lowbus.R;

	public class Custom_adapter2 extends ArrayAdapter<user_Info> {
		private LayoutInflater inflater;
		private int resource;
		private ArrayList<user_Info> list = null;
		private user_Info item = null;
		public Custom_adapter2(Context context, int res,
				ArrayList<user_Info> objects) {
			super(context, res, objects);
			
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			resource = res;
			list = objects;
			// TODO Auto-generated constructor stub
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null){
				convertView = inflater.inflate(resource, null);
			}
			TextView dd=(TextView)convertView.findViewById(R.id.kkkkk);
			TextView dd2=(TextView)convertView.findViewById(R.id.kkkkk_time);
			ImageView iv=(ImageView)convertView.findViewById(R.id.bus_iv);
			
			item = list.get(position);
			dd.setText(item.getName());
			dd2.setText(item.getTime());
			
			if(item.isCheck())
				iv.setVisibility(View.VISIBLE);
			else
				iv.setVisibility(View.INVISIBLE);
			
			// ?�정 �?��
				
			
			
			return convertView;
		}

	


}
