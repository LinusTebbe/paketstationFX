package com.example.newpaketstation;

public class Fach {

    private Paket myInhalt;

    public boolean isFachfrei() {
        return this.myInhalt == null;
    }

    public void fachLeeren() {
        this.myInhalt = null;
    }

    public Paket getPaket() {
        return this.myInhalt;
    }

    public void einlagernPaket(Paket p) {
        this.myInhalt = p;
    }
}
