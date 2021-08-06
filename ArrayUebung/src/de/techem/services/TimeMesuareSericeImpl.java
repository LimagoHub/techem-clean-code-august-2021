package de.techem.services;

import java.time.Duration;
import java.time.Instant;


/**
 * Messe die Laufzeit von run
 * 
 * @author JoachimWagner
 *
 */
public class TimeMesuareSericeImpl implements TimeMesuareService {
	@Override
	public void timeMesuareDecorator(Runnable runnable) {
		final Instant start = Instant.now();

		runnable.run();

		final Instant ende = Instant.now();
		final Duration duration = Duration.between(start, ende);
		System.out.println("Duration with  was " + duration.toMillis());
	}
}
