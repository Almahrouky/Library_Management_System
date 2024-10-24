public class Member {
    private String name;
    private String Id;
    
    public Member(String name, String Id){
        this.name = name;
        this.Id = Id;
    }

    public String getName() { return name; }
    public String getId() { return Id; }

    @Override
    public String toString(){
        return "Name is: " + name + " Id is: " + Id;
    } 
}