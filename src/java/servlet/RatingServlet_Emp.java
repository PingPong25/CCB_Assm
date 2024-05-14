package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RatingServlet_Emp", urlPatterns = {"/RatingServlet_Emp"})
public class RatingServlet_Emp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve rating from the request
        String rating = request.getParameter("rating");

        // Call method to save rating to database
        boolean success = saveRatingToDatabase(rating);

        if (success) {
            // Redirect back to rating.jsp
            response.sendRedirect("mainPage_Emp.jsp");
        } else {
            // Send error message to rating.jsp
            request.setAttribute("errorMessage", "Failed to save rating.");
            request.getRequestDispatcher("Rating_Emp.jsp").forward(request, response);
        }
    }

    private boolean saveRatingToDatabase(String rating) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            // Establish database connection
            String url = "jdbc:derby://localhost:1527/CCB";
            String user = "nbuser";
            String password = "nbuser";
            conn = DriverManager.getConnection(url, user, password);

            // Create SQL query to insert rating into database
            String sql = "INSERT INTO ratings (rating) VALUES (?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(rating));

            // Execute SQL query
            int rowsInserted = pstmt.executeUpdate();

            // Check if rating was successfully inserted
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return success;
    }
}