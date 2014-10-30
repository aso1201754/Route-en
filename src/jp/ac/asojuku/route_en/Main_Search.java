package jp.ac.asojuku.route_en;

import android.app.Activity;
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

	String item = null;
	String item2 = null;

	private TextView tv = null;
	private Spinner _spinner = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_search);

		tv = (TextView)findViewById(R.id.tv1);

		//スピナー
		_spinner =  (Spinner)this.findViewById(R.id.spinner1);
		_spinner.setOnItemSelectedListener(Spinner1_OnItemSelectedListener);


		//　button1登録
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(this);

		KeyValuePairArrayAdapter todouhuken = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
		todouhuken.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		//ArrayAdapter<String> todouhuken = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		//todouhuken.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// アイテムを追加します
		todouhuken.add(new KeyValuePair(1,"北海道"));
		todouhuken.add(new KeyValuePair(2,"青森県"));
		todouhuken.add(new KeyValuePair(3,"岩手県"));
		todouhuken.add(new KeyValuePair(4,"宮城県"));
		todouhuken.add(new KeyValuePair(5,"秋田県"));
		todouhuken.add(new KeyValuePair(6,"山形県"));
		todouhuken.add(new KeyValuePair(7,"福島県"));
		todouhuken.add(new KeyValuePair(8,"茨城県"));
		todouhuken.add(new KeyValuePair(9,"栃木県"));
		todouhuken.add(new KeyValuePair(10,"群馬県"));
		todouhuken.add(new KeyValuePair(11,"埼玉県"));
		todouhuken.add(new KeyValuePair(12,"千葉県"));
		todouhuken.add(new KeyValuePair(13,"東京都"));
		todouhuken.add(new KeyValuePair(14,"神奈川県"));
		todouhuken.add(new KeyValuePair(15,"新潟県"));
		todouhuken.add(new KeyValuePair(16,"富山県"));
		todouhuken.add(new KeyValuePair(17,"石川県"));
		todouhuken.add(new KeyValuePair(18,"福井県"));
		todouhuken.add(new KeyValuePair(19,"山梨県"));
		todouhuken.add(new KeyValuePair(20,"長野県"));
		todouhuken.add(new KeyValuePair(21,"岐阜県"));
		todouhuken.add(new KeyValuePair(22,"静岡県"));
		todouhuken.add(new KeyValuePair(23,"愛知県"));
		todouhuken.add(new KeyValuePair(24,"三重県"));
		todouhuken.add(new KeyValuePair(25,"滋賀県"));
		todouhuken.add(new KeyValuePair(26,"京都府"));
		todouhuken.add(new KeyValuePair(27,"大阪府"));
		todouhuken.add(new KeyValuePair(28,"兵庫県"));
		todouhuken.add(new KeyValuePair(29,"奈良県"));
		todouhuken.add(new KeyValuePair(30,"和歌山県"));
		todouhuken.add(new KeyValuePair(31,"鳥取県"));
		todouhuken.add(new KeyValuePair(32,"島根県"));
		todouhuken.add(new KeyValuePair(33,"岡山県"));
		todouhuken.add(new KeyValuePair(34,"広島県"));
		todouhuken.add(new KeyValuePair(35,"山口県"));
		todouhuken.add(new KeyValuePair(36,"徳島県"));

		todouhuken.add(new KeyValuePair(37,"香川県"));
		todouhuken.add(new KeyValuePair(38,"愛媛県"));
		todouhuken.add(new KeyValuePair(39,"高知県"));
		todouhuken.add(new KeyValuePair(40,"福岡県"));
		todouhuken.add(new KeyValuePair(41,"佐賀県"));
		todouhuken.add(new KeyValuePair(42,"長崎県"));
		todouhuken.add(new KeyValuePair(43,"熊本県"));
		todouhuken.add(new KeyValuePair(44,"大分県"));
		todouhuken.add(new KeyValuePair(45,"宮崎県"));
		todouhuken.add(new KeyValuePair(46,"鹿児島県"));
		todouhuken.add(new KeyValuePair(47,"沖縄県"));
		_spinner.setAdapter(todouhuken);
		//キーが2の値を選択する
		Integer selectKey = 2;
		_spinner.setSelection(todouhuken.getPosition(selectKey));











	}
    private OnItemSelectedListener Spinner1_OnItemSelectedListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair)_spinner.getSelectedItem();
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
		task.addPostParam( "text1", item );
		//task.addPostParam( "text2", item2 );

		Log.d("posttest", item);

		// タスクを開始
		task.execute();

	}



}
