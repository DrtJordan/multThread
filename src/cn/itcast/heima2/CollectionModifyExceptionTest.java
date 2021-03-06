package cn.itcast.heima2;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 
 * Description: CopyOnWriteArrayList 同步集合---普通的集合是不能进行运行时修改的
 * Created on:  2016年4月1日 下午6:13:07 
 * @author bbaiggey
 */
public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
//		本身带有写锁   先copy一个容器 之后再新copy的容器中进行add add之后将原容器的引用指向新的容器
		Collection users = new CopyOnWriteArrayList();
	
			
			//new ArrayList();
		users.add(new User("张三",28));	
		users.add(new User("李四",25));			
		users.add(new User("王五",31));	
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("aaaa");
			User user = (User)itrUsers.next();
			if("李四".equals(user.getName())){
				users.remove(user);
				//itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}
	}
}	 
