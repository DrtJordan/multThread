package cn.itcast.heima2;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 
 * Description: CopyOnWriteArrayList ͬ������---��ͨ�ļ����ǲ��ܽ�������ʱ�޸ĵ�
 * Created on:  2016��4��1�� ����6:13:07 
 * @author bbaiggey
 */
public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
//		�������д��   ��copyһ������ ֮������copy�������н���add add֮��ԭ����������ָ���µ�����
		Collection users = new CopyOnWriteArrayList();
	
			
			//new ArrayList();
		users.add(new User("����",28));	
		users.add(new User("����",25));			
		users.add(new User("����",31));	
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("aaaa");
			User user = (User)itrUsers.next();
			if("����".equals(user.getName())){
				users.remove(user);
				//itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}
	}
}	 
