package moduls;

import java.util.UUID;

public class Podjetje implements Searchable {
    private UUID id;
    private String ime;
    private String davcna_st;
    private String maticna_st;
    private boolean je_davcni_zavezanec;
    private String telefon;
    private String email;
    private String naslov;

    public Podjetje(String ime, String davcna_st, String maticna_st, boolean je_davcni_zavezanec, String telefon, String email) {
        this.ime = ime;
        this.davcna_st = davcna_st;
        this.maticna_st = maticna_st;
        this.je_davcni_zavezanec = je_davcni_zavezanec;
        this.telefon = telefon;
        this.email = email;
    }

    public Podjetje(String ime, String davcna_st, String maticna_st, boolean je_davcni_zavezanec, String telefon, String email, String naslov) {
        this.ime = ime;
        this.davcna_st = davcna_st;
        this.maticna_st = maticna_st;
        this.je_davcni_zavezanec = je_davcni_zavezanec;
        this.telefon = telefon;
        this.email = email;
        this.naslov = naslov;
    }
    //Mysql Constructor
    public Podjetje(UUID id, String ime, String davcna_st, String maticna_st, String telefon, boolean je_davcni_zavezanec, String naslov) {
        if(id == null)
        {
            this.id = UUID.randomUUID();
        }
        else
        {
            this.id = id;
        }
        this.ime = ime;
        this.davcna_st = davcna_st;
        this.maticna_st = maticna_st;
        this.je_davcni_zavezanec = je_davcni_zavezanec;
        this.telefon = telefon;
        this.naslov = naslov;
    }
    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getDavcna_st() {
        return davcna_st;
    }

    public void setDavcna_st(String davcna_st) {
        this.davcna_st = davcna_st;
    }

    public String getMaticna_st() {
        return maticna_st;
    }

    public void setMaticna_st(String maticna_st) {
        this.maticna_st = maticna_st;
    }

    public boolean isJe_davcni_zavezanec() {
        return je_davcni_zavezanec;
    }

    public void setJe_davcni_zavezanec(boolean je_davcni_zavezanec) {
        this.je_davcni_zavezanec = je_davcni_zavezanec;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public boolean search(String data)
    {
        if(ime.contains(data) || (davcna_st + "").contains(data) || (maticna_st + "").contains(data) || telefon.contains(data) || email.contains(data) || (je_davcni_zavezanec + "").contains(data))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Podjetje{" +
                "ime='" + ime + '\'' +
                ", davcna_st='" + davcna_st + '\'' +
                ", maticna_st='" + maticna_st + '\'' +
                ", je_davcni_zavezanec=" + je_davcni_zavezanec +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", naslov='" + naslov + '\'' +
                '}';
    }
}
