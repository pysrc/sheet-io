package cn.nosrc.sheet.exception;

import cn.nosrc.sheet.lang.Lang;


public class NullWorkbookException extends Exception {
    public NullWorkbookException(){
        super(Lang.get("exc.null.wb"));
    }
}
