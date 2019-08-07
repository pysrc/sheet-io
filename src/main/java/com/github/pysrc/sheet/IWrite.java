package com.github.pysrc.sheet;

import java.text.ParseException;
import java.util.List;

public interface IWrite<T> {

    /**
     * 写入时更新Schema
     * @param schema
     */
    public void updateSchema(ISchema schema);

    /**
     * 写数据
     * @param datas
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ParseException
     */
    public void write(List<T> datas) throws IllegalAccessException, NoSuchFieldException, ParseException;


}
