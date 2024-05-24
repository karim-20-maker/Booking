package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties properties;
    public static Properties readPropertyFile(String filePath){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            properties = new Properties();
            try{
                properties.load(reader);
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }
    private static  String getPropertyValue(String key , File file){
        properties = new Properties();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            properties.load(bufferedReader);
        }catch (FileNotFoundException fileExc){
            fileExc.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
    public static String readEnvFile(String key){
        File file = new File("src/config/env.properties");
        return getPropertyValue(key,file);
    }


}
