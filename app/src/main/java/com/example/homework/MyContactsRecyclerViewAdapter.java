package com.example.homework;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework.ContactsFragment.OnListFragmentInteractionListener;
import com.example.homework.contacts.ContactsListContent.Contact;

import java.util.List;


public class MyContactsRecyclerViewAdapter extends RecyclerView.Adapter<MyContactsRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyContactsRecyclerViewAdapter(List<Contact> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Contact con = mValues.get(position);
        holder.mItem = con;
        holder.mContentView.setText(con.name);

        final String pickPath = con.picPath;
        Context context = holder.mView.getContext();
        if(pickPath !=null && !pickPath.isEmpty())
        {
                Drawable conDrawable;
                switch (pickPath)
                {
                    case "1":
                        conDrawable = context.getResources().getDrawable(R.drawable.lego001);
                    break;
                    case "2":
                        conDrawable = context.getResources().getDrawable(R.drawable.lego002);
                    break;
                    case "3":
                        conDrawable = context.getResources().getDrawable(R.drawable.lego003);
                        break;
                    case "4":
                        conDrawable = context.getResources().getDrawable(R.drawable.lego004);
                        break;
                    default:
                        conDrawable = context.getResources().getDrawable(R.drawable.lego004);
                }
                holder.mConImageViev.setImageDrawable(conDrawable);

        }else
            {
            holder.mConImageViev.setImageDrawable(context.getResources().getDrawable(R.drawable.lego004));
        }

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(null !=mListener)
                {
                    mListener.onListFragmentClickInteraction(holder.mItem,position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });
        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null !=mListener)
                {
                    mListener.onListDeleteButtonClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mConImageViev;
        public final ImageButton mImageButton;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mConImageViev = view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImageButton = view.findViewById(R.id.imageButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
