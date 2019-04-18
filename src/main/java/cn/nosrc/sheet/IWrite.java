package cn.nosrc.sheet;

import java.text.ParseException;
import java.util.List;

public interface IWrite<T> {
    public void write(List<T> datas) throws IllegalAccessException, NoSuchFieldException, ParseException;
}
