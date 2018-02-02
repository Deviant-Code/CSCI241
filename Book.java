/* Jesse Ericksen
   CSCI 241
   Lab #2
   
   This provides the book object, to be used with Library.java to store Books. */

public class Book {

  private String isbn;
  private String author;
  private String title;
  private String publisher;
  private int year;
  private int edition;


  //Constructs a book object with given data.
  public Book(String isbn, String author, String title, String publisher, int year, int edition){
  
    this.isbn = isbn;
    this.author = author;
    this.title = title;
    this.publisher = publisher;
    this.year = year;
    this.edition = edition;

  }

  //Return a string representation for book.
  public String toString(){
      return String.format("ISBN: %s%nAuthor: %s%nTitle: %s%nPublisher: %s%nYear: %d%nEdition: %d",
                     this.isbn, this.author, this.title, this.publisher, this.year, this.edition);
  }



}
