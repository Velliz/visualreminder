package org.uiieditt.visualreminder.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * This class is used to check app permission on runtime
 */
public class RunPermissions {

    private Context context;

    public RunPermissions(Context contect) {
        this.context = contect;
    }

    /**
     * This method is used to check if app has granted to camera or popping up access request
     * if the app don't have access
     *
     * @param activity
     * @param requestCode
     *
     * @return bool
     */
    public boolean cameraPermission(Activity activity, int requestCode) {
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.CAMERA
            }, requestCode);
        } else {
            return true;
        }
        return false;
    }

    /**
     * This method is used to check if app has granted to write external storage or popping up access request
     * if the app don't have access
     *
     * @param activity
     * @param requestCode
     *
     * @return bool
     */
    public boolean externalStorageWritePermission(Activity activity, int requestCode) {
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, requestCode);
        } else {
            return true;
        }
        return false;
    }

}
