package jp.ac.asojuku.route_en;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import jp.ac.asojuku.route_en.Osusume_Route.Data;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Route_hyouzi extends Activity {

	TextView tv = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

	}
	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		setContentView(R.layout.route_hyouzi);

		tv = (TextView)findViewById(R.id.tv1);

		//　Main_Searchからのスピナー情報の受信
		Intent intent = getIntent();
		// intentに混入していたEtraから、キーワードを先に値を取得
		String p_name = intent.getStringExtra("p_name");

		// PHPに送信
		if(p_name!=null){
			exec_post(p_name);
		}
	}

	private void exec_post(String p_name) {

		// 非同期タスクを定義
		HttpPostTask task = new HttpPostTask(
				this,
				"http://toenp.php.xdomain.jp/Search.php",

				// タスク完了時に呼ばれるUIのハンドラ
				new HttpPostHandler(){



					// テスト表示用
					String user_id = null;
					String p_name = null;


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
							p_name = data.p_name;


						} catch (Exception e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}

						// 代入したuser_idをUIに表示
						tv.setText(p_name);

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


		task.addPostParam( "p_name", p_name);

		Log.d("posttest", p_name);

		// タスクを開始
		task.execute();

	}





}

