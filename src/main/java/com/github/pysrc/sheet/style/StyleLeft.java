package com.github.pysrc.sheet.style;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.Column;
import com.github.pysrc.sheet.ICellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;


// 左对齐
public class StyleLeft implements ICellStyle {
    @Override
    public void style(AbstractSheet base, Column column, CellStyle cellStyle) {
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
    }
}
