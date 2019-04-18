### Excel导入/出工具类

类似的东西挺多的，这个仓库仅仅根据实际使用总结出来的工具类

### Example

#### Bean类

```java
package bean;

import cn.nosrc.sheet.annotation.ColRole;
import cn.nosrc.sheet.annotation.SheetSchema;
import cn.nosrc.sheet.enums.EType;

import java.util.Date;


@SheetSchema(
        sheetName = "Sheet1",
        titleEnable = true,
        startCol = 1,
        startRow = 4
)
public class Item {
    // 以下代表 Java类型->Excel类型 导入时类型则相反
    @ColRole(title = "字符->字符")
    private String sts;
    @ColRole(title = "字符->数字", etype = EType.NUM, format = "0.00") // 保留两位小数
    private String stn;
    @ColRole(title = "数字->字符")
    private Double nts;
    @ColRole(title = "数字->数字", etype = EType.NUM, format = "00.00$", rank = 100)
    private Double ntn;
    @ColRole(title = "下拉框", valueList = {"项目1", "项目2", "项目3", "项目4"}, filter = "test")
    private String drp;
    @ColRole(title = "字符->日期", width = 256 * 20, etype = EType.DAT, format = "yyyy/MM/dd")
    private String std;
    @ColRole(title = "日期->字符", width = 256 * 20, format = "yyyy/MM/dd")
    private Date dts;
    @ColRole(title = "日期->日期", width = 256 * 30, etype = EType.DAT, format = "yyyy/MM/dd HH:mm:ss")
    private Date dtd;
    @ColRole(title = "其他测试", width = 256 * 20, filter = "test")
    private String slf;

    public Item() {
    }

    public Item(String sts, String stn, Double nts, Double ntn, String drp, String std, Date dts, Date dtd, String slf) {
        this.sts = sts;
        this.stn = stn;
        this.nts = nts;
        this.ntn = ntn;
        this.drp = drp;
        this.std = std;
        this.dts = dts;
        this.dtd = dtd;
        this.slf = slf;
    }

    // getter/setter 省略
    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s", sts, stn, nts, ntn, drp, std, dts, dtd, slf);
    }
}

```

#### 基本导入/出测试

```java
import cn.nosrc.sheet.impl.SheetRead;
import cn.nosrc.sheet.impl.SheetWrite;
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
        new SheetWrite<>(wb, Item.class).write(items);
        wb.write(os);
        wb.close();
        os.close();
    }

    public static void in() throws Exception {
        InputStream is = new FileInputStream("E:/xxx.xlsx");
        Workbook wb = new XSSFWorkbook(is);
        List<Item> read = new SheetRead<Item>(wb, Item.class).read();
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

```

**更多测试见src/test文件夹**

