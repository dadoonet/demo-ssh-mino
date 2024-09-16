package fr.pilato.test.zombie.ducctape;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakLingering;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rnorth.ducttape.timeouts.Timeouts;

import java.util.concurrent.TimeUnit;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.SUITE)
@ThreadLeakLingering(linger = 200) // 200 ms lingering
public class ZombieDucttapeDemoIT {

    @Test
    public void testZombie() throws Exception {
        Timeouts.doWithTimeout(1, TimeUnit.SECONDS, () -> {
            System.out.println("Hello world!");
        });

        // Sadly we can not call
        // Timeouts.EXECUTOR_SERVICE.shutdown();
    }
}
