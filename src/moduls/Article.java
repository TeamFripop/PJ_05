package moduls;

import com.sun.jmx.snmp.Timestamp;

import java.io.Console;
import java.util.Locale;

public class Article {
    private String article_id;
    private String barcode;
    private String name;
    private float price;
    private float vat;
    private int stock;
    private boolean deleted;
    private Timestamp created;
    private Timestamp modified;
    private String country;

    public Article(String article_id, String barcode, String name, float price, float vat, int stock, boolean deleted, Timestamp created, Timestamp modified) {
        this.article_id = article_id;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.stock = stock;
        this.deleted = deleted;
        this.created = created;
        this.modified = modified;
        this.country = (barcode.charAt(0)) + barcode.charAt(1) + barcode.charAt(2) + "";
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public static boolean checkDigit(String code, int last)
    {
        int sum = 0;
        for(int i = code.length() - 2, c = 0; i >= 0; i--, c++)
        {
            if(c % 2 == 0)
            {
                sum += 3 * Integer.parseInt(code.charAt(i) + "");
                System.out.println("3 * " + Integer.parseInt(code.charAt(i) + ""));
            }
            else
            {
                sum += 1 * Integer.parseInt(code.charAt(i) + "");
                System.out.println("1 * " + Integer.parseInt(code.charAt(i) + ""));
            }
        }
        System.out.print("\nSum: " + sum + " Check Digit: " + last + " ==> ");
        if((sum + last) % 10 == 0 || (sum - last) % 10 == 0)
        {
            System.out.print("Correct \n");
            return true;
        }
        System.out.print("Wrong \n");
        return  false;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id='" + article_id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vat=" + vat +
                ", stock=" + stock +
                ", deleted=" + deleted +
                ", created=" + created +
                ", modified=" + modified +
                ", country='" + country + '\'' +
                '}';
    }
}
