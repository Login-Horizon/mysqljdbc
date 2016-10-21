package Interactive;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruslanq on 20.10.2016.
 */
public abstract class  BookWorm implements Lib{
    public static int UserId = 0;
    public static int BookId = 0;
    public static String UserEmail = null;
    public static String UserName = null;
    public static String SearchStr = null;
    public static Map BookList = new HashMap<String,String>();
    @Override
    public int Return(int UserId, int BookId, String All, Map MyBookList) {
        return 0;
    }

    @Override
    public int SearchBook(String Keyword) {
        return 0;
    }

    @Override
    public int TookBook(int UserId, int BookId) {
        return 0;
    }
}
