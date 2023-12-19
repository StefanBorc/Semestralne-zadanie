package org.example;

import java.util.HashMap;

import java.util.Map;

public class StatistickeVlastnosti {
    public StatistickeVlastnosti() {

    }
    public static double[] refHodnoty = {0.08167, 0.01492, 0.02782,
            0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966,
            0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507,
            0.01929, 9.5E-4, 0.05987, 0.063269, 0.0905599, 0.02758,
            0.00978, 0.0236, 0.0015, 0.01974, 7.4E-4};


    protected Map<String, Double> ngramy(StringBuilder text, int n, boolean relativna) {
        Map<String, Double> ngramy = new HashMap<>();

        for (int i = 0; i < text.length() - n; i++) {
            String ngram = "";
            for (int j = i; j < i + n; j++) {
                ngram += text.charAt(j);
            }

            boolean jeBigramZnakov = true;
            for (var c : ngram.toCharArray()) {
                if (!Character.isAlphabetic(c)) {
                    jeBigramZnakov = false;
                }
            }
            if (jeBigramZnakov) {
                ngramy.merge(ngram, 1.0, Double::sum);
            }
        }
        if (relativna) {
            double pocet = ngramy.values().stream().mapToDouble(v -> v).sum();
            Map<String, Double> ngramyPercenta = new HashMap<>();
            for (var bigram : ngramy.entrySet()) {
                ngramyPercenta.put(bigram.getKey(), (bigram.getValue() / pocet) * 100);
            }
            return ngramyPercenta;
        } else {
            return ngramy;
        }
    }
}
