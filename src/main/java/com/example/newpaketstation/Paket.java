package com.example.newpaketstation;

public class Paket {

    private final String empfaenger;
    private final int sendungsnummer;

    public Paket(String empfaenger, int sendungsnummer) {
        this.empfaenger = empfaenger;
        this.sendungsnummer = sendungsnummer;
    }

    public String getEmpfaenger() {
        return this.empfaenger;
    }

    public int getSendungsnummer() {
        return this.sendungsnummer;
    }
}
