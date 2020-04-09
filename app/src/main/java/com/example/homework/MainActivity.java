package com.example.homework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.homework.contacts.ContactsListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class MainActivity extends AppCompatActivity implements ContactsFragment.OnListFragmentInteractionListener, DeleteDialog.OnDeleteDialogInteractionListener, CallDialog.OnCallDialogInteractionListener {

    public static final String contactExtra = "contactExtra";
    private int currentItemPosition = -1;
    public static String Cname = "";
    public static String Csurname="";
    public static String Cdate="";
    public static String Cphone="";
    public static String CpicPath="2";
    public static final int BUTTON_REG =1;
    public static int dialog_int =0;
    public static ContactsListContent.Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),AddContact.class);
                startActivityForResult(intent,BUTTON_REG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            View w = findViewById(R.id.contactsFragment);

            contact = (ContactsListContent.Contact) data.getExtras().getParcelable("juhu");
            ContactsListContent.addItem(contact);
            ((ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.contactsFragment)).notifyDataChange();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow( w.getWindowToken(),0);

            Toast.makeText(getApplicationContext(),getText(R.string.back_message), Toast.LENGTH_SHORT).show();
           // }
            //else
            //{
            //    Toast.makeText(getApplicationContext(),getText(R.string.LOL1), Toast.LENGTH_SHORT).show();
            //}
        }else
        {
            Toast.makeText(getApplicationContext(),getText(R.string.LOL2), Toast.LENGTH_SHORT).show();
        }


    }


    private void startSecondActivity(ContactsListContent.Contact contact, int position)
    {
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(contactExtra, contact);
        startActivity(intent);
    }

    @Override
    public void onListFragmentClickInteraction(ContactsListContent.Contact contact, int position) {
        Toast.makeText(this, getString(R.string.item_selected_msg), Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            displayContactInFragment(contact);
        }
        else
        {
            startSecondActivity(contact,position);
        }

    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        dialog_int=2;
    showCallDialog();
    currentItemPosition = position;
    }

    @Override
    public void onListDeleteButtonClick(int position) {
        deleteContact(position);
    }

    private void displayContactInFragment (ContactsListContent.Contact contact)
    {
        ContactInfoFragment contactsListContent= (ContactInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment);
        if(contactsListContent !=null)
        {
            contactsListContent.displayContact(contact);
        }
    }

    private void showDeleteDialog() {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    private void showCallDialog()
    {
        CallDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));
    }

    public void deleteContact(int position)
    {
        dialog_int=1;
        showDeleteDialog();
        currentItemPosition=position;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(dialog_int ==1)
        {
            if(currentItemPosition != -1 && currentItemPosition < ContactsListContent.ITEMS.size())
            {
                ContactsListContent.removeItem(currentItemPosition);
                ((ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.contactsFragment)).notifyDataChange();
            }

        }else if (dialog_int == 2)
        {
            Toast.makeText(this,getString(R.string.almost), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        if(dialog_int ==1)
        {
            Toast.makeText(this,getString(R.string.deleted_no), Toast.LENGTH_SHORT).show();

        }else if(dialog_int==2)
        {
            Toast.makeText(this,getString(R.string.call_declain), Toast.LENGTH_SHORT).show();
        }
    }
}
