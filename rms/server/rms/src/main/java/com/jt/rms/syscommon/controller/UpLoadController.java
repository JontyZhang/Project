package com.jt.rms.syscommon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.jt.rms.syscommon.entity.FileDto;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月15日
 */
@Controller
@RequestMapping("upload")
@Scope("prototype")
public class UpLoadController {

	@RequestMapping(value = "addFile", method = RequestMethod.POST)
	@ApiOperation(value = "上传照片", httpMethod = "POST", response = FileDto.class, notes = "上传图片")
	public FileDto addPhoto(HttpServletRequest request, MultipartFile file) {	
		FileDto result = new FileDto();
		try {
			byte[] fileBytes = file.getBytes();
			result.setFiles(fileBytes);
			result.setFileName(file.getOriginalFilename());
			result.setExtName(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
