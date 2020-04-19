package com.example.homework.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactsListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();

    private static final int COUNT = 2;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(int position)
    {
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }

    public static String getName(int position)
    {
        return ITEMS.get(position).name;
    }

    private static Contact createDummyItem(int position) {
        return new Contact(String.valueOf(position), "Jane");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Contact implements Parcelable {
        public final String id;
        public final String name;
        public final String last_name;
        public final String date;
        public final String picPath;
        public final String number;

        public Contact(String id, String name, String last_name, String date, String picPath, String number) {
            this.id = id;
            this.name = name;
            this.last_name = last_name;
            this.picPath = picPath;
            this.date = date;
            this.number = number;
        }
        public Contact(String id, String name)
        {
            this.id = id;
            this.name = name;
            this.last_name = "";
            this.date ="01/01/1900";
            this.picPath="2";
            this.number="000000000";
        }

        protected Contact(Parcel in) {
            id = in.readString();
            name = in.readString();
            last_name = in.readString();
            date = in.readString();
            picPath = in.readString();
            number = in.readString();
        }

        public static final Creator<Contact> CREATOR = new Creator<Contact>() {
            @Override
            public Contact createFromParcel(Parcel in) {
                return new Contact(in);
            }

            @Override
            public Contact[] newArray(int size) {
                return new Contact[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(last_name);
            dest.writeString(date);
            dest.writeString(picPath);
            dest.writeString(number);
        }
    }
}
