package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.AbstractCellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.DUStatusEnum;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * 设置单元格字体样式
 *
 * 默认情况下 不用显式声明该Util  除非业务上有特殊需求
 */
public class XlsxCellFontUtil extends AbstractCellWriteHandler {

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        int columnIndex = cell.getColumnIndex();

        // 列索引是2的时候是状态栏列
        // 这里处理逻辑即: 如果状态是成功，则设置字体为绿色 【这里仅针对RTReport】
        if (columnIndex == 2) {
            String stringCellValue = cell.getStringCellValue();
            if (DUStatusEnum.SUCCESS.getDesc().equalsIgnoreCase(stringCellValue)) {
                Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                CellStyle cellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setFontName("微软雅黑");
                // 这里设置字体高度，需要看Excel的高度乘以20，比如：如果要想在Excel看到的高度是11，那么这里设置为220
                font.setFontHeight((short) 220);
                font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
                cellStyle.setFont(font);
                // 设置字体对齐方式
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                cellStyle.setAlignment(HorizontalAlignment.LEFT);
                cell.setCellStyle(cellStyle);
            }
        }
    }
}
