package com.github.pysrc.sheet.exception;

import com.github.pysrc.sheet.lang.Lang;


public class NullDataClassException extends Exception {
    public NullDataClassException(){
        super(Lang.get("exc.null.class"));
    }
}
