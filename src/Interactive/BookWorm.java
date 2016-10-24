package Interactive;

import JDBC.LibBD;
import JDBC.ListDB;
import JDBC.SQLGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Ruslanq on 20.10.2016.
 */
public abstract class  BookWorm implements Lib{


    @Override
    public int Return(int UserId, int BookId, String All, Map MyBookList) {
        return 0;
    }

    @Override
    public String[][] SearchBook(String keyword) {
       SQLGenerator sqlGenerator =new SQLGenerator();
        sqlGenerator.BookByKey(keyword);
        return sqlGenerator.BookByKey(keyword) ;
    }

    @Override
    public int TookBook(int UserId, int BookId) {
        return 0;
    }

    @Override
    public ObservableList<ListDB> AllBookToListBD(String[][] allBook) {
        ObservableList<ListDB>  listofDB = FXCollections.observableArrayList();;
        ListDB  listDB = new ListDB();
        for(int j =0;j < allBook.length;j++){
            listDB.setName(allBook[j][0]);
            System.out.println(listDB.getName());
        listDB.setAuthor(allBook[j][1]);
            System.out.println(listDB.getAuthor());
        listDB.setGenre(allBook[j][2]);
            System.out.println(listDB.getGenre());
        listDB.setUserid(allBook[j][3]);
            System.out.println(listDB.getUserid());

        listofDB.add(new ListDB(listDB.getName(),listDB.getAuthor(),listDB.getGenre(),listDB.getUserid()));}
        return listofDB;
    }
}
