/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import domain.customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author ROG
 */
public class customerDA {

    private String host = "jdbc:derby://localhost:1527/CCB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "customer";
    private Connection conn;
    private PreparedStatement stmt;

    public customerDA() {
        createConnection();
    }

    public ResultSet selectRecord(String username) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE USERNAME = ? ";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }

    public ResultSet selectpassword(String username, String password) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE USERNAME = ? AND PASSWORD = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }

    public ResultSet selectPhone(String phone) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE PHONE = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, phone);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }

    public ResultSet selectEmail(String email) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE EMAIL = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }

    private String getMaxCustomerID() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(ID) FROM " + tableName);
        rs.next();
        return rs.getString(1);
    }

    public static String incrementCustomerID(String csID) {
        int id;
        if (csID != null && csID.matches("[A-Z]\\d+")) {
            id = Integer.parseInt(csID.substring(1)) + 1;
        } else {
            id = 1;
        }
        return "C" + String.format("%04d", id);
    }

    public static String generateNewCustomerID() throws SQLException {
        customerDA registerDA = new customerDA();
        String maxCustomerID = registerDA.getMaxCustomerID();
        String newCsID = registerDA.incrementCustomerID(maxCustomerID);
        return newCsID;
    }

    public String[] generateAgeOptions() {
        // Define the range of ages you want to provide as options
        int minAge = 5; // Minimum age
        int maxAge = 100; // Maximum age

        // Calculate the number of options based on the range
        int numOptions = maxAge - minAge + 1;

        // Create an array to store the age options
        String[] ageOptions = new String[numOptions];

        // Generate the age options and store them in the array
        for (int i = 0; i < numOptions; i++) {
            int age = minAge + i;
            ageOptions[i] = String.valueOf(age);
        }

        // Return the array of age options
        return ageOptions;
    }

    public void addRecord(customer cust) throws SQLException {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, cust.getId());
            stmt.setString(2, cust.getName());
            stmt.setString(3, cust.getPhone());
            stmt.setString(4, cust.getUsername());
            stmt.setString(5, cust.getPassword());
            stmt.setString(6, cust.getEmail());
            stmt.setInt(7, cust.getAge());
            stmt.setString(8, cust.getAddress());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updaterecord(customer cs) {
    try {
        ResultSet rs = selectRecord(cs.getUsername());
        if (rs.next()) {
            String updateStr = "UPDATE " + tableName + " SET NAME = ? ,  AGE= ? , ADDRESS = ? WHERE USERNAME  = ?";
            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, cs.getName());
            stmt.setInt(2, cs.getAge());
            stmt.setString(3, cs.getAddress());
            stmt.setString(4, cs.getUsername());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Update successful
                System.out.println("Customer record updated successfully.");
            } else {
                // No rows were updated, possibly due to no matching username
                System.out.println("No customer found with the provided username.");
            }
        } else {
            // No customer found with the provided username
            System.out.println("No customer found with the provided username.");
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    
    
    
}
    
    
    public void updateCust_manager(customer cs) {
    try {
        String updateStr = "UPDATE " + tableName + " SET NAME = ?, AGE = ?, ADDRESS = ? WHERE ID = ?";
        stmt = conn.prepareStatement(updateStr);
        stmt.setString(1, cs.getName());
        stmt.setInt(2, cs.getAge());
        stmt.setString(3, cs.getAddress());
        stmt.setString(4, cs.getId());

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            // Update successful
            System.out.println("Customer record updated successfully.");
            conn.commit(); // Commit changes
        } else {
            // No rows were updated
            System.out.println("No customer found with the provided ID.");
        }
    } catch (SQLException ex) {
        // Error occurred during update
        System.out.println("Error updating customer record: " + ex.getMessage());
        try {
            conn.rollback(); // Rollback changes if an error occurs
        } catch (SQLException e) {
            System.out.println("Error rolling back changes: " + e.getMessage());
        }
    }
}



    public static boolean verifyPhoneNumber(String phoneNumber) {
        // Regular expression to match the format "000-0000000"
        String regexPhoneNumber = "\\d{10}";
        // Check if the provided phone number matches the regex pattern
        boolean matchesFormat = phoneNumber.matches(regexPhoneNumber);
        // Return true if the phone number matches the desired format
        return matchesFormat;
    }

    public static boolean verifyEmail(String email) {
        // Regular expression to match the email address format
        String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Check if the provided email address matches the regex pattern
        return email.matches(regexEmail);
    }

    public String passwordValidation(String inputPsw) {
        String errMsg = "";
        if (inputPsw == null || inputPsw.trim().equals("")) {
            errMsg += ("Password cannot be null or an empty string");
        } else {
            int countDigits = 0;
            int countLetters = 0;
            for (int i = 0; i < inputPsw.length(); i++) {
                char ch = inputPsw.charAt(i);
                if (Character.isDigit(ch)) {
                    countDigits++;
                } else if (Character.isLetter(ch)) {
                    countLetters++;
                }
            }
            int countAlphaNumeric = countDigits + countLetters;

            if (countDigits == 0) {
                errMsg += "\nYour password should have at least 1 digit. \n";
            }
            if (countLetters == 0) {
                errMsg += "\nYour password should have at least 1 letter. \n";
            }
            if (countAlphaNumeric < 7) {
                errMsg += "\nYour password should have at least 7 characters. \n";
            }
        }
        return errMsg;
    }

    public String nameValidation(String inputname) {
        String errMsg = "";

        if (inputname == null || inputname.trim().equals("")) {
            errMsg += ("name cannot be null or an empty string");
        } else {
            int countDigits = 0;
            for (int i = 0; i < inputname.length(); i++) {
                char ch = inputname.charAt(i);
                if (Character.isDigit(ch)) {
                    countDigits++;
                }
            }

            if (countDigits != 0) {
                errMsg += "Your name cannot conclude number. \n";
            }
        }
        return errMsg;
    }
    
    
    public boolean verifyName(String inputname) {
        if (inputname == null || inputname.trim().equals("")) {
            return false;
        } else {
            int countDigits = 0;
            for (int i = 0; i < inputname.length(); i++) {
                char ch = inputname.charAt(i);
                if (Character.isDigit(ch)) {
                    countDigits++;
                }
            }

            if (countDigits != 0) {
                return false;
            }
        }
        return true;
    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(host, user, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


//======================================================================================================

public boolean deleteRecord(String ID) throws SQLException {
    String deleteStr = "DELETE FROM " + tableName + " WHERE ID = ?";
    try {
        stmt = conn.prepareStatement(deleteStr);
        stmt.setString(1, ID);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException ex) {
        throw ex;
    }
}


public ResultSet getCustRecord(String ID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            rs = stmt.executeQuery();
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

public boolean isCustNameExists(String ID) {
        boolean exists = false;
        try (Connection conn = DriverManager.getConnection(host, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE ID = ?")) {
            stmt.setString(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }

public boolean isRecordDeleted(String ID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(host, user, password);
             PreparedStatement stmt = conn.prepareStatement(queryStr)) {
            stmt.setString(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                return !rs.next(); // Returns true if no records found for the given CID
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

public ResultSet getAllCustomerRecords() throws SQLException {
    String queryStr = "SELECT * FROM " + tableName;
    ResultSet rs = null;
    try {
        stmt = conn.prepareStatement(queryStr);
        rs = stmt.executeQuery();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return rs;

}
public static void main(String[] args) {
        customerDA da = new customerDA();
    }

public customer getCustomerById(String id) {
    String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
    try {
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            customer cust = new customer();
            cust.setId(rs.getString("ID"));
            cust.setName(rs.getString("NAME"));
            cust.setPhone(rs.getString("PHONE"));
            cust.setUsername(rs.getString("USERNAME"));
            cust.setPassword(rs.getString("PASSWORD"));
            cust.setEmail(rs.getString("EMAIL"));
            cust.setAge(rs.getInt("AGE"));
            cust.setAddress(rs.getString("ADDRESS"));
            return cust;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    return null; // Return null if customer with given ID not found
}

public int getCustomerCount() {
    String queryStr = "SELECT COUNT(*) FROM " + tableName;
    int count = 0;
    try (PreparedStatement stmt = conn.prepareStatement(queryStr);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        // Handle or log the exception appropriately
        ex.printStackTrace(); // Example: Print the stack trace
    }
    return count;
    }

}