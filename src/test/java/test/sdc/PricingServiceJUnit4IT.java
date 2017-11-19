package test.sdc;

import org.junit.Test;

/* This is a fake test used to illustrate the distinction between unit tests and integration tests. */
public class PricingServiceJUnit4IT {

    @Test(timeout = 2_000)
    public void should_be_long()
            throws InterruptedException {
        Thread.sleep(1_000);
    }

}