import javax.swing.*;
//This class contains helper methods that are used in multiple classes in the program
public class WindowHelper {
    //This method is used for the "Back" buttons that need to show the LoginPanel
    //However, its use isn't only limited to "Back" buttons as seen with the "Enter" button in the CreateNewLogin class
    //Method is static so we don't need to create a WindowHelper object to use it and so we can simply call it in other classes
    public static void backToLogin(JPanel currentPanel){
        //Moves to the top JFrame and makes it an object so we can do things with it like closing the window
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);
        // Closes the current frame
        currentFrame.dispose();
        //Creates a new window with the LoginPanel
        Login currWindow = new Login();
        currWindow.setContentPane(currWindow.loginPanel);
        currWindow.setTitle("Login");
        currWindow.setSize(300, 210);
        currWindow.setVisible(true);
        currWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //This method is used for the "Create New Account" button on the login panel
    //Opens a new window with the createNewLoginPanel
    //Method is static so we don't need to create a WindowHelper object to use it and so we can simply call it in other classes
    public static void openCreateNewLogin(JPanel currentPanel){
        //Moves to the top JFrame and makes it an object so we can do things with it like closing the window
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);
        // Closes the current frame
        currentFrame.dispose();
        //Creates a new window with the createNewLoginPanel
        CreateNewLogin currWindow = new CreateNewLogin();
        currWindow.setContentPane(currWindow.createNewLoginPanel);
        currWindow.setTitle("Create New Login");
        currWindow.setSize(300, 210);
        currWindow.setVisible(true);
        currWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//The JPanel parameter takes the current panel so it can close it and then create a new window with the LibraryManagerPanel
    public static void openLibraryManager(JPanel currentPanel){
        //Moves to the top JFrame and makes it an object so we can do things with it like closing the window
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);
        // Closes the current frame
        currentFrame.dispose();
        //Creates a new window with the LibraryManagerPanel
        LibraryManager currWindow = new LibraryManager();
        currWindow.setContentPane(currWindow.LibraryManagerPanel);
        currWindow.setTitle("Library Manager");
        currWindow.setSize(700, 650);
        currWindow.setVisible(true);
        currWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//Essentially works the same as the openLibraryManager, but creates a addBookPage object to open the addBookPanel
    public static void openAddBookPage(JPanel currentPanel){
        //Moves to the top JFrame and makes it an object so we can do things with it like closing the window
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);
        // Closes the current frame
        currentFrame.dispose();
        //Creates a new window with the addBookPanel
        addBookPage currWindow = new addBookPage();
        currWindow.setContentPane(currWindow.addBookPanel);
        currWindow.setTitle("Add Book");
        currWindow.setSize(350, 300);
        currWindow.setVisible(true);
        currWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Essentially works the same as the addBookPage, but creates a editBookPage object to open the editBookPanel
    public static void openEditBookPage(JPanel currentPanel){
        //Moves to the top JFrame and makes it an object so we can do things with it like closing the window
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);
        // Closes the current frame
        currentFrame.dispose();
        //Creates a new window with the editBookPanel
        editBookPage currWindow = new editBookPage();
        currWindow.setContentPane(currWindow.editBookPanel);
        currWindow.setTitle("Edit Book");
        currWindow.setSize(350, 300);
        currWindow.setVisible(true);
        currWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
