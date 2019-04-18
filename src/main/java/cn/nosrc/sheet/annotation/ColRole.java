package cn.nosrc.sheet.annotation;

import cn.nosrc.sheet.enums.EType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColRole {
    int rank() default Integer.MIN_VALUE; // 列排序优先级

    String title() default ""; // 标题

    int width() default 256 * 10; // 列宽

    EType etype() default EType.STR; // 导出的Cell类型

    String format() default ""; // 数据格式

    String[] valueList() default {}; // 下拉列表

    String filter() default ""; // 字段过滤规则
}
