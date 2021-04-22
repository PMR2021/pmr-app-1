package com.example.pmrapp1;

import java.util.ArrayList;

public class Electif {
    private String promo;
    private ArrayList<Prof> profs;

    public Electif(String promo, ArrayList<Prof> profs) {
        this.promo = promo;
        this.profs = profs;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public ArrayList<Prof> getProfs() {
        return profs;
    }

    public void setProfs(ArrayList<Prof> profs) {
        this.profs = profs;
    }

    @Override
    public String toString() {
        return "Electif{" +
                "promo='" + promo + '\'' +
                ", profs=" + profs +
                '}';
    }
}
