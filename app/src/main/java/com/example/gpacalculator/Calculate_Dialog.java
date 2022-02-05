package com.example.gpacalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Calculate_Dialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_result, container, false);
        TextView textView = view.findViewById(R.id.result);
        assert getArguments() != null;
        String string = getArguments().getString("result") + textView.getText().toString();
        textView.setText(string);

        return view;
    }
}
