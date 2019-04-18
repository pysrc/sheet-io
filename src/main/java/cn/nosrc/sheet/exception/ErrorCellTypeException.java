package cn.nosrc.sheet.exception;

import cn.nosrc.sheet.lang.Lang;

public class ErrorCellTypeException extends Exception {
    public ErrorCellTypeException(){
        super(Lang.get("exc.cell.type"));
    }
}
