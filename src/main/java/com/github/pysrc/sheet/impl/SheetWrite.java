package com.github.pysrc.sheet.impl;

import com.github.pysrc.sheet.*;
import com.github.pysrc.sheet.exception.NullDataClassException;
import com.github.pysrc.sheet.exception.NullWorkbookException;
import com.github.pysrc.sheet.style.StyleDefault;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

import java.text.ParseException;
import java.util.List;


public class SheetWrite<T> extends AbstractSheet<T> {

    public SheetWrite(Workbook workbook, Class<T> dataClass) throws NullDataClassException, NullWorkbookException {
        super(workbook, dataClass);
        this.scan = new ScanWriteDefault<T>();
        this.rowStyles.add(new StyleDefault());
    }


    @Override
    public void write(List<T> datas) throws IllegalAccessException, NoSuchFieldException, ParseException {
        if (!isDone) {
            done();
        }
        int cur = startRow;
        if (titleEnable) { // 导出头信息
            Row row = sheet.createRow(cur++);
            int curCol = startCol;
            for (Column column : columns) {
                Cell cell = row.createCell(curCol);
                cell.setCellValue(column.getTitle());
                CellStyle cellStyle = workbook.createCellStyle();
                for (ICellStyle style : titleStyles) {
                    style.style(this, column, cellStyle);
                }
                cell.setCellStyle(cellStyle);
                sheet.setColumnWidth(curCol, column.getWidth());
                int maxrow = 999999;
                if (column.getValueList().length != 0) {
                    DataValidationHelper helper = sheet.getDataValidationHelper();
                    DataValidationConstraint constraint = helper.createExplicitListConstraint(column.getValueList());
                    CellRangeAddressList addressList = new CellRangeAddressList(startRow + 1, maxrow, curCol, curCol);
                    DataValidation validation = helper.createValidation(constraint, addressList);
                    validation.setSuppressDropDownArrow(false);
                    if (validation instanceof XSSFDataValidation) {
                        validation.setShowErrorBox(true);
                        validation.setSuppressDropDownArrow(true);
                    }
                    sheet.addValidationData(validation);
                }
                curCol++;
            }
        }
        if (datas == null) {
            return;
        }
        for (T t : datas) {
            Row row = sheet.createRow(cur++);
            int curCol = startCol;
            for (Column c : columns) {
                Cell cell = row.createCell(curCol++);
                this.scan.scan(this, c, t, fieldMap.get(c.getField()), cell);
                CellStyle cellStyle = workbook.createCellStyle();
                for (ICellStyle style : rowStyles) {
                    style.style(this, c, cellStyle);
                }
                cell.setCellStyle(cellStyle);
            }
        }
    }

    @Override
    public List<T> read() throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        return null;
    }
}
