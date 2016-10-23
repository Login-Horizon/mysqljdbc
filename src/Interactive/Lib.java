package Interactive;

import JDBC.LibBD;
import JDBC.ListDB;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruslanq on 20.10.2016.
 */
public interface Lib {


    public  String LogOn(String userEmail, String userId);

    public String Registration(String userEmail, String userName);

    public void LogOff();

    public int TookBook(int userId, int bookId);

    public int SearchBook(String keyword);

    public int Return(int userId, int bookId, String all, Map myBookList);

    public ObservableList<ListDB> AllBookToListBD(String[][] allBook);
}
