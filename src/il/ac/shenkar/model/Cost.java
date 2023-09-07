package il.ac.shenkar.model;
import  java.util.Date;

public class Cost {
    private double sum;
    private int categoryID;
    private String currency;
    private String desc;
    private Date time;

    public Cost(double sum, int categoryID, String currency, String desc, Date time){
        setSum(sum);
        setCategoryID(categoryID);
        setCurrency(currency);
        setDesc(desc);
        setTime(time);
    }
    public void setSum(double sum) {
        this.sum = sum;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public double getSum() {
        return sum;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDesc() {
        return desc;
    }
    public Date getTime() {
        return time;
    }

}
