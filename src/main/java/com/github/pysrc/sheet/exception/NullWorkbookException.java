package com.github.pysrc.sheet.exception;

import com.github.pysrc.sheet.lang.Lang;


public class NullWorkbookException extends Exception {
    public NullWorkbookException(){
        super(Lang.get("exc.null.wb"));
    }
}
