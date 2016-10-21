package JDBC;

import java.util.List;

/**
 * Created by Ruslanq on 20.10.2016.
 */
public interface LibBD {

    public String[][] AllBooks();

    public String UserInDb(String userId, String userEmail);

    public int InsertNewUser(String userName, String userEmail);

    public void ChangeTheBookOwner(int userId, int bookId);

    public String[][] BookByKey(String key);
}
