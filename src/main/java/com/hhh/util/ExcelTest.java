package com.hhh.util;

import java.io.File;

import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Excel导入导出
 * 
 * 
 * @version 1.0 Feb 7, 2014 4:14:51 PM
 */
public class ExcelTest {

    /**
     * 导入(导入到内存)
     */
    @Test
    public void importExcel() {
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File("D:/22.xls"));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();
            int columns=sheet.getColumns();
            // 遍历每行每列的单元格
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
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
            book = Workbook.createWorkbook(new File("D:/22.xls"));
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
    
   public static void main(String[] args) {
	   ExcelTest test=new ExcelTest();
	   test.importExcel();
}
    
    
}
