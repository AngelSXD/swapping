package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * xlsx设置单元格格式为 文本格式工具类
 *
 * 第一步：xlsx 设置单元格格式为 文本格式
 * 但是数字超过15位以上的数字串，依旧会显示为E+  解决该问题，仅设置单元格格式为 文本格式还不能完全解决，
 * 第二步：需要 将 数值类型的字段 Long类型字段 按照String 返回，去写入单元格，即可解决E+问题。 例如：ActivitySyncRecordDTO 类中的actId是Long类型超过15位的数字串，写出应该用actIdDesc为String类型。
 *
 * 【备选方案，未测试：需要配合在数字串前加上英文单引号' ，即可解决】
 *
 */
public class XlsxCellWriteUtil implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        //设置单元格格式为文本
        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("@"));
        cell.setCellStyle(cellStyle);
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }
}
