public class Book {
    private String title;
    private String author;
    private String ISBN;
    private int availableCount;

    public Book(String title, String autor, String ISBN, int availableCount){
        this.title = title;
        this.author = autor;
        this.ISBN = ISBN;
        this.availableCount = availableCount;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public int getAvailableCount() { return availableCount; }

    public boolean isAvailable(){
        return availableCount > 0;
    }
    public void borrowBook(){
        if(isAvailable())
            availableCount--;
    }

    public void returnBook(){
        availableCount++;
    }

    @Override
    public String toString(){
        return title + " by " + author + " (ISBN: " + ISBN + ") " + "Available Copies: " + availableCount;
    }
}
