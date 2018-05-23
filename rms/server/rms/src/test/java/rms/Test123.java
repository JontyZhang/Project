package rms;

import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @author :zjt
 * @time :2018年3月21日
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration({"classpath:spring/*.xml"})
public class Test123 {

	 	@Autowired
//	    private RedisTemplate redisTemplate;
	 	private static ApplicationContext ac;
	 	
	 	static{
	 		 ac = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
	 	}

	    private static final Logger log  = Logger.getLogger(Test123.class.getName());

	    @SuppressWarnings("unchecked")
		@Test
	    public void test(){
	    	RedisTemplate redisTemplate = (RedisTemplate) ac.getBean("redisTemplate");
	        redisTemplate.opsForValue().set("123", "123");
	        log.info("value："+redisTemplate.opsForValue().get("chen"));
	    }

}
