package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Priebeh {
    private Text text;
    private StatistickeVlastnosti vlastnosti;
    private Desifrovanie desifrovanie;
    private ArrayList<String> kluce;
    private ArrayList<StringBuilder> otvoreneTexty;

    public Priebeh() throws IOException {
        text = new Text();
        vlastnosti = new StatistickeVlastnosti();
        desifrovanie = new Desifrovanie(vlastnosti);
        kluce = new ArrayList<>();
        otvoreneTexty = new ArrayList<>();
        odhalitKlucText();
        vytlacitKlucText();
    }

    private void odhalitKlucText() {
        for (int i = 0; i < text.getZasifrovaneTexty().size(); i++) {
            desifrovanie.udadnutKluc(text.getZasifrovaneTexty().get(i).toString());
            kluce.add(desifrovanie.getKluc());
            StringBuilder ot = desifrovanie.desifrovatText(text.getZasifrovaneTexty().get(i).toString(), desifrovanie.getKluc());
            otvoreneTexty.add(ot);
        }

    }

    private void vytlacitKlucText() {
        for (int i = 0; i < kluce.size(); i++) {
            System.out.println("PT: " + otvoreneTexty.get(i));
            System.out.println("CT: " + text.getZasifrovaneTexty().get(i));
            System.out.println("KEY: " + kluce.get(i));

        }

    }

}
