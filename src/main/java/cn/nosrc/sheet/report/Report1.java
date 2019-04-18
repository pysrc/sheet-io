package cn.nosrc.sheet.report;

import cn.nosrc.sheet.AbstractSheet;
import cn.nosrc.sheet.style.*;


/**
 * 通用报表导出风格
 *
 */
public class Report1<T> extends AbstractReport<T> {
    public Report1(AbstractSheet<T> base) {
        super(base);
    }

    @Override
    public void before() {
        base.addTitleStyle(new StyleGray())
                .addTitleStyle(new StyleCenter())
                .addTitleStyle(new StyleBlockFont())
                .addTitleStyle(new StyleFrame())
                .addRowStyle(new StyleFrame())
                .addRowStyle(new StyleLeft());
    }

    @Override
    public void after() {

    }

}
