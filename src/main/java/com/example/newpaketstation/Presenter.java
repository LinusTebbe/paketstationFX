package com.example.newpaketstation;

import java.util.ArrayList;

public class Presenter {
    private final Paketstation paketstation = new Paketstation();

    private int nextSendungsNummer = 1;


    public String handleList() {
        StringBuilder output = new StringBuilder();

        String format = "%4s\t%-15s\t%-5s\n";
        output.append(String.format(format, "Fach", "Empfänger", "Sendungsnummer"));

        for (int i = 1; i <= this.paketstation.getFaecher().size(); i++) {
            Paket paket = this.paketstation.getFaecher().get(i - 1).getPaket();

            output.append(String.format(
                    format,
                    i,
                    paket != null ? paket.getEmpfaenger() : "leer",
                    paket != null ? paket.getSendungsnummer() : "-"
            ));
        }

        return output.toString();
    }

    public String handleInsert(String recipient) {
        Paket neuesPaket = new Paket(recipient, this.nextSendungsNummer++);
        try {
            this.paketstation.einlagernPaket(neuesPaket);
            return "Paket wurde eingelagert.";
        } catch (Exception e) {
            return "Kein freies Fach verfügbar";
        }
    }

    public String handleRemove(String recipient) {
        try {
            StringBuilder output = new StringBuilder();
            for (Paket paket: this.paketEntnehmenIo(recipient)) {
                output.append(String.format("Paket mit der Nr. %s wurde von %s entnommen\n\n", paket.getSendungsnummer(), paket.getEmpfaenger()));
            }
            return output.toString();
        } catch (Exception exception) {
            return "Es konnten keine passenden Pakete gefunden werden.";
        }
    }
    private ArrayList<Paket> paketEntnehmenIo(String eingabeKunde) throws Exception {
        try {
            int sendungsnummer = Integer.parseInt(eingabeKunde);
            return this.paketstation.entnehmen(sendungsnummer);
        } catch (NumberFormatException exception) {
            return this.paketstation.entnehmen(eingabeKunde);
        }
    }
}
