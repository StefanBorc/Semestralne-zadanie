package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static org.example.StatistickeVlastnosti.refHodnoty;

public class Desifrovanie {
    public static int DLZKA = 12;
    public static final int MODULO = 26;
    private StatistickeVlastnosti vlastnosti;
    private String kluc;

    public String getKluc() {
        return kluc;
    }

    public Desifrovanie(StatistickeVlastnosti vlastnosti) {
        this.vlastnosti = vlastnosti;
    }

    protected void udadnutKluc(String text) {
        ArrayList<ArrayList<Double>> odchylkyPosunov = new ArrayList<>();
        ArrayList<StringBuilder> stlpce = vratStlpceKluca(text);
        for (int i = 0; i < stlpce.size(); i++) {
            odchylkyPosunov.add(new ArrayList<>());
            for (char c = 'a'; c <= 'z'; c++) {
                String cR = "";
                cR += c;
                StringBuilder zrotovanyStlpec;
                if (c != 'a') {
                    zrotovanyStlpec = desifrovatText(stlpce.get(i).toString(), cR);
                } else {
                    zrotovanyStlpec = stlpce.get(i);
                }
                odchylkyPosunov.get(i).add(vyratatOdchylkuStlpcaPrePosunI(zrotovanyStlpec));
            }
        }
        poskladatKluc(odchylkyPosunov);
    }

    private ArrayList<StringBuilder> vratStlpceKluca(String text) {
        ArrayList<StringBuilder> iPozicieTextu = new ArrayList<>();

        for (int i = 0; i < DLZKA; i++) {
            iPozicieTextu.add(new StringBuilder());
            for (int j = i; j < text.length(); j += DLZKA) {
                iPozicieTextu.get(i).append(text.charAt(j));
            }
        }

        return iPozicieTextu;
    }

    private double vyratatOdchylkuStlpcaPrePosunI(StringBuilder stlpec) {
        double odchylka = 0.0;
        Map<String, Double> ngramyStlpca = vlastnosti.ngramy(stlpec, 1, true);
        String cR = "";
        for (char c = 'a'; c < 'z'; c++) {
            cR += c;
            if (ngramyStlpca.containsKey(cR)) {
                odchylka += Math.abs(ngramyStlpca.get(cR) - ((refHodnoty[c - 'a']) * 100));
            } else {
                odchylka += (refHodnoty[c - 'a'] * 100);
            }
            cR = "";
        }
        return odchylka;
    }

    private void poskladatKluc(ArrayList<ArrayList<Double>> odchylkyStlpcov) {
        kluc = "";
        for (int i = 0; i < odchylkyStlpcov.size(); i++) {
            int minOdchylka = odchylkyStlpcov.get(i).indexOf(Collections.min(odchylkyStlpcov.get(i)));
            kluc += (char) (minOdchylka + 'a');
        }
    }

    protected StringBuilder desifrovatText(String text, String kluc) {
        StringBuilder ot = new StringBuilder();
        for (int p = 0; p < text.length(); p++) {
            int indexP = (text.charAt(p) - 'a');
            indexP -= (int) kluc.charAt(p % kluc.length()) - 'a';
            indexP += MODULO;
            indexP %= MODULO;
            char pismeno = (char) (indexP + 'a');
            ot.append(pismeno);
        }
        return ot;
    }

}
