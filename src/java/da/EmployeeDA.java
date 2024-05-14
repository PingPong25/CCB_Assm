package da;

import java.sql.*;
import domain.Employee;
import javax.swing.JOptionPane;

public class EmployeeDA {

    private String host = "jdbc:derby://localhost:1527/CCB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "EMPLOYEE";
    private Connection conn;
    private PreparedStatement stmt;

    public EmployeeDA() {
        createConnection();
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String getMaxEmployeeID() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(EMPLOYEE_ID) FROM " + tableName);
        rs.next();
        return rs.getString(1);
    }

    public static String incrementEmployeeID(String empID) {
        int id;
        if (empID != null && empID.matches("[A-Z]\\d+")) {
            id = Integer.parseInt(empID.substring(1)) + 1;
        } else {
            id = 1;
        }
        return "E" + String.format("%04d", id);
    }

    public void registerEmployee(Employee emp) throws SQLException {
        try {
            String registerStr = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(registerStr);
            stmt.setString(1, emp.getEmpId().trim());
            stmt.setString(2, emp.getEmpName().trim());
            stmt.setString(3, emp.getEmpPhone().trim());
            stmt.setString(4, emp.getEmail().trim());
            stmt.setString(5, emp.getUserName().trim());
            stmt.setInt(6, emp.getEmpAge());
            stmt.setString(7, emp.getEmpGender().trim());
            stmt.setString(8, emp.getEmpJob().trim());
            stmt.setString(9, "ABC123");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // Handle the exception properly
            ex.printStackTrace();
            throw ex; // Re-throw the exception to be handled at a higher level
        } finally {
            // Close resources in a finally block to ensure they are always closed
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updatePassword(Employee emp) throws SQLException {
        try {
            String updateStr = "UPDATE " + tableName + " SET EMPLOYEE_PASSWORD = ? WHERE USERNAME = ?";
            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, emp.getEmpPass().trim());
            stmt.setString(2, emp.getUserName().trim());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // Handle the exception properly
            ex.printStackTrace();
            throw ex; // Re-throw the exception to be handled at a higher level
        } finally {
            // Close resources in a finally block to ensure they are always closed
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static String generateNewEmployeeID() throws SQLException {
        EmployeeDA registerDA = new EmployeeDA();
        String maxEmployeeID = registerDA.getMaxEmployeeID();
        String newEmpID = registerDA.incrementEmployeeID(maxEmployeeID);
        return newEmpID;
    }

    private ResultSet search(String name) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE EMPLOYEE_NAME = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, name);
        return stmt.executeQuery();
    }

    public static String[] generateAgeOptions() {
        String[] options = new String[13];
        for (int i = 0; i < 13; i++) {
            options[i] = Integer.toString(18 + i);
        }
        return options;
    }

    // Select password for a given username
    public ResultSet searchEmployee(String username) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE USERNAME = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, username);
        return stmt.executeQuery();
    }

    // Select password for a given username
    public ResultSet searchPassword(String username) throws SQLException {
        String queryStr = "SELECT EMPLOYEE_PASSWORD FROM " + tableName + " WHERE USERNAME = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, username);
        return stmt.executeQuery();
    }

    public ResultSet searchPhone(String phone) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE PHONE_NUM = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, phone);
        return stmt.executeQuery();
    }
    
    public ResultSet searchEmail(String email) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE  EMAIL = ?";
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, email);
        return stmt.executeQuery();
    }

    public static boolean verifyPhoneNumber(String phoneNumber) {
        // Regular expression to match the format "000-0000000"
        String regexPhoneNumber = "\\d{3}-\\d{7}";
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

    public String nameValidation(String inputname) {
        String errMsg = "";

        if (inputname == null || inputname.trim().equals("")) {
            errMsg += ("Name cannot be null or an empty string");
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

    public boolean isPasswordValid(String inputPsw) {
    if (inputPsw == null || inputPsw.trim().equals("")) {
        return false; // Password cannot be null or an empty string
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

        if (countDigits == 0 || countLetters == 0 || countAlphaNumeric < 7) {
            return false; // Password does not meet the criteria
        }
    }
    return true; // Password is valid
}

    
    public static boolean verifyJob(String job) {
        // Regular expression to match the format "000-0000000"
        String jobPosition = "Sales Person";
        // Check if the provided phone number matches the regex pattern
        boolean matchesFormat = job.matches(jobPosition);
        // Return true if the phone number matches the desired format
        return matchesFormat;
    }





//===========================================================================

public static void main(String[] args) {
        EmployeeDA daEmp = new EmployeeDA();
    }

public ResultSet getAllEmpRecords() throws SQLException {
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

public Employee getCustomerById(String id) {
    String queryStr = "SELECT * FROM " + tableName + " WHERE EMPLOYEE_ID = ?";
    try {
        stmt = conn.prepareStatement(queryStr);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Employee emp = new Employee();
            emp.setEmpId(rs.getString("EMPLOYEE_ID"));
            emp.setEmpName(rs.getString("EMPLOYEE_NAME"));
            emp.setEmpPhone(rs.getString("PHONE_NUM"));
            emp.setEmail(rs.getString("EMAIL"));
            emp.setUserName(rs.getString("USERNAME"));
            emp.setEmpAge(rs.getInt("AGE"));
            emp.setEmpGender(rs.getString("GENDER"));
            emp.setEmpJob(rs.getString("JOB"));
            return emp;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    return null; 
}

public boolean isEmpNameExists(String ID) {
        boolean exists = false;
        try (Connection conn = DriverManager.getConnection(host, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE EMPLOYEE_ID = ?")) {
            stmt.setString(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }



public void updateEmp_manager(Employee emp) {
    try {
        String updateStr = "UPDATE " + tableName + " SET EMPLOYEE_NAME = ?, AGE = ?, JOB = ? WHERE EMPLOYEE_ID = ?";
        stmt = conn.prepareStatement(updateStr);
        stmt.setString(1, emp.getEmpName());
        stmt.setInt(2, emp.getEmpAge());
        stmt.setString(3, emp.getEmpJob());
        stmt.setString(4, emp.getEmpId());

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            // Update successful
            System.out.println("Employee record updated successfully.");
            conn.commit(); // Commit changes
        } else {
            System.out.println("No Employee found with the provided ID.");
        }
    } catch (SQLException ex) {

        System.out.println("Error updating Employee record: " + ex.getMessage());
        try {
            conn.rollback(); 
        } catch (SQLException e) {
            System.out.println("Error rolling back changes: " + e.getMessage());
        }
    }
}

public boolean deleteRecord(String id) throws SQLException {
    String deleteStr = "DELETE FROM " + tableName + " WHERE EMPLOYEE_ID = ?";
    try {
        stmt = conn.prepareStatement(deleteStr);
        stmt.setString(1, id);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException ex) {
        throw ex;
    }
}


public boolean isEmpRecordDeleted(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE EMPLOYEE_ID = ?";
        try (Connection conn = DriverManager.getConnection(host, user, password);
             PreparedStatement stmt = conn.prepareStatement(queryStr)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return !rs.next();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

public int getEmployeeCount() {
    String queryStr = "SELECT COUNT(*) FROM " + tableName;
    int count = 0;
    try (PreparedStatement stmt = conn.prepareStatement(queryStr);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); 
    }
    return count;
}

}