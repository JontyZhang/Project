package rms;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.rms.common.cache.RedisUtil;

/**
 * 
 * @author :zjt
 * @time :2018年4月1日
 */
public class Test {
//	@Autowired
//	RedisUtil redisUtil;

	@org.junit.Test
	public void test() {
		
		//@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		RedisUtil redisUtil = (RedisUtil)context.getBean("redisUtil");
		System.out.println(redisUtil);
		redisUtil.set("test","123");
		if(redisUtil.exists("test")){
			System.out.println(redisUtil.get("test"));
		}
		
	}

}
