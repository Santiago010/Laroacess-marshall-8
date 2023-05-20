package cn.com.aratek.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.aratek.demo.featureslist.FingerprintAdapter;
import cn.com.aratek.demo.featuresrequest.APISERVICE;
import cn.com.aratek.demo.featuresrequest.ResListEmployee2;
import cn.com.aratek.demo.utils.ConfRetrofit;
import cn.com.aratek.demo.utils.Prefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployees extends AbstractBaseActivity {

    Button btn_finish;
    EditText dni_filter;
    ProgressBar progress_employees;
    RecyclerView list_employees;
    Prefs prefs;
    ConfRetrofit confRetrofit;
    APISERVICE apiservice;

    FingerprintAdapter mFingerprintAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employees);
        prefs = new Prefs(this);
        confRetrofit = new ConfRetrofit(this, prefs.getTOKEN());
        apiservice = confRetrofit.makeConfRequest().create(APISERVICE.class);

        btn_finish = findViewById(R.id.btn_finish);
        addClickListenerBtnFinish();
        dni_filter = findViewById(R.id.dni_filter);
        progress_employees = findViewById(R.id.progress_employees);
        list_employees = findViewById(R.id.list_employees);
        makeFingerAdapter();

    }

    private void addClickListenerBtnFinish() {
        btn_finish.setOnClickListener(v -> {
            finish();
        });
    }

    private void makeFingerAdapter() {
        View.OnClickListener clickListener = view -> {
            ResListEmployee2 item = (ResListEmployee2) view.getTag();
            if (item.getDni().equals("DNI")) {
            } else {
                navigateTo(item, this);
            }

        };

        mFingerprintAdapter = new FingerprintAdapter(new ArrayList<>(), clickListener);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list_employees.getContext(), LinearLayoutManager.VERTICAL);

        list_employees.addItemDecoration(dividerItemDecoration);
        list_employees.setAdapter(mFingerprintAdapter);

        getEmployes();
    }

    private void navigateTo(ResListEmployee2 item, Context context) {
        Intent intent = new Intent(context, FingerprintScannerDemo.class);
        intent.putExtra("DATAEMPLOYEE", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        getEmployes();
    }

    @Override
    protected void open() {

    }

    @Override
    protected void close() {

    }

    private void addListenerSearchDNI() {
        dni_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String texto = charSequence.toString();
                if (texto.isEmpty()) {
                    getEmployes();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String dni = editable.toString(); // Obtiene el texto actual en el EditText
                int i = 0, indexDNI;
                while (i < mFingerprintAdapter.data.size()) {
                    if (mFingerprintAdapter.data.get(i).getDni().equals(dni)) {
                        indexDNI = i;
                        makeListEmployeewithDni(indexDNI);
                        break;
                    } else {
                        i++;

                    }
                }

            }
        });
    }

    private void makeListEmployeewithDni(int i) {
        ArrayList tempList = new ArrayList<>();
        tempList.add(defineColumnsListEmployee());
        tempList.add(mFingerprintAdapter.data.get(i));
        mFingerprintAdapter.data = tempList;
        mFingerprintAdapter.notifyDataSetChanged();
    }


    private void getEmployes() {
        Call<List<ResListEmployee2>> call = apiservice.getEmploye();

        call.enqueue(new Callback<List<ResListEmployee2>>() {
            @Override
            public void onResponse(Call<List<ResListEmployee2>> call, Response<List<ResListEmployee2>> response) {
                if (response.isSuccessful()) {
                    List<ResListEmployee2> res = new ArrayList<>();
                    res.add(defineColumnsListEmployee());
                    for (ResListEmployee2 users : response.body()) {
                        res.add(users);
                    }
                    mFingerprintAdapter.data = res;
                    progress_employees.setVisibility(View.GONE);
                    addListenerSearchDNI();
                    mFingerprintAdapter.notifyDataSetChanged();
                } else {
                    System.out.println("FALLO AL TRAER LOS EMPLEADOS " + response.code() + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ResListEmployee2>> call, Throwable t) {
                System.out.println("EMPLOYES-FAILURE" + t);
            }
        });
    }

    private ResListEmployee2 defineColumnsListEmployee() {
        ResListEmployee2 columns = new ResListEmployee2();
        columns.set_id("0");
        columns.setFirst_name("Nombre");
        columns.setFirst_lastname("Apellido");
        columns.setDni("DNI");
        columns.setEmail("");
        columns.setPhone("");
        columns.setCity(new ResListEmployee2.City());
        columns.getCity().setName("");
        columns.getCity().setCountry(new ResListEmployee2.City.Country());
        columns.getCity().getCountry().setName("");
        return columns;
    }
}