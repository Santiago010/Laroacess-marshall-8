package cn.com.aratek.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.com.aratek.demo.utils.Prefs;

public class ChangeHost extends AbstractBaseActivity {

    Button btn_finish;
    EditText host;
    EditText SN;
    Button save_host;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_host);
        prefs = new Prefs(this);
        btn_finish = findViewById(R.id.btn_finish);
        addClickListenerBtnFinish();
        host = findViewById(R.id.host);
        host.setText(prefs.getUrl());
        SN = findViewById(R.id.sn);
        SN.setText(prefs.getSN());
        save_host = findViewById(R.id.save_host);
        addClickListenerSaveHost();
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

    private void addClickListenerSaveHost(){
        save_host.setOnClickListener(v -> {
            if(host.length() == 0 ){

                Toast.makeText(this,"Te falta algun campo por llenar",Toast.LENGTH_SHORT).show();
                return;
            }else {
                prefs.saveUrl(String.valueOf(host.getText()) );
                Toast.makeText(this,"Server cambiado con exito",Toast.LENGTH_LONG);

            }
        });
    }
}