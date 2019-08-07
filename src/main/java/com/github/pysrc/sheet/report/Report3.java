package com.github.pysrc.sheet.report;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.ISchema;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;


public class Report3<T> extends AbstractReport {

    private String header;
    private String param;
    private String tail;

    public Report3(AbstractSheet base, String header, String param, String tail) {
        super(base);
        this.header = header;
        this.param = param;
        this.tail = tail;
    }

    @Override
    public void before() {
        new Report2<T>(base, header, param).before();
    }

    @Override
    public void after() {
        Sheet sheet = base.getSheet();
        int maxrow = sheet.getLastRowNum();
        // 合并单元格
        Row row = sheet.createRow(maxrow + 1);
        CellRangeAddress region = new CellRangeAddress(maxrow + 1, maxrow + 1, this.base.getStartCol(), this.base.getStartCol() + this.base.getColumns().size() - 1);
        sheet.addMergedRegion(region);
        CellStyle cellStyle = base.getWorkbook().createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        for (int i = 0; i < base.getColumns().size(); i++) {
            row.createCell(base.getStartCol()+i).setCellStyle(cellStyle);
        }
        row.getCell(base.getStartCol()).setCellValue(tail);
    }

    @Override
    public void updateSchema(ISchema schema) {

    }
}
