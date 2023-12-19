package org.example;

import java.io.IOException;

public class Priebeh {
    private Text text;
    private StatistickeVlastnosti vlastnosti;
    private Desifrovanie desifrovanie;
    private String kluc;
    private StringBuilder otvorenyText;

    public Priebeh(String nazovSuboru) throws IOException {
        text = new Text(nazovSuboru);
        vlastnosti = new StatistickeVlastnosti();
        desifrovanie = new Desifrovanie(vlastnosti);
        odhalitKlucText();
        vytlacitKlucText();
    }
    private void odhalitKlucText() {
        if(text.getZasifrovanyText().length()>0){
            desifrovanie.udadnutKluc(text.getZasifrovanyText().toString());
            kluc=desifrovanie.getKluc();
            otvorenyText = desifrovanie.desifrovatText(text.getZasifrovanyText().toString(), desifrovanie.getKluc());
        }
        else{
            kluc="";
            otvorenyText=new StringBuilder();
        }
    }

    private void vytlacitKlucText() {
        System.out.println("PT: " + otvorenyText);
        System.out.println("CT: " + text.getZasifrovanyText());
        System.out.println("KEY: " + kluc);
    }

}
