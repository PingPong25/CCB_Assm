package da;

import domain.Cart;
import domain.Product;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductDA {

    private String host = "jdbc:derby://localhost:1527/CCB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "product";
    private Connection conn;
    private PreparedStatement stmt;

    public ProductDA() {
        createConnection();
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private String getMaxProductID() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(PID) FROM " + tableName);
        rs.next();
        return rs.getString(1);
    }

    public static String incrementProductID(String PID) {
        int id;
        if (PID != null && PID.matches("[A-Z]\\d+")) {
            id = Integer.parseInt(PID.substring(1)) + 1;
        } else {
            id = 1;
        }
        return "P" + String.format("%04d", id);
    }
    
    public static String generateNewProductID() throws SQLException {
        ProductDA DA = new ProductDA();
        String maxProductID = DA.getMaxProductID();
        String newProductID = DA.incrementProductID(maxProductID);
        return newProductID;
    }

    public void addProduct(Product product) throws SQLException {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
//            File imageFile = new File("image.jpg");
//            FileInputStream fis = new FileInputStream(imageFile);
            stmt.setString(1, product.getPID().trim());
            stmt.setString(2, product.getName().trim());
            stmt.setString(3, product.getDesc().trim());
            stmt.setDouble(4, product.getPrice());
            stmt.setBytes(5, product.getImage());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Product getProduct(String PID) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE PID = ?";
        Product product = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, PID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(PID, rs.getString("PNAME"), rs.getString("PDESC"), rs.getDouble("PRICE"), rs.getBytes("IMAGE"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        try {
            String updateStr = "UPDATE " + tableName + " SET PNAME = ?, PDESC = ?, PRICE = ?, IMAGE = ? WHERE PID = ?";
            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, product.getName().trim());
            stmt.setString(2, product.getDesc().trim());
            stmt.setDouble(3, product.getPrice());
            stmt.setBytes(4, product.getImage());
            stmt.setString(5, product.getPID().trim());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void deleteProduct(String PID) throws SQLException {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE PID = ?";
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, PID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public List<Product> getAllProducts(){
        
        List<Product> productList = new ArrayList<Product>();
        
        try{
            String query = "SELECT * FROM " + tableName;
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setPID(rs.getString("PID"));
                product.setName(rs.getString("PNAME"));
                product.setDesc(rs.getString("PDESC"));
                product.setPrice(rs.getDouble("PRICE"));

                
                productList.add(product);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return productList;
    }
    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList){
        
        List<Cart> productList = new ArrayList<Cart>();
        
        try{
            if(cartList.size() > 0){
                for(Cart item:cartList){
                    String query = "SELECT * FROM " + tableName + " WHERE PID = ?";
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, item.getPID());
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        Cart cart = new Cart();
                        cart.setPID(rs.getString("PID"));
                        cart.setName(rs.getString("PNAME"));
                        cart.setDesc(rs.getString("PDESC"));
                        cart.setPrice(rs.getDouble("PRICE")*item.getQuantity());
                        cart.setQuantity((item.getQuantity()));


                        productList.add(cart);
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return productList;
    }
    
//    public List<Cart> getCartProductsManager(ArrayList<Cart> cartLists){
//        
//        List<Cart> productList = new ArrayList<Cart>();
//        
//        try{
//            if(cartLists.size() > 0){
//                for(Cart item:cartLists){
//                    String query = "SELECT * FROM " + tableName + " WHERE PID = ?";
//                    stmt = conn.prepareStatement(query);
//                    stmt.setString(1, item.getPID());
//                    ResultSet rs = stmt.executeQuery();
//                    while(rs.next()){
//                        Cart cart = new Cart();
//                        cart.setPID(rs.getString("PID"));
//                        cart.setName(rs.getString("PNAME"));
//                        cart.setDesc(rs.getString("PDESC"));
//                        cart.setPrice(rs.getDouble("PRICE")*item.getQuantity());
//                        cart.setQuantity((item.getQuantity()));
//
//
//                        productList.add(cart);
//                    }
//                }
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return productList;
//    }
//    
//    
//    public List<Cart> getCartProductsEmp(ArrayList<Cart> cartList){
//        
//        List<Cart> productList = new ArrayList<Cart>();
//        
//        try{
//            if(cartList.size() > 0){
//                for(Cart item:cartList){
//                    String query = "SELECT * FROM " + tableName + " WHERE PID = ?";
//                    stmt = conn.prepareStatement(query);
//                    stmt.setString(1, item.getPID());
//                    ResultSet rs = stmt.executeQuery();
//                    while(rs.next()){
//                        Cart cart = new Cart();
//                        cart.setPID(rs.getString("PID"));
//                        cart.setName(rs.getString("PNAME"));
//                        cart.setDesc(rs.getString("PDESC"));
//                        cart.setPrice(rs.getDouble("PRICE")*item.getQuantity());
//                        cart.setQuantity((item.getQuantity()));
//
//
//                        productList.add(cart);
//                    }
//                }
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return productList;
//    }
    
    public double getSubtotal(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item:cartList) {
                    String query = "SELECT PRICE FROM " + tableName + " WHERE PID = ?";
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, item.getPID());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("PRICE")*item.getQuantity();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
    
    public double getTaxPrice(ArrayList<Cart> cartList, double taxRate) {
        double subtotal = getSubtotal(cartList);
        return subtotal * taxRate;
    }
    
    public double getShippingPrice(ArrayList<Cart> cartList, double taxRate) {
        double subtotal = getSubtotal(cartList);
        double taxPrice = getTaxPrice(cartList, taxRate);
        
        double totalPrice = subtotal + taxPrice;
        
        if (totalPrice >= 1000) {
            return 0;
        } else {
            return 25;
        }
    }
    
    public double getTotalPrice(ArrayList<Cart> cartList, double taxRate) {
        double subtotal = getSubtotal(cartList);
        double taxPrice = getTaxPrice(cartList, taxRate);
        double shippingPrice = getShippingPrice(cartList, taxRate);
        
        double totalPrice = subtotal + taxPrice + shippingPrice;

        return totalPrice;
    }

    
    public ResultSet searchPID(String PID) throws SQLException {
        String queryStr = "SELECT PID FROM " + tableName + " WHERE PID = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, PID);
        return stmt.executeQuery();
    }
    
    public ResultSet searchName(String PName) throws SQLException {
        String queryStr = "SELECT PName FROM " + tableName + " WHERE PName = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, PName);
        return stmt.executeQuery();
    }

    public static void main(String[] args) {
        ProductDA da = new ProductDA();
    }
}