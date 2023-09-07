package il.ac.shenkar.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class CostDAO implements DAO<Cost> {
    @Override
        public Cost get(int id){
        try {
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM costs WHERE cost_id = "+ id);
            rs.next();
            return new Cost(
                    rs.getDouble("amount"),
                    rs.getInt("cat_id"),
                    Currency.valueOf(rs.getString("currency")),
                    rs.getString("description"),
                    new Date(rs.getDate("date").getTime())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Cost> getAll(){
        List <Cost> costs = new LinkedList<>();
        try {
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM  costs");
            while(rs.next()){
                costs.add(new Cost(
                        rs.getDouble("amount"),
                        rs.getInt("cat_id"),
                        Currency.valueOf(rs.getString("currency")),
                        rs.getString("description"),
                        new Date(rs.getDate("date").getTime())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return costs;
    }

    @Override
    public void save(Cost c) {
        try {
            conn.stm.executeUpdate("INSERT INTO costs (cat_id, amount, currency, description, date) VALUES ("+
            c.getCategoryID() + "," +
            c.getSum() + ",'" +
            c.getCurrency().toString() + "','" +
            c.getDesc() +"'," +
            c.getTime() +")"
            );
        } catch (SQLException e) {
            e.printStackTrace();        }
    }
    @Override
    public void update(int id, Cost c) {
        try {
            conn.stm.executeUpdate("UPDATE costs SET " +
                    "cat_id = "+ c.getCategoryID() + "," +
                    "ammount = "+ c.getSum() + "," +
                    "currency = '" +c.getCurrency().toString() +"'," +
                    "description = '" + c.getDesc() + "'," +
                    "date = " + c.getTime() +"," +
                    "WHERE cost_id =" + c.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cost c) {
        try {
            conn.stm.execute("DELETE FROM costs WHERE cost_id =" + c.getId());
        } catch (SQLException e) {
            e.printStackTrace();        }

    }

    public List<Cost> getBetweenDates(Date start, Date end){
        List <Cost> costs = new LinkedList<>();
        try {
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM  costs WHERE  date BETWEEN" + start + "AND" + end);
            while(rs.next()){
                costs.add(new Cost(
                        rs.getDouble("amount"),
                        rs.getInt("cat_id"),
                        Currency.valueOf(rs.getString("currency")),
                        rs.getString("description"),
                        new Date(rs.getDate("date").getTime())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return costs;
    }
}

