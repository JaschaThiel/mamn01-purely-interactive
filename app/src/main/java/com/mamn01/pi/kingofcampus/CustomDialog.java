package com.mamn01.pi.kingofcampus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Assar on 2018-05-16.
 */

public class CustomDialog extends Dialog implements DialogInterface.OnClickListener, DialogInterface {
    Button yesButton, noButton;
    Context context;

    public CustomDialog(Context c) {
        super(c.getApplicationContext());
        this.context = c;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", this)
                .setNegativeButton("No", this).show();
    }



    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    Class c = null;
                    try {
                        c = Class.forName("com.mamn01.pi.kingofcampus.CaptureActivity");
                        Intent intent = new Intent(context, c);
                        context.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    this.dismiss();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    this.dismiss();
                    break;
            }
        }
}
