package com.github.pysrc.sheet.lang;

import java.util.HashMap;
import java.util.Map;

public class Lang {
    public static Env env = Env.EN;
    public static Map<Env, Map<String, String>> langs = new HashMap<>();

    static {
        langs.put(Env.EN, new HashMap<String, String>() {{
            put("exc.cell.type", "Cell type is error");
            put("exc.null.class", "Dataclass is null");
            put("exc.null.sheet", "Sheet is null");
            put("exc.null.wb", "Workbook is null");
        }});
        langs.put(Env.CN, new HashMap<String, String>() {{
            put("exc.cell.type", "当前Cell类型错误");
            put("exc.null.class", "数据类型类为空");
            put("exc.null.sheet", "Sheet 页为空");
            put("exc.null.wb", "Workbook 为空");
        }});
    }

    public static String get(String id) {
        return langs.get(env).get(id);
    }
}
