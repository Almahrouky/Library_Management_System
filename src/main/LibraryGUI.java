import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LibraryGUI extends JFrame{
    private Library library;
    private JTextField titleField, authorField, ISBNField, copiesCountField, nameField, IdField;
    private JTextField bookField, memberField;
    private JTextArea booksTextArea, membersTextArea, transationsTextArea;
    private DefaultListModel<String> bookListModel, memberListModel;

    public LibraryGUI(Library library){
        this.library = library;

        setTitle("Library_Management_System");
        setSize(1020, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Book Button
        JButton bookButton = new JButton("Add book");
        bookButton.setBackground(new Color(0, 255,255));
        add(bookButton);
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                bookWindow();
            }
        });

        // Member Button
        JButton memberButton = new JButton("Add member");
        memberButton.setBackground(new Color(0, 255,255));
        add(memberButton);
        memberButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                memberWindow();
            }
        });

        // Transaction Button
        JButton borrowButton = new JButton("Borrow/Return book");
        borrowButton.setBackground(new Color(0, 255,255));
        add(borrowButton);
        borrowButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                transactionWindow();
            }
        });


        // First row
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label1 = new JLabel("Available Books: ");
        label1.setFont(new Font("Arial", Font.BOLD, 15));
        label1.setForeground(Color.BLUE);
        panel1.add(label1);
        booksTextArea = new JTextArea(10, 35);
        panel1.add(new JScrollPane(booksTextArea));

        JLabel label2 = new JLabel("Current Members: ");
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        label2.setForeground(Color.BLUE);
        panel1.add(label2);
        membersTextArea = new JTextArea(10, 35);
        panel1.add(new JScrollPane(membersTextArea));
        add(panel1);

        // Second row
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label3 = new JLabel("Executed Transactions: ");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setForeground(Color.BLUE);
        panel2.add(label3);
        transationsTextArea = new JTextArea(10, 41);
        panel2.add(new JScrollPane(transationsTextArea));

        add(panel2);
    }

    private void bookWindow(){
        JFrame bookFrame = new JFrame();
        bookFrame.setTitle("Add Book");
        bookFrame.setSize(400, 200);
        bookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookFrame.setLayout(new GridLayout(5, 2));

        bookFrame.add(new JLabel("Book Title: "));
        titleField = new JTextField(10);
        bookFrame.add(titleField);

        bookFrame.add(new JLabel("Book Autor: "));
        authorField = new JTextField(10);
        bookFrame.add(authorField);

        bookFrame.add(new JLabel("Book ISBN: "));
        ISBNField = new JTextField(10);
        bookFrame.add(ISBNField);

        bookFrame.add(new JLabel("Number of copies of the book: "));
        copiesCountField = new JTextField(10);
        bookFrame.add(copiesCountField);

        JButton bookButton = new JButton("Add Book");
        bookButton.setBackground(new Color(0, 255,255));
        bookFrame.add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    String title = titleField.getText();
                    String author = authorField.getText();
                    String ISBN = ISBNField.getText();
                    int copies = Integer.parseInt(copiesCountField.getText());
                    
                    Book book = new Book(title, author, ISBN, copies);
                    library.addBook(book);
                    updateBookTextArea();
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Not a valid number of copies.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bookFrame.setVisible(true);
    }

    private void memberWindow(){
        
        JFrame memberFrame = new JFrame();
        memberFrame.setTitle("Add Member");
        memberFrame.setSize(400, 180);
        memberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberFrame.setLayout(new GridLayout(4, 2));

        memberFrame.add(new JLabel("Member Name: "));
        nameField = new JTextField(10);
        memberFrame.add(nameField);

        memberFrame.add(new JLabel("Member Id: "));
        IdField = new JTextField(10);
        memberFrame.add(IdField);
        
        JButton memberButton = new JButton("Add Member");
        memberButton.setBackground(new Color(0, 255,255));
        memberFrame.add(memberButton);

        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = nameField.getText();
                String Id = IdField.getText();
        
                Member member = new Member(name, Id);
                library.addMember(member);
                updateMemberTextArea();
            }
        });
        
        memberFrame.setVisible(true);
    }
    
    private void transactionWindow(){
        JFrame transactionFrame = new JFrame();
        transactionFrame.setTitle("Borrow a book");
        transactionFrame.setSize(550, 200);
        transactionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactionFrame.setLayout(new GridLayout(3, 3));

        // Search for a member
        transactionFrame.add(new JLabel("Member name: "));
        memberField = new JTextField(10);
        transactionFrame.add(memberField);

        memberListModel = new DefaultListModel<>();
        JList<String> memberList = new JList<>(memberListModel);
        transactionFrame.add(new JScrollPane(memberList));

        // Fill memberListModel
        ArrayList<Member> members = library.getMembers();
        int n = library.getMembers().size();
        for(int i = 0; i < n; ++i){
            String name = members.get(i).getName();
            memberListModel.addElement(name);
        }

        memberField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                updateMemberList();
            }

            @Override
            public void removeUpdate(DocumentEvent e){
                updateMemberList();
            }

            @Override
            public void changedUpdate(DocumentEvent e){
                updateMemberList();
            }
        });

        // Search for a book
        transactionFrame.add(new JLabel("Book ISBN: "));
        bookField = new JTextField(10);
        transactionFrame.add(bookField);

        bookListModel = new DefaultListModel<>();
        JList<String> bookList = new JList<>(bookListModel);
        transactionFrame.add(new JScrollPane(bookList));

        // Fill bookListModel
        ArrayList<Book> books = library.getBooks();
        n = library.getBooks().size();
        for(int i = 0; i < n; ++i){
            String name = books.get(i).getISBN();
            bookListModel.addElement(name);
        }

        bookField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                updateBookList();
            }

            @Override
            public void removeUpdate(DocumentEvent e){
                updateBookList();
            }

            @Override
            public void changedUpdate(DocumentEvent e){
                updateBookList();
            }
        });

        // Borrow Button
        JButton borrowButton = new JButton("Borrow");
        borrowButton.setBackground(new Color(0, 255,255));
        transactionFrame.add(borrowButton);

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateBorrow();
            }
        });

        // Return Button
        JButton returnButton = new JButton("Return");
        returnButton.setBackground(new Color(0, 255,255));
        transactionFrame.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateReturn();
            }
        });

        transactionFrame.setVisible(true);
    }

    private void updateBookTextArea(){
        booksTextArea.setText("");
        for(Book book:library.getBooks()){
            if(book.getAvailableCount() > 0){
                booksTextArea.append(book.toString() + '\n');
            }
        }
    }

    private void updateMemberTextArea(){
        membersTextArea.setText("");
        for(Member member:library.getMembers()){
            membersTextArea.append(member.toString() + '\n');
        }
    }

    private void updateBookList(){
        String part = bookField.getText();
        bookListModel.clear();

        ArrayList<Book> books = library.getBooks();
        int n = library.getBooks().size();
        for(int i = 0; i < n; ++i){
            String name = books.get(i).getISBN();
            if(name.startsWith(part)){
                bookListModel.addElement(name);
            }
        }
    }
    
    private void updateMemberList(){
        String part = memberField.getText();
        memberListModel.clear();

        ArrayList<Member> members = library.getMembers();
        int n = library.getMembers().size();
        for(int i = 0; i < n; ++i){
            String name = members.get(i).getName();
            if(name.startsWith(part)){
                memberListModel.addElement(name);
            }
        }
    }

    private void updateBorrow(){
        String memberName = memberField.getText();
        String bookISBN = bookField.getText();
        Date borrowDate = new Date();
        for(Book book:library.getBooks()){
            if(book.getISBN().equals(bookISBN)){
                transationsTextArea.append(memberName + " borrowed " + book.getTitle() + " book by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ") at " + borrowDate + '\n');
                book.borrowBook();
                updateBookTextArea();
            }
        }
    }   

    private void updateReturn(){
        String memberName = memberField.getText();
        String bookISBN = bookField.getText();
        Date returnDate = new Date();
        for(Book book:library.getBooks()){
            if(book.getISBN().equals(bookISBN)){
                transationsTextArea.append(memberName + " returned " + book.getTitle() + " book by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ") at " + returnDate + '\n');
                book.returnBook();
                updateBookTextArea();
            }
        }
    } 
}