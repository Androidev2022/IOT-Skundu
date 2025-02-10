package com.iot.mechatronix;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class CustPrograssbar {
    Dialog progressDialog;
    TextView txtStats;

    public void prograssCreate(Context context) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                Log.e("Error","Show!");
            } else {
                progressDialog = new  Dialog(context);
                progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                progressDialog.setContentView(R.layout.dialog_progress);
                txtStats=progressDialog.findViewById(R.id.txt_status);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                progressDialog.show();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void updateTextPrograssBar(String feedback) {
        if (progressDialog != null) {
           txtStats.setText(feedback);
        }
    }
    public void closePrograssBar() {
        if (progressDialog != null) {
            try {
                progressDialog.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
