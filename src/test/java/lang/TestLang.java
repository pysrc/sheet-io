package lang;

import cn.nosrc.sheet.exception.ErrorCellTypeException;
import cn.nosrc.sheet.lang.Env;
import cn.nosrc.sheet.lang.Lang;


public class TestLang {
    public static void main(String[] args) {
        try {
            Lang.env = Env.CN;
            throw new ErrorCellTypeException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("--------------");
        try {
            Lang.env = Env.EN;
            throw new ErrorCellTypeException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
