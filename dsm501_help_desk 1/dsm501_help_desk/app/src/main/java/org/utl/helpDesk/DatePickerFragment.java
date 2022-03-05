package org.utl.helpDesk;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText txtDate;

    public DatePickerFragment(EditText txtDate) {
        this.txtDate = txtDate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int mont, int day) {
        final Calendar calendar = Calendar.getInstance();
        final String format = "yyyy-MM-dd";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, mont);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
