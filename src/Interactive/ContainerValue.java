package Interactive;

/**
 * Created by Ruslanq on 22.10.2016.
 */
public class ContainerValue {
    public String id;
    public String name;


    //------------

    private static ContainerValue instance = null;
    private void ContainerValue(){

    }
    public static ContainerValue getInstance(){
        if(instance==null){
            instance = new ContainerValue();
        }
        return instance;
    }
}
