package moduls;

import moduls.Searchable;

import java.util.Date;

public class Artikel implements Searchable {
    public enum Vrsta {
        ARTIKEL,
        STORITEV,
        NEZNANO;
    }

    private Vrsta vrsta;
    private String naziv;
    private int cena;
    private int kolicina = 1;
    private int timeAdded;
    private int skupnaCena;

    public Artikel(String naziv, int cena, Vrsta vrsta) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.cena = cena;
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }

    public Artikel(String naziv, int cena, int kolicina, Vrsta vrsta) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.cena = cena;
        this.kolicina = kolicina;
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
        this.skupnaCena = getSkupnaCena();
    }

    public Vrsta getVrsta() {
        return vrsta;
    }

    public void setVrsta(Vrsta vrsta) {
        this.vrsta = vrsta;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
        this.skupnaCena = getSkupnaCena();
    }

    public int getSkupnaCena() {
        return this.kolicina * this.cena;
    }
    public boolean search(String data)
    {
        if((vrsta + "").contains(data) || naziv.contains(data) || (cena + "").contains(data) ||
                (kolicina + "").contains(data) || (timeAdded + "").contains(data) || (skupnaCena + "").contains(data))
            return true;
        return false;
    }
    @Override
    public String toString() {
        return "Artikel{" +
                "vrsta=" + vrsta +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", kolicina=" + kolicina +
                ", timeAdded=" + timeAdded +
                ", skupnaCena=" + skupnaCena +
                '}';
    }
}
