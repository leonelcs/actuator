package com.leonel.actuator.tools;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TPSHealth implements HealthIndicator {
	
	TPSCounter counter;

	@Override
	public Health health() {
		boolean health = counter.isWeak(); // perform some specific health check
        if (health) {
            return Health.outOfService().withDetail("Too many requests", "OutofService").build();
        }
        return Health.up().build();

	}
	
	public void updateTx() {
		if(counter == null || counter.isExpired()){
		      counter = new TPSCounter();
		      
	    }
	    counter.increment();

	}

}
