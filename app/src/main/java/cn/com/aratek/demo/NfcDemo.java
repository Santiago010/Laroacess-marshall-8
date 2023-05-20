package cn.com.aratek.demo;

import android.content.Intent;
import android.nfc.FormatException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import cn.com.aratek.demo.featuresrequest.APISERVICE;
import cn.com.aratek.demo.featuresrequest.DataAuth;
import cn.com.aratek.demo.featuresrequest.ResAuth;
import cn.com.aratek.demo.utils.ConfRetrofit;
import cn.com.aratek.demo.utils.MediaPlayerHelper;
import cn.com.aratek.demo.utils.NfcUtil;
import cn.com.aratek.demo.utils.Prefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NfcDemo  extends AbstractBaseActivity implements View.OnClickListener {

    private NfcUtil nfcUtil;
    private String serialNumberTargetNFC;

    private  ConfRetrofit confRetrofit;
    private  APISERVICE apiservice;
    private LinearLayout target_message_nfc;
    private LinearLayout target_info_employee;
    private ImageView image_employee;
    private EditText name_employee;
    private EditText lastName_employee;
    private EditText dni_employee;
    private  TextView message_auth_employee;
    private LinearLayout target_message_nfc_not_exit;
    private Button btn_back_e;
    private Button btn_back_m;
    private  Button btn_back_n;

    private Prefs prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_demo);

        prefs = new Prefs(this);
         confRetrofit = new ConfRetrofit(this, "");

        apiservice = confRetrofit.makeConfRequest().create(APISERVICE.class);
        target_message_nfc = findViewById(R.id.target_message_nfc);

        target_info_employee = findViewById(R.id.target_info_employee);

        image_employee = findViewById(R.id.image_employee);

        name_employee = findViewById(R.id.name_employee);

        lastName_employee = findViewById(R.id.lastName_employee);

        dni_employee = findViewById(R.id.dni_employee);

        message_auth_employee = findViewById(R.id.message_auth_employee);

        target_message_nfc_not_exit = findViewById(R.id.target_message_nfc_not_exit);

        btn_back_e = findViewById(R.id.btn_back_e);
        btn_back_m = findViewById(R.id.btn_back_m);
        btn_back_n= findViewById(R.id.btn_back_n);

        addClickListenerAllBtnBack();
        nfcUtil = new NfcUtil(this);
    }

    private void addClickListenerAllBtnBack(){
        btn_back_e.setOnClickListener(v -> {
            onBackPressed();
        });


        btn_back_m.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_back_n.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        NfcUtil.mNfcAdapter.enableForegroundDispatch(this, NfcUtil.mPendingIntent, NfcUtil.mIntentFilter, NfcUtil.mTechList);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void open() {

    }

    @Override
    protected void close() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MediaPlayerHelper.payMedia(NfcDemo.this, R.raw.idcard_voice);
        dismissProgressDialog();
        try {
            String str = NfcUtil.readNFCFromTag(intent);
            serialNumberTargetNFC = str;
            sendSerialNumber(serialNumberTargetNFC);

        } catch (IOException | FormatException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {

    }

    private void sendSerialNumber(String content){

        DataAuth dataAuth = new DataAuth(prefs.getSN(),"rfid",content);
        Call<ResAuth> call = apiservice.sendAuthenticationMethod(dataAuth);
        call.enqueue(new Callback<ResAuth>() {
            @Override
            public void onResponse(Call<ResAuth> call, Response<ResAuth> response) {
                if(response.isSuccessful()){
                    ResAuth res = response.body();
                    showtargetWithInfo(res);
                }else {
                    showTargetQrNotFound();
                }
            }

            @Override
            public void onFailure(Call<ResAuth> call, Throwable t) {

            }
        });


    }

    private void showtargetWithInfo(ResAuth res) {
        target_message_nfc.setVisibility(View.GONE);
        if(res.getEmployee().getProfile_picture().size() == 0){
            image_employee.setImageResource(R.drawable.withoutemployee);
        }else{
            String urlImg = prefs.getUrl().substring(0, prefs.getUrl().indexOf("/v1.0/api/"));
            Glide.with(this).load(urlImg +res.getVehicle().getProfile_picture().get(0).getUrl()).into(image_employee);
        }
        name_employee.setText(res.getEmployee().getFirst_name());
        lastName_employee.setText(res.getEmployee().getFirst_lastname());
        dni_employee.setText(res.getEmployee().getDni());
        message_auth_employee.setText(res.getMessage());
        if(res.getMessage().equals("AUTENTICACIÓN EXITOSA")){
            message_auth_employee.setBackgroundColor(getResources().getColor(R.color.green_element));
        }else{
            message_auth_employee.setBackgroundColor(getResources().getColor(R.color.red_element));
        }


    }

    private void showTargetQrNotFound(){
        target_message_nfc.setVisibility(View.GONE);
        target_info_employee.setVisibility(View.GONE);


    }
}