package jp.ac.asojuku.route_en;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main);

		ImageButton btnToko = (ImageButton)findViewById(R.id.btnToko);
		btnToko.setOnClickListener(this);
		ImageButton btnKensaku = (ImageButton)findViewById(R.id.btnKensaku);
		btnKensaku.setOnClickListener(this);
		ImageButton btnKiroku = (ImageButton)findViewById(R.id.btnKiroku);
		btnKiroku.setOnClickListener(this);
		ImageButton btnMypage = (ImageButton)findViewById(R.id.btnMypage);
		btnMypage.setOnClickListener(this);
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
		case R.id.btnToko:
			Intent intent1 = new Intent(MainMenu.this, Toko_Sentaku.class);
			startActivity(intent1);
			break;

		case R.id.btnKensaku:
			Intent intent2 = new Intent(MainMenu.this, Main_Search.class);
			startActivity(intent2);
			break;

		case R.id.btnKiroku:
			Intent intent3 = new Intent(MainMenu.this, MainActivity.class);
			startActivity(intent3);
			break;

		}
	}

}
