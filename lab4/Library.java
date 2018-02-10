/* Jesse Ericksen
   CSCI 241
   Lab #2
   
   This program represents a specification for the Library class which can hold data 
   for a collection of books.
   
   *Requires Book.java  */

public class Library {

   public static final int COLLECTION_SIZE = 100;

   //Tests out methods within Library class
   public static void main(String[] args){
   
         removeBook("32533535325");
         Book Gatsby = new Book("123232412424", "F. Scott Fitzgerald" , "The Great Gatsby",  "Scribners", 1925, 1);
         addBook(Gatsby);s
         System.out.println(Gatsby);
         removeBook("123232412424");
   
   }
   
   private Book[] books;
   private int size;
   private int count;


   //Library Constructor (Currently Default creates array of int size)
   public Library(int size){
	
       Library[] bookCollection = new Library[Collection_Size];
       count = 0;
   
   }
   
   
   //Adds a book to the Library. Returns true if succesfull.
   //If book already exists in Library, returns false.
   public static boolean addBook(Book book){
       System.out.println("addBook method has been called.");
      if(bookCollection.contains(book){
	     return false;
      } else {
          for(int i = 0 ; i < COLLECTION_SIZE; i++){
              if (bookCollection[i] = null){
                  bookCollection[i] = book;
                  count++;
                  return true;
              }
          }
          return false;
      }
   }
   
   //Removes a book from the library. Returns true if succesfull.
   //If book does not exist in Library, returns false. 
   public static boolean removeBook(String isbn){
      
      System.out.println("removeBook method has been called.");
      
      for(int i = 0; i < COLLECTION_SIZE; i++){
         if(bookCollection[i].isbn.equals(isbn)){
	         bookCollection[i] = null;
             count--;
             return true;
         }
      }
          return false;
      }
   }
   
   //Removes all books of a given year from the Library.
   //Returns the # of books removed.
   public static int removeBooksByYear(int year){
       System.out.println("removeByYear method has been called.");
       int removedCount = 0;
       for(int i = 0; i < COLLECTION_SIZE; i++){
         if(bookCollection[i].year() == year){
         	bookCollection[i] = null;
            removedCount++;
         }
      }
      count = count - removedCount;

      return removedCount;
   }
   
   //Returns a book in Library given it's ISBN. 
   
   public static Book findBook(String isbn){
      
      System.out.println("findBook method has been called.");

      for(int i = 0; i < COLLECTION_SIZE; i++){
         if(bookCollection[i].isbn.equals(isbn){
             return bookCollection[i];
         }
      }

      return null;
      
   }
   
   //Finds all books from a given author in Library.
   //Returns as an array of books.
   public static Book[] findBooksByAuthor(String author){
      
      System.out.println("findAuthor method has been called.");
      
      List<Book> bookList = new List<Book>();

      for(int i = 0; i < bookCollection.count(); i++){
         if(bookCollection[i].author.equals(author){
             bookList.add(bookCollection[i];
         }
      }

      return bookList.toArray();
             
   }
   
   //Finds all editions of a book from its ISBN.
   //Returns them as an array of books.
   //If none found return a null value
   public static Book[] findEditions(String isbn){
      
      List<Book> bookList = new List<Book>();
      Boolean bookExist = false;
      String title = "";
      String author = "";
      for(int i = 0; i < bookCollection.count(); i++){
          if(bookCollection[i].isbn.equals(isbn){
              bookExist = true;
              title = bookCollection[i].title;
              author = bookCollection[i].author;
          }

      }

      if(!bookExist){
	       return null;
      } else {

        for(int i = 0; i < COLLECTION_SIZE; i++){
           if(bookCollection[i].title.equals(title) && bookCollection[i].author.equals(author){ 
               bookList.add(bookCollection[i];
           }
        }

        return bookList.toArray();
   }
   
   //Returns the total books kept in a Library.
   public static int count(){  
      return count;
   }
   
   //Compares two books and returns a negative value if
   //first book comes before the second based on ISBN. Positive if after.
   public static int compareISBN(Book book1, Book book2){
     System.out.println("compareBooks method has been called.");
      if(Integer.parseInt(book1.isbn) < Integer.parseInt(book2.isbn)){
         return -1;
      } else if(Integer.parseInt(book1.isbn) = Integer.parseInt(book2.isbn)){
         return 0;
      } else {
      return 1;
      }
   }
   
   //Compares two books by title and returns a negative if first book comes 
   //before te second and positive if else.
   public static int compareTitle(Book book1, Book book2){
      
      System.out.println("compareTitle method has been called";
      
      if(book1.title.equals(book2.title){
         return 0;
      } else if(book1.title.toLowerCase().charAt(0) < book2.title.toLowerCase.charAt(0)){
         return -1;
      } else { 
         return 1;
   }
   
   //Prints string representation of entire Library.
   public String toString(){
      //for(int i = 0; i < bookCollection.count(); i++){
          
     
      return "null";
   }
   
   
}
