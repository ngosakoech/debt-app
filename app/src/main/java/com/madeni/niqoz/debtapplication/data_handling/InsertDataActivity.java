package com.madeni.niqoz.debtapplication.data_handling;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.madeni.niqoz.debtapplication.R;

import static android.provider.ContactsContract.Contacts;


public class InsertDataActivity extends AppCompatActivity {
    Toolbar toolbar;
    DatabaseHelper helper;

// [--< XML Elements >--]
    RadioGroup rdgroup;
    EditText name;
    EditText residence;
    EditText phone_no;
    EditText loan_amount;
    ImageButton add_contact;

// [--< Contact Function Variables >--]
    private String contactID;
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private static final String TAG = InsertDataActivity.class.getSimpleName();
    private Uri uriContact;

// [--< Database Function Variables >--]
    String name_string;
    String phoneNumberString;
    String residence_string;
    int amount_int;
    int paid_amount;
    int remaining_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_debt);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},1);

        //Replaces action bar with toolbar
        toolbar = (Toolbar)findViewById(R.id.data_toolbar);
        setSupportActionBar(toolbar);

        //Declarations
        name= (EditText)findViewById(R.id. txtName);
        residence = (EditText)findViewById(R.id.txtResidence);
        phone_no = (EditText)findViewById(R.id.txtNumber);
        loan_amount = (EditText)findViewById(R.id.txtAmount);
        add_contact = (ImageButton)findViewById(R.id.btn_Add_Contact);
        rdgroup = (RadioGroup)findViewById(R.id.rdGroup1);

        //initialization of the DB helper
        helper = new DatabaseHelper(this);

        //Click events handler
        toolbar.findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseInsertion();
            }
        });

        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                startActivityForResult(intent,REQUEST_CODE_PICK_CONTACTS);
            }
        });



    }//OnCreate End

    // <=============================================< CONTACT FUNCTIONS >===========================================>
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Log.d(TAG, "Response: " + data.toString());
            uriContact = data.getData();

            retrieveContactName();
            retrieveContactNumber();

        }
    }

    private void retrieveContactNumber() {

        String contactNumber = null;

        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact, new String[]{ContactsContract.Contacts._ID}, null, null, null);

        assert cursorID != null;
        if (cursorID.moveToFirst()) {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();

        Log.d(TAG, "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID, null, null);


        assert cursorPhone != null;
        while (cursorPhone.moveToNext()) {
           int type = cursorPhone.getInt(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            switch (type){
                case ContactsContract.CommonDataKinds.Phone.TYPE_MAIN:
                    contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone_no.setText(contactNumber);
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                    contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone_no.setText(contactNumber);
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                    contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone_no.setText(contactNumber);
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                    contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone_no.setText(contactNumber);
                    break;

                default:
                break;
            }
        }

        cursorPhone.close();

        Log.d(TAG, "Contact Phone Number: " + contactNumber);
    }

    private void retrieveContactName() {
        String contactName = null;

        // querying contact data store
        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

        assert cursor != null;
        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            name.setText(contactName);
        }
        cursor.close();

        Log.d(TAG, "Contact Name: " + contactName);
    }
    // <=============================================</CONTACT FUNCTIONS >===========================================>

    // <=============================================< DATABASE FUNCTIONS >===========================================>
    public void databaseInsertion() {
        name_string = name.getText().toString().trim();
        phoneNumberString = phone_no.getText().toString().trim();
        residence_string = residence.getText().toString().trim();
        amount_int = Integer.parseInt(loan_amount.getText().toString().trim());

        paid_amount = 0;
        remaining_amount = amount_int - paid_amount;

        if (rdgroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Select one of the options", Toast.LENGTH_LONG).show();
        }
        else if (name_string.matches("")){
            name.setError("Please fill in the blanks");
        }
        else if (phoneNumberString.matches("")){
            phone_no.setError("Please fill in the blanks");
        }
        else if (amount_int == 0 ){
            loan_amount.setError("Please fill in the blanks");
        }
        else if (rdgroup.getCheckedRadioButtonId() == R.id.rdDebtor) {
            boolean isInserted = helper.InsertDataDebt(name_string, phoneNumberString, amount_int, residence_string,paid_amount, remaining_amount);
            if (!isInserted) {
                Toast.makeText(getApplicationContext(), "Data could not be inserted", Toast.LENGTH_SHORT).show();
            } else {
                //Intent Ret = new Intent(getApplicationContext(),CreditorClass.class);
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                //startActivity(Ret);
                //finish();
            }
        }
        else if (rdgroup.getCheckedRadioButtonId() == R.id.rdCreditor) {
            Boolean isInserted = helper.InsertDataCred(name_string, phoneNumberString, amount_int, residence_string,paid_amount, remaining_amount);
            if (!isInserted) {
                Toast.makeText(getApplicationContext(), "Data could not be inserted", Toast.LENGTH_SHORT).show();
            } else {
                //Intent Ret = new Intent(getApplicationContext(),CreditorClass.class);
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                //startActivity(Ret);
                //finish();
            }
        }
    }
    // <=============================================< DATABASE FUNCTIONS >===========================================>


}//end of Main Class
