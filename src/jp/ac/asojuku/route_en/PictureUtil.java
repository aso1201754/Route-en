package jp.ac.asojuku.route_en;

import java.io.ByteArrayOutputStream;

import org.apache.http.entity.mime.content.ByteArrayBody;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;

public class PictureUtil {
	 private final static int QUALITY = 100;
	  public static Bitmap resize(Bitmap picture, int targetWidth, int targetHeight)
	  {
	    if (picture == null || targetWidth < 0 || targetHeight < 0)
	    {
	       return null;
	    }
	    int pictureWidth = picture.getWidth();
	    int pictureHeight = picture.getHeight();
	    float scale = Math.min((float) targetWidth / pictureWidth, (float) targetHeight / pictureHeight);
	    Matrix matrix = new Matrix();
	    matrix.postScale(scale, scale);
	    return Bitmap.createBitmap(picture, 0, 0, pictureWidth, pictureHeight, matrix, true);
	  }

	  public static ByteArrayBody toByteArrayBody(Bitmap picture)
	  {
	    if (picture == null)
	    {
	      return null;
	    }
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    picture.compress(CompressFormat.JPEG, QUALITY, bos);
	    byte[] data = bos.toByteArray();
	    return new ByteArrayBody(data, System.currentTimeMillis() + ".jpg");
	  }

}
