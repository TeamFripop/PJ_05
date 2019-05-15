package moduls.Seznami;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import moduls.Podpora.JsonSupport;
import moduls.Racun;

import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;

public class Invoices implements JsonSupport {
    private LinkedList<Racun> racuni;

    public Invoices(LinkedList<Racun> racuni) {
        this.racuni = racuni;
    }

    @Override
    public void fromJson(String data)
    {
        Gson gson = new Gson();
        Racun[] rac = gson.fromJson(data, Racun[].class);
        System.out.println("Stevilo racunov: " + rac.length);

        racuni = new LinkedList<>();
        for(Racun r : rac)
        {
            System.out.println(r.toString());
            racuni.addLast(r);
        }
    }

    @Override
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(racuni.toArray());
    }
}
