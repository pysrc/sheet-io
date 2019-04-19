import com.github.pysrc.sheet.impl.SheetRead;
import com.github.pysrc.sheet.impl.SheetWrite;
import bean.Item;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestBase {
    static void out() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item("字符测试", "456.9", 70.34, 43.1, "项目2", "2019/12/04", new Date(), new Date(), "Hello"));
        items.add(new Item("字符测试2", "46", 8.4, 78.45, "项目1", "2019/10/04", new Date(), new Date(), "Hi"));
        OutputStream os = new FileOutputStream("E:/xxx.xlsx");
        Workbook wb = new XSSFWorkbook();
        new SheetWrite<>(wb, Item.class)
                .write(items);
        wb.write(os);
        wb.close();
        os.close();
    }

    public static void in() throws Exception {
        InputStream is = new FileInputStream("E:/xxx.xlsx");
        Workbook wb = new XSSFWorkbook(is);
        List<Item> read = new SheetRead<Item>(wb, Item.class)
                .read();
        wb.close();
        is.close();
        for (Item i : read) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws Exception {
        out();
        in();
    }
}
