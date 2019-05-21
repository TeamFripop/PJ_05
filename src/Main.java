//import java.io.*;
import moduls.Artikel;
import moduls.Baza.*;
import moduls.Baza.MySqlArticle;
import moduls.Podpora.JsonHelper;
import moduls.Seznami.Articles;
import moduls.Seznami.Companies;
import moduls.Seznami.Invoices;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import moduls.*;


public class Main {
    public static void main(String[] args) {
        try {
            //Vaja01();
            //Vaja02();
            //Vaja04();
            //Vaja05();
            Vaja05_2();
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
        postavke_1.add(new Artikel("Flumaster", "4007817321546", 100, Artikel.Vrsta.ARTIKEL));
        postavke_1.add(new Artikel("Lucke LED 200 Zun. Bele", 2490, Artikel.Vrsta.ARTIKEL));
        postavke_1.add(new Artikel("Banane", "2116789002000", Artikel.Vrsta.ARTIKEL, 7, 250));
        postavke_1.add(new Artikel("Jabolke", "2116752001504", Artikel.Vrsta.ARTIKEL, 10, 150));
        postavke_1.add(new Artikel("Grozdje", "2116731003703", Artikel.Vrsta.ARTIKEL, 2, 1200));
        postavke_1.add(new Artikel("Grozdje - PRIMER NAPACNE KODE", "2116731003700", Artikel.Vrsta.ARTIKEL, 2, 1200));
        postavke_1.add(new Artikel("Lucke LED 200 Zun. Bele", 2490, Artikel.Vrsta.ARTIKEL));
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
        Vaja03(racuni, postavke_1.get());
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
        Artikel.checkDigit("6291041500213");
        Artikel a1 = new Artikel("Flumaster", "4007817321546", 100, Artikel.Vrsta.ARTIKEL);
        Artikel a2 = new Artikel("Zvezek", "6291041500213", 320, Artikel.Vrsta.ARTIKEL);
        Artikel a3 = new Artikel("Sir", "8481041500213", 320, Artikel.Vrsta.ARTIKEL);
    }
    private static void Vaja03(LinkedList<Racun> racuni, LinkedList<Artikel> artikli)
    {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Generiram JSON string racunov...");
        Invoices invoices = new Invoices(racuni);
        System.out.println("JSON: " + invoices.toJson());

        JsonHelper.toGsonFile(invoices.toJson(), "racuni.txt");
        invoices.fromJson(JsonHelper.fromGsonFile("racuni.txt"));

        System.out.println();
        Podjetje p1 = new Podjetje("Kinezika d.o.o.", "18918557", "3936066000", true, "034254338", "office@kinezika.si");
        Podjetje p2 = new Podjetje("Weber d.o.o.", "70175837", "5294827000", true, "045120925", "info@weber.si");
        Podjetje p3 = new Podjetje("Jakopin d.o.o.", "94015899", "5795621000", true, "073373990", "info@jakopin.si");
        Podjetje p4 = new Podjetje("Auer optika, Denis Auer s.p.", "38759373", "3547876000", true, "026200804", "optika@vsezaoci.si", "Trg vstaje 8, Ruše, 2342 Ruše");
        LinkedList<Podjetje> podjetja = new LinkedList<>();
        podjetja.addLast(p1);
        podjetja.addLast(p2);
        podjetja.addLast(p3);
        podjetja.addLast(p4);
        Companies companies = new Companies(podjetja);

        System.out.println("Generiram JSON string podjetij...");
        System.out.println("JSON: " + companies.toJson());
        JsonHelper.toGsonFile(companies.toJson(), "podjetja.txt");
        companies.fromJson(JsonHelper.fromGsonFile("podjetja.txt"));
        System.out.println();

        System.out.println("Generiram JSON string artikov...");
        Articles articles = new Articles(artikli);
        System.out.println("JSON: " + invoices.toJson());
        JsonHelper.toGsonFile(articles.toJson(), "artikli.txt");
        articles.fromJson(JsonHelper.fromGsonFile("artikli.txt"));
    }
    private static void Vaja04()
    {
        try
        {
            //DBHelper.GetProperties();
            //DBHelper.SetProperties();
            if(DBHelper.TestConnection())
            {
                System.out.println("DATABASE CONNECTION OK!");
            }
            else
            {
                System.out.println("DATABASE CONNECTION ERROR!");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
                boolean ok = true;
                while(itC.hasNext())
                {
                    Cell cell = itC.next();
                    if(c == 1)
                    {
                        // Code
                        code = cell.getStringCellValue();
                        int codeLength = code.length();
                        String prefix = "";
                        if(codeLength < 14)
                        {
                            for(int i = codeLength; i < 14; i++)
                            {
                               prefix += "0";
                            }
                        }
                        code = prefix + code;
                        if(!Artikel.checkDigit(code, true))
                        {
                            ok = false;
                            System.out.println("Code: " + code + " is not valid! ==> SKIPPING!");
                            break;
                        }
                    }
                    else if(c == 3)
                    {
                        // Name
                        name = cell.getStringCellValue();
                        name = name.replace((char)39, (char)32);
                    }
                    c++;
                }
                if(lines != 0 && ok)
                {
                    String sql = "insert into Article (article_id, barcode, name, price, vat, stock, created) " + "values(" + (Util.uuidToBinary(UUID.randomUUID())).toString() + ", " + code +"" + ", '" + name + "', " + ((int)(Math.random() * (100 - 1)) + 1) +"" + ", " + ((int)(Math.random() * (100 - 1)) + 1) +"" + ", " + "1000, NOW())";
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
    private static void Vaja05_2()
    {
        try
        {
            MySqlArticle sqlArticle = new MySqlArticle();
            MySqlnternalArticle sqlInternalArticle = new MySqlnternalArticle();
            MySqlCompany sqlCompany = new MySqlCompany();
            /*for(Artikel ar : sqlArticle.getAll())
            {
                System.out.println(ar.toString());
            }*/
            /*System.out.println("Vstavljam artikel v bazo");
            Artikel ar = new Artikel(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "00077975010198", "Banane", 1.2, 0.5, 20);
            if(sqlArticle.insert(ar))
            {
                System.out.println("Vnos uspesen");
            }
            else
            {
                System.out.println("Vnos neuspesen, Artikel najverjetneje ze obstaja");
            }
            System.out.println("Pridobivam artikel iz baze");
            Artikel artikel = sqlArticle.getById(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
            System.out.println(artikel.toString());

            System.out.println("Spreminajm artikel artikel iz baze");
            artikel.setNaziv("Grozdje");
            System.out.println(artikel.toString());
            if(sqlArticle.update(artikel))
            {
                System.out.println("Poosodobitev uspesena");
            }
            else
            {
                System.out.println("Poosodobitev neuspesna");
            }

            System.out.println("Pridobivam artikel iz baze");
            artikel = sqlArticle.getById(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
            System.out.println(artikel.toString());

            System.out.println("Brisem izdelek iz baze");
            if(sqlArticle.delete(ar))
            {
                System.out.println("Izbirs uspel");
            }
            else
            {
                System.out.println("Izbirs ni uspel, Artikel najverjetneje ne obstaja");
            }*/

            Podjetje p1 = new Podjetje(UUID.fromString("b511aafd-88b5-4dd3-8ff3-2e164840a0df"), "Kinezika d.o.o.", "18918557", "3936066000", "034254338", true, "Plinarniška ulica 4, Trnovlje pri Celju, 3000 Celje");
            Podjetje p2 = new Podjetje(UUID.fromString("580eedaa-9e85-4f88-a3f7-e3d67a34c699"), "Weber d.o.o.", "70175837", "5294827000", "045120925", true, "Stara Loka 36, Stara Loka, 4220 Škofja Loka");
            Podjetje p3 = new Podjetje(UUID.fromString("9f74e0c5-9470-4f25-9340-43a96f062271"), "Jakopin d.o.o.", "94015899", "5795621000", "073373990", true, "Streliška ulica 1A, Novo mesto, 8000 Novo mesto");
            Podjetje p4 = new Podjetje(UUID.fromString("c1965b21-905d-4df9-9ca0-9affd5eaa01f"), "Auer optika, Denis Auer s.p.", "38759373", "3547876000", "026200804", true, "Trg vstaje 8, Ruše, 2342 Ruše");
            Podjetje p5 = new Podjetje(UUID.fromString("2552c81f-d251-4519-ab15-9fa0edabed94"), "Kostanj d.o.o.", "67679803", "5530377000", "037140063 ", true, "Medlog 63, Medlog, 3000 Celje");
            sqlCompany.insert(p1);
            sqlCompany.insert(p2);
            sqlCompany.insert(p3);
            sqlCompany.insert(p4);
            sqlCompany.insert(p5);

            Artikel a1 = new Artikel(UUID.fromString("a7ad848e-2cf6-4b2c-866a-3e0d18a107f0"), "0077914010198", "Pomarance", 1.5, 0.8, 80);
            Artikel a2 = new Artikel(UUID.fromString("36b676db-a92b-47bd-9c24-03924dfede9d"), "5060226943227", "Sprej Axe",3.7 , 12.2, 20);
            Artikel a3 = new Artikel(UUID.fromString("1e6b765d-f9bc-49fb-a84f-a3b1abb36dfa"), "8016600106839", "Ovitek za telefon Huawei", 33.4, 20.0, 15);
            Artikel a4 = new Artikel(UUID.fromString("f7f582a1-3e73-49f4-a400-5a2729866b91"), "9781444758993", "Knjiga Red Rising - Pierce Brown", 8.5, 12.8, 32);
            Artikel a5 = new Artikel(UUID.fromString("9b0b8e6f-c057-41bd-a6d2-a98bb1d4429f"), "9781444707823", "Knjiga Cell - Stephen King", 9.99, 12.8, 13);
            sqlInternalArticle.insert(a1);
            sqlInternalArticle.insert(a2);
            sqlInternalArticle.insert(a3);
            sqlInternalArticle.insert(a4);
            sqlInternalArticle.insert(a5);
            sqlInternalArticle.update(a5);

            Articles ars1 = new Articles();
            ars1.add(new Artikel(a1.getId(), 8));
            ars1.add(new Artikel(a2.getId(), 3));
            ars1.add(new Artikel(a4.getId(), 7));
            Racun r1 = new Racun(UUID.fromString("0a54a574-5790-41d8-9bb7-d438e7abab1c"), ars1, "Simon Vajs");
            Articles ars2 = new Articles();
            ars2.add(new Artikel(a3.getId(), 2));
            ars2.add(new Artikel(a5.getId(), 2));
            Racun r2 = new Racun(UUID.fromString("53a19d10-ec08-4634-8ab9-a2715357f7fa"), ars2, "Primoz Vajs");

            create_invoice(r1);
            create_invoice(r2);
        }
            catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void create_invoice(Racun ra)
    {
        // Dodaj racun
        MySqlInvoice sqlInvoice = new MySqlInvoice();
        MySqlnternalArticle sqlArticle = new MySqlnternalArticle();
        sqlInvoice.insert(ra);

        // Ustrezno zmanjsaj zalogo artiklov v bazi
        for(Artikel ar : ra.getArtikli().get())
        {
            Artikel oldArticel = sqlArticle.getById(ar.getId());
            System.out.println("Stari podatki: " + oldArticel.toString());
            oldArticel.setKolicina(oldArticel.getKolicina() - ar.getKolicina());
            System.out.println("Novi podatki: " + oldArticel.toString());
            if(oldArticel.getKolicina() < 0)
            {
                System.out.println("Nakup " + oldArticel.getNaziv() + " ni mogoc, saj je zaloge zmanjkalo!");
            }
            else
            {
                sqlArticle.update(oldArticel);
            }
        }
    }
}
