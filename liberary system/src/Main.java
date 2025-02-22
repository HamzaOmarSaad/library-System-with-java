import java.util.ArrayList;
import java.util.Scanner;

class book
{
    private String title;
    private String author;
    private int id;
    private boolean isAvailable;
    private String genre;

    book(String title, String author, int id, boolean isAvailable, String genre) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isAvailable = isAvailable;
        this.genre = genre;

    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;}
    public int getId() {
        return id;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public String getGenre() {
        return genre;
    }
    public void borrow(){
        isAvailable = false;
    }
    public void rturn(){
        isAvailable = true;
    }


}
class liberary
{
    ArrayList<book> books  = new ArrayList<book>();
    ArrayList<customer>customers=new ArrayList<customer>();

    public void add_book(book b)
    {
        books.add(b);
    }
    public void remove_book(int id )
    {
        for(int i = 0; i < books.size(); i++)
        {
            if(books.get(i).getId() == id)
            {
                books.remove(i);
                break;
            }
        }
    }
    public void dispaly_books()
    {

        if(books.isEmpty())
        {
            System.out.println("No books found");
        }
        else {
            for (book book : books) {

                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() +
                        ", ID: " + book.getId() + ", Available: " + book.isAvailable());
            }

        }
    }
    public void add_costumer(customer c)
    {
        customers.add(c);
    }
    public void remove_costomer( int id )
    {
        for (customer customer : customers) {
            if (customer.getId() == id ) {
                customers.remove(customer);
                break;
            }
        }


    }

    public void borrow_book(int id , customer customer)
    {
        for (book book : books) {
            if (book.getId() == id && book.isAvailable()) {
                if (customer.borrowed_books.contains(book)) {
                    System.out.println("Book already borrowed");
                } else if (customer.borrowed_books.size() >= 3) {
                    System.out.println("Too many books you have borrowed");
                }
                else {
                    customer.borrowed_books.add(book);
                    book.borrow(); // Mark the book as borrowed
                    System.out.println(book.getTitle() + " borrowed successfully.");

                }
                return;
            }

        }



    }
    public void return_book(int id, customer customer)
    {
        for (book book : books) {
            if (book.getId() == id&& !book.isAvailable()) {
                if (customer.borrowed_books.contains(book)) {
                    customer.borrowed_books.remove(book);
                    book.rturn();
                    System.out.println( book.getTitle() + " returned successfully.");
                } else {
                    System.out.println(" You haven't borrowed this book.");
                }
            }
        }


    }

    public void suggest_books(customer customer) {
        boolean found = false;
        for (book book : books) {
            if (book.getGenre().equals(customer.getFavGenre())) {
                System.out.println("Suggested Book: " + book.getTitle() + " by " + book.getAuthor());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No suggestions found for your favorite genre.");
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books match your search.");
        }
    }
    public customer getCustomerById(int id) {
        for(customer customer:customers)
        {
            if(customer.getId()== id)
            {
                return customer;

            }
        }
        return null;

    }

}
class customer{
    private String name;
    private int age;
    ArrayList<book> borrowed_books  = new ArrayList<book>(3);
    private String fav_genra;
    private  int id;
    private static int idCounter = 1;


    public customer(String name, int age, String fav_genra) {
        this.name = name;
        this.age = age;
        this.fav_genra = fav_genra;
        this.id = idCounter;
        idCounter++;

    }

    public void print_borrowed() {
        for (book book : borrowed_books) {
            System.out.println(book.getTitle() );

        }
    }
    public String getFavGenre() {

        return fav_genra;
    }

    public int getId() {
        return id;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("welcome to the library");
        liberary liberary = new liberary();
        while (true)
        {
            Scanner sc = new Scanner(System.in);


            System.out.println("what do you want to do?");
            System.out.println("1. Book related operation");
            System.out.println("2. customer related operation");
            System.out.println("3. exit");
            int choice1 = sc.nextInt();

            if (choice1 == 1) {
                System.out.println("1. add Book");
                System.out.println("2. remove Book");
                System.out.println("3. suggest Book");
                System.out.println("4. search Book");
                System.out.println("5. display Book");
                System.out.println("6. exit");
                int choice2 = sc.nextInt();
                sc.nextLine();

                if (choice2 == 1) {
                    System.out.println("Enter book title: ");
                    String title = sc.nextLine() ;
                    System.out.println("Enter author: ");
                    String author = sc.nextLine();
                    System.out.println("Enter genre: ");
                    String genre = sc.nextLine();
                    System.out.println("Enter book ID: ");
                    int id = sc.nextInt();
                    book book = new book(title, author, id, true, genre);
                    liberary.add_book(book);
                    System.out.println("Book added successfully!");

                }
                else if (choice2 == 2) {
                    System.out.print("Enter book ID: ");
                    int id = sc.nextInt();
                    liberary.remove_book(id);

                }
                else if (choice2 == 3) {
                    System.out.print("Enter customer ID: ");
                    int id = sc.nextInt();
                    liberary.suggest_books(liberary.getCustomerById(id));
                }
                else if (choice2 == 4) {
                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();
                    liberary.searchBook(keyword);
                }
                else if (choice2 == 5) {
                    liberary.dispaly_books();
                }
                else if (choice2 == 6) {
                    System.out.println("exiting");
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid choice, please try again.");
                }
            }
            else if (choice1 == 2) {
                System.out.println("1. add customer ");
                System.out.println("2. remove customer");
                System.out.println("3. print customer's borrowed books");
                System.out.println("4. borrow book ");
                System.out.println("5. return book ");
                System.out.println("6. exit");
                int choice2 = sc.nextInt();
                sc.nextLine();

                if (choice2 == 1) {
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter customer age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter customer genre: ");
                    String genre = sc.nextLine();
                    customer customer = new customer(name, age, genre);
                    liberary.add_costumer(customer);


                }
                else if (choice2 == 2) {
                    System.out.print("Enter customer id: ");
                    int id  = sc.nextInt();
                    liberary.remove_costomer(id);

                }
                else if (choice2 == 3) {
                    System.out.print("Enter customer id: ");
                    int id  = sc.nextInt();
                    liberary.getCustomerById(id).print_borrowed();

                }
                else if (choice2 == 4) {
                    System.out.print("Enter customer id who want to borrow: ");
                    int id1  = sc.nextInt();
                    System.out.println("Enter book id : ");
                    int id2  = sc.nextInt();
                    liberary.borrow_book(id2,liberary.getCustomerById(id1));

                }
                else if (choice2 == 5) {
                    System.out.print("Enter customer id who want to return: ");
                    int id1  = sc.nextInt();
                    System.out.println("Enter book id : ");

                    int id2  = sc.nextInt();
                    liberary.return_book(id2,liberary.getCustomerById(id1));

                } else if (choice2 == 6) {
                    System.out.println("exiting");
                    System.exit(0);

                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            }
            else if (choice1 == 3) {
                System.out.println("Exiting...");
                return;
            }
            else {
                System.out.println("Invalid choice, please try again.");
            }


        }


    }
}