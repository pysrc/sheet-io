package com.github.pysrc.sheet;

import com.github.pysrc.sheet.annotation.ColRole;
import com.github.pysrc.sheet.annotation.SheetSchema;
import com.github.pysrc.sheet.exception.NullDataClassException;
import com.github.pysrc.sheet.exception.NullWorkbookException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

public abstract class AbstractSheet<T> implements IWrite<T>,IRead<T>{
    protected Workbook workbook = null;
    protected Sheet sheet = null;
    protected Class<T> dataClass = null;
    protected Map<String, Field> fieldMap = new HashMap<>();
    protected IScan<T> scan = null;
    protected List<Column> columns = new ArrayList<>();
    protected boolean titleEnable = true;
    protected int startRow = 0;
    protected int startCol = 0;
    protected List<ICellStyle> titleStyles = new ArrayList<>();
    protected List<ICellStyle> rowStyles = new ArrayList<>();
    protected String filter = ".*|\\n.*";
    protected boolean isDone = false;
    protected IReadValidator validator;

    public AbstractSheet<T> addRowStyle(ICellStyle rowStyle) {
        this.rowStyles.add(rowStyle);
        return this;
    }

    public AbstractSheet<T> addTitleStyle(ICellStyle titleStyle) {
        this.titleStyles.add(titleStyle);
        return this;
    }


    protected void classLoad() {
        for (Field field : dataClass.getDeclaredFields()) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
    }

    // 加载注解
    protected void annotationScan() {
        if (dataClass.isAnnotationPresent(SheetSchema.class)) {
            SheetSchema schema = dataClass.getAnnotation(SheetSchema.class);
            this.start(schema.startRow(), schema.startCol());
            this.titleEnable = schema.titleEnable();
            this.sheet = this.workbook.getSheet(schema.sheetName());
            if (this.sheet == null) {
                this.sheet = this.workbook.createSheet(schema.sheetName());
            }
        }
        for (Field field : dataClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(ColRole.class)) {
                continue;
            }
            ColRole col = field.getAnnotation(ColRole.class);
            Column c = new Column();
            c.setRank(col.rank());
            c.setField(field.getName());
            c.setTitle(col.title());
            c.setWidth(col.width());
            c.setEtype(col.etype());
            c.setValueList(col.valueList());
            c.setFormat(col.format());
            c.setFilter(col.filter());
            this.addColumn(c);
        }
    }

    /**
     * 写入时更新Schema
     * @param schema
     */
    @Override
    public void updateSchema(ISchema schema) {
        for(Column column:columns){
            schema.update(column);
        }
    }

    /**
     * 读时校验
     * @param validator
     */
    @Override
    public AbstractSheet<T> setValidator(IReadValidator validator) {
        this.validator = validator;
        return this;
    }


    public AbstractSheet(Workbook workbook, Class<T> dataClass) throws NullDataClassException, NullWorkbookException {
        if (workbook == null) {
            throw new NullWorkbookException();
        }
        if (dataClass == null) {
            throw new NullDataClassException();
        }
        this.workbook = workbook;
        this.dataClass = dataClass;
        classLoad();
        annotationScan();
        this.validator = (row, column,call)->{
            return true;
        };
    }


    /**
     * 属性设置完成事件
     *
     * @return
     */
    public AbstractSheet<T> done() {
        // 按规则过滤列
        List<Column> _t = new ArrayList<>();
        for (Column c : columns) {
            if (Pattern.matches(filter, c.getFilter())) {
                _t.add(c);
            }
        }
        columns = _t;
        // 填充默认列数
        int i = 0;
        for (Column c : columns) {
            if (c.getRank() == Integer.MIN_VALUE) {
                c.setRank(i++);
            }
        }
        // 排序，从新定义列序数
        Collections.sort(columns);
        int _c = 0;
        for (Column c : columns) {
            c.setRank(_c++);
        }
        isDone = true;
        return this;
    }

    public AbstractSheet<T> start(int row, int col) {
        this.startRow = row;
        this.startCol = col;
        return this;
    }

    public AbstractSheet<T> titleEnable(boolean is) {
        this.titleEnable = is;
        return this;
    }

    public AbstractSheet<T> scan(IScan<T> scan) {
        this.scan = scan;
        return this;
    }

    public AbstractSheet<T> addColumn(Column column) {
        this.columns.add(column);
        return this;
    }

    public AbstractSheet<T> addColumns(List<Column> columns) {
        for (Column c : columns) {
            this.addColumn(c);
        }
        return this;
    }

    // getter/setter

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Class<T> getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public IScan<T> getScan() {
        return scan;
    }

    public void setScan(IScan<T> scan) {
        this.scan = scan;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public boolean isTitleEnable() {
        return titleEnable;
    }

    public void setTitleEnable(boolean titleEnable) {
        this.titleEnable = titleEnable;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public String getFilter() {
        return filter;
    }

    public AbstractSheet<T> setFilter(String filter) {
        this.filter = filter;
        return this;
    }

    public boolean isDone() {
        return isDone;
    }
}
