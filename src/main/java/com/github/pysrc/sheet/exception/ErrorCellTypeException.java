package com.github.pysrc.sheet.exception;

import com.github.pysrc.sheet.lang.Lang;

public class ErrorCellTypeException extends Exception {
    public ErrorCellTypeException(){
        super(Lang.get("exc.cell.type"));
    }
}
