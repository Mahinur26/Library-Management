import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CreateNewLogin extends JFrame {
    //The JPanel is public so it can be accessed in the WindowHelper class
    public JPanel createNewLoginPanel;
    private JButton buttonBack;
    private JLabel accessKeyLabel;
    private JTextField textFieldAccessKey;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;
    private JButton buttonEnter;

    //Only admin will know this variable and it will serve as a way for the library to make new accounts for new employees
    //Stored a hashed version of the access key for security
    //Store the hashed access key and the salt
    /*
    The salt is used to ensure that the same password isn't always the same due to the randomly generated salt being added on to the encrypted password
    Each time the program runs, the salt ensures that the ACCESS_KEY appears to be different
     */
    private static final byte[] SALT = User.generateRandomSalt();
    //The first parameter in the plain text of the ACCESS_KEY
    private static final String ACCESS_KEY = User.hashPassword("Password123", SALT);

    //Creates a new panel for the create login page which will ask for an access key, username, and password
    //The username and password will later be stored as a new object in the users arraylist seen in the User class
    public CreateNewLogin(){
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //See use of the backToLogin method in the WindowHelper class
                WindowHelper.backToLogin(createNewLoginPanel);
            }
        });

//The enter button is used to process the information written in the textboxes in the createNewLoginPanel
//If the access key is correct, the username is unique, and a password is provided, then a new User object is added to the users arraylist
        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Used to check if the Username exists already
                boolean uniqueUsername = true;
                //Linearly searches through the whole users arraylist to ensure that none of the Login object have the same Username as the Username inputted into the text field
                for(int i = 0; i<User.users.size(); i++){
                    if(textFieldUsername.getText().equals(User.users.get(i).getUsername())) {
                        uniqueUsername = false;
                        //Ends the loop early once an instance of the inputted username is found
                        break;
                    }
                }
                //Gets the users input for the access key so it can be used to determine what to output
                //Also adds a layer of abstraction so the code is easier to read
                String inputAccessKey = textFieldAccessKey.getText();

                //If no information is fill out, then a pop-up message is shown saying "Nothing was Inputted"
                if (textFieldAccessKey.getText().isEmpty() && textFieldUsername.getText().isEmpty() && textFieldPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(buttonEnter, "Nothing was Inputted");
                }
                //If the Access Key is incorrect then pop-up is displayed saying "Incorrect Access Key"
                else if (!checkAccessKey(inputAccessKey)) {
                    JOptionPane.showMessageDialog(buttonEnter, "Incorrect Access Key");
                }
                //If the Access Key is correct, but information is missing from either the Username or Password text field, then, a pop-up message is shown saying "Information is Missing"
                else if (checkAccessKey(inputAccessKey) && (textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(buttonEnter, "Information is Missing");
                }
                //If the Username isn't unique but the rest of the information is correct then a message is prompted telling the user to change the Username
                else if(!uniqueUsername){
                    JOptionPane.showMessageDialog(buttonEnter, "This Username is Already Taken");
                }
                //By the order of if-statements, the Access Key is correct, the Username and Password textboxes are not empty, and the Username doesn't match an existing Username
                //Thus a new User object is added to the users arraylist
                else {
                    //Creates a new User object to add to the users arraylist in the User class
                    User newEmployee = new User(textFieldUsername.getText(), textFieldPassword.getText());
                    User.users.add(newEmployee);
                    //Takes you back to the LoginPanel so you can login with your new Username and Password
                    WindowHelper.backToLogin(createNewLoginPanel);
                }
            }
        });
    }
    //Method to check the entered access key against the stored hash and salt (in this class)
    //inputKey is what the user inputs for the ACCESS_KEY
    //The same salt variable (SALT) is used to ensure that if the hashedInputKey is correct, then it will match the hashed ACCESS_KEY
    //Returns true if the inputKey is correct, otherwise the inputKey is wrong and returns false
    private boolean checkAccessKey(String inputKey) {
        String hashedInputKey = User.hashPassword(inputKey, SALT);
        return hashedInputKey.equals(ACCESS_KEY);
    }
}