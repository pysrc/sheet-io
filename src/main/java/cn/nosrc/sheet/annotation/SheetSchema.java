package cn.nosrc.sheet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SheetSchema {
    String sheetName(); // Sheet页名称
    boolean titleEnable() default true; // 是否包含标题
    int startRow() default 1; // 开始的行
    int startCol() default 1; // 开始的列
}
