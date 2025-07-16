import javax.swing.*;


public class Main {

    //Main method is in the Login class so the program can open the panel
    public static void main(String[] args) {
//Creates the login page
        Login a = new Login();
        a.setContentPane(a.loginPanel);
        a.setTitle("Login");
        a.setSize(300, 210);
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //This is used as a global variable so both the LibraryManager class and the editBookPage have access to this int value
    //Stores the row number of the book selected from the books arraylist in the Book class
    //This is used to fill in information for the selected book in the editBookPage and then change the information for the same row
    public static int selectedRow;
}