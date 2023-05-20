package cn.com.aratek.demo.featuresrequest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APISERVICE {

    //TODO: employee
    @Headers("Content-Type: application/json")
    @GET("employee")
    Call<List<ResListEmployee2>> getEmploye();

    //TODO: fingerprint
    @Multipart
    @POST("fingerprint")
    Call<ResNewFp> sendFp(@Part("employee") RequestBody employee, @Part MultipartBody.Part fingerprint);

    @GET("fingerprint")
    Call<List<ResListFp>> getFps(@Query("employeeId") String employeeId);

    @GET("fingerprint/{id}")
    Call<ResponseBody> getFp(@Path("id") String id);

    @GET("fingerprint")
    Call<List<ResListFpsDevice>> getFpsDevice(@Query("sn") String sn);


    @POST("authentication-record")
    Call<ResAuth> sendAuthenticationMethod(@Body DataAuth dataAuth);

    //TODO AUTH
    @POST("auth")
    Call<Token> sendDataCoordinator(@Body DataLoginCoordinator dataLoginCoordinator);

    @PATCH("device")
    Call<resSn> sendSn(@Query("sn") String  sn, @Body DataSn dataSn);
}
