package com.usermanagement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usernameOrEmail = request.getParameter("username");
        String password = request.getParameter("password");


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/userdb", "root", "");

            // Prepare SQL statement
            String sql = "SELECT * FROM users WHERE (username=? OR email=?) AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, password);


            // Execute query
            ResultSet rs = stmt.executeQuery();
if (rs.next()) {
    String name = rs.getString("name");
    String username = rs.getString("username");
    String email = rs.getString("email");
    String pass = rs.getString("password");

    out.println("<html><head><title>Welcome</title>");
    out.println("<style>");
    out.println("body {"
        + "margin:0; padding:0; font-family:sans-serif; background: linear-gradient(to right, #0f2027, #203a43, #2c5364);"
        + "color: #fff; display:flex; justify-content:center; align-items:center; height:100vh; flex-direction:column;}");
    out.println("table {"
        + "border-collapse: collapse; margin: 20px 0; font-size: 16px; min-width: 400px; border-radius: 10px;"
        + "overflow: hidden; box-shadow: 0 0 15px rgba(0,255,255,0.3); background: rgba(255,255,255,0.1);"
        + "backdrop-filter: blur(6px); color: #00ffff; text-align:left;}");
    out.println("th, td { padding: 12px 20px; border-bottom: 1px solid rgba(255,255,255,0.2); }");
    out.println("th { background-color: rgba(0,255,255,0.2); color: #fff; }");
    out.println(".celebrate {"
        + "font-size: 18px; margin-top: 15px; display: flex; align-items: center; justify-content: center; gap: 10px; "
        + "animation: glow 1.5s ease-in-out infinite alternate; }");
    out.println("@keyframes glow {"
        + "from { text-shadow: 0 0 5px #00ffff; }"
        + "to { text-shadow: 0 0 20px #00ffff; }}");
    out.println("button {"
        + "margin-top: 12px; padding: 10px 20px; font-weight:bold; font-size: 15px; "
        + "color: #000; background-color: #00ffff; border: none; border-radius: 6px; cursor: pointer;}");
    out.println("button:hover { background-color: #00cccc; }");
    out.println(".info-note { font-size: 14px; margin-top: 10px; color: #cfcfcf; }");
    out.println("</style></head><body>");

    out.println("<h2>Welcome!</h2>");
    out.println("<table>");
    out.println("<tr><th>Name</th><td>" + name + "</td></tr>");
    out.println("<tr><th>Username</th><td>" + username + "</td></tr>");
    out.println("<tr><th>Email</th><td>" + email + "</td></tr>");
    out.println("<tr><th>Password</th><td>" + pass + "</td></tr>");
    out.println("</table>");

    out.println("<div class='celebrate'>&#x1F389; Congratulations on successful login! &#x1F389;</div>");

    out.println("<div class='info-note'>If you wish to change any field data, click on Update</div>");
    out.println("<form action='update.html' method='get'><button type='submit'>Update</button></form>");

    out.println("</body></html>");
}

   
else {
    out.println("<html><head><title>Login Failed</title>");
    out.println("<style>");
    out.println("body {"
        + "margin:0; padding:0; font-family:sans-serif; background: linear-gradient(to left, #3a1c71, #d76d77, #ffaf7b);"
        + "color: #fff; display:flex; justify-content:center; align-items:center; height:100vh; flex-direction:column;}");
    out.println("h2 { color: #ff4444; margin-bottom: 10px; }");
    out.println("p { font-size: 16px; color: #fff; }");
    out.println("</style></head><body>");
    out.println("<h2>Login Failed!</h2>");
    out.println("<p>Invalid username/email or password.</p>");
    out.println("</body></html>");
}


            conn.close();

        } catch (Exception e) {
            out.println("<p style='color: red;'>Error: " + e.getMessage() + "</p>");
        }

        out.close();
    }
}

