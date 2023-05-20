package cn.com.aratek.demo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.com.aratek.demo.featuresrequest.APISERVICE;
import cn.com.aratek.demo.featuresrequest.DataLoginCoordinator;
import cn.com.aratek.demo.featuresrequest.Token;
import cn.com.aratek.demo.utils.ConfRetrofit;
import cn.com.aratek.demo.utils.Prefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AbstractBaseActivity {

    EditText user;
    EditText passw;
    TextView alert_login;
    Button login;
    Button btn_finish;
    Prefs prefs;
    ConfRetrofit confRetrofit;
    APISERVICE apiservice;
    ProgressBar progress_circular_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = new Prefs(this);
        user = findViewById(R.id.user);
        passw = findViewById(R.id.passw);
        progress_circular_login = findViewById(R.id.progress_circular_login);
        alert_login = findViewById(R.id.alert_login);
        login = findViewById(R.id.login);
        btn_finish = findViewById(R.id.btn_finish);
        addClickListenerBtnFinish();
        addClickListenerBtnLogin();

        confRetrofit = new ConfRetrofit(this,"");
        apiservice = confRetrofit.makeConfRequest().create(APISERVICE.class);


    }

    @Override
    protected void open() {

    }

    @Override
    protected void close() {

    }

    private void addClickListenerBtnFinish(){
        btn_finish.setOnClickListener(v -> {
            finish();
        });
    }

    private void  addClickListenerBtnLogin(){
       login.setOnClickListener(v -> {
           trySendDataCoordinator();
       });
    }

    private Integer verifyDataCorrect(){
        if(user.getText() == null || passw.getText() == null){
            return 0;
        }
        if(user.getText() == null){
            return 1;

        }else if(user.getText() == null){
            return 2;
        }else{
            return 3;
        }
    }

    private void trySendDataCoordinator(){
        switch (verifyDataCorrect()){
            case 0:
                alert_login.setTextColor(getResources().getColor(R.color.error_details_text_color));
                alert_login.setText("El usuario y la contraseña son requeridos");
                break;
            case 1:
                alert_login.setTextColor(getResources().getColor(R.color.error_details_text_color));
                alert_login.setText("El usuario es requerido");
                break;
            case 2:
                alert_login.setTextColor(getResources().getColor(R.color.error_details_text_color));
                alert_login.setText("La contraseña es requerida");
                break;
            case 3:
                sendDataCoordinator();

        }
    }

    private void sendDataCoordinator(){
        progress_circular_login.setVisibility(View.VISIBLE);
        DataLoginCoordinator dataLoginCoordinator = new DataLoginCoordinator(user.getText().toString(),passw.getText().toString());
        Call<Token> call = apiservice.sendDataCoordinator(dataLoginCoordinator);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                progress_circular_login.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Token res = response.body();
                    alert_login.setTextColor(getResources().getColor(R.color.green_element));
                    alert_login.setText("Autenticado con exito");
                    prefs.saveTOKEN(res.getToken());
                    navigateTo();
                } else {
                    alert_login.setTextColor(getResources().getColor(R.color.red_element));
                    alert_login.setText(user.getText().toString() + " no se pudo autenticar, intente de nuevo",null);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("ONFAILURE",t.getMessage());
            }
        });

    }


    private void  navigateTo(){
        Intent intent = new Intent(this,OptionsCoordinator.class);
        startActivity(intent);
    }
}