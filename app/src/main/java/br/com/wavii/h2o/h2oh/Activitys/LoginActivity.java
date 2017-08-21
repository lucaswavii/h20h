package br.com.wavii.h2o.h2oh.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import br.com.wavii.h2o.h2oh.R;
import br.com.wavii.h2o.h2oh.Utils.WebService;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuarioLogin;
    private EditText etSenhaLogin;
    private ProgressDialog mprogressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        etUsuarioLogin = ( EditText ) findViewById(R.id.etEmailRegistro);
        etSenhaLogin = (EditText) findViewById(R.id.etSenhaRegistro);
    }

    public void btnRegistroLoginClick(View view) {
        Intent it = new Intent(this, RegistroActivity.class);
        startActivity(it);
    }

    public void btnLogarClick(View view) {

        try {

            String usuario = etUsuarioLogin.getText().toString();
            String senha = etSenhaLogin.getText().toString();
            boolean validaLogin = true;
            if (usuario.length() == 0) {
                validaLogin = false;
                etUsuarioLogin.setFocusable(true);
                etUsuarioLogin.setError("Informe o email de login.");
            }

            if (senha.length() == 0) {
                validaLogin = false;
                etSenhaLogin.setFocusable(true);
                etSenhaLogin.setError("Informe a senha de login.");
            }

            if (validaLogin) {

                JsonObject json = new JsonObject();
                json.addProperty("email", usuario);
                json.addProperty("senha", senha);

                WebService webService = new WebService();
                mprogressDialog = ProgressDialog.show(LoginActivity.this, "Aguarde", "Efetuando Login...");

                Ion.with(getBaseContext())
                        .load("POST", webService.URL_WEBSERVICE + "getLogin")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e != null) {
                                    mprogressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Erro inesperado.", Toast.LENGTH_LONG).show();
                                }

                                if (result.getAsJsonObject().get("sucesso").toString().equals("false")) {
                                    mprogressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), result.getAsJsonObject().get("mensagem").toString(), Toast.LENGTH_LONG).show();

                                } else {
                                    mprogressDialog.dismiss();
                                    Intent it = new Intent( LoginActivity.this, MainActivity.class);
                                    startActivity(it);
                                }
                            }
                        });
            }
        } catch(Exception ex ){


        }
    }
}
