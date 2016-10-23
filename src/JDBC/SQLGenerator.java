package JDBC;

import PojectConfiguration.ConfigReader;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ruslanq on 20.10.2016.
 */
public class SQLGenerator implements LibBD {

    private static final String url = ConfigReader.readConfig("url");
    private static final String user = ConfigReader.readConfig("USERNAME");
    private static final String password = ConfigReader.readConfig("PASSWORD");
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static int i = 0;


    @Override
    public String[][] AllBooks() {

        String[][] book = new String[100][4];
        String query = "select  name book_title," +
                " author," +
                "genre , case when userid is not null then" +
                " (select CONCAT_WS(' ', name,'took the book')\n" +
                "        from user_list where user_id =userid )\n" +
                "                else CONCAT_WS(' ',' book with the number',idbook_list,'is available ')" +
                " end book_status  from book_list order by 1";

        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            //put results into array
            while (rs.next()) {
                book[i][0] = rs.getString("book_title");
                book[i][1] = rs.getString("author");
                book[i][2] = rs.getString("genre");
                book[i][3] = rs.getString("book_status");
                i++;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return book;
    }

    @Override
    public String UserInDb(String userId, String userEmail) {
       String query= "select user_id,name from my_db.user_list  where user_id ="+userId+" and email = '"+userEmail+"'";
        String username=null;
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            //put results into array
            if(rs.next()) {
              username=  rs.getString("name")+":"+rs.getString("user_id")+":";
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return username;
    }

    @Override
    public int InsertNewUser(String userName, String userEmail) {
        return 0;
    }

    @Override
    public void ChangeTheBookOwner(int userId, int bookId) {

    }

    @Override
    public String[][] BookByKey(String key) {
        String[][] book = new String[100][4];
        String query ="select  CONCAT_WS(':','book_title',name) book_title, "+
        "CONCAT_WS(':','author',author) author, "+
                "CONCAT_WS(':','genre',genre)genre , "+
       "case when userid is not null then (select CONCAT_WS(' ','book_status:', name,'took the book') "+
                "from user_list where user_id =userid ) "+
       " else CONCAT_WS(' ','book_status: book with the number',idbook_list,'is available ') "+
       " end book_status "+
       "from book_list where CONCAT_WS('',name,author,genre) like '%"+key+"%'";
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            //put results into array
            while (rs.next()) {
                book[i][0] = rs.getString("book_title");
                book[i][1] = rs.getString("author");
                book[i][2] = rs.getString("genre");
                book[i][3] = rs.getString("book_status");
                i++;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return book;
    }
}
