package hugbo.bualfur.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by stefanc on 06/04/17.
 */

public class PictureUtils {
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight){

        //Read in the dimensions of the image on disk

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(path, options);


        float srcWidth = options.outWidth;

        float srcHeight = options.outHeight;

        int inSampleSize = 1;

        if(srcHeight > destHeight || srcWidth > destWidth){
            float heighScale = srcHeight/destHeight;

            float widthScale = srcWidth/destWidth;

            inSampleSize = Math.round(heighScale > widthScale ? heighScale : widthScale);

        }

        options = new BitmapFactory.Options();

        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(path,options);
    }


    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();

        activity.getWindowManager().getDefaultDisplay().getSize(size);


        return getScaledBitmap(path, size.x, size.y);
    }
}
