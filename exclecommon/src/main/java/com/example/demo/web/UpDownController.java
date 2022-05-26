package com.example.demo.web;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.api.CommonResult;
import com.example.demo.exception.Asserts;
import com.example.demo.util.ExcelUtils;
import com.example.demo.vo.excel.DiagnoseExcelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * @Description: 上传下载管理API
 * @author: songxl
 * @time: 2021/7/20 12:39
 */
@RestController
@Api(value = "上传下载管理API", tags = {"上传下载管理API"})
@RequestMapping("/updown")
public class UpDownController {

    @ApiOperation(value = "Excle导入 [by:songxl]")
    @PostMapping("/importExcel")
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            //标题行，如果没有就不填，否则导入时数据会变成null
          List<DiagnoseExcelVO> diagnoseExcelVOS =  ExcelUtils.importExcel(file,0,1, DiagnoseExcelVO.class);
          System.out.println(diagnoseExcelVOS.size());
        } catch (Exception e) {
            Asserts.fail("导入失败:{"+ e.getMessage()+"}");
        }
        return CommonResult.success(true);
    }
}
