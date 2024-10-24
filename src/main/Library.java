import java.util.ArrayList;

public class Library {
    ArrayList<Book> books;
    ArrayList<Member> members;

    public Library(){
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }
    
    public void addMember(Member member){
        members.add(member);
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Member> getMembers(){
        return members;
    }
}
