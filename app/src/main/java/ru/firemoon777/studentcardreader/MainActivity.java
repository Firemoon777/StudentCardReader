package ru.firemoon777.studentcardreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NfcAdapter nfcAdapter;
    public View content;
    public boolean showPersonalBool = false;
    public boolean showDebug = true;
    private ListView listView;
    private StudentCardData scd;
    private StudentCardDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        content = findViewById(R.id.content);
        listView = findViewById(R.id.listView);

        adapter = new StudentCardDataAdapter(this, this.scd);
        listView.setAdapter(adapter);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null) {
            Snackbar.make(content, R.string.nfc_unsupported, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null).show();
            return;
        }

        if(nfcAdapter.isEnabled() == false) {
            Snackbar.make(content, R.string.nfc_disabled, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null).show();
            return;
        }

        Snackbar.make(content, R.string.nfc_ready, Snackbar.LENGTH_SHORT).show();
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
        if (id == R.id.action_debug) {
            item.setChecked(!item.isChecked());
            showDebug = item.isChecked();
            adapter.notifyDataSetChanged();
            return true;
        }

        if (id == R.id.action_show_personal) {
            item.setChecked(!item.isChecked());
            showPersonalBool = item.isChecked();
            adapter.notifyDataSetChanged();
            return true;
        }

        if(id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.about).setTitle(R.string.action_about).setNeutralButton(R.string.about_ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{ new String[] {
                "android.nfc.tech.NfcA",
                "android.nfc.tech.MifareClassic"
        }};

        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_TECH_DISCOVERED);

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    private static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupForegroundDispatch(this, nfcAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopForegroundDispatch(this, nfcAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            Snackbar.make(content, R.string.nfc_tag_found, Snackbar.LENGTH_SHORT).show();
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NfcAsyncReader asyncReader = new NfcAsyncReader(this);
            asyncReader.execute(tag);
        }
    }

    public void updateCardData(StudentCardData scd) {
        adapter.clear();
        adapter.addAll(StudentCardParser.parseStudentCardData(this, scd));
        this.showPersonalBool = false;
        this.scd = scd;
    }

    @Override
    public void onClick(View view) {

    }
}
