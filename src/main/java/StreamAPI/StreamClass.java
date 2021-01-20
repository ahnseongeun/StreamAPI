package StreamAPI;

public class StreamClass {
    int id;
    String title;
    boolean closed;

    StreamClass(int id,String title,boolean closed){
        this.id = id;
        this.title = title;
        this.closed = closed;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public boolean isClosed(){
        return closed;
    }
}
