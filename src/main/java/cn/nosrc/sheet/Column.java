package cn.nosrc.sheet;

import cn.nosrc.sheet.enums.EType;

/**
 * Sheet页的列信息
 */
public class Column implements Comparable<Column> {
    public int rank = Integer.MIN_VALUE;
    public String field;
    public String title;
    public int width = 256 * 10; // 10个字符宽度
    public EType etype = EType.STR;
    public String format = "";
    public String[] valueList = {};
    public String filter = "";

    public Column() {
    }

    public Column(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String[] getValueList() {
        return valueList;
    }

    public void setValueList(String[] valueList) {
        this.valueList = valueList;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public EType getEtype() {
        return etype;
    }

    public void setEtype(EType etype) {
        this.etype = etype;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Column o) {
        return this.rank - o.getRank();
    }

}
