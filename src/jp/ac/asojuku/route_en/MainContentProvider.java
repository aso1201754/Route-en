package jp.ac.asojuku.route_en;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MainContentProvider extends ContentProvider
{

	private DatabaseHelper database;

	private static final int Main = 10;
	private static final int Main_ID = 20;
	private static final String AUTHORITY = "jp.ac.asojuku.route_en.MainContentProvider";

	private static final String BASE_PATH = "main";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/Main";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/Main";

	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static {
		uriMatcher.addURI(AUTHORITY, BASE_PATH, Main);
		uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#",Main_ID);
	}
	@Override
	public boolean onCreate() {
		// TODO 自動生成されたメソッド・スタブ
		database = new DatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO 自動生成されたメソッド・スタブ
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		queryBuilder.setTables(DatabaseHelper.TABLE_MAIN);
		int uriType = uriMatcher.match(uri);
		switch (uriType)
		{
		case Main:
			break;
		case Main_ID:
			queryBuilder.appendWhere(DatabaseHelper.COLUMN_ID + "=" + uri.getLastPathSegment());
			break;
			default:
				throw new IllegalArgumentException("Unknown URI:" + uri);
		}
		SQLiteDatabase db = database.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO 自動生成されたメソッド・スタブ
		int uriType = uriMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		//int rowsDeleted = 0;
		long id = 0;
		switch (uriType)
		{
		case Main:
			id = sqlDB.insert(DatabaseHelper.TABLE_MAIN, null ,values);
			break;
			default:
				throw new IllegalArgumentException("Unknown URI:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	}