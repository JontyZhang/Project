package com.jt.rms.common.util.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

public class FileOperateUtil {
	
	//表单提交
	public static void download(HttpServletRequest request,HttpServletResponse response,String fileName,byte[] bytes) throws Exception {
			request.setCharacterEncoding("UTF-8");
			BufferedOutputStream bos = null;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachement;filename="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));		
			bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(bytes);
			bos.close();		
	}
	
	public static boolean deleteFile(HttpServletRequest request, String fileName){
		boolean exeResult = true;
		String uploadFilePath = request.getSession().getServletContext().getRealPath("/") + "upload"+File.separator;
		String filePath = uploadFilePath +  fileName;
		try {
			File file = new File(filePath);
			if(file.exists()){
				//文件存在
				exeResult = file.delete();
			}
		} catch (Exception e) {
			exeResult = false;
		}
		return exeResult;
	}
	
	
	public static void fileCopyPath(String fileName,String oldLocation,String newLocation){
		try {
	 	    // 文件原地址
			File oldFile = new File(oldLocation, fileName);
			// 文件新（目标）地址
			File fnewpath = new File(newLocation);
			// 判断文件夹是否存在
			if (!fnewpath.exists())
				fnewpath.mkdirs();
			// 将文件移到新文件里
			File destFile = new File(newLocation + fileName );
			FileUtils.copyFile(oldFile, destFile);
			oldFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 备份文件
	 * @param input
	 * @param buf
	 */
	public static void backupFile(String backFilePath,byte[] buf){
		try {
			File input = new File(backFilePath);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
			FileUtils.copyInputStreamToFile(inputStream, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
