package jp.ac.asojuku.route_en;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Osusume_Route extends Activity implements AdapterView.OnItemClickListener {

	TextView tv = null;

	List<String> o_route = new ArrayList<String>();
	ArrayAdapter<String> adapter = null;


	String[] o_route2 = new String[13];



	/*
	// テスト表示用
	String user_id1 = null;
	String p_name1 = null;
	 */
	// Jsonデータ用のデータ
	public class Data {
		public String user_id;
		public String p_name;
		public String c_name;
		public String spot_name_1;
		public String spot_comment_1;
		public String spot_image_1;
		public String spot_name_2;
		public String spot_image_2;
		public String spot_comment_2;
		public String spot_name_3;
		public String spot_image_3;
		public String spot_comment_3;
		public String con_comment;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		setContentView(R.layout.osusume_route);

		tv = (TextView)findViewById(R.id.tv1);

		//　Main_Searchからのスピナー情報の受信
		Intent intent = getIntent();
		// intentに混入していたEtraから、キーワードを先に値を取得
		String p_id = intent.getStringExtra("p_id");
		String c_id = intent.getStringExtra("c_id");



		// PHPに送信
		if(p_id!=null && c_id!=null){
			exec_post(this, p_id, c_id);
		}

		// ListViewにリスナーをセット
		ListView o_lv1 = (ListView) findViewById(R.id.listView);
		o_lv1.setOnItemClickListener(this);

	}

	private void exec_post(final Context context, String p_id, String c_id) {

		// 非同期タスクを定義
		HttpPostTask task = new HttpPostTask(
				this,
				"http://toenp.php.xdomain.jp/Search.php",

				// タスク完了時に呼ばれるUIのハンドラ
				new HttpPostHandler(){



					// テスト表示用
					String user_id = null;
					String c_name = null;
					String p_name = null;
					String spot_name_1 = null;
					String spot_comment_1 = null;
					String spot_image_1 = null;
					String spot_name_2 = null;
					String spot_comment_2 = null;
					String spot_image_2 = null;
					String spot_name_3 = null;
					String spot_comment_3 = null;
					String spot_image_3 = null;
					String con_comment;




					@Override
					public void onPostCompleted(String response) {

						Log.d("post_test", response);
						Log.d("post_test", "response出力");

						//文字列をJSONオブジェクトに変換
						try {

							// responseをData型の中身を持ったリストdatalistに変換
							Gson gson = new Gson();
							Type collectionType = new TypeToken<Collection<Data>>(){}.getType();
							List<Data> datalist = gson.fromJson(response, collectionType);

							// datalistの0番目を取得
							Data data = datalist.get(0);

							// 取得した0番目のデータのuser_idを変数に代入
							user_id = data.user_id;
							// p_nameも
							c_name = data.c_name;
							//以下全部
							p_name = data.p_name;
							spot_name_1 = data.spot_name_1;
							spot_image_1 = data.spot_image_1;
							spot_comment_1 = data.spot_comment_1;
							spot_name_1 = data.spot_name_2;
							spot_image_1 = data.spot_image_2;
							spot_comment_1 = data.spot_comment_2;
							spot_name_1 = data.spot_name_3;
							spot_image_1 = data.spot_image_3;
							spot_comment_1 = data.spot_comment_3;
							p_name = data.con_comment;

							//ListViewの登録
							int max = datalist.size();
							for(int i=0;i<max; i++){
								data = datalist.get(i);
								c_name = data.c_name;
								o_route.add(c_name);



								p_name = data.p_name;
								spot_name_1 = data.spot_name_1;
								spot_image_1 = data.spot_image_1;
								spot_comment_1 = data.spot_comment_1;
								spot_name_1 = data.spot_name_2;
								spot_image_1 = data.spot_image_2;
								spot_comment_1 = data.spot_comment_2;
								spot_name_1 = data.spot_name_3;
								spot_image_1 = data.spot_image_3;
								spot_comment_1 = data.spot_comment_3;
								p_name = data.con_comment;


							}


							// adapterにセット
							//ListViewの登録
							ListView o_lv1 = (ListView) findViewById(R.id.listView);
							ArrayAdapter<String> m_adapter = new ArrayAdapter<String>(context, android.R.layout. simple_list_item_1, o_route);
							o_lv1.setAdapter(m_adapter);
							// adapter.notifyDataSetChanged();
							// adapter.notifyDataSetInvalidated();


						} catch (Exception e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}

						// 代入したuser_idをUIに表示
						tv.setText( user_id + c_name);

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


		task.addPostParam( "p_id", p_id);
		task.addPostParam( "c_id", c_id);

		Log.d("posttest", p_id);
		Log.d("posttest2", c_id);

		// タスクを開始
		task.execute();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
        ListView listView = (ListView) parent;
        String item = (String) listView.getItemAtPosition(position);



		// 代入したuser_idをUIに表示
		tv.setText(item);

		Intent intent = new Intent(Osusume_Route.this, Route_hyouzi.class);


		String c_name = item;

		intent.putExtra("c_name", c_name);




		startActivity(intent);


	}




}
