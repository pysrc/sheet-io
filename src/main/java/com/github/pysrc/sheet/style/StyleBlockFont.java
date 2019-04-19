package com.github.pysrc.sheet.style;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.Column;
import com.github.pysrc.sheet.ICellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

// 左对齐
public class StyleBlockFont implements ICellStyle {
    @Override
    public void style(AbstractSheet base, Column column, CellStyle cellStyle) {
        Workbook workbook = base.getWorkbook();
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
    }
}
