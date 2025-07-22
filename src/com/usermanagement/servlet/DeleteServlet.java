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

public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdb", "root", "");

            String sql = "DELETE FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                // Success page with redirect
                out.println("<html><head><meta charset='UTF-8'><style>");
                out.println("body { background-color: #000; color: #00ff00; text-align: center; font-family: Arial; }");
                out.println(".msg { font-size: 24px; margin-top: 200px; text-shadow: 0 0 10px #00ff00; }");
                out.println("</style>");
                out.println("<meta http-equiv='refresh' content='3;URL=login.html' />");
                out.println("</head><body>");
                out.println("<div class='msg'>User deleted successfully.<br>Redirecting to Login page...</div>");
                out.println("</body></html>");
            } else {
                // No user found
                out.println("<html><head><meta charset='UTF-8'><style>");
                out.println("body { background-color: #1a1a1a; color: orange; text-align: center; font-family: Arial; }");
                out.println(".msg { font-size: 20px; margin-top: 200px; text-shadow: 0 0 5px orange; }");
                out.println("</style></head><body>");
                out.println("<div class='msg'>No user found with the given username.</div>");
                out.println("</body></html>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("<html><head><meta charset='UTF-8'><style>");
            out.println("body { background-color: black; color: red; font-family: monospace; text-align: center; }");
            out.println("h2 { margin-top: 200px; }");
            out.println("</style></head><body>");
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            out.println("</body></html>");
        }

        out.close();
    }
}
