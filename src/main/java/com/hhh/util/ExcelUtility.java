package com.hhh.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtility {
	
	
	
	 /**
     * 导入(导入到内存)
     */
    @Test
    public void importExcel(CommonsMultipartFile file) {
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(file.getInputStream());
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();//行
            
            int columns=sheet.getColumns();//列
           
            // 遍历每行每列的单元格
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    Cell cell = sheet.getCell(j, i);
                    System.out.println(cell);
                    String result = cell.getContents();
                    System.out.println(result);
                    if(j==0){
                        System.out.print("姓名："+result+" ");
                    }
                    if(j==1){
                        System.out.print("年龄："+result+" ");
                    }
                    if((j+1)%2==0){ 
                        System.out.println();
                    }
                }
            }
            System.out.println("========");
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            System.out.println(result);
            System.out.println("========");
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                book.close();
            }
        }
    }

    /**
     * 导出(导出到磁盘)
     */
    @Test
    public void exportExcel() {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("D:/22.xlsx"));
            // 生成名为"学生"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("学生", 0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为张三
            Label label = new Label(0, 0, "张三");
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // 保存数字的单元格必须使用Number的完整包路径
            jxl.write.Number number = new jxl.write.Number(1, 0, 30);
            sheet.addCell(number);
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * excel表导入
	 * @param in  承载着Excel的输入流
	 * @param sheetName  要输入的工作表名称集合,如String[] sheetName={"sheet1","sheet2","sheet3","sheet4"}
	 * @param entityClass  List中对象的类型
	 * @param fieldMap   Excel中的中文列头和类的英文属性的对应关系Map
	 * @param uniqueFields  指定业务主键组合（即复合主键），这些列的组合不能重复
	 * @return
	 */
	public <T>  List excelToList(InputStream in, String[] sheetName,
			LinkedHashMap<String, Class<?>> entityClass,

			LinkedHashMap<String, LinkedHashMap<String, String>> fieldMap,
			LinkedHashMap<String, String[]> uniqueFields)  {

		// 定义要返回的List列表
		List<T> resultList = new ArrayList<T>();
		try {
			// 根据Excel数据源创建WorkBook
			Workbook wb = Workbook.getWorkbook(in);
			// 获取工作表
			for (int k = 0; k < sheetName.length; k++) {
				resultList.clear();
				Sheet sheet = wb.getSheet(sheetName[k]);
				String singleSheetName = sheetName[k]; // 获取sheet名为
				Class<?> enClassName = null;
				// 给对象中的sheet所对应的实体
				for (Entry<String, Class<?>> entry : entityClass.entrySet()) {
					// 获取中文字段名
					String enSheetName = entry.getKey();
					// 获取英文字段名
					if (enSheetName.equals(singleSheetName)) {
						enClassName = entry.getValue();
					}
				}
				// 获取工作表的有效行数
				int realRows = 0;
				for (int i = 0; i < sheet.getRows(); i++) {
					int nullCols = 0;
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell currentCell = sheet.getCell(j, i);
						if (currentCell == null
								|| "".equals(currentCell.getContents()
										.toString())) {
							nullCols++;
						}
					}
					if (nullCols == sheet.getColumns()) {
						break;
					} else {
						realRows++;
					}					
				}
				// 如果Excel中没有数据则提示错误
				if (realRows <= 1) {
					throw new Exception("Excel文件中的" + sheetName[k]+ "没有任何数据");
				}
				Cell[] firstRow = sheet.getRow(0);
				String[] excelFieldNames = new String[firstRow.length];
				// 获取Excel中的列名
				for (int i = 0; i < firstRow.length; i++) {
					excelFieldNames[i] = firstRow[i].getContents().toString()
							.trim();
				}
				// 判断需要的字段在Excel中是否都存在
				boolean isExist = true;
				List<String> excelFieldList = Arrays.asList(excelFieldNames);
				LinkedHashMap<String, String> enfiledMap = new LinkedHashMap<String, String>();
				// 给对象中的sheet所对应的普通字段
				for (Entry<String, LinkedHashMap<String, String>> entry : fieldMap
						.entrySet()) {
					// 获取中文字段名
					String enSheetName = entry.getKey();
					// 获取英文字段名
					if (enSheetName.equals(singleSheetName)) {
						enfiledMap = entry.getValue();
						for (String cnName : enfiledMap.keySet()) {
							if (!excelFieldList.contains(cnName)) {
								isExist = false;
								break;
							}
						}
					}
				}
				// 如果有列名不存在，则抛出异常，提示错误
				if (!isExist) {
					throw new Exception("Excel中缺少必要的字段，或字段名称有误");
				}
				// 将列名和列号放入Map中,这样通过列名就可以拿到列号
				LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
				for (int i = 0; i < excelFieldNames.length; i++) {
					colMap.put(excelFieldNames[i], firstRow[i].getColumn());
				}				
				// 给对象中的sheet所对应的确定重复字段
				for (Entry<String, String[]> fields : uniqueFields.entrySet()) {
					// 获取中文字段名
					String enSheetName = fields.getKey();
					String[] enuniqueFile=null;
					// 获取英文字段名
					if (enSheetName.equals(singleSheetName)) {
						enuniqueFile = fields.getValue();
						// 判断是否有重复行
						// 1.获取uniqueFields指定的列
						Cell[][] uniqueCells = new Cell[enuniqueFile.length][];
						for (int i = 0; i < enuniqueFile.length; i++) {
							int col = colMap.get(enuniqueFile[i]);
							uniqueCells[i] = sheet.getColumn(col);
						}
						// 2.从指定列中寻找重复行
						for (int i = 1; i < realRows; i++) {
							int nullCols = 0;
							for (int j = 0; j < enuniqueFile.length; j++) {
								String currentContent = uniqueCells[j][i].getContents();
								Cell sameCell = sheet.findCell(currentContent,
										uniqueCells[j][i].getColumn(),
										uniqueCells[j][i].getRow() + 1,
										uniqueCells[j][i].getColumn(),
										uniqueCells[j][realRows - 1].getRow(), true);
								if (sameCell != null) {
									nullCols++;
								}
							}
							if (nullCols == enuniqueFile.length) {
								throw new Exception("Excel中有重复行，请检查");
							}
						}
					}
				}				
				// 将sheet转换为list
				for (int i = 1; i < realRows; i++) {
					
					if (enClassName!=null) {
						// 新建要转换的对象
						T entity = (T) enClassName.newInstance();

						// 给对象中的字段赋值
						for (Entry<String, String> entry : enfiledMap.entrySet()) {
							// 获取中文字段名
							String cnNormalName = entry.getKey();
							// 获取英文字段名
							String enNormalName = entry.getValue();
							// 根据中文字段名获取列号
							int col = colMap.get(cnNormalName);

							// 获取当前单元格中的内容
							String content = sheet.getCell(col, i).getContents()
									.toString().trim();

							// 给对象赋值
							//setFieldValueByName(enNormalName, content, entity);
						}
						resultList.add(entity);
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return resultList;
	}

}
