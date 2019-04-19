package lang;

import com.github.pysrc.sheet.exception.ErrorCellTypeException;
import com.github.pysrc.sheet.lang.Env;
import com.github.pysrc.sheet.lang.Lang;


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
