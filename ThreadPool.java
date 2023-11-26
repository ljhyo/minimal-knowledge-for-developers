import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) throws Exception {

        // 최소 스레드 풀의 개수
        int CORE_POOL_SIZE = 2;

        // 최대 스레드 풀의 개수
        int MAX_POOL_SIZE = 5;
        // 최소 스레드 풀의 개수로 돌아가기 위해 대기하는 시간
        int KEEP_ALIVE_TIME = 10;

        // 대기 큐 생성
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10);

        // 스레드 풀 객체 생성
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, queue);

        // 스레드 풀에 10개 작업 할당
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Task());
        }

        // 스레드 풀에 작업을 할당하지 않고, 이미 할당된 스레드는 작업이 완료될 때까지 기다린 후 스레드 풀 종료
        threadPool.shutdown();
    }

    // 작업 실행 클래스
    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "번 스레드 작업 수행 완료");
        }
    }
}