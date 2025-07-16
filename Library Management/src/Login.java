import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame{
    public JPanel loginPanel;
    private JLabel titleUsername;
    private JLabel titlePassword;
    private JPasswordField passwordField;
    private JTextField txtUsername;
    private JButton loginButton;
    private JButton createNewAccountButton;


   //Login button checks if the username and password match with any of the Login objects in the users arraylist
    //If the Login does match then clicking the button takes you to the LibraryManager Page and if false then it pops up a message saying "Try again"
    public Login(){
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //The input Username and Password are given variables to make code easier to read
                String inputUsername = txtUsername.getText();
                //Converts/cast the input char[] to String so the inputPassword can be a parameter for the validatePassword method
                String inputPassword = new String(passwordField.getPassword());
                //The for loop traverses the users arraylist to find User objects that may fit the input for Username and Password
                for (int i=0; i<User.users.size(); i++){
                    //Since the password is hashed, the validatePassword method must be used to check against the inputPassword
                    //The Username isn't hashed in the users arraylist so it doesn't need to be validated
                   if(User.users.get(i).getUsername().equals(inputUsername)  && User.users.get(i).checkPassword(inputPassword)){
                       //Go to WindowHelper.java for more information
                       //Closes the loginPanel and opens a new window with the contents of the LibraryManagerPanel
                       WindowHelper.openLibraryManager(loginPanel);
                       //The return ensures that the other options aren't executed
                       return;
                  }
                   //If the Username matches one in the users arraylist, but the password is wrong then a fitting pop-up is displayed
                   if(User.users.get(i).getUsername().equals(inputUsername)  && !(User.users.get(i).checkPassword(inputPassword))){
                       JOptionPane.showMessageDialog(loginButton, "Incorrect password");
                       //The return ensures that the end statement isn't executed
                       return;
                   }
              }
                //If neither the password or username are correct then this pop-up displays
                JOptionPane.showMessageDialog(loginButton, "Try again");
            }
        });


//Button seen as "Create New Account"
//This will show a new window with the JPanel from the CreateNewLogin class
       createNewAccountButton.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
                WindowHelper.openCreateNewLogin(loginPanel);
            }
        });
    }

}

