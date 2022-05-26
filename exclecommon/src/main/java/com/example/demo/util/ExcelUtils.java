package com.example.demo.util;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.example.demo.exception.Asserts;
import com.example.demo.service.impl.ExcelExportStylerUserImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Description: excel 导入导出工具类
 * @author: gaodm
 * @time: 2020/6/2 19:18
 */
public class ExcelUtils {
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    public static void exportExcelUser(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                       HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setStyle(ExcelExportStylerUserImpl.class);
        userExport(list, pojoClass, fileName, response, exportParams);
    }

    public static void exportXSSFExcelUser(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                       HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        exportParams.setStyle(ExcelExportStylerUserImpl.class);
        userExport(list, pojoClass, fileName, response, exportParams);
    }

    public static void exportExcelDynamicCol(List<ExcelExportEntity> entityList, Collection<?> dataSet, String title, String sheetName, String fileName,
                                    HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        dynamicColExport(entityList, dataSet, fileName, response, exportParams);
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response, float height) {
        Boolean havTitle = false;
        if (StringUtils.isNotBlank(title)) {
            havTitle = true;
        }
        userExport2(list, pojoClass, fileName, response, new ExportParams(title, sheetName), height, havTitle);
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            ;
        }
        downLoadExcel(fileName, response, workbook);
    }

    private static void dynamicColExport(List<ExcelExportEntity> entityList, Collection<?> dataSet, String fileName, HttpServletResponse response,
                                         ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,entityList,dataSet);
        if (workbook != null) {
            ;
        }
        downLoadExcel(fileName, response, workbook);
    }

    private static void userExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                   ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            //列宽设置
            sheet.setColumnWidth(8, 256 * 20);
            sheet.setColumnWidth(9, 256 * 50);
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                row.setHeightInPoints(12.8f);
            }
        }
        downLoadExcel(fileName, response, workbook);
    }

    private static void userExport2(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                    ExportParams exportParams, float height, Boolean havTitle) {
        if(StringUtils.isNotEmpty(exportParams.getTitle()) && exportParams.getTitle().equals("病历质控报表")){
            exportParams.setTitleHeight(Short.valueOf("20"));
        };
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int startRowNum = 1;
            if (havTitle) {
                startRowNum = 2;
            }
            for (int i = startRowNum; i <= rowNum; i++) {
                row = sheet.getRow(i);
                row.setHeightInPoints(height);
            }
        }
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            // throw new NormalException(e.getMessage());
            Asserts.fail("导出Excel异常");
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) {
            ;
        }
        downLoadExcel(fileName, response, workbook);
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            Asserts.fail("excel文件不能为空");
        } catch (Exception e) {
            Asserts.fail("导入失败:{"+ e.getMessage()+"}");
        }
        return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
                                          Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            Asserts.fail("excel文件不能为空");
        } catch (Exception e) {
            Asserts.fail("导入失败:{"+ e.getMessage()+"}");
        }
        return list;
    }

}
