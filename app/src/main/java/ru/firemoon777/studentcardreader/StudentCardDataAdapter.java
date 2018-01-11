package ru.firemoon777.studentcardreader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dragon on 11.01.2018.
 */

public class StudentCardDataAdapter extends ArrayAdapter<StudentCardDataRow> {

    MainActivity mainActivity;
    List<StudentCardDataRow> data;

    public StudentCardDataAdapter(MainActivity mainActivity, StudentCardData scd) {
        this(mainActivity.getApplicationContext(), scd);
        this.mainActivity = mainActivity;
    }

    public StudentCardDataAdapter(Context context, StudentCardData scd) {
        this(context, StudentCardParser.parseStudentCardData(context, scd));
    }

    public StudentCardDataAdapter(Context context, List<StudentCardDataRow> data) {
        super(context, android.R.layout.simple_list_item_2, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentCardDataRow d = data.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_row_main, null);
            convertView.setTag(convertView);
        }
        ((TextView) convertView.findViewById(R.id.name))
                .setText(d.name);
        TextView releaseTextView = convertView.findViewById(R.id.releaseValue);
        if(d.releaseValue == null) {
            releaseTextView.setVisibility(View.GONE);
            releaseTextView.setBackgroundColor(convertView.getResources().getColor(android.R.color.darker_gray));
        } else {
            releaseTextView.setBackgroundColor(convertView.getResources().getColor(android.R.color.white));
            releaseTextView.setVisibility(View.VISIBLE);
            if (d.personal == true && mainActivity.showPersonalBool == false) {
                releaseTextView.setText("(Скрыто)");
            } else {
                releaseTextView.setText(d.releaseValue);
            }
        }
        TextView debugTextView = convertView.findViewById(R.id.debugValue);
        if(d.debugValue == null || mainActivity.showDebug == false) {
            debugTextView.setVisibility(View.GONE);
        } else {
            debugTextView.setVisibility(View.VISIBLE);
            debugTextView.setText(d.debugValue);
        }
        return convertView;
    }
}
