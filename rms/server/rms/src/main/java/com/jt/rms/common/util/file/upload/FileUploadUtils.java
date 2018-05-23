package com.jt.rms.common.util.file.upload;

import java.io.IOException;
import java.util.List;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.syscommon.entity.FileDto;

/**
 * 文件上传的工具类
 * @author zjt
 * @time 2017/12/20
 */
public class FileUploadUtils {
	//保存图片至服务器
	public String savePhotoToSer(MultipartFile picFile){
		String filePath=null;
		// 拼接服务区的文件路径  
	    StringBuffer sbPath = new StringBuffer();  
	    sbPath.append("http://192.168.52.135");  
		if(picFile.isEmpty()){
			throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(),Code.EMPTY_PARAME.getErrorMessage());
		}
		//上传至图片服务器
		try{
			//取图片名字
			String originalFilename =picFile.getOriginalFilename();
			//取扩展名，不要"."
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			//初始化文件资源
			ClientGlobal.init("classpath:properties/fdfs_client.conf");
			//链接FastDFS服务器，创建tracker和Stroage  
			 TrackerClient trackerClient = new TrackerClient();  
		     TrackerServer trackerServer = trackerClient.getConnection();  
		     StorageServer storageServer = null;  
		     //最终操作对象
		     StorageClient storageClient = new StorageClient(trackerServer,  
		                storageServer);  
		     //利用字节流上传文件
		     String[] strings =storageClient.upload_file(picFile.getBytes(), extName, null);
		     for (String  string : strings) {
		    	 sbPath.append("/" + string);  
		         System.out.println(string);  
			}
		     //全路径
		     System.out.println(sbPath);
		}catch(IOException | MyException e){
			e.printStackTrace();  
		}
		//生成String 的path
		filePath=sbPath.toString();
		return filePath;
	 }
		/**
		 * 保存图片
		 * 返回图片路径
		 * @return
		 * @throws MyException 
		 * @throws IOException 
		 */	
	public String insertPhotoToSer (FileDto params) throws IOException, MyException {		
			String filePath=null;
			// 拼接服务区的文件路径  
		    StringBuffer sbPath = new StringBuffer();  
		    sbPath.append("http://192.168.52.135:8889");  
			if(params==null){
				throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(),Code.EMPTY_PARAME.getErrorMessage());
			}		
			try{
				 ClientGlobal.init("D:\\eclipse-work\\rms\\src\\main\\resources\\properties\\fdfs_client.conf");
				//链接FastDFS服务器，创建tracker和Stroage  
				 TrackerClient trackerClient = new TrackerClient();  
			     TrackerServer trackerServer = trackerClient.getConnection();  
			     StorageServer storageServer = null;  
			     //最终操作对象
			     StorageClient storageClient = new StorageClient(trackerServer,  
			                storageServer);  
			     //利用字节流上传文件
			     String[] strings =storageClient.upload_file(params.getFiles(), params.getExtName(), null);
			     for (String  string : strings) {
			    	 sbPath.append("/" + string);  
			         System.out.println(string);  
				}
			     //全路径
			     System.out.println(sbPath);
			}catch(IOException | MyException e){
				e.printStackTrace();  
			}
			filePath=sbPath.toString();
			return filePath;
	}
		/**
		 * 删除图片
		 * 返回0/-1
		 * @return
		 * @throws MyException 
		 * @throws IOException 
		 */	
	public Integer deletePhotoFromSer (String params) throws IOException, MyException {		
		Integer result=-1;
		String group_name="group1";
		String remote_filename="";
		// 拼接服务区的文件路径  
	    StringBuffer sbPath = new StringBuffer();  
	    remote_filename= params.substring(params.indexOf("g")+6);
	    
		if(params==null){
			throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(),Code.EMPTY_PARAME.getErrorMessage());
		}		
		try{
			 ClientGlobal.init("D:\\eclipse-work\\rms\\src\\main\\resources\\properties\\fdfs_client.conf");
				//链接FastDFS服务器，创建tracker和Stroage  
				 TrackerClient trackerClient = new TrackerClient();  
			     TrackerServer trackerServer = trackerClient.getConnection();  
			     StorageServer storageServer = null;  
			     //最终操作对象
			     StorageClient storageClient = new StorageClient(trackerServer,  
			                storageServer); 
			     
			result = storageClient.delete_file(group_name, remote_filename);  
		}catch(IOException | MyException e){
			e.printStackTrace();  
		};
		return result;
	}
}

