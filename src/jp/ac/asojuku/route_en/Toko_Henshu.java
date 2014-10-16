package jp.ac.asojuku.route_en;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Toko_Henshu extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toko_henshu);

		// button4登録
		Button button4 = (Button)findViewById(R.id.button4);
		button4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.button4:
			Intent intent4 = new Intent(Toko_Henshu.this, Toko_Sentaku.class);
			startActivity(intent4);
		}

	}

}
