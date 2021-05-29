package com.example.automaticattendancesystem;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;



public class MyDialog extends DialogFragment {
    public static final  String CLASS_ADD_DIALOG="addClass";
    public static final  String CLASS_UPDATE_DIALOG="updateClass";
    public static final  String STUDENT_ADD_DIALOG="addStudent";
    public static final String STUDENT_UPDATE_DIALOG = "updateStudent" ;

    private onClickListener listener;
    private int roll;
    private String name;

    public MyDialog(int roll, String name) {

        this.roll = roll;
        this.name = name;
    }

    public MyDialog() {

    }

    public  interface  onClickListener{
        void onClick(String text1, String text2);
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = null;
        if (getTag().equals(CLASS_ADD_DIALOG))dialog=getAddClassDialog();
        if (getTag().equals(STUDENT_ADD_DIALOG))dialog = getAddStudentDialog();
        if (getTag().equals(CLASS_UPDATE_DIALOG))dialog = getUpdateClassDialog();
        if (getTag().equals(STUDENT_UPDATE_DIALOG))dialog = getUpdateStudentDialog();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private Dialog getUpdateStudentDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Update Student");

        EditText roll_edt = view.findViewById(R.id.edt01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);
        add.setText("Update");
        roll_edt.setText(roll+"");
        name_edt.setText(name);
        roll_edt.setEnabled(false);
        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            listener.onClick(roll,name);
            dismiss();

        });
        return builder.create();

    }

    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Update Class class");

        EditText class_edt = view.findViewById(R.id.edt01);
        EditText subject_edt = view.findViewById(R.id.edt02);

        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);
        add.setText("Update");

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String className = class_edt.getText().toString();
            String subName = subject_edt.getText().toString();
            listener.onClick(className,subName);
            dismiss();
        });
        return builder.create();
    }

    private Dialog getAddStudentDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Student");

        EditText roll_edt = view.findViewById(R.id.edt01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            roll_edt.setText(String.valueOf(Integer.parseInt(roll)+1));
            name_edt.setText("");
            listener.onClick(roll,name);

        });
        return builder.create();

    }


    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("ADD NEW CLASS");

     EditText class_edt = view.findViewById(R.id.edt01);
     EditText subject_edt = view.findViewById(R.id.edt02);

        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String className = class_edt.getText().toString();
            String subName = subject_edt.getText().toString();
            listener.onClick(className,subName);
            dismiss();
        });
        return builder.create();
    }
}
