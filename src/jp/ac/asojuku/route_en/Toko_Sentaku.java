package jp.ac.asojuku.route_en;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Toko_Sentaku extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toko_sentaku);

		// button1登録
		Button button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(this);

		// 配列準備（仮）
		String[] members = {"ルート1", "ルート2"};
		// listView1登録
		ListView lv1 = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, members);
		lv1.setAdapter(adapter);
		// lv1クリック時
		lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent2 = new Intent(Toko_Sentaku.this, Toko_Henshu.class);
				startActivity(intent2);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		// button1
		case R.id.button1:
			// MainActivityに遷移
			Intent intent1 = new Intent(Toko_Sentaku.this, MainActivity.class);
			startActivity(intent1);

		}

	}

	/**
	 * 指定URLからgetした文字列を取得する
	 * @param sUrl
	 * @return
	 */
	public String getData(String sUrl) {
		HttpClient objHttp = new DefaultHttpClient();
		HttpParams params = objHttp.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 1000); // 接続のタイムアウト
		HttpConnectionParams.setSoTimeout(params, 1000); // データ取得のタイムアウト
		String sReturn = "";
		try {
			HttpGet objGet = new HttpGet(sUrl);
			HttpResponse objResponse = objHttp.execute(objGet);
			if (objResponse.getStatusLine().getStatusCode() < 400) {
				InputStream objStream = objResponse.getEntity().getContent();
				InputStreamReader objReader = new InputStreamReader(objStream);
				BufferedReader objBuf = new BufferedReader(objReader);
				StringBuilder objJson = new StringBuilder();
				String sLine;
				while ((sLine = objBuf.readLine()) != null) {
					objJson.append(sLine);
				}
				sReturn = objJson.toString();
				objStream.close();
			}
		} catch (IOException e) {
			return null;
		}
		return sReturn;

	}


}
