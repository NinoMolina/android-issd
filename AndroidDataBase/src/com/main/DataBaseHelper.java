package com.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	public static final String EMPLOYEE_DATABASE = "CREATE TABLE employee ("
			+ "_id integer primary key autoincrement," + "name text not null,"
			+ "lastname text not null," + "age text not null,"
			+ "profession text not null);";

	public static final String MODIFY_CONTACT = "modify_contact";
	
	private static DataBaseHelper instance;

	public static DataBaseHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DataBaseHelper(context.getApplicationContext(),
					"Employees_database", null, 1);
		}
		return instance;
	}

	private DataBaseHelper(Context context, String name,
			SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(EMPLOYEE_DATABASE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public long insertAnEmployee(String name, String lastName, String age,
			String profession) {
		long result = -1;
		try {
			SQLiteDatabase database = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("lastname", lastName);
			values.put("age", age);
			values.put("profession", profession);
			result = database.insert("employee", null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Cursor getEmployees() {
		Cursor result = null;
		try {
			SQLiteDatabase database = getReadableDatabase();
			result = database.query("employee", null, null, null, null, null,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Cursor getEmployee(String id) {
		Cursor result = null;
		try {
			SQLiteDatabase database = getReadableDatabase();
			result = database.query("employee", null, "_id=?",
					new String[] { id }, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public long updateEmployee(String id, String name, String lastName,
			String age, String profession) {
		long result = -1;
		try {
			SQLiteDatabase database = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("lastname", lastName);
			values.put("age", age);
			values.put("profession", profession);

			result = database.update("employee", values, " _id=?",
					new String[] { id });
		} catch (Exception e) {

		}
		return result;
	}

	public void deleteEmployee(String id) {
		try {
			SQLiteDatabase database = getWritableDatabase();
			database.delete("employee", "_id=?", new String[] { id });
		} catch (Exception e) {

		}
	}
}
