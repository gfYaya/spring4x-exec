package com.smart.basic;

/*
  只有一个threadLocal变量的时候,thread.threadLocals这个ThreadLocalMap的size始终唯一,再加一个seqNumB变量之后,threadLocals的size可以变为2了.也就是说这个map存储的是该线程的所有threadLocal变量.
  added by intopass:
  每个线程的map是互相独立的,所以（线程+ThreadLocal）两个因素，可以定位一个值。所以一直在说ThreadLocal变量是一把钥匙（或者说一个号码牌）,
  有号码，如银行保险柜取东西即可.线程关了，等于银行关了，有钥匙也没用了.同一把钥匙，在不同的线程都可以使用,且不会相互冲突，因为只能去（当前线程）的map找.
 */
public class SequenceNumber {
    //private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
    static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        //通过匿名内部类覆盖initialValue()方法,指定初始值
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    static ThreadLocal<Integer> seqNumB = new ThreadLocal<Integer>(){
        //通过匿名内部类覆盖initialValue()方法,指定初始值
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //获取下一个序列值
    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    private static class TestClient extends Thread{
        private SequenceNumber sn;
        public TestClient(SequenceNumber sn){
            this.sn = sn;
        }

        public void run(){
            for(int i=0;i<3;i++){
                //System.out.println("Thread["+Thread.currentThread().getName()+"] sn["+sn.getNextNum()+"]");
                this.sn.seqNum.set(i);
                this.sn.seqNumB.set(i);
            }
            System.out.println("Thread["+Thread.currentThread().getName()+"] sn["+sn.seqNum.get()+"]");
            System.out.println("Thread["+Thread.currentThread().getName()+"] snB["+sn.seqNumB.get()+"]");
        }
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        //三个线程共享sn,各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }
}
