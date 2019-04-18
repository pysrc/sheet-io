package cn.nosrc.sheet.style;

import cn.nosrc.sheet.AbstractSheet;
import cn.nosrc.sheet.Column;
import cn.nosrc.sheet.ICellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;


// 左对齐
public class StyleCenter implements ICellStyle {
    @Override
    public void style(AbstractSheet base, Column column, CellStyle cellStyle) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }
}
