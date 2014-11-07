package jp.ac.asojuku.route_en;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class UploadAsyncTask extends AsyncTask<Bitmap, Integer, Integer>{
	ProgressDialog dialog;
	Context context;
	public UploadAsyncTask(Context context)
	{
		this.context = context;
	}

	@Override
	protected Integer doInBackground(Bitmap... params)
	{
		Bitmap picture = params[0];
		String resp = null;
		String postUrl = "http://xxxxxxxxxx."; //(URL)
		ByteArrayBody bab = PictureUtil.toByteArrayBody(picture);
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(postUrl);
		MultipartEntity entity = new MultipartEntity();
		try
		{
			entity.addPart("userfile", bab);
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
	}

	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(context);
		dialog.setTitle("Please wait");
		dialog.setMessage("Uploading...");
		dialog.show();

	}

}
