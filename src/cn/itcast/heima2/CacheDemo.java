package cn.itcast.heima2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * Description: 读写锁  Lock
 * <p>This lock supports a maximum of 65535 recursive write locks
 * and 65535 read locks. Attempts to exceed these limits result in
 * {@link Error} throws from locking methods.
 * Created on:  2016年3月23日 下午11:18:04 
 * @author bbaiggey
 */
public class CacheDemo {

	private Map<String, Object> cache = new HashMap<String, Object>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new CacheDemo().getData("hello"));

	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public  Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					if(value==null){	//判断第一个线程是否拿到了数据 ，如果拿到了其他线程就释放写锁，获得并发读锁
						value = "aaaa";//实际是去queryDB();
//						查询结束之后加入缓存中
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
