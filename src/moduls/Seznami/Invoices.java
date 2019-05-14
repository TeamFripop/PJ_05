package moduls.Seznami;

import moduls.Podpora.JsonSupport;
import moduls.Racun;

import java.util.LinkedList;

public class Invoices implements JsonSupport {
    private LinkedList<Racun> racuni;

    @Override
    public void fromJson(String data) {

    }

    @Override
    public String toJson(String data) {
        return null;
    }
}
