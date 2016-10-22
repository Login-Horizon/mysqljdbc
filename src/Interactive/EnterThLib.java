package Interactive;

import JDBC.SQLGenerator;

import java.awt.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ruslanq on 20.10.2016.
 */
public abstract class EnterThLib implements Lib{

   private SQLGenerator sqlGenerator = new SQLGenerator();




    @Override
    public   String LogOn(String userEmail, String userId) {
        String username = sqlGenerator.UserInDb(userId, userEmail);
        if (username != null) {
            Pattern pattern = Pattern.compile(":(.*?):");
            Matcher matcher = pattern.matcher(username);
            if (matcher.find()) {
                ContainerValue.getInstance().id = (matcher.group(1));
            }

            return username;
        }
        return username;
    }
    @Override
    public String Registration(String userEmail, String userName) {

        return null;
    }

    @Override
    public void LogOff() {

    }

}
