import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class LibraryManager extends JFrame{

    public JPanel LibraryManagerPanel;
    private JLabel libraryManagerLabel;
    private JButton buttonCheckout;
    private JButton addNewBookButton;
    private JTable tableBooks;
    private JButton deleteButton;
    private JButton buttonEdit;
    private JButton buttonSaveFile;
    private JButton returnButton;
    private JButton buttonBack;


    public LibraryManager() {
        //Organizes the books arraylist before the table is created
        //Makes the table on the JPanel
        createTable();
        //Makes the ComboBox for the different book Types in order
        addNewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Go to WindowHelper.java for more information
                //Closes the current LibraryManagerPanel and opens a addBookPanel
                WindowHelper.openAddBookPage(LibraryManagerPanel);
            }
        });
        //When a row is selected the delete button will delete the Book object at that row
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //The if-statement checks if a row is selected, if not then an error message is presented
                if(tableBooks.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(deleteButton, "Select a row to delete.");
                }
                else{
                    //The row index is retrieved and used to remove the Book object from the chose row
                    Book.books.remove(tableBooks.getSelectedRow());
                    //Updates the gui model of the table
                    createTable();
                }
            }

        });
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //The if-statement checks if a row is selected, if not then an error message is presented
                if(tableBooks.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(buttonEdit, "Select a row to edit.");
                }
                else{
                    //Saves the index of the selected row
                    Main.selectedRow = tableBooks.getSelectedRow();
                    //Disposes the current LibraryManagerPanel and opens a editBookPanel
                    WindowHelper.openEditBookPage(LibraryManagerPanel);
                }
            }
        });

        //Saves the arraylist as a csv file
        buttonSaveFile.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                try {
                    String csvFile = "Library Books.csv";
                    FileWriter out = new FileWriter(csvFile);
                    out.write("Type,Last Name,First Name,Book Title,ISBN13,Status\n"); // Proper newline escape sequence
                    for (Book book : Book.books) {
                        out.write(
                                quoteCSV(book.getType()) + "," +
                                        quoteCSV(book.getLastName()) + "," +
                                        quoteCSV(book.getFirstName()) + "," +
                                        quoteCSV(book.getBookTitle()) + "," +
                                        quoteCSV(book.getISBN13()) + "," +
                                        quoteCSV(book.getStatus()) + "\n"
                        );
                    }
                    out.close();
                    JOptionPane.showMessageDialog(buttonSaveFile, "Saved");
                } catch (IOException ex) {
                    ex.printStackTrace(); // Debugging output for exceptions
                }

            }
        });
        //Pre-condition: A row must be selected and the book in that row must have a status of "Available" or an according message will show
        //Changes the status of the book selected from "Available" --> "Unavailable"
        buttonCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If a row isn't selected, a message is shown and the method ends early
                if(tableBooks.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(buttonCheckout, "Select a Book to Checkout");
                    //exits early for better efficiency
                    return;
                }
                //The Book object from the selected row is retrieved and the status is saved separately
                Book selectedBook = Book.books.get(tableBooks.getSelectedRow());
                String status = selectedBook.getStatus();
                //If the status of the selected book is "Available" then the status is changed and a message is shown indicating the book was checked out
                if(status.equals("Available") ){
                    selectedBook.setStatus("Unavailable"); // Update the status
                    createTable();
                    JOptionPane.showMessageDialog(buttonCheckout, "Book checked out successfully!");
                }
                //If book is "Unavailable" then a message is returned stating that the book can't be checked out
                else{
                    JOptionPane.showMessageDialog(buttonCheckout, "Book is Unavailable");
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If a row isn't selected, a message is shown and the method ends early
                if(tableBooks.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(returnButton, "Select a Book to Return");
                    //exits early for better efficiency
                    return;
                }
                //The Book object from the selected row is retrieved and the status is saved separately
                Book selectedBook = Book.books.get(tableBooks.getSelectedRow());
                String status = selectedBook.getStatus();
                //If the status of the selected book is "Unavailable" then the status is changed and a message is shown indicating the book was returned
                if(status.equals("Unavailable") ){
                    selectedBook.setStatus("Available"); // Update the status
                    createTable();
                    JOptionPane.showMessageDialog(returnButton, "Book returned successfully!");
                }
                //If book is "Available" then a message is returned stating that the book can't be returned
                else{
                    JOptionPane.showMessageDialog(returnButton, "This book is already available in the catalog");
                }

            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowHelper.backToLogin(LibraryManagerPanel);
            }
        });
    }


    private void createTable(){
        //The books are first organized before the put into the table in order
        //Look in the Book class for more information on how they are organized
        Book.organizeBooksList();
        //The data is created as an Object so it can take in any data type
        //The 2d object array is initialized with enough rows to fit all the Book object in the books arraylist seen in the Book class and there 6 columns for each header
        Object[][] booksData = new Object[Book.books.size()][6];
        for(int i=0; i<Book.books.size(); i++){
            //Creates a temporary book object with the information of the book object at that index
            Book temp = Book.books.get(i);
            //Each column of the row at i is filled with the proper data for each Book object
            booksData[i][0] = temp.getType();
            booksData[i][1] = temp.getLastName();
            booksData[i][2] = temp.getFirstName();
            booksData[i][3] = temp.getBookTitle();
            booksData[i][4] = temp.getISBN13();
            booksData[i][5] = temp.getStatus();
        }
        tableBooks.setModel(new DefaultTableModel(
                //The data the table shows is the books 2d array
                booksData,
                //A 2d String array that has the name of the columns for the table
                new String []{"Type", "Last Name", "First Name", "Title", "ISBN13", "Status"}
        ));

        //Sets the default width of the columns to the desired value
        //tableBooks is cast from a JTable to the data type of DefaultTableColumnModel
        DefaultTableColumnModel columns = (DefaultTableColumnModel) tableBooks.getColumnModel();
        columns.getColumn(0).setMaxWidth(65);
        columns.getColumn(1).setMinWidth(70);
        columns.getColumn(2).setMinWidth(70);
        columns.getColumn(3).setMinWidth(150);
        // Set a custom cell renderer so the "Status" column (index 5) has colored background colors depending on the status
        tableBooks.getColumnModel().getColumn(5).setCellRenderer(new StatusCellRenderer());
    }


    //Helper method to escape special characters in CSV
    //Ensures data from the table in the program matches the data in the csv file without any messed up formatting due to the use of special characters in csv files
    private String quoteCSV(String value) {
        //Makes null values in the table as empty values in the csv
        if (value == null) {
            return "";
        }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\""); // Escape double quotes by doubling them
            return "\"" + value + "\""; // Wrap field in quotes
        }
        //Returns the same value if there are no special characters or if it isn't null
        return value;
    }

}
