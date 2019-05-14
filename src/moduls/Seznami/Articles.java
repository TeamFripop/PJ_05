package moduls.Seznami;

import moduls.Artikel;

import java.util.LinkedList;

public class Articles {
    private LinkedList<Artikel> postavke;
    private String naziv;

    public Articles() {
        postavke = new LinkedList<Artikel>();
    }

    public Articles(String naziv) {
        this.naziv = naziv;
        postavke = new LinkedList<Artikel>();
    }

    public void add(Artikel postavka) {
        postavke.add(postavka);
    }

    public void add(Artikel postavka, int stevilo) {
        for (int i = 0; i < stevilo; i++) {
            postavke.add(postavka);
        }
    }

    public void remove(int index)
    {
        postavke.remove(index);
    }

    public void modify(int index, Artikel postavka)
    {
        postavke.set(index, postavka);
    }

    public LinkedList<Artikel> get() {
        return postavke;
    }

    public void set(LinkedList<Artikel> postavke) {
        this.postavke = postavke;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Artikli{" +
                "postavke=" + postavke +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
