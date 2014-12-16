package jp.ac.asojuku.route_en;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final String DBNAME ="main.db";
	private static final int DBVERSION =1;
	public static final String TABLE_MAIN  = "main";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LON = "lon";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_DATE ="date";
	private static final String CREATE_TABLE_SQL="create table" + TABLE_MAIN + " " + "(" + COLUMN_LAT + "integer primary key autoincrement,"
			+ COLUMN_LAT + "real not null,"
			+ COLUMN_LON + "real not null,"
			+ COLUMN_ADDRESS + "text null,"
			+ COLUMN_DATE + "text not null)";

	public DatabaseHelper(Context context)
	{
		super(context,DBNAME,null,DBVERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ

	}
	}