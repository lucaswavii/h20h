package br.com.wavii.h2o.h2oh.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import br.com.wavii.h2o.h2oh.Models.Usuario;
import br.com.wavii.h2o.h2oh.R;
import br.com.wavii.h2o.h2oh.Utils.MessagemDialog;
import br.com.wavii.h2o.h2oh.Utils.WebService;

public class RegistroActivity extends AppCompatActivity {

    private EditText etEmailRegistro;
    private EditText etSenhaRegistro;
    private ProgressDialog mprogressDialog;
    private MessagemDialog messagemDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registro);
        messagemDialog = new MessagemDialog();
    }

    public void btnRegistroClick(View view) {

        etEmailRegistro = (EditText) findViewById(R.id.etEmailRegistro);
        etSenhaRegistro = (EditText) findViewById(R.id.etSenhaRegistro);

        boolean valiaRegistro = true;

        String email = etEmailRegistro.getText().toString();
        String senha = etSenhaRegistro.getText().toString();

        if (email.length() == 0 ) {
            valiaRegistro = false;
            etEmailRegistro.setFocusable(true);
            etEmailRegistro.setError("E-mail é obrigatório!");
        }

        if (senha.length() == 0 ) {
            valiaRegistro = false;
            etSenhaRegistro.setFocusable(true);
            etSenhaRegistro.setError("Senha é obrigatório!");
        }


        if ( valiaRegistro ) {

            Usuario usuario = new Usuario(0, email, senha);


            try {

                JsonObject json = new JsonObject();
                json.addProperty("email", usuario.getEmail());
                json.addProperty("senha", usuario.getSenha());

                WebService webService = new WebService();
                mprogressDialog = ProgressDialog.show(RegistroActivity.this, "Aguarde", "Efetuando Registro...");

                Ion.with(getBaseContext())
                        .load("POST", webService.URL_WEBSERVICE + "cadastrarUsuario" )
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e != null ) {
                                    mprogressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Erro inesperado.", Toast.LENGTH_LONG ).show();
                                }

                                if ( result.getAsJsonObject().get("sucesso").toString().equals("false")) {
                                    mprogressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), result.getAsJsonObject().get("mensagem").toString(),Toast.LENGTH_LONG ).show();

                                } else {
                                    mprogressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), result.getAsJsonObject().get("mensagem").toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } catch( Exception ex ) {

            }
        }
    }
}
