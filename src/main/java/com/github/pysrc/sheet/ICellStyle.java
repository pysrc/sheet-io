package com.github.pysrc.sheet;

import org.apache.poi.ss.usermodel.CellStyle;


public interface ICellStyle {
    public void style(AbstractSheet base, Column column, CellStyle cellStyle);
}

