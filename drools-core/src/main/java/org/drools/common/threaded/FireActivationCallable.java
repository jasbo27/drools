package org.drools.common.threaded;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.drools.common.DefaultAgenda;
import org.drools.spi.Activation;

public class FireActivationCallable implements Callable<String> {
    DefaultAgenda agenda;
    Activation activation;
    CountDownLatch latch;


    public FireActivationCallable() {
        super();
    }


    public FireActivationCallable(DefaultAgenda agenda, Activation activation,
                                  CountDownLatch latch) {
        super();
        this.agenda = agenda;
        this.activation = activation;
        this.latch = latch;
    }


    public String call() throws Exception {
        try {
            agenda.fireActivation(activation);
        } finally {
            latch.countDown();
        }
        return null;
    }

}
