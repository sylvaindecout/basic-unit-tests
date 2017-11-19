package test.sdc.jupiter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/*
This class has been created so that tests are taken into account by the build, as the logic to identify integration tests is too different between JUnit4 (*IT.java) and Jupiter (@Tag per method).
In a 100%-Jupiter project, all tests of this class could be moved into PricingServerJupiterTest.
*/
class PricingServerJupiterIT {

    /* This is a fake test used to illustrate the distinction between unit tests and integration tests. */
    @Test
    @Tag("integration")
    void should_be_long()
            throws InterruptedException {
        assertTimeout(ofSeconds(2), () -> Thread.sleep(1_000));
    }

}
