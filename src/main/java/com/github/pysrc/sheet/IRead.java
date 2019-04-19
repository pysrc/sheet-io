package com.github.pysrc.sheet;

import java.text.ParseException;
import java.util.List;

public interface IRead<T> {
    public List<T> read() throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException;
}
