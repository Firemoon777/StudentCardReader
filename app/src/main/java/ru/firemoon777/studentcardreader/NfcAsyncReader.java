package ru.firemoon777.studentcardreader;

import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Dragon on 06.01.2018.
 */

public class NfcAsyncReader extends AsyncTask<Tag, Void, String> {

    private View content;

    public NfcAsyncReader(View content) {
        this.content = content;
    }

    @Override
    protected String doInBackground(Tag... tags) {
        Tag tag = tags[0];
        MifareClassic mfc = MifareClassic.get(tag);
        try {
            mfc.connect();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Snackbar.make(content, "Успех", Snackbar.LENGTH_SHORT).show();
    }
}
