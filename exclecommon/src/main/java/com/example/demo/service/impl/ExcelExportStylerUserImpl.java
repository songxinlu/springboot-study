package com.example.demo.service.impl;

import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Description:
 * @author: gaodm
 * @time: 2020/6/3 11:45
 */
public class ExcelExportStylerUserImpl extends AbstractExcelExportStyler implements IExcelExportStyler {
    public ExcelExportStylerUserImpl(Workbook workbook) {
        super.createStyles(workbook);
    }

    public CellStyle getTitleStyle(short color) {
        CellStyle titleStyle = this.workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    public CellStyle stringSeptailStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        } else {
            style.setAlignment(HorizontalAlignment.LEFT);
        }

        return style;
    }

    public CellStyle getHeaderStyle(short color) {
        CellStyle titleStyle = this.workbook.createCellStyle();
        Font font = this.workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return titleStyle;
    }

    public CellStyle stringNoneStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        } else {
            style.setAlignment(HorizontalAlignment.LEFT);
        }

        return style;
    }
}
