package org.uiieditt.visualreminder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.gun0912.tedpicker.ImagePickerActivity;

import org.uiieditt.visualreminder.utility.RunPermissions;

import java.util.ArrayList;

/**
 * This class is main part of the app cycles
 */
public class ListReminder extends AppCompatActivity {

    private static final int INTENT_REQUEST_GET_IMAGES = 81;
    private static final int INTENT_REQUEST_CAMERA = 12;
    private static final int INTENT_REQUEST_EXTERNAL_STORAGE = 13;

    private Intent addReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RunPermissions permission = new RunPermissions(getBaseContext());

        permission.cameraPermission(this, INTENT_REQUEST_CAMERA);
        permission.externalStorageWritePermission(this, INTENT_REQUEST_EXTERNAL_STORAGE);

        if (addReminder == null) {
            addReminder = new Intent(this, ImagePickerActivity.class);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                startActivityForResult(addReminder, INTENT_REQUEST_GET_IMAGES);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);

        if (requestCode == INTENT_REQUEST_GET_IMAGES && resuleCode == Activity.RESULT_OK) {
            ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

            //do something

        }
    }
}
