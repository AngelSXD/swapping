package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;


/**
 * xlsx单元格样式处理器
 *
 * 默认不用显式设置 除非业务有特殊需求
 */
public class XlsxCellStyleUtil {

    private XlsxCellStyleUtil() {
        throw new IllegalArgumentException();
    }

    public static HorizontalCellStyleStrategy getCellStyleStrategy() {
        /*******自定义列标题和内容的样式******/
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        // 设置标题字体大小
        headWriteFont.setFontHeightInPoints((short) 11);
        // 设置标题字体
        headWriteFont.setFontName("微软雅黑");
        // 设置标题字体是否加粗
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 设置是否自动换行
        headWriteCellStyle.setWrapped(true);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 设置内容字体
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontName("微软雅黑");
        // 设置标题字体大小
        contentWriteFont.setFontHeightInPoints((short) 11);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(false);
        //设置 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置 水平居左
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}
