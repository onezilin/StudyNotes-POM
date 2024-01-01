package com.studynotes.demo03_lock;

/**
 * Description: ZooKeeper 锁接口
 */
public interface Demo01_Lock {

    /**
     * Description:  加锁方法
     *
     * @return boolean 是否成功加锁
     */
    void lock() throws Exception;


    /**
     * Description:  解锁方法
     *
     * @return boolean 是否成功解锁
     */
    void unlock();
}
