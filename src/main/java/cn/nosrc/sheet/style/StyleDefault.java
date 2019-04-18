package cn.nosrc.sheet.style;

import cn.nosrc.sheet.AbstractSheet;
import cn.nosrc.sheet.Column;
import cn.nosrc.sheet.ICellStyle;
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
