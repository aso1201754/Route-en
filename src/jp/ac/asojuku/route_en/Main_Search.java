package jp.ac.asojuku.route_en;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Main_Search extends Activity implements View.OnClickListener{

	KeyValuePair item = null;
	KeyValuePair item2 = null;

	private TextView tv = null;
	Spinner _spinner = null;
	Spinner _spinner2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}








	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ

		setContentView(R.layout.main_search);
		super.onResume();
		tv = (TextView)findViewById(R.id.tv);

		//スピナー1
		_spinner =  (Spinner)this.findViewById(R.id.spinner1);
		_spinner.setOnItemSelectedListener(Spinner1_OnItemSelectedListener);
		//スピナーのドロップダウンアイテムを設定
		List<KeyValuePair> list = getSpinnerData();
		KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spinner.setAdapter(adapter);


		//スピナー2
		_spinner2 =  (Spinner)this.findViewById(R.id.spinner2);
		_spinner2.setOnItemSelectedListener(Spinner2_OnItemSelectedListener);
		//スピナーのドロップダウンアイテムを設定
		List<KeyValuePair> list2 = getSpinnerData2();
		KeyValuePairArrayAdapter adapter2 = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item, list2);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spinner2.setAdapter(adapter2);


		//　button1登録
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(this);
	}


	//1
	private List<KeyValuePair> getSpinnerData(){
		List<KeyValuePair> list = new ArrayList<KeyValuePair>();
		Resources res = getResources();
		TypedArray spinner1_data = res.obtainTypedArray(R.array.spinner1_data);
		for (int i = 0; i < spinner1_data.length(); ++i) {
			int id = spinner1_data.getResourceId(i, -1);
			if (id > -1) {
				String[] item_ = res.getStringArray(id);
				list.add(new KeyValuePair(String.valueOf(item_[0]), item_[1]));
			}
		}
		spinner1_data.recycle();
		return list;
	}

    private OnItemSelectedListener Spinner1_OnItemSelectedListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            item = (KeyValuePair)_spinner.getSelectedItem();
            Toast.makeText(Main_Search.this, item.getKey().toString(), Toast.LENGTH_LONG).show();

        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    //2
	private List<KeyValuePair> getSpinnerData2(){
		List<KeyValuePair> list2 = new ArrayList<KeyValuePair>();
		Resources res2 = getResources();
		TypedArray spinner2_data = res2.obtainTypedArray(R.array.spinner2_data);
		for (int i = 0; i < spinner2_data.length(); ++i) {
			int id = spinner2_data.getResourceId(i, -1);
			if (id > -1) {
				String[] item_2 = res2.getStringArray(id);
				list2.add(new KeyValuePair(String.valueOf(item_2[0]), item_2[1]));
			}
		}
		spinner2_data.recycle();
		return list2;
	}

    private OnItemSelectedListener Spinner2_OnItemSelectedListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            item2 = (KeyValuePair)_spinner2.getSelectedItem();
            Toast.makeText(Main_Search.this, item2.getKey().toString(), Toast.LENGTH_LONG).show();

        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };


	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.button:

			Intent intent = new Intent(Main_Search.this, Osusume_Route.class);

			String item_1 = item.getKey().toString();
			String item_2 = item2.getKey().toString();

			intent.putExtra("p_id", item_1);
			intent.putExtra("c_id", item_2);
			startActivity(intent);



			//exec_post();
		}




	}


	// POST通信を実行（AsyncTaskによる非同期処理を使うバージョン）
	private void exec_post() {

		// 非同期タスクを定義
		HttpPostTask task = new HttpPostTask(
				this,
				"http://toenp.php.xdomain.jp/Search.php",

				// タスク完了時に呼ばれるUIのハンドラ
				new HttpPostHandler(){

					@Override
					public void onPostCompleted(String response) {
						// 受信結果をUIに表示
						tv.setText( response );
					}

					@Override
					public void onPostFailed(String response) {
						tv.setText( response );
						Toast.makeText(
								getApplicationContext(),
								"エラーが発生しました。",
								Toast.LENGTH_LONG
								).show();
					}
				}
				);
		task.addPostParam( "p_id", item.getKey().toString() );
		task.addPostParam( "c_id", item2.getKey().toString() );

		Log.d("posttest", item.getKey().toString());
		Log.d("posttes2t", item2.getKey().toString());

		// タスクを開始
		task.execute();

	}



}
