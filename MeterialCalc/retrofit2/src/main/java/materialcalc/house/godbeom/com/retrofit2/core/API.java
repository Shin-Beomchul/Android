package materialcalc.house.godbeom.com.retrofit2.core;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.reflect.Type;
import java.util.Date;

import materialcalc.house.godbeom.com.retrofit2.define.Server;
import materialcalc.house.godbeom.com.retrofit2.interceptor.LoggerIntercepter;
import materialcalc.house.godbeom.com.retrofit2.utill.ApiLog;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class API {

    public static volatile API mInstance = null;

    private Context mContext;               // for SharedPreference
    private Server mServerType;             // ServerType

    private Retrofit mRetrofit;

    public static API instance() {
        if( mInstance == null ) {
            synchronized (API.class) {
                if( mInstance == null ) {
                    mInstance = new API();
                }
            }
        }
        return mInstance;
    }

    public Retrofit retrofit() {
        return mRetrofit;
    }

    public Context context() {
        return mContext;
    }

    public Server getServerType() {
        return mServerType;
    }

    public API initialize(Context context, Server defaultServerType) {
        mContext = context;
        mServerType = Server.getCurrentServerType(context);
        if( mServerType == null ) {
            mServerType = defaultServerType;
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mServerType.getApiUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(generateGsonFactory())
                .client(generateOkHttpClient())
                .build();


        ApiLog.i("[Retrofit2 API] ServerType : " + mServerType + " is initialize !!");
        return this;
    }


    private OkHttpClient generateOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new LoggerIntercepter());
        builder.addNetworkInterceptor(new StethoInterceptor());
        return builder.build();
    }

    private GsonConverterFactory generateGsonFactory()  {
        Gson gson = new GsonBuilder()
                .setLenient()
//                .registerTypeAdapter(Date.class, new DateTypeDeserializer())//FIXME
                .create();

        return GsonConverterFactory.create(gson);
    }

    private class DateTypeDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            String date = json.getAsString();
            return new Date(Long.parseLong(date));
        }
    }

    public Context getAppContext() {
        return mContext.getApplicationContext();
    }

}
