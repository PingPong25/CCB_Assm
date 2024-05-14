package da;

import domain.Sales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SalesDA {

    private String host = "jdbc:derby://localhost:1527/CCB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "sales";
    private Connection conn;
    private PreparedStatement stmt;

    public SalesDA() {
        createConnection();
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String getMaxSalesID() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(SID) FROM " + tableName);
        rs.next();
        return rs.getString(1);
    }

    public static String incrementSalesID(String SID) {
        int id;
        if (SID != null && SID.matches("[A-Z]\\d+")) {
            id = Integer.parseInt(SID.substring(1)) + 1;
        } else {
            id = 1;
        }
        return "S" + String.format("%04d", id);
    }

    public static String generateNewSalesID() throws SQLException {
        SalesDA DA = new SalesDA();
        String maxSalesID = DA.getMaxSalesID();
        String newSalesID = DA.incrementSalesID(maxSalesID);
        return newSalesID;
    }

    public void addSales(Sales sales) throws SQLException {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, sales.getSID().trim());
            stmt.setString(2, sales.getPID().trim());
            stmt.setInt(3, sales.getQuantity());
            stmt.setDouble(4, sales.getRevenue());
            stmt.setInt(5, sales.getDay());
            stmt.setInt(6, sales.getMonth());
            stmt.setInt(7, sales.getYears());
            stmt.setTimestamp(8, sales.getDate());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Sales> getSales(String day, String month, String year){

        List<Sales> salesList = new ArrayList<Sales>();

        try{
            String query = "SELECT * FROM " + tableName;
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Sales sales = new Sales();
                sales.setSID(rs.getString("SID"));
                sales.setPID(rs.getString("PID"));
                sales.setQuantity(rs.getInt("QUANTITY"));
                sales.setRevenue(rs.getDouble("REVENUE"));
                sales.setQuantity(rs.getInt("DAY"));
                sales.setQuantity(rs.getInt("MONTH"));
                sales.setQuantity(rs.getInt("YEARS"));
                sales.setDate(rs.getTimestamp("DATE"));

                salesList.add(sales);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return salesList;
    }

    public List<Sales> getDailySales(int day, int month, int year){

        List<Sales> salesList = new ArrayList<Sales>();

        try{
            String query = "SELECT * FROM " + tableName + " WHERE DAY = ? AND MONTH = ? AND YEARS = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, day);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Sales sales = new Sales();
                sales.setSID(rs.getString("SID"));
                sales.setPID(rs.getString("PID"));
                sales.setQuantity(rs.getInt("QUANTITY"));
                sales.setRevenue(rs.getDouble("REVENUE"));
                sales.setQuantity(rs.getInt("DAY"));
                sales.setQuantity(rs.getInt("MONTH"));
                sales.setQuantity(rs.getInt("YEARS"));
                sales.setDate(rs.getTimestamp("DATE"));

                salesList.add(sales);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return salesList;
    }

    public List<Sales> getMonthlySales(int month, int year){

        List<Sales> salesList = new ArrayList<Sales>();

        try{
            String query = "SELECT * FROM " + tableName + " WHERE MONTH = ? AND YEARS = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Sales sales = new Sales();
                sales.setSID(rs.getString("SID"));
                sales.setPID(rs.getString("PID"));
                sales.setQuantity(rs.getInt("QUANTITY"));
                sales.setRevenue(rs.getDouble("REVENUE"));
                sales.setQuantity(rs.getInt("DAY"));
                sales.setQuantity(rs.getInt("MONTH"));
                sales.setQuantity(rs.getInt("YEARS"));
                sales.setDate(rs.getTimestamp("DATE"));

                salesList.add(sales);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return salesList;
    }

    public List<Sales> getYearlySales(int year){

        List<Sales> salesList = new ArrayList<Sales>();

        try{
            String query = "SELECT * FROM " + tableName + " WHERE YEARS = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Sales sales = new Sales();
                sales.setSID(rs.getString("SID"));
                sales.setPID(rs.getString("PID"));
                sales.setQuantity(rs.getInt("QUANTITY"));
                sales.setRevenue(rs.getDouble("REVENUE"));
                sales.setQuantity(rs.getInt("DAY"));
                sales.setQuantity(rs.getInt("MONTH"));
                sales.setQuantity(rs.getInt("YEARS"));
                sales.setDate(rs.getTimestamp("DATE"));

                salesList.add(sales);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return salesList;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static void main(String[] args) {
        ProductDA da = new ProductDA();
    }
}