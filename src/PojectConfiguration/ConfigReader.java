package PojectConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Ruslanq on 21.10.2016.
 */
public class ConfigReader {
    public static  String readConfig(String Stringkey){
        HashMap<String,String> configInfo=new HashMap<String,String>();
        try{
            File file = new File("config.properties");
            FileInputStream fileInput=new FileInputStream(file);
            Properties properties= new Properties();
            properties.load(new FileInputStream(file));
            fileInput.close();
            Enumeration enuKey=properties.keys();
            while(enuKey.hasMoreElements()){
                String key= (String) enuKey.nextElement();
                String value=properties.getProperty(key);
                configInfo.put(key,value);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return    configInfo.get(Stringkey);
    }
}
