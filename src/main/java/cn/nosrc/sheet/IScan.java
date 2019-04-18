package cn.nosrc.sheet;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.text.ParseException;

public interface IScan<T> {
    /**
     * 单元格扫描处理函数
     * @param column
     * @param data
     * @param field
     * @param cell
     */
    void scan(AbstractSheet base, Column column, T data, Field field, Cell cell) throws NoSuchFieldException, IllegalAccessException, ParseException;
}
