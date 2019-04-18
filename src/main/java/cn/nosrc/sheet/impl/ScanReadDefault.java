package cn.nosrc.sheet.impl;

import cn.nosrc.sheet.AbstractSheet;
import cn.nosrc.sheet.Column;
import cn.nosrc.sheet.IScan;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScanReadDefault<T> implements IScan<T> {
    @Override
    public void scan(AbstractSheet base, Column column, T data, Field field, Cell cell) throws NoSuchFieldException, IllegalAccessException, ParseException {
        if (cell == null) {
            return;
        }
        // 判断属性类型
        Class tp = field.getType();
        if (tp == Date.class) {
            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                field.set(data, DateUtil.getJavaDate(cell.getNumericCellValue()));
                return;
            }
        }
        cell.setCellType(CellType.STRING);
        String value = cell.getStringCellValue();
        if (tp == String.class) {
            switch (column.getEtype()) {
                case DAT:
                    if ("".equals(column.getEtype())) {
                        break;
                    }
                    field.set(data, new SimpleDateFormat(column.getFormat()).format(DateUtil.getJavaDate(new Double(value))));
                    break;
                default:
                    field.set(data, value);
            }
        } else if (tp == Date.class) {
            Date date = new SimpleDateFormat(column.getFormat()).parse(value);
            field.set(data, date);
        } else if (tp == Integer.class) {
            field.set(data, new Integer(value));
        } else if (tp == BigInteger.class) {
            field.set(data, new BigInteger(value));
        } else if (tp == Long.class) {
            field.set(data, new Long(value));
        } else if (tp == Short.class) {
            field.set(data, new Short(value));
        } else if (tp == BigDecimal.class) {
            field.set(data, new BigDecimal(value));
        } else if (tp == Double.class) {
            field.set(data, new Double(value));
        } else if (tp == Float.class) {
            field.set(data, new Float(value));
        }

    }
}
