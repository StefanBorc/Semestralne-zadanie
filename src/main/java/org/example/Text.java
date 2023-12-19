package org.example;

import java.io.*;

public class Text {
    private StringBuilder zasifrovanyText;
    public StringBuilder getZasifrovanyText() {
        return zasifrovanyText;
    }
    public Text(String nazovSuboru) throws IOException {
        zasifrovanyText = new StringBuilder();
        nacitatText(nazovSuboru);
    }
    protected void nacitatText(String nazovSuboru) throws IOException {
        String st;
        File f = new File(nazovSuboru);
        if(!f.canExecute()){
            return ;
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        st = "";
        while ((st = br.readLine()) != null) {
            zasifrovanyText.append(st);
        }
        br.close();
    }
}
