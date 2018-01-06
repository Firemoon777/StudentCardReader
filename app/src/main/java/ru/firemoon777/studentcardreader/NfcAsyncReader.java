package ru.firemoon777.studentcardreader;

import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

/**
 * Created by Dragon on 06.01.2018.
 */

public class NfcAsyncReader extends AsyncTask<Tag, Void, StudentCardData> {

    Boolean success;
    Exception exception;

    MainActivity activity;

    public static final byte[] key8A = new byte[]{(byte) 0x26, (byte) 0x97, (byte) 0x3e, (byte) 0xa7, (byte) 0x43, (byte) 0x21};
    public static final byte[] key12B = new byte[]{(byte) 0x71, (byte) 0xF3, (byte) 0xA3, (byte) 0x15, (byte) 0xAD, (byte) 0x26};
    public static final byte[] key13A = new byte[]{(byte) 0xAC, (byte) 0x70, (byte) 0xCA, (byte) 0x32, (byte) 0x7a, (byte) 0x04};
    public static final byte[] key14A = new byte[]{(byte) 0x51, (byte) 0x04, (byte) 0x4e, (byte) 0xfb, (byte) 0x5a, (byte) 0xab};

    public NfcAsyncReader(MainActivity activity) {
        this.activity = activity;
        this.success = true;
        this.exception = null;
    }

    @Override
    protected StudentCardData doInBackground(Tag... tags) {
        Tag tag = tags[0];
        MifareClassic mfc = MifareClassic.get(tag);
        StudentCardData result = new StudentCardData();
        boolean auth;
        try {
            mfc.connect();
            auth = mfc.authenticateSectorWithKeyA(8, key8A);
            if(auth == false)
                return  null;

            int bIndex = mfc.sectorToBlock(8);
            byte[] data = mfc.readBlock(bIndex);
            result.setValidUntil(byteToDateString(data, 10, true));
            data = mfc.readBlock(bIndex + 1);
            result.setValidFrom(byteToDateString(data, 0, true));
            data = mfc.readBlock(bIndex + 2);
            result.setMetroTime(byteToDateString(data, 1, true));

            Log.wtf("Test", "" + result);

            return result;
        } catch (Exception e) {
            this.exception = e;
        }
        return null;
    }

    String byteToRuAsciiString(byte[] data, int offset, int count) {
        String result = "";
        for(int i = offset; i - offset < Math.min(count, data.length - offset); i++) {
            int c = (int)data[i] & 0xFF;
            if(0xC0 <= c && c <  0xE0) {
                result += (char)((c - 0xC0) + 'А');
                continue;
            }
            if(0xE0 <= c) {
                result += (char)((c - 0xE0) + 'а');
                continue;
            }
            result += (char)c;
        }
        return result;
    }

    String byteToDateString(byte[] data, int offset, boolean dateOnly) {
        int year = 2000 + data[offset];
        int month = data[offset + 1];
        int day = data[offset + 2];
        if(dateOnly == false) {
            int hour = 3 + data[offset + 3];
            int mins = data[offset + 4];
            return String.format("%02d.%02d.%04d %02d:%02d", day, month, year, hour, mins);
        }
        return String.format("%02d.%02d.%04d", day, month, year);
    }

    @Override
    protected void onPostExecute(StudentCardData s) {
        super.onPostExecute(s);
        if(s != null) {
            activity.updateCardData(s);
            Snackbar.make(activity.content, "Успех", Snackbar.LENGTH_SHORT).show();
        } else if(this.exception == null) {
            Snackbar.make(activity.content, R.string.nfc_tag_unsupported, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(activity.content, this.exception.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }
}
