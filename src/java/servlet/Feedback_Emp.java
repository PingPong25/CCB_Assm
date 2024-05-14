/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "Feedback_Emp", urlPatterns = {"/Feedback_Emp"})
public class Feedback_Emp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null && action.equals("comment")) {
            saveComment(request, response);
        } else if (action != null && action.equals("rating")) {
            saveRating(request, response);
        } else {
            response.getWriter().println("Invalid action");
        }
    }

    private void saveComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentText = request.getParameter("commentText");
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String url = "jdbc:derby://localhost:1527/CCB";
            String user = "nbuser";
            String password = "nbuser";

            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO comments (comment_text) VALUES (?)";
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, commentText);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                // Retrieve the auto-generated comment ID
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int commentId = generatedKeys.getInt(1);
                    // You can use the commentId as needed
                }

                // Redirect to the same page
                response.sendRedirect("Product_emp.jsp");
            } else {
                response.getWriter().println("Failed to add comment");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while adding the comment");
        } finally {
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
    }

    private void saveRating(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rating = Integer.parseInt(request.getParameter("rating"));
        String ratingType = request.getParameter("ratingType");
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String url = "jdbc:derby://localhost:1527/CCB";
            String user = "nbuser";
            String password = "nbuser";

            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO ratings (rating, rating_type) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rating);
            pstmt.setString(2, ratingType);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                // Redirect to the same page
                response.sendRedirect("Product.jsp");
            } else {
                response.getWriter().println("Failed to add rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while adding the rating");
        } finally {
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
    }
}
