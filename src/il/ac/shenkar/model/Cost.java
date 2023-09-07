package il.ac.shenkar.model;
import  java.util.Date;


public class Cost {

    private int id;
    private double sum;
    private int categoryID;
    private Currency currency;
    private String desc;
    private Date time;

    public Cost(double sum, int categoryID, Currency currency, String desc, Date time){
        setSum(sum);
        setCategoryID(categoryID);
        setCurrency(currency);
        setDesc(desc);
        setTime(time);
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public void setCurrency( Currency currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
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

    public Currency getCurrency() {
        return currency;
    }

    public String getDesc() {
        return desc;
    }
    public Date getTime() {
        return time;
    }

}
