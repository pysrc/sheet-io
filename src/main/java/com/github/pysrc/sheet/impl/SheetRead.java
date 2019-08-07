package com.github.pysrc.sheet.impl;

import com.github.pysrc.sheet.*;
import com.github.pysrc.sheet.exception.NullDataClassException;
import com.github.pysrc.sheet.exception.NullSheetException;
import com.github.pysrc.sheet.exception.NullWorkbookException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class SheetRead<T> extends AbstractSheet<T> {
    public SheetRead(Workbook workbook, Class<T> dataClass) throws NullDataClassException, NullWorkbookException {
        super(workbook, dataClass);
        this.scan = new ScanReadDefault<T>();
    }

    @Override
    public List<T> read() throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {

        if (!isDone) {
            done();
        }

        if (this.sheet == null) {
            new NullSheetException();
        }
        List<T> res = new ArrayList<>();
        if (titleEnable) {
            startRow++;
        }
        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            // 数据至此断
            if (row == null) {
                break;
            }
            boolean isend = true;
            int curCol = startCol;
            for (Column c : columns) {
                if (row.getCell(curCol++) != null) {
                    isend = false;
                    break;
                }
            }
            if (isend) {
                break;
            }
            T d = dataClass.newInstance();
            curCol = startCol;
            boolean vali = true;
            for (Column c : columns) {
                Cell cell = row.getCell(curCol++);
                if (this.validator.pass(i, c, cell)) {
                    scan.scan(this, c, d, fieldMap.get(c.getField()), cell);
                } else {
                    vali = false;
                }
            }
            if (vali) {
                res.add(d);
            }
        }
        return res;
    }

    @Override
    public void write(List<T> datas) throws IllegalAccessException, NoSuchFieldException, ParseException {

    }
}
