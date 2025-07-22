package com.usermanagement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get values from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "");

            // SQL insert query
            String sql = "INSERT INTO users (name, email, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, username);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();

    if (rowsInserted > 0) {
    out.println("<!DOCTYPE html>");
    out.println("<html><head><title>Success</title>");
    out.println("<style>");
    out.println("body { margin:0; padding:0; font-family:'Segoe UI'; background: linear-gradient(to right, #00c6ff, #0072ff); height:100vh; display:flex; justify-content:center; align-items:center; }");
    out.println(".box { background: rgba(255,255,255,0.05); padding: 40px; border-radius: 20px; text-align:center; box-shadow: 0 0 20px rgba(0,0,0,0.4); backdrop-filter: blur(10px); }");
    out.println("h2 { color: #00ff99; margin-bottom: 20px; text-shadow: 0 0 8px #00ff99; }");
    out.println("p { color: #f0f0f0; margin-bottom: 30px; }");
    out.println("a { text-decoration: none; background: linear-gradient(to right, #ff416c, #ff4b2b); color: white; padding: 12px 25px; border-radius: 30px; font-weight: bold; box-shadow: 0 0 10px #ff4b2b; transition: 0.3s ease; }");
    out.println("a:hover { background: linear-gradient(to right, #ff4b2b, #ff416c); box-shadow: 0 0 15px #ff4b2b; }");
    out.println("</style></head><body>");
    out.println("<div class='box'>");
    out.println("<h2>User Registered Successfully!</h2>");
    out.println("<p>Please login using your credentials.</p>");
    out.println("<a href='login.html'>Go to Login</a>");
    out.println("</div>");
    out.println("</body></html>");
}else {
       out.println("<h2 style='color:red;'>User Registration Failed!</h2>");
      }


            conn.close();
        } catch (Exception e) {
            out.println("<h2 style='color:red;'>Error: " + e.getMessage() + "</h2>");
        }

        out.close();
    }
}
