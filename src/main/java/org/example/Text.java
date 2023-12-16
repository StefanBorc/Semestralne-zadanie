package org.example;

import java.io.*;

import java.util.ArrayList;

public class Text {
    private ArrayList<StringBuilder> zasifrovaneTexty;

    public ArrayList<StringBuilder> getZasifrovaneTexty() {
        return zasifrovaneTexty;
    }

    public Text() throws IOException {
        zasifrovaneTexty = new ArrayList<>();
        nacitatTexty();
    }

    protected void nacitatTexty() throws IOException {
        int i = 1;
        String st;
        File f;
        while (i <= 30) {
            f = new File("prilohy", i + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            st = "";
            while ((st = br.readLine()) != null) {
                zasifrovaneTexty.add(new StringBuilder(st));
            }
            i++;
            br.close();
        }
    }
}
