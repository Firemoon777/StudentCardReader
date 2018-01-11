package ru.firemoon777.studentcardreader;

import android.app.Activity;
import android.app.PendingIntent;
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
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NfcAdapter nfcAdapter;
    public View content;

    private TextView validFromTextView;
    private TextView validUntilTextView;
    private TextView metroTimeTextView;
    private TextView groundTimeTextView;
    private TextView boardNumberTextView;
    private TextView typeTextView;
    private TextView passportTextView;
    private TextView nameTextView;
    private TextView stationTextView;
    private TextView updateTextView;
    private TextView debugTextView;
    private TextView entranceTextView;
    private TextView cardTypeTextView;
    private Button showPersonal;
    private boolean showPersonalBool;
    private StudentCardData scd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        content = findViewById(R.id.content);
        validFromTextView = findViewById(R.id.validFromTextView);
        validUntilTextView = findViewById(R.id.validUntilTextView);
        metroTimeTextView = findViewById(R.id.metroTimeTextView);
        groundTimeTextView = findViewById(R.id.groundTimeTextView);
        typeTextView = findViewById(R.id.typeTextView);
        passportTextView = findViewById(R.id.passportTextView);
        nameTextView = findViewById(R.id.nameTextView);
        showPersonal = findViewById(R.id.showPersonal);
        stationTextView = findViewById(R.id.stationTextView);
        updateTextView = findViewById(R.id.updateTextView);
        entranceTextView = findViewById(R.id.entranceTextView);
        cardTypeTextView = findViewById(R.id.cardTypeTextView);
        showPersonal.setOnClickListener(this);

        debugTextView = findViewById(R.id.debugTextView);

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
        if (id == R.id.action_settings) {
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
        cardTypeTextView.setText(scd.getCardType());
        updateTextView.setText(scd.getUpdateTime());
        entranceTextView.setText(scd.getEntrance().toString());
        validFromTextView.setText(scd.getValidFrom());
        validUntilTextView.setText(scd.getValidUntil());
        metroTimeTextView.setText(scd.getMetroTime());
        groundTimeTextView.setText(scd.getGroundTime());
        typeTextView.setText(scd.getType().toString());
        passportTextView.setText(R.string.hidden);
        nameTextView.setText(R.string.hidden);
        stationTextView.setText(scd.getStation());
        debugTextView.setText(scd.getDebug());
        this.showPersonalBool = false;
        this.scd = scd;
        this.showPersonal.setText(R.string.activity_show_personal);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.showPersonal) {
            if(scd != null) {
                if(showPersonalBool) {
                    passportTextView.setText(R.string.hidden);
                    nameTextView.setText(R.string.hidden);
                    this.showPersonalBool = false;
                    this.showPersonal.setText(R.string.activity_show_personal);
                } else {
                    passportTextView.setText(this.scd.getPassport());
                    nameTextView.setText(this.scd.getSurname() + " " + this.scd.getFirstName());
                    this.showPersonalBool = true;
                    this.showPersonal.setText(R.string.activity_dismiss_personal);
                }
            }
        }
    }
}
