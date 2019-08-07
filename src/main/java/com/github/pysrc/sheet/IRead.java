package com.github.pysrc.sheet;

import java.text.ParseException;
import java.util.List;

public interface IRead<T> {
    /**
     * 读数据校验/修改
     * @param validator
     */
    AbstractSheet<T> setValidator(IReadValidator validator);
    /**
     * 读Sheet方法
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     * @throws ParseException
     */
    public List<T> read() throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException;
}
