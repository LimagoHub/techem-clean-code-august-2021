package demo;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.techem.services.ArrayServiceImpl;
import de.techem.services.TimeMesuareSericeImpl;
import de.techem.services.TimeMesuareService;



public class Main {

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE >> 1;
	private int data[] = new int[MAX_ARRAY_SIZE];

	public static void main(String[] args) {
		new Main().run();

	}

	private void run() {
		
		TimeMesuareService service = new TimeMesuareSericeImpl();
		ArrayServiceImpl arrayService = new ArrayServiceImpl(service);
		arrayService.fillArrayWithRandomNumbersParallel(data);
		
	}
	
	
}
