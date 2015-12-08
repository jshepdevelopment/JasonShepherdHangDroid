package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsActivity extends ListActivity {

    // declares a ListView and Cursor attributes for this class
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // construct the cursor
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        // startManagingCursor is deprecated, but we'll use it here for demonstration purposes
        startManagingCursor(cursor); // use CursorLoader for asynchronous loading instead

        final String[] Texter = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        // item is the combined layouts of text1 and text2
        int[] item = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter listadapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, Texter, item, 0); //

        setListAdapter(listadapter);

        listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //add a listener to listview and respond to click by calling the TextActivity and send it to
        // the selected phone number
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectPhone = ((TextView)
                        (listView.findViewById(android.R.id.text2))).getText().toString();
                Log.d("JSLOG", "onClick: " + position + "/" + id + "/" + selectPhone);

                //create the intent
                Intent intent = new Intent(ContactsActivity.this, StartTextActivity.class);
                //send the phone with the intent
                intent.putExtra("Phone", selectPhone);
                //start the activity
                startActivity(intent);
            }
        });
    }
}


