import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editBookPage extends JFrame{
    private JButton backButton;
    private JButton editButton;
    private JTextField textFieldTitle;
    private JTextField textFieldFirstname;
    private JTextField textFieldLastname;
    private JTextField textFieldType;
    private JTextField textFieldISBN13;
    private JComboBox comboBoxStatus;
    public JPanel editBookPanel;

    public editBookPage() {
        initializeComboBox();
        insertValues();

        //The back button goes back to the LibraryManagerPanel and disposes of the current editBookPanel
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowHelper.openLibraryManager(editBookPanel);
            }
        });
        //Changes the values in the books arraylist to what the user decided to alter and then opens the Library Manager page
        //If nothing was changed, then the same values are simply inputted for the same Book object
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //A temporary book object is created and will be added to the books Arraylist
                //All the information currently in the textFields and comboBox are used to create a book object
                //The user is then took back to the Library Manager Panel
                Book temp = new Book(textFieldType.getText(), textFieldLastname.getText(), textFieldFirstname.getText(), textFieldTitle.getText(),
                textFieldISBN13.getText(), (String) comboBoxStatus.getSelectedItem());
                Book.books.set(Main.selectedRow, temp);
                WindowHelper.openLibraryManager(editBookPanel);
            }
        });
    }

    //Fills all the text boxes in with the information from the selectedRow in the Library Manager's JTable
    //Retrieves the index of the row selected and uses this index to access the Book object in the books arraylist to then fill out the information as needed
    private void insertValues(){
        textFieldTitle.setText(Book.books.get(Main.selectedRow).getBookTitle());
        textFieldFirstname.setText(Book.books.get(Main.selectedRow).getFirstName());
        textFieldLastname.setText(Book.books.get(Main.selectedRow).getLastName());
        textFieldType.setText(Book.books.get(Main.selectedRow).getType());
        textFieldISBN13.setText(Book.books.get(Main.selectedRow).getISBN13());
        //Sets the status of the comboBox as the first option/element ("Available") if the status at the selectedRow is equal to "Available"
        if(Book.books.get(Main.selectedRow).getStatus().equals("Available")) {
            comboBoxStatus.setSelectedIndex(0);
        }
        //The only other status is "Unavailable" which is the second option/element of the comboBox
        else{
            comboBoxStatus.setSelectedIndex(1);
        }
    }
    //Initializes the comboBoxStatus with 2 options for the book object being created - "Available" or "Unavailable"
    private void initializeComboBox(){
        comboBoxStatus.setModel(new DefaultComboBoxModel(new String []{"Available", "Unavailable"}));
    }

}
