package br.com.wavii.h2o.h2oh.Utils;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by lucas.ricarte on 18/08/2017.
 */

public class MessagemDialog extends DialogFragment {

    private AlertDialog alerta;

    public MessagemDialog() {}

    public void MessagemDialogErro(  Context context, String mensagem  ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Mensagem Alerta");
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
