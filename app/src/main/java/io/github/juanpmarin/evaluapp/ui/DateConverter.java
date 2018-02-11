package io.github.juanpmarin.evaluapp.ui;

import android.databinding.BindingConversion;

import java.text.DateFormat;
import java.util.Date;

public class DateConverter {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

    @BindingConversion
    public static String convertDateToDisplayedText(Date date) {
        return DATE_FORMAT.format(date);
    }

}
