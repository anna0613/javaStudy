package lock;

/**
 * Created by Administrator on 2017/2/22.
 */
public class Counter {
    public static volatile int count = 0;

    public static synchronized void inc() {//
//        synchronized(this) {
//            int count = 0;
            //这里延迟1毫秒，使得结果明显
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            count++;
            System.out.println(count);
//        }
    }

    public static void main(String[] args) throws InterruptedException{

        //主线程等待子线程结束后再执行 CountDownLatch
        int threadNumber = 1000;
//        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);

        //同时启动1000个子线程，去进行i++计算，看看实际结果
//        Counter c = new Counter();
synchronized (Counter.class) {
    for (int i = 0; i < threadNumber; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                    c.inc();
                Counter.inc();
//                countDownLatch.countDown();
            }
        }).start();
    }
}

//        countDownLatch.await();

        //这里每次运行的值都有可能不同,可能为1000  主线程
        System.out.println("运行结果:Counter.count=" + Counter.count);
    }
}
