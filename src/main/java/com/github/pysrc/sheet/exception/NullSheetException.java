package com.github.pysrc.sheet.exception;

import com.github.pysrc.sheet.lang.Lang;


public class NullSheetException extends Exception {
    public NullSheetException(){
        super(Lang.get("exc.null.sheet"));
    }
}
