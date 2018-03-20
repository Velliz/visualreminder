package org.uiieditt.visualreminder.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import org.uiieditt.visualreminder.DetailReminder;
import org.uiieditt.visualreminder.R;
import org.uiieditt.visualreminder.objects.VisualObjects;

/**
 * A list item action groups
 */
public class VisualHolder implements View.OnClickListener {

    private static String IDENTIFIER = "ID";

    public TextView dateCreated, title, itemCount, status;

    public VisualObjects objects;

    public VisualHolder(View view) {

        /*
        dateCreated = (TextView) view.findViewById(R.id.date_created);
        title = (TextView) view.findViewById(R.id.title);
        itemCount = (TextView) view.findViewById(R.id.item_count);
        status = (TextView) view.findViewById(R.id.status);
        */

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailReminder.class);

        Activity act = (Activity) view.getContext();
        intent.putExtra(IDENTIFIER, objects);

        ActivityCompat.startActivity(act, intent, null);
    }

}
