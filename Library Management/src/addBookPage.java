import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addBookPage extends JFrame {
    public JPanel addBookPanel;
    private JButton buttonAddBook;
    private JTextField textFieldLastname;
    private JTextField textFieldFirstname;
    private JTextField textFieldBookTitle;
    private JTextField textFieldISBN13;
    private JButton buttonBack;
    private JComboBox comboBoxStatus;
    private JLabel labelType;
    private JTextField textFieldType;



    //The back button takes you back to the LibraryManagerPanel
    public addBookPage() {
        initializeComboBox();
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Go to WindowHelper.java for more information
                //Closes the current window and opens a new window with the content of the LibraryMangerPanel
                WindowHelper.openLibraryManager(addBookPanel);
            }
        });

//Add Book button which will add the book in the table on the LibraryManager panel and take you to the LibraryManager panel
//Books can be created with missing information if the library doesn't have certain info for any reason
        buttonAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //The local variables are used for the parameters of a Book Object and put into the new Object
                String lastName = textFieldLastname.getText();
                String firstName = textFieldFirstname.getText();
                String bookTitle = textFieldBookTitle.getText();
                String type = textFieldType.getText();
                String ISBN13 = textFieldISBN13.getText();
                //Since combo boxes return objects instead of strings, the .equals comparator is used to check if the choice selected is "Available" or "Unavailable"
                String status = "Available";
                if(comboBoxStatus.getSelectedItem().equals("Unavailable")){
                    status = "Unavailable";
                }
                //New Book object is created then added to the books ArrayList
                Book newBook = new Book(type, lastName, firstName, bookTitle, ISBN13, status);
                Book.books.add(newBook);
                //the LibraryManager panel is opened and addBookPanel is closed
                WindowHelper.openLibraryManager(addBookPanel);
            }
        });
    }


    //Initializes the comboBoxStatus with 2 options for the book object being created - "Available" or "Unavailable"
    private void initializeComboBox(){
        comboBoxStatus.setModel(new DefaultComboBoxModel(new String []{"Available", "Unavailable"}));
    }


}
