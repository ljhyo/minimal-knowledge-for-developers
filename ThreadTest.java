import java.util.ArrayList;
import java.util.List;

class Consumer implements Runnable {

	private List<String> data;
	private int threadID = 0;

	public Consumer(int threadID, List<String> data) {

		this.data = data;
		this.threadID = threadID;
	}

	@Override
	public void run() {

		String myData;

		while (true) {

			try {

				// 0.1초 쉬기
				Thread.sleep(100);
				
				// 데이터가 비어있지 않을 경우에만 데이터 가져오기
				if (!data.isEmpty()) {

					// 첫 번째 배열의 값 가져오기
					myData = data.get(0);

					// 공유 자원에서 가져온 데이터 출력
					System.out.println("[Thread " + threadID + "] : " + myData + "번 데이터를 가져옵니다.");

					// 첫 번째 배열 삭제
					data.remove(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Producer implements Runnable {
	private List<String> data;

	public Producer(List<String> data) {
		this.data = data;
	}

	@Override
	public void run() {

		// 1부터 10까지 10개 데이터를 순차적으로 삽입
		for (int i = 1; i <= 10; i++) {
			data.add(String.valueOf(i));
		}
	}
}

public class ThreadTest {

	public static void main(String[] args) {

		// 공유 자원 생성
		List<String> data = new ArrayList<>();

		// 스레드 생성
		Thread producerThread = new Thread(new Producer(data));
		Thread consumerThread1 = new Thread(new Consumer(1, data));
		Thread consumerThread2 = new Thread(new Consumer(2, data));

		// 스레드 실행
		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();
	}
}