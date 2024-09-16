package fr.pilato.test.zombie.minio;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import com.carrotsearch.randomizedtesting.annotations.TimeoutSuite;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ConnectException;

@RunWith(RandomizedRunner.class)
@TimeoutSuite(millis = 5 * 60 * 1000)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 10000) // 5 sec lingering
public class ZombieDemoIT {

    @Test
    public void testZombie() throws Exception {
        System.out.println("Starting Minio Client");
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:8080")
                .credentials("foo", "bar")
                .build();
        try {
            minioClient.bucketExists(BucketExistsArgs.builder().bucket("foo").build());
        } catch (ConnectException expected) {
            // Expected
        }
        minioClient.close();
        System.out.println("Minio Client stopped");
    }
}
