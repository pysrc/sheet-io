package cn.nosrc.sheet.report;

import cn.nosrc.sheet.AbstractSheet;
import cn.nosrc.sheet.IWrite;

import java.text.ParseException;
import java.util.List;

/**
 * 代理模式，嵌入导出样式
 */
public abstract class AbstractReport<T> implements IWrite<T> {
    protected AbstractSheet<T> base;

    public AbstractReport(AbstractSheet<T> base) {
        this.base = base;
    }
    @Override
    public void write(List<T> datas) throws IllegalAccessException, NoSuchFieldException, ParseException {
        if(!base.isDone()){
            base.done();
        }
        before();
        base.write(datas);
        after();
    }
    public abstract void before();
    public abstract void after();
}
