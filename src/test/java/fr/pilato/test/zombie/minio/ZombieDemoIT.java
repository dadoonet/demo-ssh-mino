package fr.pilato.test.zombie.minio;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ConnectException;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 200) // 200 ms lingering
public class ZombieDemoIT {

    @Test
    public void testZombie() throws Exception {
        try (MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("minioadmin", "minioadmin")
                .build()) {
            System.out.println("Starting Minio Client");
            minioClient.bucketExists(BucketExistsArgs.builder().bucket("foo").build());
        } catch (ConnectException e) {
            System.out.println("You need to start a minio service first:");
            System.out.println("docker run -p 9000:9000 minio/minio server /tmp");
        }
    }
}
