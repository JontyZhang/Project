package rms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;

/**
 * 
 * @author :zjt
 * @time :2018年4月26日
 */
public class D123 {
    public static char findFirstRepeat(String A, int n) {
        HashSet hs=new HashSet();
        int length=A.length();
        //利用toCharArray()将String类型转化为char类型
        char[] a=A.toCharArray();
        for(int i=0;i < length;i++)
            {
            boolean b=hs.add(a[i]);//通过往hashset塞值（hashset不准有重复元素），判断当前一段数据中是否有重复元素，一但有，立刻返回
                if(b==false)
                {
                 return a[i];
            }
        }
        return'0';
    }
	public static void main(String []args){
//		String a ="sdjakjjj";
//		int n = a.length();
//		char b = findFirstRepeat(a, n);
//		System.out.println(b);
//		double a =1800.00;
//		System.out.println(a);
//		try {
//			InetAddress address = InetAddress.getLocalHost();
//			System.out.println(address.getHostAddress()+"00"+address.getHostName()+"000"+address.getAddress());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("asd我是");
	}
}
