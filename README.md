# Cash Management App (Manajemen Kas)

A Java-based desktop application for cash management (income and expenses). This application is designed to help users record and monitor cash flow easily through a modern and user-friendly interface.

## Key Features

- **User Management**: User login and authentication.
- **Dashboard**: Cash flow summary view.
- **Cash Recording**: Input data for income and expenses.
- **Reports**: Generate cash reports (PDF).
- **Modern Interface**: Uses FlatLaf for a clean and responsive look.

## Tech Stack

This project is built using the following technologies:

- **Programming Language**: Java
- **GUI Framework**: Java Swing (NetBeans GUI Builder)
- **UI Library**: [FlatLaf](https://www.formdev.com/flatlaf/) (FlatLightLaf)
- **Database**: MySQL
- **Database Driver**: MySQL Connector/J
- **Reporting**: iText (for PDF generation)
- **Additional Components**: JCalendar (Date Picker), Bouncy Castle (Security)

## Prerequisites

Before running the application, ensure you have:

1.  **Java Development Kit (JDK)**: Version 8 or newer.
2.  **MySQL Server**: Make sure the MySQL service is running.
3.  **IDE**: NetBeans IDE (Recommended) or IntelliJ IDEA.

## How to Run

1.  **Clone Project** or download the source code.
2.  **Database Configuration**:
    - Create a new database in MySQL named: `db_manajemen-kas`
    - Database connection configuration is located in `src/manajemen/kas/services/DBConnection.java`. Default settings:
        - URL: `jdbc:mysql://localhost:3306/db_manajemen-kas`
        - Username: `root`
        - Password: `""` (empty)
    - Adjust the `username` and `password` if your MySQL configuration differs.
    - *Note: Ensure the necessary tables (User, Pemasukan, Pengeluaran) are created in the database.*

3.  **Open Project**:
    - Open the project using NetBeans or your preferred IDE.
    - Ensure libraries in the `lib` folder are correctly loaded in the project classpath.

4.  **Run Application**:
    - Locate the file `src/manajemen/kas/LoginForm.java`.
    - Right-click and select **Run File** (Shift+F6 in NetBeans).

## Project Structure

- `src/manajemen/kas`: Main application package.
    - `dao`: Data Access Object for database interaction.
    - `model`: Data object representations (User, etc.).
    - `services`: Business logic and helpers (DBConnection, SessionManager, ReportService).
    - `assets`: Images and static resources.
- `lib`: External libraries folder.
