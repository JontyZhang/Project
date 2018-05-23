package com.jt.rms.common.util.file.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.jt.rms.contract.entity.ContractEntity;

/**
 * 
 * @author :zjt
 * @time :2018年4月29日
 * 在线生成合同
 */
public class WordUtils {
	public void createWord(ContractEntity params) throws FileNotFoundException{
//		Map<String, Object> datas = new HashMap<String, Object>(){{
//            put("landlordName", "ver 0.0.3");
//            put("tenantName", "ver 0.0.3");
//            put("address", "ver 0.0.3");           
//            put("rentFromDate", "ver 0.0.3");
//            put("rentToDate", "ver 0.0.3");
//            put("rentDate", "ver 0.0.3");
//                //读取模板，进行渲染
//        XWPFTemplate doc = XWPFTemplate
//                .create("src/main/java/common/util/file/download/ContractTemplate.docx");
//        RenderAPI.render(doc, datas);
// 
//        //输出渲染后的文件
//        FileOutputStream out = new FileOutputStream("out.docx");
//        doc.write(out);
//        out.flush();
//        out.close();
	}
}
