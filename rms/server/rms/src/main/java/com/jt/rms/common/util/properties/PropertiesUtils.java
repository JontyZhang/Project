package com.jt.rms.common.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 *
 * @author zjt
 * @time   2017/12/20
 */
public class PropertiesUtils {


	private static final Logger logger = Logger.getLogger(PropertiesUtils.class);
	private static Properties properties;
	private static String CONFIG_FILE = "/properties/config.properties";
	
	static {
		InputStream in = PropertiesUtils.class.getResourceAsStream(CONFIG_FILE);
		try {
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
	
	public static String getValue(String key){
		return properties.getProperty(key);
	}
	
	
	public static String getNewestValue(String key){
		String value = "";
		InputStream in = null;
		Properties p = new Properties();
		try {
			in = Object.class.getResourceAsStream(CONFIG_FILE);   
			p.load(in);
			value = p.getProperty(key);
		} catch (IOException e) {
			logger.error("",e);
		}finally{
			if(in != null ){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("",e);
				}
				in = null;
			}
			p = null;
		}
		return value;
	}
}
