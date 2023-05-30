package com.example.battlebots;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class DialogBox extends DialogFragment {

    private EditText editarmor;
    private EditText editpower;
    private EditText editscan;
    private EditText editname;
    private ItemViewModel viewModel;


    public DialogBox() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(requireActivity());
        View myView = inflater.inflate(R.layout.fragment_dialog_box, null);

        ItemViewModel viewModel = new ViewModelProvider(getActivity()).get(ItemViewModel.class);        //sets up viewmodel

        editarmor = (EditText) myView.findViewById(R.id.ArmorField);        //links variables with edit text fields
        editpower = (EditText) myView.findViewById(R.id.PowerField);
        editscan = (EditText) myView.findViewById(R.id.ScanField);
        editname = (EditText) myView.findViewById(R.id.NameField);

        editarmor.addTextChangedListener(new TextWatcher(){         //checks values are within bounds before allowing ok button to be pressed
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    Integer Armor = Integer.parseInt(editarmor.getText().toString());    //gets dialog box values
                    Integer Power = Integer.parseInt(editpower.getText().toString());
                    Integer Scan = Integer.parseInt(editscan.getText().toString());
                    String Name = editname.getText().toString();
                    AlertDialog dialog = (AlertDialog) getDialog();

                    if (Armor > 5 || Power > 5 || Scan > 5){
                        Toast.makeText(getActivity(), "Please ensure all values are below 5", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Armor < 1 || Power < 1 || Scan < 1) {
                        Toast.makeText(getActivity(), "Please ensure all values are greater than 1", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Armor + Power + Scan > 8) {
                        Toast.makeText(getActivity(), "A maximum of 5 points overall can be added", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Name == null || Name == "") {
                        Toast.makeText(getActivity(), "The name can't be blank", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }
                } catch (Exception e) {

                }
            }
        });

        editpower.addTextChangedListener(new TextWatcher(){         //checks values are within bounds before allowing ok button to be pressed
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    Integer Armor = Integer.parseInt(editarmor.getText().toString());    //gets dialog box values
                    Integer Power = Integer.parseInt(editpower.getText().toString());
                    Integer Scan = Integer.parseInt(editscan.getText().toString());
                    String Name = editname.getText().toString();
                    AlertDialog dialog = (AlertDialog) getDialog();

                    if (Armor > 5 || Power > 5 || Scan > 5){
                        Toast.makeText(getActivity(), "Please ensure all values are below 5", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Armor < 1 || Power < 1 || Scan < 1) {
                        Toast.makeText(getActivity(), "Please ensure all values are greater than 1", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Armor + Power + Scan > 8) {
                        Toast.makeText(getActivity(), "A maximum of 5 points overall can be added", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Name == null || Name == "") {
                        Toast.makeText(getActivity(), "The name can't be blank", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }
                } catch (Exception e) {

                }
            }
        });

        editscan.addTextChangedListener(new TextWatcher(){         //checks values are within bounds before allowing ok button to be pressed
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    Integer Armor = Integer.parseInt(editarmor.getText().toString());    //gets dialog box values
                    Integer Power = Integer.parseInt(editpower.getText().toString());
                    Integer Scan = Integer.parseInt(editscan.getText().toString());
                    String Name = editname.getText().toString();
                    AlertDialog dialog = (AlertDialog) getDialog();

                    if (Armor > 5 || Power > 5 || Scan > 5){
                        Toast.makeText(getActivity(), "Please ensure all values are below 5", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Armor < 1 || Power < 1 || Scan < 1) {
                        Toast.makeText(getActivity(), "Please ensure all values are greater than 1", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Armor + Power + Scan > 8) {
                        Toast.makeText(getActivity(), "A maximum of 5 points overall can be added", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else if (Name == null || Name == "") {
                        Toast.makeText(getActivity(), "The name can't be blank", Toast.LENGTH_LONG).show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }
                } catch (Exception e) {

                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(myView).
                setTitle("Setup");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Integer Armor = Integer.parseInt(editarmor.getText().toString());    //gets dialog box values
                Integer Power = Integer.parseInt(editpower.getText().toString());
                Integer Scan = Integer.parseInt(editscan.getText().toString());
                String Name = editname.getText().toString();

                viewModel.setArmor(Armor-1);      //pass dialog box values to viewmodel
                viewModel.setPower(Power-1);
                viewModel.setScan(Scan-1);
                viewModel.setName(Name);
                dismiss();
            }
        });

        builder.setCancelable(false);

        Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return dialog;

    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
    }


}