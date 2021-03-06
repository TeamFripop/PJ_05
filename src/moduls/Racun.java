package moduls;

import moduls.Seznami.Articles;

import java.util.Date;
import java.util.UUID;

public class Racun implements Searchable {
    private static int GLOBAL_ID = 1000;
    private UUID id;
    private Date datum;
    private int skupniZnesek;
    private String EAN;
    private Articles artikli;
    private double ddv = 22.2;
    private String izdajatelj;
    private String idDDV;

    public Racun(UUID id) {
        this.id = id;
        datum = new Date();
    }

    public Racun(UUID id, String EAN) {
        this.id = id;
        datum = new Date();
    }

    public Racun(UUID id, double total, double total_vat) {
        this.id = id;
        this.skupniZnesek = (int)(total * 100);
        this.ddv = total_vat;
        datum = new Date();
    }

    public Racun(UUID id, Articles artikli, String izdajatelj) {
        this.id = id;
        this.artikli = artikli;
        this.izdajatelj = izdajatelj;
        datum = new Date();
        calculatePrice();
    }

    /*public static int getGlobalId() {
        GLOBAL_ID++;
        return GLOBAL_ID;
    }*/

    public static UUID getGlobalId() {
        return UUID.randomUUID();
    }
    public Articles getArtikli() {
        return artikli;
    }

    public void setArtikli(Articles postavke) {
        this.artikli = postavke;
        calculatePrice();
    }

    private void calculatePrice() {
        skupniZnesek = 0;
        for (Artikel a : artikli.get()) {
            skupniZnesek += ((a.getSkupnaCena()));
        }
    }

    public int getSkupniZnesek() {
        return skupniZnesek;
    }

    public double getSkupniZnesekEvri() {

        return skupniZnesek / (double) 100;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getIzdajatelj() {
        return izdajatelj;
    }

    public void setIzdajatelj(String izdajatelj) {
        this.izdajatelj = izdajatelj;
    }

    public String getIdDDV() {
        return idDDV;
    }

    public void setIdDDV(String idDDV) {
        this.idDDV = idDDV;
    }

    public boolean search(String data)
    {
        if((id + "").contains(data) || (datum + "").contains(data) || (skupniZnesek + "").contains(data) || EAN.contains(data)
            || (ddv + "").contains(data) || izdajatelj.contains(data) || idDDV.contains(data))
            return true;
        return false;
    }
    public String toString() {
        return "Racun{" +
                "id=" + id +
                ", datum=" + datum +
                ", skupniZnesek=" + skupniZnesek +
                ", EAN='" + EAN + '\'' +
                ", postavke=" + artikli +
                ", ddv=" + ddv +
                ", izdajatelj='" + izdajatelj + '\'' +
                ", idDDV='" + idDDV + '\'' +
                '}';
    }

    public double getDdv() {
        return ddv;
    }

    public void setDdv(int ddv) {
        this.ddv = ddv;
    }
}

