# 📚 Library Management System

A secure and interactive desktop application for managing a library’s book catalog and user authentication, built using **Java Swing** with **IntelliJ IDEA's GUI Designer**. It employs object-oriented principles, GUI abstraction, and robust security mechanisms such as **PBKDF2 hashing with salting**.

---

## 🚀 Features

### 🔐 User Authentication & Security
- Secure login system using **PBKDF2 with HMAC SHA-256 and per-user salt**.
- Access-controlled **Admin Account Creation** via a hashed Access Key.
- All passwords and keys are hashed — never stored in plaintext.

### 📚 Book Catalog Management
- Add, edit, delete, check out, and return books.
- Real-time **status indicators** with custom table cell coloring (available/unavailable).
- Catalog dynamically stored in an `ArrayList<Book>`, saved to and loaded from `Library Books.csv`.
- Books are sorted by status, type, author, and title.

### 🖼️ Graphical User Interface (GUI)
- Built with **Java Swing** and designed using **IntelliJ IDEA GUI Designer**.
- `.form` files ensure a clean visual design and automatic GUI code generation.
- Modular window navigation using helper utilities.

---

## 🛠️ Technical Highlights

### 🧩 Object-Oriented Programming
- **Encapsulation**: Each class has clear responsibilities with private fields and public methods.
- **Inheritance**: GUI classes extend `JFrame`, enabling window control and interaction.
- **Polymorphism**: Overridden methods (e.g., `getTableCellRendererComponent`) customize GUI behavior.
- **Abstraction**: Utility classes simplify complex actions like navigation and status rendering.

### 📁 File Structure & Helpers
- `WindowHelper.java`: Static methods abstract away frame switching logic.
- GUI panels are declared `public` to be accessed across navigation methods.
- `Main.java` holds global variables (e.g., `selectedRow`) accessible across classes.

### 📊 Data Management
- Books stored in `ArrayList<Book>`; Users in `ArrayList<User>`.
- Sorting via a **custom compare method** and **bubble sort**.
- Book data saved in `.csv` using `FileWriter`, with proper **delimiter handling** and quoting.

### 🔒 Password & Access Key Hashing
- Utilizes PBKDF2 with SHA-256 and random per-user **salt generation**.
- Passwords are hashed and validated with iterative comparison.
- Access key required for account creation is securely validated.

---

## 🖥️ GUI Windows Overview

| Class                    | Window              | Purpose                             |
|--------------------------|---------------------|-------------------------------------|
| `Login.java`             | Login Window        | Secure user login                   |
| `CreateNewLogin.java`    | Account Creation    | Admin-only user creation            |
| `LibraryManager.java`    | Main Dashboard      | View, edit, and manage book records |
| `addBookPage.java`       | Add Book            | Enter new book data                 |
| `editBookPage.java`      | Edit Book           | Modify selected book                |
| `StatusCellRenderer.java`| -                   | Visual feedback on book availability |
| `WindowHelper.java`      | -                   | Frame navigation across GUI         |

---

## 🧪 Decision-Making Logic

- Buttons implement **error-handling** and **feedback** via popups.
- Input validation ensures unique usernames and correct access credentials.
- Logic flows:
  - **Login**: Validates user credentials and opens Library Manager  
  - **CreateNewLogin**: Verifies uniqueness and hashes input data  
  - **Add/Edit Book**: Dynamically alters the `books` ArrayList and refreshes GUI  
  - **Checkout/Return**: Displays appropriate confirmation or error messages  

---

## 🏗️ How to Run

### 💡 Recommended: IntelliJ IDEA

1. **Open Project**:  
   `File > Open` → Select the `Library Management` folder.
2. **Set SDK**:  
   `File > Project Structure > Project` → Set Java 8+.
3. **Build & Run**:  
   Right-click `Main.java` → `Run 'Main.main()'`.

### 🧪 Running Without IntelliJ

> GUI editing via `.form` files only works in IntelliJ.

Compile and run from terminal:
```bash
javac src/*.java
java -cp src Main

