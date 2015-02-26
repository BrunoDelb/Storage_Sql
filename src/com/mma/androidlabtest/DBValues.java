package com.mma.androidlabtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBValues extends SQLiteOpenHelper {

	public DBValues(Context context) {
		super(context, "mydata.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE myvalues (id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT NOT NULL);");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS myvalues");
		onCreate (db);
	}

}
