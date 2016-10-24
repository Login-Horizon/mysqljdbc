package JDBC;

import Interactive.ContainerValue;
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
                "genre , case   when  userid ="+ ContainerValue.getInstance().id +" then CONCAT_WS(':','You took  the book with number',idbook_list) " +
                "when userid is not null then" +
                " (select CONCAT_WS(' ', name,'took the book with number:',idbook_list)\n" +
                "        from user_list where user_id =userid )\n" +
                "                else CONCAT_WS(' ',' book with the number',idbook_list,'is available ')" +
                " end book_status  from book_list order by 1";

        try {
            System.out.println(query);
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
    public String InsertNewUser(String userName, String userEmail) {
        String insert ="INSERT INTO `my_db`.`user_list` (`user_id`, `name`,  `email`) VALUES (null,'" +
                userName +"','"
                + userEmail+"');";
        System.out.println(insert);
        String id="select user_id from my_db.user_list where email ='"+userEmail+"'";
        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();

            // executing SELECT query
             stmt.executeUpdate(insert);

            rs =stmt.executeQuery(id);
            rs.next();
            ContainerValue.getInstance().id = rs.getString("user_id");
            System.out.println(rs.getString("user_id"));
            //put results into array

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
        return  ContainerValue.getInstance().id;
    }

    @Override
    public String ChangeTheBookOwner(String bookId) {
        String sqlquerry ="select IFNULL(userid,0) BOOK_STAT from my_db.book_list where (userid = "+ContainerValue.getInstance().id +
                " or userid is null) "+ "and idbook_list ="+bookId;
        String setnull ="UPDATE `my_db`.`book_list` SET  `userid` = null  where `idbook_list` ="+bookId;
        String setUser ="UPDATE `my_db`.`book_list` SET  `userid` = "+ContainerValue.getInstance().id+"  where `idbook_list` ="+bookId;

        try {
            // opening database connection to MySQL server
            con = (Connection) DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = (Statement) con.createStatement();

            // executing SELECT query

            rs =stmt.executeQuery(sqlquerry);
            if(rs.next()){

                System.out.println(rs.getString("BOOK_STAT").equals("0"));
                if (rs.getString("BOOK_STAT").equals("0")){
                    stmt.executeUpdate(setUser);
                    return "book taken";
                }
                else { stmt.executeUpdate(setnull);
                return "book returned to the Library ";}
            }
            else
                return  "book does not available ";
            //put results into array

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
        return null;
    }

    @Override
    public String[][] BookByKey(String key) {
        String[][] book = new String[100][4];
        String query ="select  name book_title, "+
        "author author, "+
                "genre genre , "+
       " case when  userid is not null and userid ="+ContainerValue.getInstance().id+" then CONCAT_WS(':','You took  the book with number',idbook_list) "+
               " when userid is not null then (select CONCAT_WS(' ', name,'took the book with number:',idbook_list) "+
                "from user_list where user_id =userid ) "+
       " else CONCAT_WS(' ','book with the number ',idbook_list,'is available ') "+
       " end book_status "+
       "from book_list where CONCAT_WS('',name,author,genre) like '%"+key+"%'";
        try {
            System.out.println(query);
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
