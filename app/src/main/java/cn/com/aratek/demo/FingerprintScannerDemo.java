package cn.com.aratek.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import cn.com.aratek.demo.featuresrequest.APISERVICE;
import cn.com.aratek.demo.featuresrequest.DataAuth;
import cn.com.aratek.demo.featuresrequest.ResAuth;
import cn.com.aratek.demo.featuresrequest.ResListEmployee2;
import cn.com.aratek.demo.featuresrequest.ResListFpsDevice;
import cn.com.aratek.demo.featuresrequest.ResNewFp;
import cn.com.aratek.demo.utils.ConfRetrofit;
import cn.com.aratek.demo.utils.Prefs;
import cn.com.aratek.fp.Bione;
import cn.com.aratek.fp.FingerprintImage;
import cn.com.aratek.fp.FingerprintScanner;
import cn.com.aratek.util.Result;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FingerprintScannerDemo extends AbstractBaseActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    private static final String TAG = FingerprintScannerDemo.class.getSimpleName();
    private static final String FP_DB_PATH = "/sdcard/fp.db";
    private static final int MSG_UPDATE_FIRMWARE_VERSION = 100;
    private static final int MSG_UPDATE_SERIAL_NUMBER = 101;
    private static final int MSG_UPDATE_FINGERPRINT = 102;
    private static final int MSG_UPDATE_TIME_INFORMATIONS = 103;
    private static final int MSG_ENABLE_BUTTONS = 104;
    private FingerprintScanner mFingerprintScanner;
    private int mId;
    private int mLfdLevel = FingerprintScanner.LFD_LEVEL_OFF;
    private Intent infoIntent;
    private Prefs prefs;
    private ResListEmployee2 user;
    private FingerprintImage fiEm = null;
    private ConfRetrofit confRetrofit;
    private APISERVICE apiservice;
    private List<ResListFpsDevice> listFpsDevices;
    private Context context;
    private LinearLayout target_new_finger;
    private LinearLayout bt_enroll1;
    private LinearLayout bt_enroll2;
    private EditText name_employee_n_f;
    private EditText dni_employee_n_f;
    private EditText lastName_employee_n_f;
    private LinearLayout target_message_fp;
    private Button btn_back_m;
    private Button btn_back_s;
    private LinearLayout target_info_employee;
    private ImageView image_employee;
    private EditText name_employee;
    private EditText lastName_employee;
    private EditText dni_employee;
    private TextView message_auth_employee;
    private Button btn_back_e;
    private LinearLayout target_message_fp_not_exit;
    private Button btn_back_f;

    private AlertDialog dialog1;
    private AlertDialog dialog2;
    private int numberBtnEnroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        context = this;
        prefs = new Prefs(this);
        mFingerprintScanner = FingerprintScanner.getInstance(this);
        confRetrofit = new ConfRetrofit(this, prefs.getTOKEN());
        apiservice = confRetrofit.makeConfRequest().create(APISERVICE.class);
        infoIntent = getIntent();
        user = (ResListEmployee2) infoIntent.getSerializableExtra("DATAEMPLOYEE");

        target_new_finger = findViewById(R.id.target_new_finger);
        bt_enroll1 = findViewById(R.id.bt_enroll1);
        bt_enroll2 = findViewById(R.id.bt_enroll2);
        name_employee_n_f = findViewById(R.id.name_employee_n_f);
        lastName_employee_n_f = findViewById(R.id.lastName_employee_n_f);
        dni_employee_n_f = findViewById(R.id.dni_employee_n_f);
        btn_back_s = findViewById(R.id.btn_back_s);
        target_message_fp = findViewById(R.id.target_message_fp);
        target_info_employee = findViewById(R.id.target_info_employee);
        image_employee = findViewById(R.id.image_employee);
        name_employee = findViewById(R.id.name_employee);
        lastName_employee = findViewById(R.id.lastName_employee);
        dni_employee = findViewById(R.id.dni_employee);
        message_auth_employee = findViewById(R.id.message_auth_employee);
        target_message_fp_not_exit = findViewById(R.id.target_message_fp_not_exit);
        btn_back_f = findViewById(R.id.btn_back_f);
        btn_back_e = findViewById(R.id.btn_back_e);
        btn_back_m = findViewById(R.id.btn_back_m);
        changeVisibleBtns();
        addClickListenerAllBtnBack();
    }

    private void createDialogCreateFps() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.layout.modal_fp1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder2.setView(R.layout.modal_fp2);
        }
        dialog1 = builder.create();
        dialog2 = builder2.create();

    }

    private void addClickListenerAllBtnBack() {
        btn_back_f.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_back_e.setOnClickListener(v -> {
            onBackPressed();
        });


        btn_back_m.setOnClickListener(v -> {
            onBackPressed();
        });
    }



    private void renderInfoEmployee() {
        name_employee_n_f.setText(user.getFirst_name());
        lastName_employee_n_f.setText(user.getFirst_lastname());
        dni_employee_n_f.setText(user.getDni());
    }

    private void addClickListenerBtnsEnroll() {
        btn_back_s.setOnClickListener(v -> {
            Toast.makeText(context, "Huellas guardadas", Toast.LENGTH_SHORT).show();
            onBackPressed();
        });
        bt_enroll1.setOnClickListener(v -> {
            enroll();
            numberBtnEnroll = 1;
            makeThreadforShowDialog(true);
        });
        bt_enroll2.setOnClickListener(v -> {
            enroll();
            numberBtnEnroll = 2;
            makeThreadforShowDialog(true);
        });
    }

    private void changeVisibleBtns() {
        if (user == null) {
            getFpsDevice();
            target_new_finger.setVisibility(View.GONE);
        } else {
            target_message_fp.setVisibility(View.GONE);
            renderInfoEmployee();
            addClickListenerBtnsEnroll();
            createDialogCreateFps();
        }
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
            case MSG_UPDATE_FINGERPRINT: {
                break;
            }
            case MSG_UPDATE_TIME_INFORMATIONS: {

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

        mFingerprintScanner.powerOn(); // ignore power on errors

        error = mFingerprintScanner.open();

        if (error == FingerprintScanner.RESULT_OK) {
            mStatus.set(STATUS_OPENED);

            showInformation(getString(R.string.fingerprint_device_open_success), null);

            res = mFingerprintScanner.getFirmwareVersion();
            res = mFingerprintScanner.getSerial();
            mFingerprintScanner.setLfdLevel(mLfdLevel);
            // initialize fingerprint algorithm
            if ((error = Bione.initialize(FingerprintScannerDemo.this, FP_DB_PATH))
                    != Bione.RESULT_OK) {
                showError(getString(R.string.algorithm_initialization_failed),
                        getErrorString(error));
            }
        } else {

            mFingerprintScanner.powerOff(); // ignore power off errors
            mStatus.set(STATUS_CLOSED);

            showError(getString(R.string.fingerprint_device_open_failed), getErrorString(error));
        }

    }

    @Override
    protected synchronized void close() {
        if (STATUS_CLOSED == mStatus.get()) {
            return;
        }

        mStatus.set(STATUS_CLOSING);
        shutdownExecutorAndAwaitTermination();
        int error;

        Bione.exit();
        error = mFingerprintScanner.close();
        if (error == FingerprintScanner.RESULT_OK) {
            showInformation(getString(R.string.fingerprint_device_close_success), null);
        } else {
            showError(getString(R.string.fingerprint_device_close_failed), getErrorString(error));
        }
        mFingerprintScanner.powerOff(); // ignore power off errors
        mStatus.set(STATUS_CLOSED);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switchLfd(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    private void switchLfd(int level) {
        mLfdLevel = level;
        if (STATUS_OPENED == mStatus.get()) {
            System.out.println(getString(R.string.switching_lfd));
//            getExecutor().execute(new FingerprintScannerTask("switch_lfd"));
        }
    }

    //TODO:cuando esta funcion es llamada por el boton de enroll lo que hace es ejecutar un hilo que instancia la clase
    //TODO:FingerprintScannerTask y le pasa por parametro la accion enroll
    private void enroll() {
        getExecutor().execute(new FingerprintScannerTask("enroll"));
    }

    private void verify() {
        getExecutor().execute(new FingerprintScannerTask("verify"));
    }

    private String getErrorString(int error) {
        int strid;
        switch (error) {
            case FingerprintScanner.RESULT_OK:
                strid = R.string.operation_successful;
                break;
            case FingerprintScanner.RESULT_FAIL:
                strid = R.string.error_operation_failed;
                break;
            case FingerprintScanner.WRONG_CONNECTION:
                strid = R.string.error_wrong_connection;
                break;
            case FingerprintScanner.DEVICE_BUSY:
                strid = R.string.error_device_busy;
                break;
            case FingerprintScanner.DEVICE_NOT_OPEN:
                strid = R.string.error_device_not_open;
                break;
            case FingerprintScanner.TIMEOUT:
                strid = R.string.error_timeout;
                break;
            case FingerprintScanner.NO_PERMISSION:
                strid = R.string.error_no_permission;
                break;
            case FingerprintScanner.WRONG_PARAMETER:
                strid = R.string.error_wrong_parameter;
                break;
            case FingerprintScanner.DECODE_ERROR:
                strid = R.string.error_decode;
                break;
            case FingerprintScanner.INIT_FAIL:
                strid = R.string.error_initialization_failed;
                break;
            case FingerprintScanner.UNKNOWN_ERROR:
                strid = R.string.error_unknown;
                break;
            case FingerprintScanner.NOT_SUPPORT:
                strid = R.string.error_not_support;
                break;
            case FingerprintScanner.NOT_ENOUGH_MEMORY:
                strid = R.string.error_not_enough_memory;
                break;
            case FingerprintScanner.DEVICE_NOT_FOUND:
                strid = R.string.error_device_not_found;
                break;
            case FingerprintScanner.DEVICE_REOPEN:
                strid = R.string.error_device_reopen;
                break;
            case FingerprintScanner.NO_FINGER:
                strid = R.string.error_no_finger;
                break;
            case Bione.INITIALIZE_ERROR:
                strid = R.string.error_algorithm_initialization_failed;
                break;
            case Bione.INVALID_FEATURE_DATA:
                strid = R.string.error_invalid_feature_data;
                break;
            case Bione.BAD_IMAGE:
                strid = R.string.error_bad_image;
                break;
            case Bione.NOT_MATCH:
                strid = R.string.error_not_match;
                break;
            case Bione.LOW_POINT:
                strid = R.string.error_low_point;
                break;
            case Bione.NO_RESULT:
                strid = R.string.error_no_result;
                break;
            case Bione.OUT_OF_BOUND:
                strid = R.string.error_out_of_bound;
                break;
            case Bione.DATABASE_FULL:
                strid = R.string.error_database_full;
                break;
            case Bione.LIBRARY_MISSING:
                strid = R.string.error_library_missing;
                break;
            case Bione.UNINITIALIZE:
                strid = R.string.error_algorithm_uninitialize;
                break;
            case Bione.REINITIALIZE:
                strid = R.string.error_algorithm_reinitialize;
                break;
            case Bione.REPEATED_ENROLL:
                strid = R.string.error_repeated_enroll;
                break;
            case Bione.NOT_ENROLLED:
                strid = R.string.error_not_enrolled;
                break;
            default:
                return getString(R.string.error_other, error);
        }
        return getString(strid);
    }

    private void makeThreadforShowDialog(Boolean hidden) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (hidden) {
                    if (numberBtnEnroll == 1) {
                        dialog1.show();
                    } else {
                        dialog2.show();
                    }

                } else {
                    if (numberBtnEnroll == 1) {
                        dialog1.dismiss();
                    } else {
                        dialog2.dismiss();
                    }
                }
            }
        });
    }


    //TODO:esta clase extiende del Runnable la cual solo tiene un metodo que se va llamar cuando es instaciada
    ///TODO: el cual es run
    private class FingerprintScannerTask implements Runnable {
        private String mTask;

        FingerprintScannerTask(String task) {
            mTask = task;
        }

        @Override
        public void run() {
            FingerprintImage fi = null;
            byte[] fpFeat = null, fpTemp = null;
            Result res;
            do {
                if ( mTask.equals("enroll") || mTask.equals("verify")) {

                    mFingerprintScanner.prepare();

                    do {
                        res = mFingerprintScanner.capture();

                        fi = (FingerprintImage) res.data;


                        if (res.error != FingerprintScanner.NO_FINGER ||
                                getExecutor().isShutdown()) {
                            break;
                        }
                    } while (true);
                    mFingerprintScanner.finish();

                    if (getExecutor().isShutdown()) {
                        break;
                    }


                    if (res.error == FingerprintScanner.FAKE_FINGER) {
                        showError(getString(R.string.fake_finger_detected), null);
                        break;
                    }
                    if (res.error != FingerprintScanner.RESULT_OK) {
                        showError(getString(R.string.capture_image_failed),
                                getErrorString(res.error));
                        break;
                    }
                }

                if (mTask.equals("enroll") || mTask.equals("verify")) {
                    res = Bione.extractFeature(fi);
                    if (res.error != Bione.RESULT_OK) {
                        showError(getString(R.string.enroll_failed_because_of_extract_feature),
                                getErrorString(res.error));
                        break;
                    }

                    fpFeat = (byte[]) res.data;
                }
                if (mTask.equals("enroll")) {

                    res = Bione.makeTemplate(fpFeat, fpFeat, fpFeat);

                    if (res.error != Bione.RESULT_OK) {
                        showError(getString(R.string.enroll_failed_because_of_make_template),
                                getErrorString(res.error));
                        break;
                    }
                    fpTemp = (byte[]) res.data;


                    int id = Bione.getFreeID();

                    if (id < 0) {
                        showError(getString(R.string.enroll_failed_because_of_get_id),
                                getErrorString(id));
                        break;
                    }

                    int ret = Bione.enroll(id, fpTemp);

                    if (ret != Bione.RESULT_OK) {
                        showError(getString(R.string.enroll_failed_because_of_error),
                                getErrorString(ret));
                        break;
                    } else {
                        saveFile(fi.convert2Jpeg());
                        mId = id;
                    }
                    makeThreadforShowDialog(false);
                    break;
                }

                if (mTask.equals("verify")) {

                    res = tourAllTheFp(fpFeat);

                    if (res.error != Bione.RESULT_OK) {

                        showFpNotFound();

                        break;
                    }

                    if ((Boolean) res.data) {
                        showInformation(getString(R.string.employee_authenticated),null);
                    } else {
                        showError(getString(R.string.employee_not_found), null);
                        showFpNotFound();
                    }
                    break;
                }
            } while (false);

        }
    }

    private void sendFp(File fp) {
        RequestBody employee = RequestBody.create(MediaType.parse("text/plain"), user.get_id());
        RequestBody fingerprint = RequestBody.create(MediaType.parse("image/jpeg"), fp);
        MultipartBody.Part fingerprintPath = MultipartBody.Part.createFormData("fingerprint", fp.getName(), fingerprint);

        Call<ResNewFp> call = apiservice.sendFp(employee, fingerprintPath);
        call.enqueue(new Callback<ResNewFp>() {
            @Override
            public void onResponse(Call<ResNewFp> call, Response<ResNewFp> response) {
                if (response.isSuccessful()) {
                    ResNewFp res = response.body();
                    showInformation(getString(R.string.employee_enroll_success), null);
                } else {
                    showError(getString(R.string.employee_enroll_fail), null);

                }
            }

            @Override
            public void onFailure(Call<ResNewFp> call, Throwable t) {
                showError(getString(R.string.employee_enroll_fail), null);
            }
        });


    }

    private void getFpsDevice() {
        Call<List<ResListFpsDevice>> call = apiservice.getFpsDevice(prefs.getSN());
        System.out.println(prefs.getSN());
        call.enqueue(new Callback<List<ResListFpsDevice>>() {
            @Override
            public void onResponse(Call<List<ResListFpsDevice>> call, Response<List<ResListFpsDevice>> response) {
                if (response.isSuccessful()) {
                    List<ResListFpsDevice> res = response.body();
                    System.out.println(res.size());
                    if(res.size() > 0){
                        listFpsDevices = res;
                        verify();
                    }else{
                        showError(getString(R.string.error_get_fp_by_device), null);
                    }


                } else {
                    System.out.println(response.code() + "   " + response.message());
                    showError(getString(R.string.error_get_fp_by_device), null);
                }
            }

            @Override
            public void onFailure(Call<List<ResListFpsDevice>> call, Throwable t) {
                showError(getString(R.string.error_get_fp_by_device), null);
            }
        });
    }

    private Result tourAllTheFp(byte[] fpFeat) {
        int i = 0;
        Result res = new Result(Bione.INITIALIZE_ERROR);
        do {
            try {
                res = untilTheFingerprintIsVerified(listFpsDevices.get(i).getFingerprint(), fpFeat);
                if ((Boolean) res.data && res.error == 0) {
//                   TODO: mostrar info del empleado
                    sendRegisterFp(listFpsDevices.get(i));
                    break;
                } else {
                    i++;
                }
            } catch (InterruptedException e) {
                break;
            }
        } while (i < listFpsDevices.size());

        return res;
    }


    private Result untilTheFingerprintIsVerified(String nameFingerprint, byte[] fpFeat) throws InterruptedException {
        final Result[] result = new Result[1];
        CountDownLatch latch = new CountDownLatch(1);
        getFp(nameFingerprint, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    byte[] fp;
                    Result resExtractFeature;
                    ResponseBody res = response.body();

                    try {
                        File imageFpInDevice = convertFp(res);
                        fiEm = FingerprintImage.fromFile(imageFpInDevice);

                        resExtractFeature = Bione.extractFeature(fiEm);
                        fp = (byte[]) resExtractFeature.data;
                        resExtractFeature = Bione.verify(fp, fpFeat);
                        result[0] = resExtractFeature;
                    } catch (IOException e) {
                        showError(getString(R.string.error_save_fp), e.toString());
                        result[0] = new Result(Bione.FAKE_FINGER, false);
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                } else {
                    showError(getString(R.string.error_get_fp), null);
                    result[0] = new Result(Bione.NO_FINGER, false);
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showError(getString(R.string.error_get_fp), " Mensaje: " + t.getMessage());
                result[0] = new Result(Bione.NO_RESULT, false);
                latch.countDown();
            }
        });

        latch.await();
        System.out.println(result[0].error);
        System.out.println(result[0].data);
        return result[0];
    }

    private Call<ResponseBody> getFp(String id, Callback<ResponseBody> callback) {
        Call<ResponseBody> call = apiservice.getFp(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    callback.onResponse(call, response);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
        return call;
    }


    private void saveFile(byte[] a) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(a, 0, a.length);
        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "imagen.jpg");

        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            sendFp(file);
        } catch (IOException e) {
            showError(getString(R.string.error_save_fp_to_send), e.getMessage());
            e.printStackTrace();
        }
    }

    private File convertFp(ResponseBody res) throws IOException {
        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "imagen.jpg");
        InputStream inputStream = res.byteStream();
        try {
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
            return file;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw e;
        }
    }


    private void sendRegisterFp(ResListFpsDevice resListFpsDevice) {
        System.out.println(resListFpsDevice.getEmployee().get_id());
        DataAuth dataAuth = new DataAuth(prefs.getSN(), "fingerprint", resListFpsDevice.getEmployee().get_id());
        Call<ResAuth> call = apiservice.sendAuthenticationMethod(dataAuth);
        call.enqueue(new Callback<ResAuth>() {
            @Override
            public void onResponse(Call<ResAuth> call, Response<ResAuth> response) {
                if (response.isSuccessful()) {
                    showtargetWithInfo(response.body());
                } else {
                    showFpNotFound();
                }
            }

            @Override
            public void onFailure(Call<ResAuth> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private void showtargetWithInfo(ResAuth res) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                target_message_fp.setVisibility(View.GONE);
                if(res.getEmployee().getProfile_picture().size() == 0){
                    image_employee.setImageResource(R.drawable.withoutemployee);
                }else{
                    String urlImg = prefs.getUrl().substring(0, prefs.getUrl().indexOf("/v1.0/api/"));
                    Glide.with(context).load(urlImg +res.getVehicle().getProfile_picture().get(0).getUrl()).into(image_employee);
                }
                name_employee.setText(res.getEmployee().getFirst_name());
                lastName_employee.setText(res.getEmployee().getFirst_lastname());
                dni_employee.setText(res.getEmployee().getDni());
                message_auth_employee.setText(res.getMessage());
                if (res.getMessage().equals("AUTENTICACIÓN EXITOSA")) {
                    message_auth_employee.setBackgroundColor(getResources().getColor(R.color.green_element));
                } else {
                    message_auth_employee.setBackgroundColor(getResources().getColor(R.color.red_element));
                }
            }
        });
    }


    private void showFpNotFound() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                target_message_fp.setVisibility(View.GONE);
                target_info_employee.setVisibility(View.GONE);
            }
        });

    }

}
