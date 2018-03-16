package materialcalc.house.godbeom.com.retrofit2.interceptor;


import java.io.IOException;

import materialcalc.house.godbeom.com.retrofit2.utill.ApiLog;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


public class LoggerIntercepter implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();

        long startMills = System.currentTimeMillis();
        ApiLog.i("[->][" + method + "] Request : " + request.url());

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if( copy.body() != null ) {
                copy.body().writeTo(buffer);
                ApiLog.i("[->]["+method+"] RequestBody : " + buffer.readUtf8());
            }
        } catch (final IOException e) {
            // do nothing
        }

        Response response = chain.proceed(request);
        String body = response.body().string();

        long endMills = System.currentTimeMillis();
        ApiLog.i("[<-][" + method + "] Response : " + request.url() + " (Spend Time : " + (endMills-startMills) + ")\n" + body);

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), body))
                .message(body)
                .build();
    }
}
