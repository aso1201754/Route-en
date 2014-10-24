package jp.ac.asojuku.route_en;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Toko_Henshu extends Activity implements View.OnClickListener {

	String text1;
	String text2;
	String text3;
	String text4;

	  private Button btn = null;
	  private TextView tv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toko_henshu);

		// button4登録
		Button button4 = (Button)findViewById(R.id.button4);
		button4.setOnClickListener(this);

	    btn = (Button)findViewById(R.id.btn1);
	    tv = (TextView)findViewById(R.id.tv1);

	    btn.setOnClickListener(this);


		// editText1
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		SpannableStringBuilder sb1 = (SpannableStringBuilder)editText1.getText();
		text1 = sb1.toString();
		// editText2
		EditText editText2 = (EditText)findViewById(R.id.editText2);
		SpannableStringBuilder sb2 = (SpannableStringBuilder)editText2.getText();
		text2 = sb2.toString();
		// editText3
		EditText editText3 = (EditText)findViewById(R.id.editText3);
		SpannableStringBuilder sb3 = (SpannableStringBuilder)editText3.getText();
		text3 = sb3.toString();
		// editText4
		EditText editText4 = (EditText)findViewById(R.id.editText4);
		SpannableStringBuilder sb4 = (SpannableStringBuilder)editText4.getText();
		text4 = sb4.toString();

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// アイテムを追加します
		adapter1.add("福岡県");
		adapter1.add("佐賀県");
		adapter1.add("長崎県");
		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
		// アダプターを設定します
		spinner1.setAdapter(adapter1);

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// アイテムを追加します
		adapter2.add("デート");
		adapter2.add("一人で");
		adapter2.add("家族で");
		adapter2.add("旅行");
		adapter2.add("おまかせ！");
		Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
		// アダプターを設定します
		spinner2.setAdapter(adapter2);

	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.button4:
			doPost("http://toenp.php.xdomain.jp/test.php");
			Intent intent4 = new Intent(Toko_Henshu.this, Toko_Sentaku.class);
			startActivity(intent4);
		case R.id.btn1:
			exec_post();
		}

	}

	public String doPost(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);

		// リクエストパラメータの設定
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("text1", "aaaa"));
		try {
			method.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = client.execute(method);
			int status = response.getStatusLine().getStatusCode();
			return "status:" + status;
		} catch (Exception e) {
			return "Error:" + e.getMessage();
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
	    task.addPostParam( "text1", "ユーザID" );
	    task.addPostParam( "text2", "パスワード" );

	    // タスクを開始
	    task.execute();

	  }


}
