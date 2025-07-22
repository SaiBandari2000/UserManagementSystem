package com.usermanagement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdb", "root", "");

            String sql = "UPDATE users SET name=?, email=?, password=? WHERE username=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, username);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                out.println("<html><head><title>Update Successful</title>");
                out.println("<style>");
                out.println("body {"
                        + "font-family: 'Segoe UI', sans-serif;"
                        + "background: linear-gradient(to right, #00c9ff, #92fe9d);"
                        + "display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");

                out.println(".box {"
                        + "background: rgba(255, 255, 255, 0.1);"
                        + "padding: 40px; border-radius: 20px; text-align: center;"
                        + "backdrop-filter: blur(10px); box-shadow: 0 0 25px rgba(0,0,0,0.3);"
                        + "width: 380px; }");

                out.println("h2 {"
                        + "color: #ff4c4c;"
                        + "text-shadow: 0 0 10px red, 0 0 20px red;"
                        + "margin-bottom: 20px;"
                        + "font-size: 24px; }");

                out.println("p {"
                        + "color: #ffffff; font-size: 15px; margin-bottom: 30px; }");

                out.println("a.button {"
                        + "display: block; margin: 10px auto; padding: 12px 20px;"
                        + "background-color: #ffffff; color: #000;"
                        + "text-decoration: none; font-weight: bold;"
                        + "border-radius: 8px; box-shadow: 0 0 10px rgba(255,255,255,0.4);"
                        + "transition: 0.3s ease; width: 80%; }");

                out.println("a.button:hover { background-color: #f2f2f2; }");
                out.println("</style></head><body>");

                out.println("<div class='box'>");
                out.println("<h2>Profile Updated Successfully!</h2>");
                out.println("<p>Your details have been saved successfully. Please login again.</p>");
                out.println("<a class='button' href='login.html'>Go to Login</a>");
                out.println("<a class='button' href='index.html'>Back to Home</a>");
                out.println("</div>");

                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h3 style='color:red; text-align:center;'>Update failed! Username not found.</h3>");
                out.println("</body></html>");
            }

            conn.close();

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
            out.println("</body></html>");
        }

        out.close();
    }
}
