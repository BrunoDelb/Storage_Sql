package com.mma.androidlabtest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class Main extends Activity {

	private DBValues dbValues;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		EditText et_output = (EditText)findViewById (R.id.et_output);
		dbValues = new DBValues (this);
		try {
			SQLiteDatabase db = dbValues.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put ("value", "Hello " + System.currentTimeMillis());
			db.insertOrThrow("myvalues", null, values);
			
			db = dbValues.getReadableDatabase();
			Cursor cursor = db.query("myvalues", new String[]{"id", "value"}, null, null, null, null, "value DESC");
			startManagingCursor (cursor);
			
			StringBuilder builder = new StringBuilder();
			while (cursor.moveToNext()) {
				builder.append ("id=").append(cursor.getString(0)).append(", value=").append(cursor.getString(1));
			}
			et_output.setText (builder);
		} finally {
			dbValues.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
