package demo;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Main {

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE >> 1;
	private int data[] = new int[MAX_ARRAY_SIZE];

	public static void main(String[] args) {
		new Main().run();

	}

	private void run() {
		try {
			final Random random = new Random();
			final int availableProcessors = Runtime.getRuntime().availableProcessors();

			for (int threadCount = 1; threadCount <= availableProcessors + 1; threadCount++) {

				final Instant start = Instant.now();
				
				final ExecutorService service = Executors.newFixedThreadPool(threadCount);
				
				for(int currentThreadNumber = 0; currentThreadNumber < threadCount ; currentThreadNumber++) {
					final int segmentSize = MAX_ARRAY_SIZE / threadCount;
					final int segmentStart = currentThreadNumber * segmentSize;
					final int segmentEnd = (currentThreadNumber + 1) * segmentSize;
					
					service.execute(()->lamdaDemo(segmentStart, segmentEnd));
				}
				
				
				service.shutdown();
				service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
				
				final Instant ende = Instant.now();
				final Duration duration = Duration.between(start, ende);
				System.out.println("Duration with " + threadCount + " was " + duration.toMillis());
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	private void lamdaDemo(final int start,final  int end) {
		final Random random = new Random();
		
		
			for(int i = start; i < end; i++) {
				data[i] = random.nextInt();
			}
			
		
	}
	
	class MySegmentFillerWorker implements Runnable {
		
		private final Random random = new Random();
		
		public MySegmentFillerWorker(final int start,final  int end) {
			this.start = start;
			this.end = end;
		}



		private final int start;
		private final int end;
		
		
		
		@Override
		public void run() {
			for(int i = start; i < end; i++) {
				data[i] = random.nextInt();
			}
			
		}
	}

}
