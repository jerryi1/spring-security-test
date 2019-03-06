package com.example.demo2.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author lihuaqing
 * @create 2019-03-05 14:54
 **/
@Component
public class ExcelUtils {
    /**
     * 根据不同的excel 后缀生成不同的文件
     * */
    public Workbook createWorkbook(InputStream is,String excelFileName) throws IOException {
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        }else if (excelFileName.endsWith(".xlsx")){
            return new HSSFWorkbook(is);
        }
        return null;
    }

    /**
     * 根据sheet名称获取对应的sheet
     * */
    public Sheet getSheet(Workbook workbook,Integer index){
        return workbook.getSheetAt(index);
    }

    /**
     * 将Excel 表中的数据，导入到数据库里面去
     * */
    public List<Object> importDataFromExcel(Object vo,InputStream is,String excelFileName){
        Object vo1 = vo;
        List<Object> list = new ArrayList<Object>();
        try{
            //创建工作簿
            Workbook workbook = this.createWorkbook(is,excelFileName);
            //创建工作sheet
            Sheet sheet = this.getSheet(workbook,0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，讲JavaBean的属性进行赋值
            Field[] feilds = vo1.getClass().getDeclaredFields();
            for (int i=1;i<rows;i++){
                Row row = sheet.getRow(i);
                int index = 0;
                while(index <cells){
                   Cell cell =row.getCell(index);
                   if (null==cell){
                       cell = row.createCell(index);
                   }
                   cell.setCellType(Cell.CELL_TYPE_STRING);
                   String value = null==cell?"":cell.getStringCellValue();

                   //放置数据
                    Field field = feilds[index];
                    String fieldName = field.getName();
                    String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    Method setMethod = vo1.getClass().getMethod(methodName,new Class[]{String.class});
                    setMethod.invoke(vo1,new Object[]{value});
                    index++;
                }
                if (isHasValues(vo1)){
                    list.add(vo1);
                    //重新创建一个vo对象
                    vo1 = vo1.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断对象是否有值
     * */
    public Boolean isHasValues(Object object){
        boolean flag = false;
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                String methodNmae = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getmethod;
                getmethod = object.getClass().getMethod(methodNmae);
                Object obj = getmethod.invoke(object);
                if (null!=obj&&!"".equals(obj)){
                    flag=true;
                    break;
                }
            }
        }catch (NoSuchMethodException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return flag;
    }

}