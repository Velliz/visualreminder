package org.uiieditt.visualreminder.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class VisualObjects implements Parcelable {

    private String title, status, dateCreated;

    private int id, itemCount;

    public VisualObjects() {
    }

    public VisualObjects(Parcel in) {
        title = in.readString();
        status = in.readString();
        dateCreated = in.readString();
        id = in.readInt();
        itemCount = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(status);
        parcel.writeString(dateCreated);
    }

    public static final Parcelable.Creator<VisualObjects> CREATOR = new Parcelable.Creator<VisualObjects>() {
        @Override
        public VisualObjects createFromParcel(Parcel in) {
            return new VisualObjects(in);
        }

        @Override
        public VisualObjects[] newArray(int size) {
            return new VisualObjects[size];
        }
    };
}
