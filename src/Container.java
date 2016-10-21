/**
 * Created by Ruslanq on 20.10.2016.
 */
public class Container {
    private static Container ourInstance = new Container();

    public static Container getInstance() {
        return ourInstance;
    }

    private Container() {
    }
}
