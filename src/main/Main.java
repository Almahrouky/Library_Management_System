public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryGUI gui = new LibraryGUI(library);
        gui.setVisible(true);
    }
}