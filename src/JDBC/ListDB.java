package JDBC;

/**
 * Created by Ruslanq on 23.10.2016.
 */
public class ListDB {

    private String name;
    private String author;
    private String genre;
    private String userid;

    public ListDB( String name, String author, String genre, String userid) {

        this.name = name;
        this.author = author;
        this.genre = genre;
        this.userid = userid;

    }

    public ListDB() {
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
