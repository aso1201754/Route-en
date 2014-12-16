package jp.ac.asojuku.route_en;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity
	implements LocationListener, LocationSource,
	OnClickListener, LoaderCallbacks<Address>
	{
	static final LatLng TOKYO_STATION = new LatLng(35.681588,139.76608); //東京駅の緯度経度
	static final LatLng SKY_TREE = new LatLng(35.709958,139.81083); //スカイツリーの緯度経度


		private static final int ADDRESSLOADER_ID = 0;
		private static final int CURSORLOADER_ID = 1;

		private static Location mMyLocation = null;
		private static boolean mMyLocationCentering = false;

		private static GoogleMap mMap;
		private OnLocationChangedListener mListener;  //LocationSource
		private LocationManager locationManager;
		private double mLat = 0;
		private double mLon = 0;
		private EditText edText1;
		private DatabaseHelper dbhelper;
		private static MarkerOptions mMyMarkerOptions = null;







	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map01)).getMap();

	    if (mMap != null){
	    	mMap.setMyLocationEnabled(true);
	    	 mMap.setOnMyLocationChangeListener(new OnMyLocationChangeListener(){

				@Override
				public void onMyLocationChange(Location location) {
					// TODO 自動生成されたメソッド・スタブ
					mMyLocation = location;
					if (mMyLocation != null && mMyLocationCentering == false) {    // 一度だけ現在地を画面中央に表示する
                        mMyLocationCentering = true;
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(mMyLocation.getLatitude(), mMyLocation.getLongitude()), 14.0f);
                        mMap.animateCamera(cameraUpdate);

                        // 逆ジオコーディングで現在地の住所を取得する
                        requestReverseGeocode(location.getLatitude(), location.getLongitude());

				}
			}

				private void requestReverseGeocode(double latitude, double longitude) {
					// TODO 自動生成されたメソッド・スタブ

				}

	    	 });





	    }
	    mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map01)).getMap();
	    if (mMap != null) {
	    	mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

				@Override
				public void onMapClick(LatLng latLng) {
					// TODO 自動生成されたメソッド・スタブ
					mMyMarkerOptions = new MarkerOptions();
					mMyMarkerOptions.position(latLng);

					//古いピンを消去
					mMap.clear();
					//タップした位置にピンを立てる
					mMap.addMarker(mMyMarkerOptions);
					//逆ジオコーディングでピンを立てた位置の住所を取得する
					requestReverseGeocode(latLng.latitude,latLng.longitude);
				}

				private void requestReverseGeocode(double latitude, double longitude) {
					// TODO 自動生成されたメソッド・スタブ

				}
			});
	    }

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		if(locationManager != null)
		{
			boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			boolean netIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);



			if(gpsIsEnabled)
			{
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 2.0f, this);
			} else if(netIsEnabled)
			{
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 2.0f, this);
			}

			}
		else
		{
			Toast.makeText(this, "locationManager is null", Toast.LENGTH_SHORT).show();

		}
		setUpMapIfNeedawszed();
		edText1=(EditText)findViewById(R.id.editText1);

		Button btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		Button btn2 = (Button)findViewById(R.id.button2);
		btn2.setOnClickListener(this);
		// DatabaseHelperの生成
		//ContentProvier化
		//dbhelper = new DatabaseHelper(this);

		}


	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();


	}
	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
		setUpMapIfNeedawszed();

		if(locationManager != null)
		{
			mMap.setMyLocationEnabled(true);
		}
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}





	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		if(locationManager != null)
		{
			locationManager.removeUpdates(this);
		}
		super.onStop();

	}
	private void setUpMapIfNeedawszed() {
		if (mMap == null)
		{
			mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map01)).getMap();
			if(mMap != null)
			{
				mMap.setMyLocationEnabled(true);
			}
			mMap.setLocationSource(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自動生成されたメソッド・スタブ
		// Inflate the menu; this adds items to the actio bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}





	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		switch (item.getItemId())
		{
		case R.id.marker_on:
			markerOn();
			return true;
		case R.id.marker_off:
			markerOff();
			return true;
			}
		return false;
		}
	private void markerOn(){
		getLoaderManager().restartLoader(CURSORLOADER_ID, null,curCallback);
	}
	private void markerOff(){
		mMap.clear();
	}

	@Override
	public void activate(OnLocationChangedListener listener)
	{
		mListener = listener;



	}
	@Override
	public void deactivate()
	{
		mListener = null;
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		if(mListener != null)
		{
			mListener.onLocationChanged(location);
			mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
			mLat = location.getLatitude();
			mLon = location.getLongitude();
		}
	}

		public void onProviderDisabled(String arg0){
		Toast.makeText(this, "provider disabled", Toast.LENGTH_SHORT).show();
	}
	public void onProviderEnabled(String args0)
	{
		Toast.makeText(this, "provider enabled", Toast.LENGTH_SHORT).show();
	}
	public void OnStatusChanged(String provider, int status, Bundle extra)
	{

	}
	public Loader<Address> onCreateLoader(int id, Bundle args)
	{
		double lat = args.getDouble("lat");
		double lon = args.getDouble("lon");
		return new AddressTaskLoader(this, lat,lon);
	}
	public void onLoadFinished1(Loader<Address> loader, Address result)
	{
		if(result != null)
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < result.getMaxAddressLineIndex() + 1; i++)
			{
				String item = result.getAddressLine(i);
				if(item == null)
				{
					break;
				}
				sb.append(item);
			}
			edText1.setText(sb.toString());
		}
	}

	public void onLoaderReset(Loader<Address> loader)
	{

	}
	LoaderManager.LoaderCallbacks<Cursor> curCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

		@Override
		public Loader<Cursor> onCreateLoader(int id,Bundle args)
		{
			String[] projection = {
					DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_LAT, DatabaseHelper.COLUMN_LON,
					DatabaseHelper.COLUMN_ADDRESS, DatabaseHelper.COLUMN_DATE};
			CursorLoader cursorLoader = new CursorLoader(MainActivity.this,MainContentProvider.CONTENT_URI, projection, null, null, null);
			return cursorLoader;
			}
		public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor)
		{
			cursor.moveToFirst();
			String strText="";
			while (!cursor.isAfterLast())
			{
				MarkerOptions options = new MarkerOptions();
				options.position(new LatLng(cursor.getDouble(1), cursor.getDouble(2)));
				options.title(cursor.getString(3));
				options.snippet(cursor.getString(4));
				mMap.addMarker(options);
				cursor.moveToNext();
			}
		}
		public void onLoaderReset(Loader<Cursor> arg0)
		{

		}
		};
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.button1:
				getAddressByLoader();
				mMap.setMyLocationEnabled(true);
				return;
			case R.id.button2:
				showDialog();
				return;

				default:
					break;
			}
		}
		private void getAddressByLoader()
		{
			if(mLat != 0)
			{
				Bundle args = new Bundle();
				args.putDouble("lat", mLat);
				args.putDouble("lon", mLon);

				getLoaderManager().restartLoader(ADDRESSLOADER_ID, args, this);
			}
		}
		void showDialog() {
			String address = edText1.getText().toString();
			if(address.equals(""))
			{
				Toast.makeText(this, "住所を取得してください", Toast.LENGTH_SHORT).show();
				edText1.requestFocus();
				return;
			}
			DialogFragment newFragment = MyAlertDialogFragment.newInstance(
					R.string.alert_dialog_fin_confirm_title, R.string.alert_dialog_fin_confirm_message);
			newFragment.show(getFragmentManager(), "dialog");
		}
		public void doPositiveClick() {
		//savePoint();
			savePointViaCTP();
		}
		public void doNegativeClick()
		{
			//何もしない
		}
		private void savePointViaCTP(){
			ContentValues values = new ContentValues();
				double lat = mLat;
				double lon = mLon;
				String address = edText1.getText().toString();
				String strDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date(0));

			values.put(DatabaseHelper.COLUMN_LAT, lat);
			values.put(DatabaseHelper.COLUMN_LON, lon);
			values.put(DatabaseHelper.COLUMN_ADDRESS, address);
			values.put(DatabaseHelper.COLUMN_DATE, strDate);
			getContentResolver().insert(MainContentProvider.CONTENT_URI,  values);
			Toast.makeText(this, "データを保存しました",Toast.LENGTH_SHORT).show();

		}
private void savePoint()
{
	SQLiteDatabase db = dbhelper.getWritableDatabase();

	ContentValues values = new ContentValues();
	double lat = mLat;
	double lon = mLon;
	String address = edText1.getText().toString();
	String strDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(new Date(0));

	values.put("lat", lat);
	values.put("lon", lon);
	values.put("address", address);
	values.put("date", strDate);
	try
	{
		db.insert("Main", null, values);
		Toast.makeText(this, "データを保存しました", Toast.LENGTH_SHORT).show();
	} catch (Exception e)
	{
		Toast.makeText(this, "データの保存に失敗しました", Toast.LENGTH_SHORT).show();
	} finally {
		db.close();
	}
}


@Override
public void onLoadFinished(Loader<Address> loader, Address data) {
	// TODO 自動生成されたメソッド・スタブ

}



@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO 自動生成されたメソッド・スタブ

}
}