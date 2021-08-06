package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class ThreadDemo {

	public static void main(String[] args) {
		new ThreadDemo().run();

	}

	private void run() {
		try {
			ExecutorService service = Executors.newFixedThreadPool(10);
			for (int i = 0; i < 10; i++) {
				service.execute(new MyWorker());
			}
			service.shutdown();
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			System.out.println("Fertig");
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class MyWorker implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep((long)(Math.random() * 100));
				System.out.println("Thread " + Thread.currentThread().getId() +  " terminated");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
