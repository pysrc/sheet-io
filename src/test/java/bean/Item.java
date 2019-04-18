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

    // getter/setter

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    public Double getNts() {
        return nts;
    }

    public void setNts(Double nts) {
        this.nts = nts;
    }

    public Double getNtn() {
        return ntn;
    }

    public void setNtn(Double ntn) {
        this.ntn = ntn;
    }

    public String getDrp() {
        return drp;
    }

    public void setDrp(String drp) {
        this.drp = drp;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public Date getDts() {
        return dts;
    }

    public void setDts(Date dts) {
        this.dts = dts;
    }

    public Date getDtd() {
        return dtd;
    }

    public void setDtd(Date dtd) {
        this.dtd = dtd;
    }

    public String getSlf() {
        return slf;
    }

    public void setSlf(String slf) {
        this.slf = slf;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s", sts, stn, nts, ntn, drp, std, dts, dtd, slf);
    }
}
