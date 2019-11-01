package com.example.apaguide.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.apaguide.R;
import com.example.apaguide.model.OptionFilter;

import java.util.ArrayList;

public class OptionFilterDialog extends DialogFragment {
    public interface OptionFilterDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, OptionFilter optionFilter);
    }
    OptionFilterDialogListener mListener;
    private OptionFilter optionFilter;
    private View view;
    private Spinner spinAuthor;
    private Spinner spinDate;

    // This will not be called.
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try {
            // Instantiate the OptionFilterDialogListener so we can send events to the host
            mListener = (OptionFilterDialogListener) childFragment;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(childFragment.toString()
                    + " must implement OptionFilterDialogListener");
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            // Instantiate the OptionFilterDialogListener so we can send events to the host
//            mListener = (OptionFilterDialogListener) context;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(context.toString()
//                    + " must implement OptionFilterDialogListener");
//        }
//    }

    public void setOnOkButtonClickListerner(OptionFilterDialogListener listerner){
        this.mListener = listerner;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_option_filter, null);
        builder.setView(view);

        optionFilter = new OptionFilter();
        if (getArguments() != null) {
            optionFilter = (OptionFilter)getArguments().getSerializable("option");
        }

        spinAuthor = view.findViewById(R.id.spinAuthor);
        spinDate = view.findViewById(R.id.spinDate);

        Button button = view.findViewById(R.id.btnCancel);  // Cancel
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        button = view.findViewById(R.id.btnOK);     // Apply
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                optionFilter.authorNumber = spinAuthor.getSelectedItemPosition() + 1;
                optionFilter.hasDate = spinDate.getSelectedItemPosition() + 1;
                if(mListener != null){
                    mListener.onDialogPositiveClick(OptionFilterDialog.this, optionFilter);
                }
                dismiss();
            }
        });

        CreateAuthorSpinner();  // create Author spinner
        CreateDateSpinner();    // create Date spinner

        return builder.create();
    }

    private void CreateAuthorSpinner() {
        ArrayList<String> array = new ArrayList<>();
        String[] dataArray = new String[]{"1 author", "2 authors", "3-5 authors", "6+ authors", "Group authors"};

        for (int i = 0; i < dataArray.length; i++) {
            array.add(dataArray[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAuthor.setAdapter(adapter);
        spinAuthor.setSelection(optionFilter.authorNumber - 1);   // select the first one
    }

    private void CreateDateSpinner() {
        ArrayList<String> array = new ArrayList<>();
        String[] dataArray = new String[]{"Year", "No year"};

        for (int i = 0; i < dataArray.length; i++) {
            array.add(dataArray[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDate.setAdapter(adapter);
        spinDate.setSelection(optionFilter.hasDate - 1);   // select the first one
    }
}
