/*package com.wenyuankeji.spring.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wenyuankeji.spring.model.QuestionModel;
import com.wenyuankeji.spring.model.UserModel;

public class ExcelUtil {
	*//**
	 * Read the Excel 2003-2007
	 *//*
	public static List<QuestionModel> readXls(String path, HttpSession session)
			throws IOException {
		UserModel sessionQuestion = (UserModel) session.getAttribute("userModel");

		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		QuestionModel question = null;
		List<QuestionModel> list = new ArrayList<QuestionModel>();
		// Read the Sheet
		for (int numSheet = 1; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					question = new QuestionModel();

					HSSFCell questionNum = hssfRow.getCell(0);
					HSSFCell guanJianZi = hssfRow.getCell(1);
					HSSFCell nanYiDu = hssfRow.getCell(2);
					HSSFCell typeId = hssfRow.getCell(3);
					HSSFCell tiGan = hssfRow.getCell(4);
					HSSFCell daAn = hssfRow.getCell(5);
					HSSFCell buJu = hssfRow.getCell(6);
					HSSFCell option_a = hssfRow.getCell(7);
					HSSFCell option_b = hssfRow.getCell(8);
					HSSFCell option_c = hssfRow.getCell(9);
					HSSFCell option_d = hssfRow.getCell(10);

					question.setQuestion_num(getValue(questionNum));
					question.setQ_keyword(getValue(guanJianZi));
					question.setHard_level(getValue(nanYiDu));
					question.setQ_type(getValue(typeId));
					question.setQ_title(getValue(tiGan));
					question.setQ_answer(getValue(daAn));
					question.setBu_ju(getValue(buJu));
					question.setOption_a(getValue(option_a));
					question.setOption_b(getValue(option_b));
					question.setOption_c(getValue(option_c));
					question.setOption_d(getValue(option_d));
					question.setCreate_id(sessionQuestion.getUser_id());
					question.setCreate_date(new Date());

					list.add(question);
				}
			}
		}
		return list;
	}

	*//**
	 * getValue
	 *//*
	@SuppressWarnings("static-access")
	public static String getValue(HSSFCell hssfCell) {
		if(hssfCell == null)
			return null;
		
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			Double d = hssfCell.getNumericCellValue();
			DecimalFormat df = new DecimalFormat("#");
			return df.format(d);
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}*/