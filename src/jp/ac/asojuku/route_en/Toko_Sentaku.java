package jp.ac.asojuku.route_en;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
			Intent intent = new Intent(Toko_Sentaku.this, MainActivity.class);
			startActivity(intent);

		}

	}


}
