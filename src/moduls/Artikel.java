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
    private int teza;
    private String code;
    private boolean isOk = true;

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

    public Artikel(String naziv, String code, Vrsta vrsta, int kolicina, int cenaZaKilogram) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.code = code;
        this.kolicina = kolicina;
        this.cena = cenaZaKilogram;
        getDataFromCode();
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }
    public String getCode()
    {
        return this.code;
    }
    public void setCode(String code)
    {
        this.code = code;
        getDataFromCode();
    }

    private void getDataFromCode()
    {
        if(!Artikel.checkDigit(code))
        {
            System.out.println("NAPACNA KODA");
            isOk = false;
            return;
        }
        boolean foundNonZero = false;
        String tmp = "";
        for(int i = code.length() - 5; i < code.length() - 1; i++)
        {

            if(code.charAt(i) != '0' || foundNonZero)
            {
                foundNonZero = true;
                tmp += code.charAt(i);
            }

        }
        System.out.println("TEZA: " + tmp);
        teza = Integer.parseInt(tmp);
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
        if (teza == 0)
        {
            return this.kolicina * this.cena;
        }
        else
        {
            return this.kolicina * this.cena * this.teza / 1000;
        }
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
                ", teza=" + teza +
                ", code='" + code + '\'' +
                ", isOk=" + isOk +
                '}';
    }

    public static boolean checkDigit(String code)
    {
        int sum = 0;
        int last = Integer.parseInt(code.charAt(code.length() -1) + "");
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
}
