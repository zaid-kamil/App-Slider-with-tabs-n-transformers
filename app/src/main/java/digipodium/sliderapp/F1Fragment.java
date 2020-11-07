package digipodium.sliderapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

import digipodium.sliderapp.databinding.FragmentF1Binding;


public class F1Fragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private digipodium.sliderapp.databinding.FragmentF1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentF1Binding.bind(view);
        binding.tvSelectDate.setOnClickListener(view1 -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(
                    getActivity(),
                    this,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int yr, int month, int day) {
        binding.tvSelectDate.setText(yr + "/" + (month + 1)+"/" + day);
    }
}