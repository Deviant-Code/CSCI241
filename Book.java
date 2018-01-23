public class Book {

  public int isbn;
  //seprate author into three variables
  public String author;
  public String title;
  public String publisher;
  public int year;
  public int edition;

  public Book(int isbn, String author, String title, String publisher, int year, int edition){
    this.isbn = isbn;
    this.author = author;
    this.title = title;
    this.publisher = publisher;
    this.year = year;
    this.edition = edition;

  }

  public Book(){
    this(0, null, null, null, 0, 0);
  }

  public String toString(){
      return String.format("ISBN: %d%nAuthor: %s%nTitle: %s%nPublisher: %s%nYear: %d%nEdition: %d",
                     this.isbn, this.author, this.title, this.publisher, this.year, this.edition);
  }

  public void author(String author){
    this.author = author;
  }

  public void isbn(int isbn){
    this.isbn = isbn;
  }

  public void title(String title){
    this.title = title;
  }

  public void publisher(String publisher){
    this.publisher = publisher;
  }

  public void year(int year){
    this.year = year;
  }

  public void edition(int edition){
    this.edition = edition;
  }





}
