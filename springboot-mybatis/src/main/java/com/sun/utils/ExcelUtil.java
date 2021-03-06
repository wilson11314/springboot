package com.sun.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sun.entity.Student;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author wilson
 */
@Slf4j
public class ExcelUtil {
    /**
     * 单个Excel导出
     * @param fileName 文件全路径（包含文件的名称）
     * @param sheetName 工作表名称
     * @param head  导出表格的对象
     * @param data  写入表格数据
     */
    public static void write(String fileName, String sheetName, Class head, List data){
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).head(head).build();
        excelWriter.write(data, writeSheet);
        //关闭流
        excelWriter.finish();
    }

    /**
     * 单个Excel，多个sheet导出
     * @param fileName 文件全路径（包含文件的名称）
     * @param sheetName 工作表名称
     * @param head 导出表格的对象
     * @param data 写入表格数据
     */
    public static void repeatedWrite(String fileName, String sheetName, Class head, List<List<?>> data){
        ExcelWriter excelWriter = EasyExcel.write(fileName, head).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < data.size(); i++) {
            WriteSheet writeSheet = EasyExcel.writerSheet(i,sheetName).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(data.get(i), writeSheet);
        }
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
    /**
     * 页面单个Excel导出
     * @param fileName 文件全路径（
     * @param sheetName 工作表名称
     * @param head  导出表格的对象
     * @param data  写入表格数据
     */
    public static void exportExcel(HttpServletResponse response,String fileName, String sheetName, Class head, List data){
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileNameTemp = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileNameTemp + ".xlsx");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).head(head).build();
            excelWriter.write(data, writeSheet);
            //关闭流
            excelWriter.finish();
        }catch(Exception e){
            log.info("导出数据表异常 {} ", e.getMessage());
        }
    }

    /**
     * 页面单个Excel，多个sheet导出
     * @param fileName 文件名称
     * @param sheetName 工作表名称
     * @param head 导出表格的对象
     * @param data 写入表格数据
     */
    public static void exportExcelMultiSheet(HttpServletResponse response,String fileName, String sheetName, Class head, List<List<?>> data){
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileNameTemp = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileNameTemp + ".xlsx");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), head).build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < data.size(); i++) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i,sheetName).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                excelWriter.write(data.get(i), writeSheet);
            }
            /// 千万别忘记finish 会帮忙关闭流
            excelWriter.finish();
        }catch (Exception e){
            log.info("导出数据表异常 {} ", e.getMessage());
        }
    }

    /**
     * 简单读取表格
     * @param fileName 表格的文件路径
     * @param head  表格对象
     * @param readListener 表格监听类
     */
    public static void simpleRead(String fileName, Class head, ReadListener readListener){
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, head, readListener).sheet().doRead();
    }
}
