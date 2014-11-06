package jp.ac.asojuku.route_en;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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
	String item2 = null;

	private TextView tv = null;
	Spinner _spinner = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_search);

		tv = (TextView)findViewById(R.id.tv1);

		//スピナー
		_spinner =  (Spinner)this.findViewById(R.id.spinner1);
		_spinner.setOnItemSelectedListener(Spinner1_OnItemSelectedListener);
		//スピナーのドロップダウンアイテムを設定
		List<KeyValuePair> list = getSpinnerData();
		KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spinner.setAdapter(adapter);



		//　button1登録
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(this);






	}
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



	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.button:

			exec_post();
		}


	}


	// POST通信を実行（AsyncTaskによる非同期処理を使うバージョン）
	private void exec_post() {

		// 非同期タスクを定義
		HttpPostTask task = new HttpPostTask(
				this,
				"http://toenp.php.xdomain.jp/test.php",

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
		//task.addPostParam( "text2", item2 );

		Log.d("posttest", item.getKey().toString());

		// タスクを開始
		task.execute();

	}



}
