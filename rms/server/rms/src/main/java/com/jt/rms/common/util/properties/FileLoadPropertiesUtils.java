package com.jt.rms.common.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jt.rms.common.ConfigMapUtils;
/**
 * 配置文件
 * @author zjt
 * @time 2017/12/20
 */
public class FileLoadPropertiesUtils {
	private static final Logger logger = Logger.getLogger(FileLoadPropertiesUtils.class);

	private static FileLoadPropertiesUtils FileLoadPropertiesUtils = new FileLoadPropertiesUtils();
    public static FileLoadPropertiesUtils getInstance() {
        return FileLoadPropertiesUtils;
    }
    public FileLoadPropertiesUtils() {
        String rootPath = "";
		rootPath = this.getClass().getClassLoader().getResource("").getPath();
        if(!isLinux()){
            rootPath = rootPath.substring(1);
        }
        initConfig( rootPath, "fileurl.properties"); // 
    }

    /**
     * @Description: 初始化轨迹基础数据配置信息
     *
     * @author:
     * @date: 2017/11/3
     */
    public void initConfig(String rootPath, String configName){
        Properties prop;
        InputStream is = null;
        String configPath = rootPath + File.separator + configName;
        try {
            is = new FileInputStream(new File(configPath));
        } catch (IOException e) {
            logger.error("配置文件读取错误！！！！");
            return;
        }
        prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            logger.error("配置信息读取错误！！！！", e);
        }
        Map<String,Object> map = Conversions(prop);
        ConfigMapUtils.fileConfig = map;
        
    }


    public static boolean isLinux() {
        String fileSeparator = System.getProperty("file.separator");
        return fileSeparator.equals("/");
    }

    /*
     *  Properties to map
     * */
    public Map<String,Object> Conversions(Properties prop){
        Map<String,Object> map = new HashMap<String ,Object>();
        Enumeration en = prop.propertyNames();
        while (en.hasMoreElements()){
            String key = (String) en.nextElement();
            try {
				map.put(key,new String(prop.get(key).toString().getBytes("ISO-8859-1"),"UTF-8") );
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
        }
        return map;
    }
}
