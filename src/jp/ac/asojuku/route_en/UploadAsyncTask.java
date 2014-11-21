package jp.ac.asojuku.route_en;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class UploadAsyncTask extends AsyncTask<Bitmap, Integer, Integer>{
	ProgressDialog dialog;
	Context context;

	//entity初期化（暫定）
	@SuppressWarnings("deprecation")
	//MultipartEntity entity = new MultipartEntity();
	MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	//private Activity Toko_Henshu;
	//String resp;
	public UploadAsyncTask(Context context)
	{
		this.context = context;
	}

	  /* --------------------- POSTパラメータ --------------------- */


	  // 追加
	  public void addPostParam( String post_name, String post_value )
	  {
	    try {
			//entity.addPart( post_name, new StringBody(post_value) );
	    	entity.addPart( post_name, new StringBody(post_value, Charset.forName("UTF-8")) );
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	  }

	@Override
	protected Integer doInBackground(Bitmap... params)
	{
		Bitmap picture1 = params[0];
		Bitmap picture2 = params[1];
		Bitmap picture3 = params[2];
		String resp = null;
		String postUrl = "http://toenp.php.xdomain.jp/Contribute.php"; //(URL)
		ByteArrayBody bab1 = PictureUtil.toByteArrayBody(picture1);
		ByteArrayBody bab2 = PictureUtil.toByteArrayBody(picture2);
		ByteArrayBody bab3 = PictureUtil.toByteArrayBody(picture3);
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(postUrl);
		//@SuppressWarnings("deprecation")
		//MultipartEntity entity = new MultipartEntity();
		try
		{
			entity.addPart("userfile1", bab1);
			entity.addPart("userfile2", bab2);
			entity.addPart("userfile3", bab3);
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK){
				ByteArrayOutputStream oStream = new ByteArrayOutputStream();
				response.getEntity().writeTo(oStream);
				resp = oStream.toString();
			} else {
				Log.v("ERR","response status:" + String.valueOf(status));
			}
		} catch(IOException e) {
			Log.v("ERR","msg:" + e.getMessage());
		}
		return 0;
	}

	@Override
	protected void onPostExecute(Integer result) {
		if(dialog != null){
			dialog.dismiss();
		}
		//TextView tv = (TextView)this.Toko_Henshu.findViewById(R.id.tv1);
		//tv.setText(resp);
	}

	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(context);
		dialog.setTitle("Please wait");
		dialog.setMessage("Uploading...");
		dialog.show();

	}

}
