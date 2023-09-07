import il.ac.shenkar.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CategoryDAO cat = new CategoryDAO();
        cat.save(new Category(-1, "test1"));
        cat.save(new Category(-1, "test2"));
        cat.save(new Category(-1, "test3"));

        List<Category> lcat = cat.getAll();

        System.out.println(cat.get(lcat.get(1).getId()).getName());


    }

}


