package com.example.homework;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework.contacts.ContactsListContent;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactInfoFragment newInstance(String param1, String param2) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            ContactsListContent.Contact receivedContact = intent.getParcelableExtra(MainActivity.contactExtra);
            if (receivedContact != null) {
                displayContact(receivedContact);
            }
        }
    }


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }

    public void displayContact(ContactsListContent.Contact contact)
    {
        FragmentActivity activity = getActivity();

        TextView contactInfoName = activity.findViewById(R.id.contactInfoName);
        TextView contactNumber = activity.findViewById(R.id.contactNumber);
        TextView contactDate = activity.findViewById(R.id.contactDate);
        ImageView contactInfoImage = activity.findViewById(R.id.contactInfoImage);

        contactInfoName.setText(contact.name);
        contactNumber.setText(contact.number);
        contactDate.setText(contact.date);
        if(contact.picPath != null && !contact.picPath.isEmpty())
        {
            Drawable conctactDrawable;
                if (contact.picPath.equals("1")) conctactDrawable = activity.getResources().getDrawable(R.drawable.lego001);
                else if(contact.picPath.equals("2")) conctactDrawable = activity.getResources().getDrawable(R.drawable.lego002);
                else if(contact.picPath.equals("3")) conctactDrawable = activity.getResources().getDrawable(R.drawable.lego003);
                else conctactDrawable = activity.getResources().getDrawable(R.drawable.lego004);

                contactInfoImage.setImageDrawable(conctactDrawable);
            }
            else {
                contactInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.lego004));
            }

    }


}
