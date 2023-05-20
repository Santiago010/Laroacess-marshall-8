package cn.com.aratek.demo.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfRetrofit {
    public  Prefs prefs;

    public  OkHttpClient.Builder httpClient;

    public  ConfRetrofit(Context context,String token){
        this.prefs = new Prefs(context);
        httpClient = new OkHttpClient.Builder();
        initInterceptor(token);
    }

    private void initInterceptor(String token){
        httpClient.addInterceptor(new Interceptor() {

            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(request);
            }
        });
    }


    private GsonConverterFactory makeConfGson() {
        Gson gsonC = new GsonBuilder().create();
        GsonConverterFactory gsonConverterFactoryC = GsonConverterFactory.create(gsonC);
        return gsonConverterFactoryC;
    }

    public Retrofit makeConfRequest() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(prefs.getUrl())
                .addConverterFactory(makeConfGson()).client(httpClient.build()).build();
        return retrofit;
    }
}
