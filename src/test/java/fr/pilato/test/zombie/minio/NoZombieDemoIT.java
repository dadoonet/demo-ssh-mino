package fr.pilato.test.zombie.minio;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import com.carrotsearch.randomizedtesting.annotations.TimeoutSuite;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RandomizedRunner.class)
@TimeoutSuite(millis = 5 * 60 * 1000)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 10000) // 5 sec lingering
public class NoZombieDemoIT {

    @Test(expected = java.net.ConnectException.class)
    public void testZombie() throws Exception {
        System.out.println("Starting Minio Client");
        try (MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:8080")
                .credentials("foo", "bar")
                .build()) {
            minioClient.bucketExists(BucketExistsArgs.builder().bucket("foo").build());
        }
    }
}
