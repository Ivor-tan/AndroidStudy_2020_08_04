package com.example.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Book implements Parcelable {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Book(Parcel in) {
        this.name = in.readString();
//        Log.d("tbq", "Book: ==========>name" + in.readString());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        Log.d("tbq", "writeToParcel: ==========>name" + name);
        parcel.writeString(name);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
