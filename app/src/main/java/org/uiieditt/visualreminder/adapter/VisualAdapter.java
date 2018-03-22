package org.uiieditt.visualreminder.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.uiieditt.visualreminder.R;
import org.uiieditt.visualreminder.objects.VisualObjects;

import java.util.List;

/**
 * This is a list view reminder adapter
 */
public class VisualAdapter extends RecyclerView.Adapter<VisualHolder> {

    private List<VisualObjects> visualList;

    private Resources res;

    public VisualAdapter(List<VisualObjects> visualList) {
        this.visualList = visualList;
    }

    public void setList(List<VisualObjects> visualList) {
        this.visualList = visualList;
    }

    @NonNull
    @Override
    public VisualHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        res = parent.getResources();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_list_reminder, parent, false
        );
        return new VisualHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VisualHolder holder, int position) {
        VisualObjects object = visualList.get(position);

        String qty = String.format(res.getString(R.string.cuci_jumlah), object.getItemCount());

        holder.title.setText(object.getTitle());
        holder.dateCreated.setText(object.getDateCreated());
        holder.itemCount.setText(qty);
        holder.status.setText(object.getStatus());

        Log.d("VAL", object.getTitle());
        Log.d("VAL", object.getDateCreated());
        Log.d("VAL", qty);
        Log.d("VAL", object.getStatus());
    }

    @Override
    public int getItemCount() {
        return visualList.size();
    }
}
