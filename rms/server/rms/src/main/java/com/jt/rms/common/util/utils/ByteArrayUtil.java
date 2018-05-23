package com.jt.rms.common.util.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ByteArrayUtil {
	
	/***
	 * 得到文件byte字节流
	 * @param filePath
	 * @return
	 */
	public static byte[] getByteArrayOutputStream(String filePath){
		byte[] bytes = new byte[1024];
		InputStream ips = null;
		ByteArrayOutputStream output =null;
		try {
			ips = new BufferedInputStream(new FileInputStream(new File(filePath)));
			output = new ByteArrayOutputStream();
			int length=0;
			while((length=ips.read(bytes))!=-1){
				output.write(bytes, 0, length);
			}
			output.close();
			ips.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != output){
			return output.toByteArray();
		}
		return null;
	}
	
    public static void byte2File(byte[] buf, String filePath, String fileName){  
        BufferedOutputStream bos = null;  
        try{  
            File dir = new File(filePath);  
            if (!dir.exists() && dir.isDirectory()){  
                dir.mkdirs();  
            }
            bos = new BufferedOutputStream(new FileOutputStream( new File(filePath + File.separator + fileName)));  
            bos.write(buf);  
            bos.close();  
        }catch (Exception e){  
            e.printStackTrace();  
        } 
    } 
	
}
