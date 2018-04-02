package mysql.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {

	String tableName1 = "BUS_HISTORY";
	String tableName2 = "STATIONS_HISTORY";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
		insertData(db);
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		///
	}
	
	private void createTable(SQLiteDatabase db) {
		//String sql = "create table " + tableName + "(id text, name text, age integer)";
		// table 삭제는 drop table
		String sql;

		// 순번, Data
		sql = "create table " + tableName1
				+ " (seq integer, " +
				"Data text" + ")";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 순번, Data
		sql = "create table " + tableName2
				+ " (seq integer, " +
				"Data text" + ")";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void insertData(SQLiteDatabase db) {
		
	}
	
}