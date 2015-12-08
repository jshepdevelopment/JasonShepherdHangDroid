package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.telephony.SmsMessage.createFromPdu;

public class IncomingSms extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                Log.d("JSLOG", "Bundle: " + bundle);
                // get pdu from the bundle
                final Object[] pdus = (Object[]) bundle.get("pdus");
                // get the format of the bundle
                String format = bundle.getString("format");

                // loop through pdus and store phone number along with message
                for (int i = 0; i < pdus.length; i++) {

                    // build an SMS message based from pdus
                    SmsMessage currentMessage = createFromPdu((byte[]) pdus[i], format);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    // assign the message body to a string
                    String message = currentMessage.getDisplayMessageBody();
                    Log.d("JSLOG", "phone: " + phoneNumber + "; message: " + message);

                    //Show a toast alert
                    Toast.makeText(context, "Text received from " + phoneNumber, Toast.LENGTH_LONG).show();

                    //Set up to store preferences * add context because getSharedPreferences() needs
                    SharedPreferences preferences = context.getSharedPreferences("TEXT_MSGS", Context.MODE_PRIVATE);

                    //start the preference editor
                    SharedPreferences.Editor editor= preferences.edit();

                    // use putString() to add the message
                    Log.d("JSLOG", "TextedWord: " + message);
                    editor.putString("TextedWord", message);

                    //saves the buffer buffer
                    editor.commit();
                }

            }
        } catch (Exception e) { // catch exceptions when using broadcast receivers!
            Log.e("JSLOG", "Exception smsReceiver" + e);
        }
    }
}
