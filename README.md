# User Management System (Java Servlet + MySQL + HTML/CSS)

A full-stack Java web application to manage user registration, login, update, and deletion using Servlets, JSP-like HTML pages, and MySQL database.

---

## 🌐 Technologies Used

- Java Servlet (Backend)
- HTML5 + CSS3 (Frontend)
- MySQL (Database)
- Apache Tomcat (Server)
- Git & GitHub (Version Control)
- VS Code (IDE)

---

## 📁 Folder Structure

UserManagementSystem/
├── src/ # Java source code
├── lib/ # JAR dependencies
├── build/ # Compiled .class files
├── bin/ # Optional backup .class files
├── UserApp/ # UI + web.xml inside WEB-INF
│ ├── index.html
│ ├── createuser.html
│ ├── login.html
│ ├── update.html
│ ├── delete.html
│ └── WEB-INF/web.xml
├── README.md # You're reading it


---

## 🚀 How to Run This Project

> 🔧 Pre-requisites: Java, MySQL, Apache Tomcat, and VS Code with Java Extension Pack.

1. Create a MySQL database:
   ```sql
   CREATE DATABASE userdb;

Create users table:

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE,
  name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(100)
);

Compile the Servlets:
javac -encoding UTF-8 -cp "lib/mysql-connector-j-9.3.0.jar;C:/xampp/tomcat/lib/servlet-api.jar" -d build src/com/usermanagement/servlet/*.java

Deploy UserApp to:
C:/xampp/tomcat/webapps/UserApp/

Start Tomcat via XAMPP.

Open your browser and test:
http://localhost:8081/UserApp/index.html

👨‍💻 Features

Create user

Login (via username or email)

Update user profile

Delete user

Stylish UI with glassmorphism and gradient effects

📢 For Recruiters
This project is designed and built by Sai Bandari.
It demonstrates Java Servlet backend integration with a fully responsive UI.

✅ You can test it live on your local machine
✅ It follows MVC structure
✅ Clear folder structure and modular code

Please check the UserApp folder for all UI pages and src/ for all backend logic.
