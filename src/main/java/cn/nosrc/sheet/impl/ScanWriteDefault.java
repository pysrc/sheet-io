package cn.nosrc.sheet.impl;

import cn.nosrc.sheet.AbstractSheet;
import cn.nosrc.sheet.Column;
import cn.nosrc.sheet.IScan;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScanWriteDefault<T> implements IScan<T> {
    @Override
    public void scan(AbstractSheet base, Column column, T data, Field field, Cell cell) throws IllegalAccessException, ParseException {
        Object o = field.get(data);
        if (o == null) {
            return;
        }
        switch (column.getEtype()) {
            case NUM:
                if (o instanceof String) {
                    cell.setCellValue(new Double(o.toString()));
                } else if (o instanceof Number) {
                    cell.setCellValue(((Number) o).doubleValue());
                }
                break;
            case DAT:
                if (o instanceof Date) {
                    cell.setCellValue(DateUtil.getExcelDate((Date) o, false));
                } else if (o instanceof String) {
                    cell.setCellValue(DateUtil.getExcelDate(new SimpleDateFormat(column.getFormat()).parse(o.toString()), false));
                }
                break;
            default:
                if (o instanceof Date && !"".equals(column.getFormat())) {
                    cell.setCellValue(new SimpleDateFormat(column.getFormat()).format((Date) o));
                } else {
                    cell.setCellValue(o.toString());
                }
        }
    }
}
