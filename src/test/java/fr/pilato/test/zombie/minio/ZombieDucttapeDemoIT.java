package fr.pilato.test.zombie.minio;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import com.carrotsearch.randomizedtesting.annotations.TimeoutSuite;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rnorth.ducttape.timeouts.Timeouts;
import org.testcontainers.containers.MinIOContainer;

import java.util.concurrent.TimeUnit;

@RunWith(RandomizedRunner.class)
@TimeoutSuite(millis = 5 * 60 * 1000)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 10000) // 5 sec lingering
public class ZombieDucttapeDemoIT {

    @Test
    public void testZombie() throws Exception {
        Timeouts.doWithTimeout(1, TimeUnit.SECONDS, () -> {
            System.out.println("Hello world!");
        });
    }
}
