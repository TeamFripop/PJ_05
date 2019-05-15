package moduls.Seznami;

import com.google.gson.Gson;
import moduls.Podjetje;
import moduls.Podpora.JsonSupport;
import moduls.Racun;

import java.util.LinkedList;

public class Companies implements JsonSupport {
    private LinkedList<Podjetje> podjetja;
    public Companies(LinkedList<Podjetje> podjetja) {
        this.podjetja = podjetja;
    }

    @Override
    public void fromJson(String data)
    {
        Gson gson = new Gson();
        Podjetje[] pod = gson.fromJson(data, Podjetje[].class);
        System.out.println("Stevilo podjetij: " + pod.length);

        podjetja = new LinkedList<>();
        for(Podjetje p : pod)
        {
            System.out.println(p.toString());
            podjetja.addLast(p);
        }
    }

    @Override
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(podjetja.toArray());
    }

}
