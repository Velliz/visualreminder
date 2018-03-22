package org.uiieditt.visualreminder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.gun0912.tedpicker.ImagePickerActivity;

import org.uiieditt.visualreminder.adapter.VisualAdapter;
import org.uiieditt.visualreminder.objects.VisualObjects;
import org.uiieditt.visualreminder.utility.RunPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is main part of the app cycles
 */
public class ListReminder extends AppCompatActivity {

    private static final int INTENT_REQUEST_GET_IMAGES = 81;
    private static final int INTENT_REQUEST_CAMERA = 12;
    private static final int INTENT_REQUEST_EXTERNAL_STORAGE = 13;

    private Intent addReminder;

    private VisualAdapter visualData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (visualData == null) {

            //todo: hardcoded for data sample
            VisualObjects firstObj = new VisualObjects();
            firstObj.setId(1);
            firstObj.setTitle("Cuci Selimut");
            firstObj.setItemCount(2);
            firstObj.setDateCreated("1 Feb 2018");
            firstObj.setStatus("SELESAI");

            VisualObjects secondObj = new VisualObjects();
            firstObj.setId(2);
            firstObj.setTitle("Cuci Baju");
            firstObj.setItemCount(31);
            firstObj.setDateCreated("3 Feb 2018");
            firstObj.setStatus("SELESAI");

            VisualObjects thridObj = new VisualObjects();
            firstObj.setId(3);
            firstObj.setTitle("Cuci Celana");
            firstObj.setItemCount(5);
            firstObj.setDateCreated("6 Feb 2018");
            firstObj.setStatus("PENDING");

            List<VisualObjects> visualArray = new ArrayList<>();
            visualArray.add(firstObj);
            visualArray.add(secondObj);
            visualArray.add(thridObj);

            visualData = new VisualAdapter(visualArray);
        }

        RecyclerView recyclerView = findViewById(R.id.visual_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(visualData);

        RunPermissions permission = new RunPermissions(getBaseContext());

        permission.cameraPermission(this, INTENT_REQUEST_CAMERA);
        permission.externalStorageWritePermission(this, INTENT_REQUEST_EXTERNAL_STORAGE);

        if (addReminder == null) {
            addReminder = new Intent(this, ImagePickerActivity.class);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
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
    protected void onStart() {
        super.onStart();
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
