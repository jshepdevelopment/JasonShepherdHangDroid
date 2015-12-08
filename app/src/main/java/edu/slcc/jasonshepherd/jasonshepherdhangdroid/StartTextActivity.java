package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartTextActivity extends AppCompatActivity {

    private EditText editText;
    private SharedPreferences preferences;
    private TextView textView;
    private String wordToGuess;
    private String friendPhone;
    private String texterPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_text);
        // get text from shared preferences
        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);
        //read preferences to get friends phone if called from contacts activity
        friendPhone = getIntent().getStringExtra("Phone"); //defaults if data does not come with intent

        Log.d("MYLOG", "Friend: " + friendPhone);

        getTextFromPref();
        //get phone from intent if it was called from contacts
    }

    public void setTextMessage (View view) {
        getTextFromPref();
    }

    public void getTextFromPref() {
        // get text message from shared preferences and read preferences
        // this is the same as it is in the basic app
        wordToGuess = preferences.getString("TextedWord", "NO WORD"); // NOW WORD if preferences not found
        texterPhone = preferences.getString("TexterPhone", "NO PHONE"); //NO PHONE if preferences are not found
        textView = (TextView) findViewById(R.id.editTextWord);

        //set up boolean values
        boolean phone = true;
        boolean word = true;
        boolean friend = true;
        if (wordToGuess == "NO WORD") word = false;
        if (texterPhone == "NO PHONE") phone = false;
        if (friendPhone == null) friend = false;

        // word but no friend selected
        if (!friend && word) {
            textView.setText(wordToGuess);
            wordToGuess = "";
            texterPhone = "";
            return;
        }

        // if word and phone are true then check friend phone
        if (word && phone) {
            if (friendPhone.equals(texterPhone)) {
                textView.setText(wordToGuess);
                wordToGuess = "";
                texterPhone = "";
            } else {
                Toast.makeText(this, "No Text from Selected Friend", Toast.LENGTH_LONG).show();
            }
            return;
        }
        if (!word) { // if there is no word, then there was no text
            Toast.makeText(this, "No Text Received", Toast.LENGTH_LONG).show();
        }
    }

    //play button
    public void startTextGame(View view) {

        // added because I don't want to create duplicate GameActivity classes
        boolean multiPlayerFlag = true; // a flag to send to the game activity

        // get the word and cast it to a String
        String textWord = textView.getText().toString();
        if (textWord.length() > 0) {
            //clear field for next word
            textView.setText("");
            //clear word from shared preferences
            preferences.edit().remove("TextedWord").commit();
            Log.d("JSLOG", "Removed Texted Word: " + textView);

            // create an explicit intent then send word and multiplayer flag data using putExtra
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("GUESS_WORD", wordToGuess);
            //multiPlayerFlag is used in startTextGame, because I'm not a fan of duplicating classes
            intent.putExtra("MULTIPLAYER_FLAG", multiPlayerFlag);
        } else {
          Toast.makeText(this, "There is no word. Try Get Word again.", Toast.LENGTH_LONG).show();
        }
    }
}