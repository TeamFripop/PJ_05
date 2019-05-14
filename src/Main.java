//import java.io.*;
import moduls.Artikel;
import moduls.Seznami.Articles;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;

import moduls.*;


public class Main {
    public static void main(String[] args) {
    //--- VAJA 1 ---//

        try
        {
            Vaja02();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void Vaja01()
    {
        LinkedList<Racun> racuni = new LinkedList<Racun>();

        Articles postavke_1 = new Articles("Nakupovalni listek");
        postavke_1.add(new Artikel("Spar Vital Voda 0,75l", 35, Artikel.Vrsta.ARTIKEL));
        postavke_1.add(new Artikel("Spar Strucka Rezna Mes", 129, Artikel.Vrsta.ARTIKEL));
        postavke_1.add(new Artikel("SI Osebna Tehnica", 1990, Artikel.Vrsta.ARTIKEL));
        postavke_1.add(new Artikel("Lucke LED 200 Zun. Bele", 2490, Artikel.Vrsta.ARTIKEL));
        racuni.add(new Racun(Racun.getGlobalId(), postavke_1, "Tuš"));

        Articles postavke_2 = new Articles("Servis motorja 2019");
        postavke_2.add(new Artikel("Tesnilo Vilc", 1410, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Guma 180/55R17", 7943, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Svecka CR9EH9", 1234, 4, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Olje vilic 10W", 607, 2, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Moto Sintetico", 1033, 4, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Oljni filter", 1287, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Hladilna tekocina", 85277878, Artikel.Vrsta.ARTIKEL));
        postavke_2.remove(6);
        postavke_2.add(new Artikel("Hladilna tekocina", 852, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Guma 120/70R17", 6221, Artikel.Vrsta.ARTIKEL));
        postavke_2.add(new Artikel("Storitev", 9869, 6, Artikel.Vrsta.STORITEV));
        postavke_2.add(new Artikel("Storitev", 410, Artikel.Vrsta.STORITEV));
        postavke_2.add(new Artikel("Storitev", 1230, 2, Artikel.Vrsta.STORITEV));
        postavke_2.modify(8, new Artikel("Storitev", 2869, 6, Artikel.Vrsta.STORITEV));
        racuni.add(new Racun(Racun.getGlobalId(), postavke_2, "Louis"));

        for (Racun r : racuni) {
            System.out.println("Skupni znesek na racunu: " + r.getId() + " ki je bil dodan: " + r.getDatum() + " je: " + r.getSkupniZnesekEvri() + "€");
            System.out.println(r.toString());
        }
    }
    private static void Vaja02()
    {
        Podjetje p1 = new Podjetje("Kinezika d.o.o.", "18918557", "3936066000", true, "034254338", "office@kinezika.si");
        Podjetje p2 = new Podjetje("Weber d.o.o.", "70175837", "5294827000", true, "045120925", "info@weber.si");
        Podjetje p3 = new Podjetje("Jakopin d.o.o.", "94015899", "5795621000", true, "073373990", "info@jakopin.si");
        Podjetje p4 = new Podjetje("Auer optika, Denis Auer s.p.", "38759373", "3547876000", true, "026200804", "optika@vsezaoci.si", "Trg vstaje 8, Ruše, 2342 Ruše");

        if(!p4.getDavcna_st().isEmpty())
        {
            System.out.println("Podjetje " + p4.getIme() + " z sedezem na " + p4.getNaslov() + " je davcni zavezanec z stevilko " + p4.getDavcna_st());
        }
        else
        {
            System.out.println("Podjetje " + p4.getIme() + " z sedezem na " + p4.getNaslov() + " ni davcni zavezanec");
        }
        System.out.println("Preverjam EAR kodo 6291041500216 z stevilom 3");
        Article.checkDigit("6291041500216", 3);
    }
    private static void Vaja05()
    {
        try
        {
            fillArticleFromFile("Grocery_UPC_Database.xlsx");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void fillArticleFromFile(String path)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/InoviceManager_dev", "root", "cookies");
            conn.setAutoCommit(false);
            Statement stat = conn.createStatement();
            /* String sqla = "insert into Article (article_id, barcode, name, price, vat, stock, created) Values(0000001, 3194912, 'lolek', 10, 10, 1000, NOW())";

            stat.executeUpdate(sqla);
            conn.commit();
            */
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream( new File(path)));
            XSSFSheet sheet = book.getSheetAt(0);
            Iterator<Row> itR = sheet.iterator();
            int lines = 0;
            int total = 0;
            while(itR.hasNext())
            {
                Row row = itR.next();
                Iterator<Cell> itC = row.cellIterator();
                int c = 0;
                String code = "", name = "";
                while(itC.hasNext())
                {
                    Cell cell = itC.next();
                    if(c == 1)
                    {
                        // Code
                        String tmpCode = cell.getStringCellValue();
                        int codeLength = code.length();
                        if(codeLength < 14)
                        {
                            for(int i = codeLength; i < 14; i++)
                            {
                                code += "0";
                            }
                        }
                        code = tmpCode;
                    }
                    else if(c == 3)
                    {
                        // Name
                        name = cell.getStringCellValue();
                        name = name.replace((char)39, (char)32);
                    }
                    c++;
                }
                if(lines != 0)
                {
                    String sql = "insert into Article (article_id, barcode, name, price, vat, stock, created) " + "values(" + lines +""  + ", " + code +"" + ", '" + name + "', " + ((int)(Math.random() * (100 - 1)) + 1) +"" + ", " + ((int)(Math.random() * (100 - 1)) + 1) +"" + ", " + "1000, NOW())";
                    System.out.println(sql);
                    stat.addBatch(sql);
                }
                // Next Line
                lines++;
                if(lines % 1000 == 0)
                {
                    System.out.println("Commit");
                    int[] count = stat.executeBatch();
                    total += count.length;
                    conn.commit();
                    // Comit
                }
            }
            System.out.println("Commit");
            int[] count = stat.executeBatch();
            total += count.length;
            conn.commit();
            System.out.println("Total Commits [" + total + "]");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
