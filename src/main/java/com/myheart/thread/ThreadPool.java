package com.myheart.thread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yangxin
 * @date 2018/12/10
 */
public class ThreadPool {

    /**
     * 核心线程数 默认为1
     */
    private int coreThreadSize = 1;

    /**
     * 最大线程数 默认为1
     */
    private int maxThreadSize = 1;

    /**
     * 当前存在的线程数
     */
    private int currentSize;
    /**
     * 阻塞任务队列
     */
    private BlockingQueue<Runnable> queue;

    /**
     * 工作线程缓存
     */
    private Set<Worker> workQueue = new HashSet<Worker>();

    /**
     * 空闲线程队列
     */
    private List<Worker> workerFrees = new ArrayList<Worker>();

    /**
     * 线程池启动状态 默认未启动
     */
    private Boolean RUNNING = false;

    public ThreadPool() {
        this(0,0);
    }

    public ThreadPool(int coreThreadSize, int maxThreadSize) {
        this(coreThreadSize,maxThreadSize,100);
    }
    public ThreadPool(int coreThreadSize, int maxThreadSize, int queueSize) {
        if(coreThreadSize!=0){
            this.coreThreadSize = coreThreadSize;
        }
        if(maxThreadSize!=0){
            this.maxThreadSize = maxThreadSize;
        }
        this.queue = new ArrayBlockingQueue<Runnable>(queueSize);
    }


    public void start(){
        RUNNING = true;
    }
    public void shutdown() {
        RUNNING = false;
    }

    public Boolean submit(Runnable runnable) throws Exception{
        if(!RUNNING || !addWorker(runnable)){
            throw  new Exception();
        }
        currentSize++;
        return true;
    }

    private Boolean addWorker(Runnable runnable){
        Worker worker = null;
        if(!workerFrees.isEmpty()){
            worker = workerFrees.remove(0);
            worker.setTask(runnable);
            worker.start();
            return true;
        }else if(currentSize < maxThreadSize){
            worker = new Worker(runnable);
            workQueue.add(worker);
            return true;
        }
        return addTask(runnable);
    }

    private Boolean addTask(Runnable runnable){
        return queue.offer(runnable);
    }

    class Worker implements Runnable {

        private Boolean STARTING = false;

        private Runnable task = null;

        public void setTask(Runnable task) {
            this.task = task;
        }

        public Worker(Runnable task) {
            queue.offer(task);
            start();
        }

        public void start(){
            STARTING = true;
        }
        public void shutdown(){
            STARTING = false;
        }

        @Override
        public void run() {
            while (RUNNING){
                if(STARTING){
                    //临时任务不空或者从队列获得任务成功
                    if (task != null || ((task = queue.peek()) != null && queue.remove(task))) {
                        task.run();
                    }
                    task = null;
                    workerFrees.add(this);
                    shutdown();
                }
            }
        }
    }

}
