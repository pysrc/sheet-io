package com.github.pysrc.sheet.report;

import com.github.pysrc.sheet.AbstractSheet;
import com.github.pysrc.sheet.style.*;
import com.github.pysrc.sheet.style.*;


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
