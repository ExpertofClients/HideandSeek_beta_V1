package de.expertofclients.hideandseek.Exeption;

public class CheckExeption extends  Exception{
    String fehler;

    public CheckExeption(String fehler) {
        this.fehler = fehler;
    }

    public String getFehler( ){
        return fehler;
    }
}
