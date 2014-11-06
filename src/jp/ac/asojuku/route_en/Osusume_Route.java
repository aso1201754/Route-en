package jp.ac.asojuku.route_en;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Osusume_Route extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.osusume_route);



		//配列準備
		String[] o_route = {"おすすめルート１","おすすめルート２"};

		//ListViewの登録
		ListView o_lv1 = (ListView) findViewById(R.id.listView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, o_route);
		o_lv1.setAdapter(adapter);



	}




}
