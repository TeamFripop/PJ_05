package moduls.Baza;

import com.mysql.cj.xdevapi.Result;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBHelper {

    public static Connection getConnection()
    {
        try (InputStream reader = new FileInputStream("config.properties"))
        {
            System.out.println("Getting database connection...");
            Properties properties = new Properties();
            properties.load(reader);

            Connection conn = DriverManager.getConnection(properties.getProperty("database.URL"), properties.getProperty("database.User"), properties.getProperty("database.Password"));
            return conn;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void SetProperties()
    {
        try (OutputStream writer = new FileOutputStream("config.properties"))
        {
            Properties properties = new Properties();
            properties.setProperty("database.URL", "jdbc:mysql://localhost:3306/InoviceManager_dev");
            properties.setProperty("database.User", "root");
            properties.setProperty("database.Password", "cookies");
            properties.store(writer, null);
            System.out.println("Properties set: " + properties);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public static void SetProperties(Properties pr)
    {
        try (OutputStream writer = new FileOutputStream("config.properties"))
        {
            pr.store(writer, null);
            System.out.println("Properties set: " + pr);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public static Properties getProperties()
    {
        try (OutputStream writer = new FileOutputStream("config.properties"))
        {
            Properties properties = new Properties();
            properties.setProperty("database.URL", "jdbc:mysql://localhost:3306/InoviceManager_dev");
            properties.setProperty("database.User", "root");
            properties.setProperty("database.Password", "cookies");
            properties.store(writer, null);
            System.out.println("Properties set: " + properties);
            return properties;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void GetProperties()
    {
        try (InputStream reader = new FileInputStream("config.properties"))
        {
            Properties properties = new Properties();
            properties.load(reader);
            System.out.println("Propertie set: " + properties.getProperty("database.URL"));
            System.out.println("Propertie set: " + properties.getProperty("database.User"));
            System.out.println("Propertie set: " + properties.getProperty("database.Password"));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static boolean TestConnection()
    {
        try (InputStream reader = new FileInputStream("config.properties"))
        {
            System.out.println("Testing database connection...");
            System.out.println("Getting first 5 Articles...");
            Properties properties = new Properties();
            properties.load(reader);

            Connection conn = DriverManager.getConnection(properties.getProperty("database.URL"), properties.getProperty("database.User"), properties.getProperty("database.Password"));
            conn.setAutoCommit(false);
            Statement stat = conn.createStatement();
            String sqla = "select * from Article LIMIT 5";
            ResultSet rs = stat.executeQuery(sqla);
            boolean ok = true;
            while(rs.next())
            {
                if(rs.getString("name").equals("") || rs.getString("name") == null)ok = false;
                System.out.println(rs.getString("name"));
            }
            stat.close();
            return ok;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
