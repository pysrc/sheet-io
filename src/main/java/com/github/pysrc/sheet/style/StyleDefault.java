package com.github.pysrc.sheet.style;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.Column;
import com.github.pysrc.sheet.ICellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;


public class StyleDefault implements ICellStyle {
    @Override
    public void style(AbstractSheet base, Column column, CellStyle cellStyle) {
        if ("".equals(column.getFormat())) {
            return;
        }
        Workbook workbook = base.getWorkbook();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat(column.getFormat()));
    }
}
