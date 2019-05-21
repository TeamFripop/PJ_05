package moduls;

import moduls.Podpora.CountryCodeHelper;

import java.util.Date;
import java.util.UUID;

public class Artikel implements Searchable  {
    public enum Vrsta {
        ARTIKEL,
        STORITEV,
        NEZNANO;
    }
    private UUID id;
    private Vrsta vrsta;
    private String naziv;
    private int cena;
    private int kolicina = 1;
    private int timeAdded;
    private int skupnaCena;
    private int teza;
    private String code;
    private String internalCode;
    private String drzava;
    private boolean isOk = true;
    private double ddv = 22;

    public Artikel(String naziv, int cena, Vrsta vrsta) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.cena = cena;
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }

    public Artikel(String naziv, String code, int cena, Vrsta vrsta) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.cena = cena;
        this.code = code;
        this.drzava = getCountryFromCode(code);
        System.out.println("Drzava razbrabna iz kode: " + this.drzava + " za izdelek: " + this.naziv);
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }

    public double getDdv() {
        return ddv;
    }

    public void setDdv(double ddv) {
        this.ddv = ddv;
    }

    public Artikel(String naziv, int cena, int kolicina, Vrsta vrsta) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.cena = cena;
        this.kolicina = kolicina;
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }

    public Artikel(String naziv, String internalCode, Vrsta vrsta, int kolicina, int cenaZaKilogram) {
        this.vrsta = vrsta;
        this.naziv = naziv;
        this.internalCode = internalCode;
        this.kolicina = kolicina;
        this.cena = cenaZaKilogram;
        getDataFromCode(internalCode);
        this.timeAdded = (int) (new Date().getTime() / 1000);
        this.skupnaCena = getSkupnaCena();
    }
    //Database Constructor
    public Artikel(UUID id, String code, String title, Double price, Double ddv, int stock)
    {
        if(id == null)
        {
            this.id = UUID.randomUUID();
        }
        else
        {
            this.id = id;
        }
        this.code = code;
        this.drzava = getCountryFromCode(code);
        this.naziv = title;
        this.ddv = ddv;
        this.kolicina = stock;
        this.cena = (int)(price * 100);
        this.skupnaCena = getSkupnaCena();
    }
    // Buy Constructor
    public Artikel(UUID id, int stock)
    {
        if(id == null)
        {
            this.id = UUID.randomUUID();
        }
        else
        {
            this.id = id;
        }
        this.kolicina = stock;
    }

    public String getCode()
    {
        return this.code;
    }
    public void setCode(String code)
    {
        this.code = code;
        getDataFromCode(code);
    }

    private void getDataFromCode(String code)
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getDrzava() {
        return drzava;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", vrsta=" + vrsta +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", kolicina=" + kolicina +
                ", timeAdded=" + timeAdded +
                ", skupnaCena=" + skupnaCena +
                ", teza=" + teza +
                ", code='" + code + '\'' +
                ", internalCode='" + internalCode + '\'' +
                ", drzava='" + drzava + '\'' +
                ", isOk=" + isOk +
                ", ddv=" + ddv +
                '}';
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public static String getCountryFromCode(String code)
    {
        return CountryCodeHelper.getCountryFromCode(code);
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

    public static boolean checkDigit(String code)
    {
        return checkDigit(code, false);
    }
    public static boolean checkDigit(String code, boolean silent)
    {
        try
        {
            int sum = 0;
            int last = Integer.parseInt(code.charAt(code.length() -1) + "");
            for(int i = code.length() - 2, c = 0; i >= 0; i--, c++)
            {
                if(c % 2 == 0)
                {
                    sum += 3 * Integer.parseInt(code.charAt(i) + "");
                    if(!silent)System.out.println("3 * " + Integer.parseInt(code.charAt(i) + ""));
                }
                else
                {
                    sum += 1 * Integer.parseInt(code.charAt(i) + "");
                    if(!silent) System.out.println("1 * " + Integer.parseInt(code.charAt(i) + ""));
                }
            }
            if(!silent) System.out.print("\nSum: " + sum + " Check Digit: " + last + " ==> ");
            if((sum + last) % 10 == 0 || (sum - last) % 10 == 0)
            {
                if(!silent) System.out.print("Correct \n");
                return true;
            }
            System.out.print("Wrong \n");
            return  false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
