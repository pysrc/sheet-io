package com.github.pysrc.sheet;


import org.apache.poi.ss.usermodel.Cell;

/**
 * 导入校验器
 */
public interface IReadValidator {
    /**
     * 校验/修改导入的数据
     * @param row 数据的行标
     * @param column 数据列的Schema
     * @param cell Excel的Cell
     * @return 是否通过校验，通过校验的数据才会写入导入Bean
     */
    boolean pass(int row, Column column, Cell cell);
}
