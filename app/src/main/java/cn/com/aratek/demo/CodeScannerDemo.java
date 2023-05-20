package cn.com.aratek.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.io.UnsupportedEncodingException;
import cn.com.aratek.demo.featuresrequest.APISERVICE;
import cn.com.aratek.demo.featuresrequest.DataAuth;
import cn.com.aratek.demo.featuresrequest.ResAuth;
import cn.com.aratek.demo.utils.ConfRetrofit;
import cn.com.aratek.demo.utils.Prefs;
import cn.com.aratek.qrc.CodeScanner;
import cn.com.aratek.util.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.bumptech.glide.Glide;

public class CodeScannerDemo extends AbstractBaseActivity implements View.OnClickListener {

    private static final int MSG_UPDATE_FIRMWARE_VERSION = 100;
    private static final int MSG_UPDATE_SERIAL_NUMBER = 101;
    private static final int MSG_UPDATE_OUTPUT_DATA = 102;
    private static final int MSG_ENABLE_BUTTONS = 103;
    private CodeScanner mCodeScanner;
    private ConfRetrofit confRetrofit;
    private APISERVICE apiservice;
    private LinearLayout target_message_qr;
    private LinearLayout target_info_employee;
    private ImageView image_employee;
    private EditText name_employee;
    private EditText lastName_employee;
    private EditText dni_employee;
    private  TextView message_auth_employee;
    private LinearLayout target_info_vehicle;
    private ImageView image_vehicle;
    private EditText plate_vehicle;
    private EditText color_vehicle;
    private EditText model_vehicle;
    private  TextView message_auth_vehicle;
    private LinearLayout target_message_qr_not_exit;
    private Button btn_back_e;
    private Button btn_back_v;
    private Button btn_back_m;
    private  Button btn_back_n;
    private Prefs prefs;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        mCodeScanner = CodeScanner.getInstance(this);

        confRetrofit = new ConfRetrofit(this,"");

        apiservice = confRetrofit.makeConfRequest().create(APISERVICE.class);

        prefs = new Prefs(this);


        target_message_qr = findViewById(R.id.target_message_qr);

        target_info_employee = findViewById(R.id.target_info_employee);

        image_employee = findViewById(R.id.image_employee);

        name_employee = findViewById(R.id.name_employee);

        lastName_employee = findViewById(R.id.lastName_employee);

        dni_employee = findViewById(R.id.dni_employee);

        message_auth_employee = findViewById(R.id.message_auth_employee);

        target_info_vehicle = findViewById(R.id.target_info_vehicle);

        image_vehicle = findViewById(R.id.image_vehicle);

        plate_vehicle = findViewById(R.id.plate_vehicle);

        color_vehicle = findViewById(R.id.color_vehicle);

        model_vehicle = findViewById(R.id.model_vehicle);

        message_auth_vehicle = findViewById(R.id.message_auth_vehicle);

        target_message_qr_not_exit = findViewById(R.id.target_message_qr_not_exit);

        btn_back_e = findViewById(R.id.btn_back_e);

        btn_back_v = findViewById(R.id.btn_back_v);

        btn_back_m = findViewById(R.id.btn_back_m);
        btn_back_n= findViewById(R.id.btn_back_n);


        addClickListenerAllBtnBack();
    }

    private void addClickListenerAllBtnBack(){
        btn_back_e.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_back_v.setOnClickListener(v -> {
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
    protected void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case MSG_UPDATE_FIRMWARE_VERSION: {
                break;
            }
            case MSG_UPDATE_SERIAL_NUMBER: {
                break;
            }
            case MSG_UPDATE_OUTPUT_DATA: {
                break;
            }
            case MSG_ENABLE_BUTTONS: {
                break;
            }
            default: {
                super.handleMessage(msg);
                break;
            }
        }
    }

    @Override
    protected synchronized void open() {
        if (STATUS_CLOSED != mStatus.get()) {
            return;
        }

        mStatus.set(STATUS_OPENING);


        createNewExecutor();

        int error;
        Result res;

        mCodeScanner.powerOn(); // ignore power on errors
        error = mCodeScanner.open();
        if (error == CodeScanner.RESULT_OK) {
            mStatus.set(STATUS_OPENED);

            showInformation(getString(R.string.hardware_barcode_scanner_open_success), null);
            res = mCodeScanner.getFirmwareVersion();
            res = mCodeScanner.getSerial();

        } else {
            mCodeScanner.powerOff(); // ignore power off errors
            mStatus.set(STATUS_CLOSED);

            showError(getString(R.string.hardware_barcode_scanner_open_failed),
                    getErrorString(error));

        }

        scan();
    }

    @Override
    protected synchronized void close() {
        if (STATUS_CLOSED == mStatus.get()) {
            return;
        }

        mStatus.set(STATUS_CLOSING);


        shutdownExecutorAndAwaitTermination();

        int error;

        error = mCodeScanner.close();
        if (error == CodeScanner.RESULT_OK) {
            showInformation(getString(R.string.hardware_barcode_scanner_close_success), null);
        } else {
            showError(getString(R.string.hardware_barcode_scanner_close_failed),
                    getErrorString(error));
        }
        mCodeScanner.powerOff(); // ignore power off errors
        mStatus.set(STATUS_CLOSED);

    }

    @Override
    public void onClick(View v) {

    }



    private void updateOutputData(String info) {
        getHandler().sendMessage(getHandler().obtainMessage(MSG_UPDATE_OUTPUT_DATA, info));
    }



    private void scan() {
        getExecutor().execute(new CodeScannerTask("scan"));
    }

    private String getErrorString(int error) {
        int strid;
        switch (error) {
            case CodeScanner.RESULT_OK:
                strid = R.string.operation_successful;
                break;
            case CodeScanner.RESULT_FAIL:
                strid = R.string.error_operation_failed;
                break;
            case CodeScanner.WRONG_CONNECTION:
                strid = R.string.error_wrong_connection;
                break;
            case CodeScanner.DEVICE_BUSY:
                strid = R.string.error_device_busy;
                break;
            case CodeScanner.DEVICE_NOT_OPEN:
                strid = R.string.error_device_not_open;
                break;
            case CodeScanner.TIMEOUT:
                strid = R.string.error_timeout;
                break;
            case CodeScanner.NO_PERMISSION:
                strid = R.string.error_no_permission;
                break;
            case CodeScanner.WRONG_PARAMETER:
                strid = R.string.error_wrong_parameter;
                break;
            case CodeScanner.DECODE_ERROR:
                strid = R.string.error_decode;
                break;
            case CodeScanner.INIT_FAIL:
                strid = R.string.error_initialization_failed;
                break;
            case CodeScanner.UNKNOWN_ERROR:
                strid = R.string.error_unknown;
                break;
            case CodeScanner.NOT_SUPPORT:
                strid = R.string.error_not_support;
                break;
            case CodeScanner.NOT_ENOUGH_MEMORY:
                strid = R.string.error_not_enough_memory;
                break;
            case CodeScanner.DEVICE_NOT_FOUND:
                strid = R.string.error_device_not_found;
                break;
            case CodeScanner.DEVICE_REOPEN:
                strid = R.string.error_device_reopen;
                break;
            default:
                return getString(R.string.error_other, error);
        }
        return getString(strid);
    }



    private class CodeScannerTask implements Runnable {
        private String mTask;

        CodeScannerTask(String task) {
            mTask = task;
        }

        @Override
        public void run() {
            Result res;
            String contentBarcode;


            if (mTask.equals("scan") ) {


                do {
                    res = mCodeScanner.scan();
                } while (res.error == CodeScanner.TIMEOUT && !getExecutor().isShutdown());

                if (!getExecutor().isShutdown()) {
                    if (res.error == CodeScanner.RESULT_OK) {
                        try {
                            updateOutputData(new String((byte[]) res.data, "UTF-8").trim());
                            showInformation(getString(R.string.barcode_scan_success), null);
                        } catch (UnsupportedEncodingException e) {
                            showError(getString(R.string.unsupported_encoding), null);
                        }
                    } else {
                        showError(getString(R.string.barcode_scan_failed),
                                getErrorString(res.error));
                    }
                }



                if (mTask.equals("scan")) {
                    if(res.data != null){
                        contentBarcode = new String((byte[]) res.data).trim();
                        sendBarCodeAndQr(contentBarcode);
                    }
                }
            }
        }
    }




    private void sendBarCodeAndQr(String content){

        DataAuth dataAuth = new DataAuth(prefs.getSN(),"barcode",content);
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


    private void showtargetWithInfo(ResAuth res){

        target_message_qr.setVisibility(View.GONE);
        if(res.getEmployee() == null && res.getVehicle() != null){
            target_info_employee.setVisibility(View.GONE);
            if(res.getVehicle().getProfile_picture().size() == 0){
                image_vehicle.setImageResource(R.drawable.vehicle);
            }else{
                String urlImg = prefs.getUrl().substring(0, prefs.getUrl().indexOf("/v1.0/api/"));
                Glide.with(this).load(urlImg +res.getVehicle().getProfile_picture().get(0).getUrl()).into(image_vehicle);
            }

            plate_vehicle.setText(res.getVehicle().getPlate());
            color_vehicle.setText(res.getVehicle().getColor());
            model_vehicle.setText(res.getVehicle().getModel());
            message_auth_vehicle.setText(res.getMessage());
            if(res.getMessage() != "AUTENTICACIÓN EXITOSA"){
                message_auth_employee.setBackgroundColor( getResources().getColor(R.color.red_element));
            }else{
                message_auth_employee.setBackgroundColor( getResources().getColor(R.color.green_element));
            }



        } else if (res.getVehicle() == null && res.getEmployee() != null) {
            target_info_vehicle.setVisibility(View.GONE);
            if(res.getEmployee().getProfile_picture().size() == 0){
                image_employee.setImageResource(R.drawable.withoutemployee);
            }else{
                String urlImg = prefs.getUrl().substring(0, prefs.getUrl().indexOf("/v1.0/api/"));
                Glide.with(this).load(urlImg + res.getEmployee().getProfile_picture().get(0).getUrl()).into(image_employee);
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
    }

    private void showTargetQrNotFound(){
        target_message_qr.setVisibility(View.GONE);
        target_info_employee.setVisibility(View.GONE);
        target_info_vehicle.setVisibility(View.GONE);

    }
}
