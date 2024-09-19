package fr.pilato.test.zombie.okhttp;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 1000) // 1s lingering
public class ZombieOkHttpIT {

    @Test
    public void testZombie() throws Exception {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(1, 100, TimeUnit.MILLISECONDS))
                .connectTimeout(100, TimeUnit.MILLISECONDS)
                .writeTimeout(100, TimeUnit.MILLISECONDS)
                .readTimeout(100, TimeUnit.MILLISECONDS)
                .callTimeout(100, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:8080")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("response = " + response.body().string());
        } catch (ConnectException e) {
            System.out.println("You need to start a minio service first:");
            System.out.println("docker run -p 8080:80 httpd");
        }

        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }
}
