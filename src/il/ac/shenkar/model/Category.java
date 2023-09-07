package il.ac.shenkar.model;

public class Category {
    private int id  = -1;
    private String name;

    public Category(int id, String name){
        setId(id);
        setName(name);
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

}
