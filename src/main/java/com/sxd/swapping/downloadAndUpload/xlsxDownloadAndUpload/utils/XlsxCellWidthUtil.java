package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * xlsx单元格宽度工具类
 *
 * 一般情况下，可不一不用显式的重写该 方法，但 可能遇上 导出xlsx文件没有表头的情况，为解决这种异常，就需要显式去重写该方法。
 */
public class XlsxCellWidthUtil extends AbstractColumnWidthStyleStrategy {

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            int columnWidth = 25;
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
        }
    }

}
