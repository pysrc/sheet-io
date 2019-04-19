package com.github.pysrc.sheet.style;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.Column;
import com.github.pysrc.sheet.ICellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;


// 四周边框
public class StyleFrame implements ICellStyle {
    @Override
    public void style(AbstractSheet base, Column column, CellStyle cellStyle) {
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
    }
}
