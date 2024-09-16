package fr.pilato.test.zombie.minio;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import com.carrotsearch.randomizedtesting.annotations.TimeoutSuite;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testcontainers.containers.MinIOContainer;

@RunWith(RandomizedRunner.class)
@TimeoutSuite(millis = 5 * 60 * 1000)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 10000) // 5 sec lingering
public class ZombieDemoIT {

    @Test
    public void testZombie() throws Exception {
        try (MinIOContainer container = new MinIOContainer("minio/minio")) {
            System.out.println("Starting Minio Container");
            container.start();

            try (MinioClient minioClient = MinioClient.builder()
                    .endpoint(container.getS3URL())
                    .credentials(container.getUserName(), container.getPassword())
                    .build()) {
                System.out.println("Starting Minio Client");
                minioClient.bucketExists(BucketExistsArgs.builder().bucket("foo").build());
            }
        }
    }
}
