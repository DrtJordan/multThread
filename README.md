# multThread
多线程
1、银行转账
2、多线程信号量通信
3、线程池、固数量线程池、带有缓存的线池、单线程池
4、Lock
5、读写锁、读的时候加读锁、写的时候加写锁、写锁的权限大于读锁,写锁可以降级为读锁，写锁只允许一个，读锁可以同时获取，以便支持高并发
java5线程并发库
	1、原子类型
	2、线程池
	3、Callable和Future
	4、Lock和Condition
	5、ReentrantReadWriteLock	读写锁与互斥锁定相比，读-写锁定允许对共享数据进行更高级别的并发访问。虽然一次只有一个线程（writer 线程）可以修改共享数据，但在许多情况下，任何数量的线程可以同时读取共享数据（reader 线程），读-写锁定利用了这一点。从理论上讲，与互斥锁定相比，使用读-写锁定所允许的并发性增强将带来更大的性能提高。在实践中，只有在多处理器上并且只在访问模式适用于共享数据时，才能完全实现并发性增强
	6、Condition-->类似与wait和notify	这个Condition必须与类一起使用，使用自己的await和signal进行线程间通讯
	7、 Semaphore----sp.acquire();sp.release(); ->信号灯--->[实现可用资源的统一管理]实现线程之间的互斥，与syn不同的是它自己获取的锁可以由其他线程释放--->构造方法中可以选择调度的原则可以实现公平调度
	8、CyclicBarrier----cb.await();->线程同步工具类		必须所有的线程都同时执行结束之后才能进行下一步操作[类似与一群约定到某个地点集合之后一起出发]
	9、CountdownLatch--->计数器归零了开始执行 --->教练发号施令，运动员开始跑--->全部结束后教练收到结果
	10、Exchanger--->两个线程之间进行数据交换
	11、final BlockingQueue queue = new ArrayBlockingQueue(3);--->阻塞队列
	12、	Collections.synchronizedMap(m)-->同步集合
	14、Disruptor--->生产消费模型
