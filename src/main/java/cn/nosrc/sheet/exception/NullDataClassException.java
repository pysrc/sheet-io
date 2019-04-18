package cn.nosrc.sheet.exception;

import cn.nosrc.sheet.lang.Lang;


public class NullDataClassException extends Exception {
    public NullDataClassException(){
        super(Lang.get("exc.null.class"));
    }
}
