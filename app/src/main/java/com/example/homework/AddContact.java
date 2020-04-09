package com.example.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.homework.contacts.ContactsListContent;

import java.util.Random;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

            }


    public void AddContact(View view) {
        EditText nameEditText = findViewById(R.id.addName);
        EditText surnameEditText = findViewById(R.id.addSurname);
        EditText dateEditText = findViewById(R.id.addDate);
        EditText phoneEditText = findViewById(R.id.addPhone);
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        Random rand = new Random();
        int rand_int = rand.nextInt(4);
        String rand_string;
        if (rand_int == 1) rand_string ="1";
        else if(rand_int ==2) rand_string= "2";
        else if(rand_int ==3) rand_string= "3";
        else rand_string= "4";
        Intent data = new Intent();
        //date checking
        Log.d("LENGHT", "lenght = " + date.length());
        boolean date_check = false;
        if(date.length() ==10)
        {
            if(date.charAt(2) == 47 && date.charAt(5) == 47)
            {
                char dc1 = date.charAt(0);
                int d1 = Character.getNumericValue(dc1);
                char dc2 = date.charAt(1);
                int d2 = Character.getNumericValue(dc2);
                Log.d("TAG1", "d0 =" +d1 +"d1 =" +d2);
                int dni = 10*d1+d2;
                if((dni)>0 && (dni) <32)
                {
                    char dc3 = date.charAt(3);
                    int d3 = Character.getNumericValue(dc3);
                    char dc4 = date.charAt(4);
                    int d4= Character.getNumericValue(dc4);
                    Log.d("TAG2", "d0 =" +d1 +"d1 =" +d2+ "d3 =" +d3 + "d4 = "+d4);
                    if((10*d3+d4) >0 && (10*d3+d4) <13)
                    {
                        char dc6 = date.charAt(6);
                        int d6 = Character.getNumericValue(dc6);
                        char dc7 = date.charAt(7);
                        int d7= Character.getNumericValue(dc7);
                        char dc8 = date.charAt(8);
                        int d8 = Character.getNumericValue(dc8);
                        char dc9 = date.charAt(9);
                        int d9= Character.getNumericValue(dc9);
                        Log.d("TAG3", "d0 =" +d1 +"d1 =" +d2+ "d3 =" +d3 + "d4 = "+d4 + "d5 =" +d6+ "d7 =" +d7 + "d9 =" +d9 + "d9 =" +d9);
                        if((1000*d6+100*d7+10*d8+d9) > 1900 && (1000*d6+100*d7+10*d8+d9) <2021)
                        {
                            date_check = true;
                        }

                    }
                }
            }
        }


        //phone checking
        int phone_check = 0;
        int lenght = phone.length();
        if(lenght ==9)
        {
            for(int i = 0; i <lenght ; i++)
            {
                if(phone.charAt(i) >= 48 && phone.charAt(i) <=57)
                {
                    phone_check++;
                }
            }
        }


        if(name.isEmpty() || surname.isEmpty() || date.isEmpty() || phone.isEmpty() || phone_check !=9 || date_check == false)
        {
            setResult(RESULT_CANCELED, data);
        }else
        {

            ContactsListContent.Contact cont = new ContactsListContent.Contact("Contact."+ContactsListContent.ITEMS.size()+1,
                    name,surname,date,rand_string,phone);
            data.putExtra("juhu",cont);
            setResult(RESULT_OK,data);

        }

        nameEditText.setText("");
        surnameEditText.setText("");
        dateEditText.setText("");
        phoneEditText.setText("");

        finish();
    }
}
