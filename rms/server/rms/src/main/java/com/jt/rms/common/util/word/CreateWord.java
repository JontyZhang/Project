package com.jt.rms.common.util.word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.jt.rms.contract.entity.ContractEntity;
import com.jt.rms.deposit.entity.DepositEntity;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年5月13日
 */
public class CreateWord {
	public static void createContract (ContractEntity contract,RentingHouse rentingHouse,DepositEntity deposit,User user) throws IOException {
		//Blank Document  
        XWPFDocument document= new XWPFDocument();  
        
        //Write the Document in file system  
        File file = new File("D:/合同/合同.docx");
        FileOutputStream out = new FileOutputStream(file);
       // FileOutputStream OUT2 = new Fil
        //添加标题  
        XWPFParagraph titleParagraph = document.createParagraph();  
        //设置段落居中  
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);  
  
        XWPFRun titleParagraphRun = titleParagraph.createRun();  
        titleParagraphRun.setText("租赁合同");  
        titleParagraphRun.setColor("000000");  
        titleParagraphRun.setFontSize(20);  
 
        //换行  
        XWPFParagraph paragraph1 = document.createParagraph();  
        XWPFRun paragraphRun1 = paragraph1.createRun();  
        paragraphRun1.setText("\r");  
  
  
        //基本信息表格  
        XWPFTable infoTable = document.createTable();  
        //去表格边框  
        infoTable.getCTTbl().getTblPr().unsetTblBorders();  
  
  
        //列宽自动分割  
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();  
        infoTableWidth.setType(STTblWidth.AUTO);  
        infoTableWidth.setW(BigInteger.valueOf(9072));  
  
  
        //表格第一行  
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);  
        infoTableRowOne.getCell(0).setText("甲方(出租人):");  
        String name1 = contract.getLandlordName();
        infoTableRowOne.addNewTableCell().setText(name1); 
        infoTableRowOne.addNewTableCell().setText("   "); 
        infoTableRowOne.addNewTableCell().setText("身份证号:");
        String name2 = contract.getLandlordCID();
        infoTableRowOne.addNewTableCell().setText(name2);  
  
        //表格第二行  
        XWPFTableRow infoTableRowTwo = infoTable.createRow(); 
        infoTableRowTwo.getCell(0).setText("乙方(承租人):");  
        String name3 = contract.getTenantName();
        infoTableRowTwo.getCell(1).setText(name3);  
        infoTableRowTwo.getCell(3).setText("身份证号：");
        String name4 =contract.getTenantCID();
        infoTableRowTwo.getCell(4).setText(name4);  
        

        //两个表格之间加个换行  
        XWPFParagraph paragraph = document.createParagraph();  
        XWPFRun paragraphRun = paragraph.createRun();  
        paragraphRun.setText("\r");  
  
        //段落  
        XWPFParagraph firstParagraph = document.createParagraph();  
        XWPFRun run = firstParagraph.createRun();  
        run.setText("根据《中华人民共和国合同法》及其他相关法律、法规规定，甲乙双方在平等、自愿、协商一致的基础上，就下列房屋的租赁达成如下协议：");  
        run.setText("\n");
        XWPFParagraph firstParagraph1 = document.createParagraph();  
        XWPFRun run1 = firstParagraph1.createRun();  
        run1.setText("第一条：房屋基本情况");
        XWPFParagraph firstParagraph12 = document.createParagraph();  
        XWPFRun run12 = firstParagraph12.createRun();  
        run12.setText("甲方将自有的坐落在"+rentingHouse.getAddress()+"的房屋出租给乙方使用。");
        run12.setText("\r");

        XWPFParagraph firstParagraph2 = document.createParagraph();  
        XWPFRun run2 = firstParagraph2.createRun();  
        run2.setText("第二条：租赁期限");
        XWPFParagraph firstParagraph21 = document.createParagraph();  
        XWPFRun run21= firstParagraph21.createRun();  
        run21.setText("租赁期共"+contract.getRentDate()+"个月，甲方从"+contract.getRentFromDate()+"起将出租房屋交付乙方使用，至"+contract.getRentToDate()+"收回。");
        run21.setText("\r");
        
        XWPFParagraph firstParagraph3 = document.createParagraph();  
        XWPFRun run3 = firstParagraph3.createRun();  
        run3.setText("　第三条：租金");
        XWPFParagraph firstParagraph31 = document.createParagraph();  
        XWPFRun run31= firstParagraph31.createRun();  
        run31.setText("本房屋月租金为人民币"+contract.getRent()+"元，按月/季度/年结算。每月月初/每季季初/每年年初X日内，乙方向甲方支付全月/季/年租金。");
        run31.setText("\r");

        XWPFParagraph firstParagraph4 = document.createParagraph();  
        XWPFRun run4 = firstParagraph4.createRun();  
        run4.setText("第四条：交付房租期限");
        XWPFParagraph firstParagraph41 = document.createParagraph();  
        XWPFRun run41= firstParagraph41.createRun();  
        run41.setText("乙方应于本合同生效之日起七日内，将该房屋交付给甲方。");
        run41.setText("\r");

        XWPFParagraph firstParagraph5 = document.createParagraph();  
        XWPFRun run5 = firstParagraph5.createRun();  
        run5.setText("第五条：房屋租赁期间相关费用说明");
        XWPFParagraph firstParagraph51 = document.createParagraph();  
        XWPFRun run51= firstParagraph51.createRun();  
        run51.setText("乙方租赁期间，水、电、取暖、燃气、电话、物业以及其它由乙方居住而产生的费用由乙方负担。租赁结束时，乙方须交清欠费。");

        XWPFParagraph firstParagraph6 = document.createParagraph();  
        XWPFRun run6 = firstParagraph6.createRun();  
        run6.setText("第六条：房屋维护养护责任");
        XWPFParagraph firstParagraph61 = document.createParagraph();  
        XWPFRun run61= firstParagraph61.createRun();  
        run61.setText("租赁期共"+contract.getRentDate()+"个月，甲方从"+contract.getRentFromDate()+"起将出租房屋交付乙方使用，至"+contract.getRentToDate()+"收回。");

        XWPFParagraph firstParagraph7 = document.createParagraph();  
        XWPFRun run7 = firstParagraph7.createRun();  
        run7.setText("第七条：租赁期满");
        XWPFParagraph firstParagraph71 = document.createParagraph();  
        XWPFRun run71= firstParagraph71.createRun();  
        run71.setText("租赁期满后，如乙方要求继续租赁，则须提前一个月向甲方提出，甲方收到乙方要求后七天内答复。如同意继续租赁，则续签租赁合同。同等条件下，乙方享有优先租赁的权利。");

        XWPFParagraph firstParagraph8 = document.createParagraph();  
        XWPFRun run8 = firstParagraph8.createRun();  
        run8.setText("第八条：提前终止合同");
        XWPFParagraph firstParagraph81 = document.createParagraph();  
        XWPFRun run81= firstParagraph81.createRun();  
        run81.setText("在房屋租赁期间，任何一方提出终止合同，需提前一个月书面通知对方，经双方协商后签订终止合同书，在终止合同书签订前，本合同仍有效。");
        XWPFParagraph firstParagraph82 = document.createParagraph();  
        XWPFRun run82= firstParagraph82.createRun();  
        run82.setText("受不可抗力因素影响，甲方必须终止合同时，一般应提前一个月书面通知乙方。乙方的经济损失甲方不予补偿。");

        XWPFParagraph firstParagraph9 = document.createParagraph();  
        XWPFRun run9 = firstParagraph9.createRun();  
        run9.setText("第九条：违约责任");
        XWPFParagraph firstParagraph91 = document.createParagraph();  
        XWPFRun run91= firstParagraph91.createRun();  
        run91.setText("在房屋租赁期间，任何一方违反本合同的规定，依据事实轻重，按年度须向对方交纳年度租金的10%作为违约金。乙方逾期未交付租金的，每逾期一日，甲方有权按月租金的2%向乙方加收滞纳金。");

        XWPFParagraph firstParagraph10 = document.createParagraph();  
        XWPFRun run10 = firstParagraph10.createRun();  
        run2.setText("第十条：本合同页数，一式2份，甲、乙双方各执一份，均具有同等效力。");

        
        //第二个表格
        //基本信息表格  
        XWPFTable infoTable1 = document.createTable();  
        //去表格边框  
        infoTable1.getCTTbl().getTblPr().unsetTblBorders();  
  
  
        //列宽自动分割  
        CTTblWidth infoTableWidth1 = infoTable1.getCTTbl().addNewTblPr().addNewTblW();  
        infoTableWidth1.setType(STTblWidth.AUTO);  
        infoTableWidth1.setW(BigInteger.valueOf(9072)); 
        //表格第一行  
        XWPFTableRow infoTableRowfour = infoTable1.getRow(0);  
        infoTableRowfour.getCell(0).setText("甲方(出租人):");  
        infoTableRowOne.addNewTableCell().setText("   "); 
        infoTableRowOne.addNewTableCell().setText("乙方(承租人)"); 
        infoTableRowOne.addNewTableCell().setText("   "); 
  
  
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();  
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);  
  
        //添加页眉  
        CTP ctpHeader = CTP.Factory.newInstance();  
        CTR ctrHeader = ctpHeader.addNewR();  
        CTText ctHeader = ctrHeader.addNewT();  
        String headerText = "Java POI create MS word file.";  
        ctHeader.setStringValue(headerText);  
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);  
        //设置为右对齐  
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);  
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];  
        parsHeader[0] = headerParagraph;  
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);  
  
  
        //添加页脚  
        CTP ctpFooter = CTP.Factory.newInstance();  
        CTR ctrFooter = ctpFooter.addNewR();  
        CTText ctFooter = ctrFooter.addNewT();  
        String footerText = "http://blog.csdn.net/zhouseawater";  
        ctFooter.setStringValue(footerText);  
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);  
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);  
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];  
        parsFooter[0] = footerParagraph;  
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);  
  
  
        document.write(out);  
        out.close();  
        System.out.println("create_table document written success.");  
	}
}
