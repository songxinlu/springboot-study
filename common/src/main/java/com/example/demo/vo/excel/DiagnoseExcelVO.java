package com.example.demo.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:诊断导入模板
 * @Author songxl
 * @Date 2022/2/22
 */
@ExcelTarget("DiagnoseExcelVO")
@Data
public class DiagnoseExcelVO implements Serializable {
    @Excel(name = "医院诊断名称")
    private String hospitalDiagName;
    @Excel(name = "ICD-10编码")
    private String ICD10;
    @Excel(name = "标准诊断名称")
    private String name;
    @Excel(name = "是否匹配【未匹配、已匹配】")
    private String mate;
    @Excel(name = "数据来源【不填、标准词、同义词、编码、历史数据、相似词】")
    private String source;
    @Excel(name = "标准术语状态")
    private String status;
}
