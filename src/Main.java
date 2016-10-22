import Interactive.EnterThLib;
import JDBC.SQLGenerator;
import PojectConfiguration.ConfigReader;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;
import com.sun.xml.internal.fastinfoset.sax.Properties;

import java.io.IOException;
import java.sql.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Ruslanq on 20.10.2016.
 */
class Main {


    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        System.out.print("hello java");
        SQLGenerator sqlGenerator = new SQLGenerator();
        boolean empty = true;
        String[][] booksList;
        System.out.println(sqlGenerator.UserInDb("2","Username@email.com"));
      System.out.println(  new EnterThLib() {
            @Override
            public int TookBook(int userId, int bookId) {
                return 0;
            }

            @Override
            public int SearchBook(String keyword) {
                return 0;
            }

            @Override
            public int Return(int userId, int bookId, String all, Map myBookList) {
                return 0;
            }
        }.LogOn("Username@email.com","2"));
//
//        for (String[] item : booksList) {
//            if (item[3] == null)
//                break;
//            System.out.println(Arrays.deepToString(item));
//        }
//        String[] cls = new String[]{"cmd.exe", "/c", "cls"};
//        Runtime.getRuntime().exec(cls);
//        System.out.println("new life");
//        booksList = sqlGenerator.BookByKey("kun");
//        if (booksList.length == 0)
//            System.out.println("foun nu");
//
//        for (String[] item : booksList) {
//            if (item[3] == null)
//                continue;
//            else
//                empty = false;
//            System.out.println(Arrays.deepToString(item));
//        }
//        if (empty == true)
//            System.out.println("found nothing");
//        booksList = sqlGenerator.AllBooks();
//        for (String[] item : booksList) {
//            if (item[3] == null)
//                continue;
//            System.out.println(Arrays.deepToString(item));
//        }
//
        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
