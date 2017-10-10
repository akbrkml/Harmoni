package com.harmoni.harmonikeluarga.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;

/**
 * Created by akbar on 09/10/17.
 */

public class DialogUtils {

    public static void customExitAppDialog(final Context context, String message){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_confirmation);

        // set the custom dialog components - text and button
        TextView text = dialog.findViewById(R.id.tvKet);
        text.setText(message);

        Button mButtonCancel = dialog.findViewById(R.id.btBatal);
        Button mButtonOk = dialog.findViewById(R.id.btOk);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
