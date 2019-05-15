package moduls.Podpora;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;

public class JsonHelper {
    public static void toGsonFile(String data, String path)
    {
        Gson gson = new Gson();
        try(FileWriter writer = new FileWriter(path))
        {
            JsonElement jsonElement = gson.fromJson(data, JsonElement.class);
            System.out.println("Zapisujem JSON v datoteko " + path);
            gson.toJson(jsonElement, writer);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static String fromGsonFile(String path)
    {
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(path))
        {
            System.out.println("Berem JSON v datoteko " + path);
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            return gson.toJson(jsonElement);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
