package com.github.pysrc.sheet.report;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.ISchema;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * 通用报表导出风格
 *
 */
public class Report2<T> extends AbstractReport<T> {

    private String header;
    private String param;

    public Report2(AbstractSheet<T> base, String header, String param) {
        super(base);
        this.header = header;
        this.param = param;
    }


    @Override
    public void before() {
        if (base.getStartRow() - 2 < 0) {
            return;
        }
        Sheet sheet = this.base.getSheet();
        // 合并单元格
        Row row = sheet.createRow(base.getStartRow() - 2);
        CellRangeAddress region = new CellRangeAddress(this.base.getStartRow() - 2, this.base.getStartRow() - 2, this.base.getStartCol(), this.base.getStartCol() + this.base.getColumns().size() - 1);
        sheet.addMergedRegion(region);
        Cell cell = row.createCell(base.getStartCol());
        cell.setCellValue(header);
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 15);
        cellStyle.setFont(font);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
        for (int i = 1; i < base.getColumns().size(); i++) {
            Cell c = row.createCell(base.getStartCol() + i);
            CellStyle style = base.getWorkbook().createCellStyle();
            style.setBorderTop(BorderStyle.THIN);
            if (i == base.getColumns().size() - 1) {
                style.setBorderRight(BorderStyle.THIN);
            }
            c.setCellStyle(style);
        }
        row = sheet.createRow(base.getStartRow() - 1);
        CellRangeAddress region2 = new CellRangeAddress(this.base.getStartRow() - 1, this.base.getStartRow() - 1, this.base.getStartCol(), this.base.getStartCol() + this.base.getColumns().size() - 1);
        sheet.addMergedRegion(region2);
        Cell cell1 = row.createCell(base.getStartCol());
        cell1.setCellValue(param);
        CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
        cellStyle1.setBorderLeft(BorderStyle.THIN);
        cellStyle1.setBorderRight(BorderStyle.THIN);
        cell1.setCellStyle(cellStyle1);
        row.createCell(base.getStartCol() + base.getColumns().size() - 1).setCellStyle(cellStyle1);
        new Report1(base).before();
    }

    @Override
    public void after() {

    }

    @Override
    public void updateSchema(ISchema schema) {

    }
}
