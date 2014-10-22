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

public class Toko_Henshu extends Activity implements View.OnClickListener {

	String text1;
	String text2;
	String text3;
	String text4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toko_henshu);

		// button4登録
		Button button4 = (Button)findViewById(R.id.button4);
		button4.setOnClickListener(this);

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

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.button4:
			Intent intent4 = new Intent(Toko_Henshu.this, Toko_Sentaku.class);
			startActivity(intent4);
			doPost("http://");
		}

	}

	public String doPost(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);

		// リクエストパラメータの設定
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params_id", text1));
		params.add(new BasicNameValuePair("params_data", text2));
		try {
			method.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = client.execute(method);
			int status = response.getStatusLine().getStatusCode();
			return "status:" + status;
		} catch (Exception e) {
			return "Error:" + e.getMessage();
		}
	}

}
