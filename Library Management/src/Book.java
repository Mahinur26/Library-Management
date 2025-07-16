import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Book {

    //Variables for the Book object
    private String type;
    private String lastName;
    private String firstName;
    private String bookTitle;
    private String ISBN13;
    private String status;

    //Each book object will store basic information about the book like what type of book it is(manga, nonfiction, fiction, etc.)
    //This information will be used to sort the books in order so it's easy to navigate the library manager
    //Status means if the book is available (true = available)
    public Book(String type, String lastName, String firstName, String bookTitle, String ISBN13, String status) {
        this.type = type;
        this.lastName = lastName;
        this.firstName = firstName;
        this.bookTitle = bookTitle;
        this.ISBN13 = ISBN13;
        this.status = status;
    }


    //Getters - return the corresponding instance variable
    public String getType() {
        return type;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public String getStatus() {
        return status;
    }

    //Setters, to change the value of each of the instance variables that make up a Book object
    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    //Arraylist containing all the books the library currently catalogs
    //The books that have a false status have been checked out while the true ones are available
    public static ArrayList<Book> books = new ArrayList<Book>() {{
        add(new Book("Fiction", "Morrison", "Toni", "Beloved", "9781400033416", "Available"));
        add(new Book("Fiction", "Saadawi", "Nawal El", "Woman at Point Zero", "9781848133151", "Unavailable"));
        add(new Book("Drama", "Shakespeare", "William", "King Lear", "9780743482769", "Available"));
        add(new Book("Fiction", "Calvino", "Italo", "The Nonexistent Knight", "9780156659758", "Available"));
        add(new Book("Fiction", "Rowling", "J.K.", "Harry Potter and the Sorcerer's Stone", "9780590353427", "Available"));
        add(new Book("Fiction", "Rowling", "J.K.", "Harry Potter and the Chamber of Secrets", "9780439064873", "Available"));
        add(new Book("Fiction", "Rowling", "J.K.", "Harry Potter and the Prisoner of Azkaban", "9780439136365", "Available"));
        add(new Book("Fiction", "Rowling", "J.K.", "Harry Potter and the Goblet of Fire", "9780439139601", "Available"));
        add(new Book("Fiction", "Rowling", "J.K.", "Harry Potter and the Order of the Phoenix", "9780439358071", "Available"));
        add(new Book("Fiction", "Rowling", "J.K.", "Harry Potter and the Half-Blood Prince", "9780439785969", "Unavailable"));
        add(new Book("Manga", "Kubo", "Tite", "Bleach, Vol. 1", "9781591164418", "Available"));
        add(new Book("Manga", "Kubo", "Tite", "Bleach, Vol. 2", "9781591164456", "Available"));
        add(new Book("Manga", "Kubo", "Tite", "Bleach, Vol. 3", "9781591164470", "Available"));
        add(new Book("Manga", "Kubo", "Tite", "Bleach, Vol. 4", "9781591164449", "Available"));
        add(new Book("Manga", "Kubo", "Tite", "Bleach, Vol. 5", "9781591164432", "Unavailable"));
        add(new Book("Manga", "Toriyama", "Akira", "Dragon Ball, Vol. 1", "9781569319208", "Available"));
        add(new Book("Manga", "Toriyama", "Akira", "Dragon Ball, Vol. 2", "9781569319215", "Available"));
        add(new Book("Manga", "Toriyama", "Akira", "Dragon Ball, Vol. 3", "9781569319222", "Available"));
        add(new Book("Fiction", "Fitzgerald", "F. Scott", "The Great Gatsby", "9780743273565", "Available"));
        add(new Book("Fiction", "Orwell", "George", "Animal Farm", "9780451526342", "Available"));
        add(new Book("Fiction", "Baum", "L. Frank", "The Wizard of Oz", "9780141321028", "Available"));
        add(new Book("Fiction", "Riordan", "Rick", "The Lightning Thief", "9780786838653", "Unavailable"));
        add(new Book("Fiction", "Riordan", "Rick", "The Sea of Monsters", "9780786856862", "Available"));
        add(new Book("Fiction", "Riordan", "Rick", "The Titan's Curse", "9781423101451", "Available"));
        add(new Book("Fiction", "Riordan", "Rick", "The Battle of the Labyrinth", "9781423101468", "Available"));
        add(new Book("Fiction", "Riordan", "Rick", "The Last Olympian", "9781423101475", "Available"));
        add(new Book("Epic", "Homer", "", "The Odyssey", "9780140268867", "Available"));
        add(new Book("Nonfiction", "Harari", "Yuval Noah", "Sapiens: A Brief History of Humankind", "9780062316110", "Unavailable"));
        add(new Book("Nonfiction", "Hawking", "Stephen", "A Brief History of Time", "9780553380163", "Available"));
        add(new Book("Nonfiction", "Obama", "Michelle", "Becoming", "9781524763138", "Available"));
        add(new Book("Nonfiction", "Gawande", "Atul", "Being Mortal: Medicine and What Matters in the End", "9780805095159", "Available"));
        add(new Book("Nonfiction", "Kahneman", "Daniel", "Thinking, Fast and Slow", "9780374533557", "Available"));
        add(new Book("Nonfiction", "Hill", "Napoleon", "Think and Grow Rich", "9781585424337", "Available"));
        add(new Book("Drama", "Wilson", "August", "Fences", "9780452275152", "Unavailable"));
    }};


    //Overriding the compare method in the Comparator class to compare the Book objects in a desired manner
    /*Sorts the ArrayList so the Book objects are put in alphabetical order in the following prioritization of instance variables:
    /* status --> type --> lastName --> firstName --> title
//For the compareToIgnoreCase method, if 0 is returned then that means the values are the same so the next order of organization must be completed
/*If the values aren't the same, then a 1 or -1 is returned which indicates where to place each Book object in the ArrayList
/* compareToIgnoreCase is use instead of compareTo because capitalization isn't key to ordering the books
*/
        public static int compareBook(Book book1, Book book2) {
            // First compare by status
            int statusComparison = book1.getStatus().compareToIgnoreCase(book2.getStatus());
            if (statusComparison != 0) {
                return statusComparison;
            }
            // If status is the same, compare by type
            int typeComparison = book1.getType().compareToIgnoreCase(book2.getType());
            if (typeComparison != 0) {
                return typeComparison;
            }
            // If type is the same, compare by lastName
            int lastNameComparison = book1.getLastName().compareToIgnoreCase(book2.getLastName());
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }
            // If lastName is the same, compare by firstName
            int firstNameComparison = book1.getFirstName().compareToIgnoreCase(book2.getFirstName());
            if (firstNameComparison != 0) {
                return firstNameComparison;
            }
            // If firstName is the same, compare by bookTitle
            return book1.getBookTitle().compareToIgnoreCase(book2.getBookTitle());
        }

    // Sorts the books ArrayList using the compareBook method defined just above
    //Sorts the ArrayList so the Book objects are put in alphabetical order in the following prioritization of instance variables:
    /* status --> type --> lastName --> firstName --> title
     */
    //Uses a bubble sort to sort the Book objects in order
    //This is used as a helper method in the createTable method seen in the LibraryManager so everytime the table is created in the GUI, it's also organized
    public static void organizeBooksList() {
        for (int i = 0; i< books.size(); i++){
            //swapped is reset at the start of each pass
            boolean swapped = false;
            for (int j = 0; j< books.size() - i - 1; j++){
            //Swaps the book objects if they aren't in alphabetical order
            if(compareBook(books.get(j), books.get(j+1)) > 0){
                Collections.swap(books, j, j + 1);
                swapped = true;
            }
              }
            //If the values were not swapped then that means the arraylist is ordered and doesn't need to go through the rest of the algorithm
            //The break ends the method early
            if(!swapped){
            break;
            }
        }
    }

}