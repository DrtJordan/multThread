package com.bg.interfac.callback;

public class MyFetcher implements Fetcher {
	final Data data;

	public MyFetcher(Data data) {
		System.out.println("调用MyFetcher的构造函数");
		this.data = data;
	}

	/**
	 * 此方法接受一个对象
	 */
	@Override
	public void fetchData(final FetcherCallback callback) {
		try {
			//后端开启线程进程处理 处理结果调用callback返回给前端
			//正常情况
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("调用fetchData方法正常做了处理----task 比较耗时");
					
					data.setM(100);
					
					try {
						Thread.sleep(5000);
						callback.onData(data);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			
		} catch (Exception e) {
			//报错情况
			System.out.println("调用fetchData方法异常");
			callback.onError(e);
		}
	}

}
