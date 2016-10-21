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

/**
 * Created by Ruslanq on 20.10.2016.
 */
class Main {


    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        System.out.print("hello java");
        SQLGenerator sqlGenerator = new SQLGenerator();

        String[][] booksList ;
//
//        for (String[] item : booksList) {
//            if (item[3] == null)
//                break;
//            System.out.println(Arrays.deepToString(item));
//        }
        String[] cls = new String[] {"cmd.exe", "/c", "cls"};
        Runtime.getRuntime().exec(cls);
        System.out.println("new life");
        booksList =sqlGenerator.BookByKey("har");

        for (String[] item : booksList) {
            if (item[3] == null)
                break;
            System.out.println(Arrays.deepToString(item));
        }
        booksList = sqlGenerator.AllBooks();
        for (String[] item : booksList) {
            if (item[3] != null)
            System.out.println(Arrays.deepToString(item));
        }
        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
