package cn.nosrc.sheet.exception;

import cn.nosrc.sheet.lang.Lang;


public class NullSheetException extends Exception {
    public NullSheetException(){
        super(Lang.get("exc.null.sheet"));
    }
}
