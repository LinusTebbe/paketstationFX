package dev.tebbe.PaketstationFX;


import java.util.ArrayList;

public class Paketstation {

    private static final int ANZAHL_FAECHER = 9;

    private final ArrayList<Fach> faecher = new ArrayList<>();

    public Paketstation() {
        for (int i = 0; i < Paketstation.ANZAHL_FAECHER; i++) {
            this.faecher.add(new Fach());
        }
    }

    public void einlagernPaket(Paket p) throws Exception {
        Fach fach = this.getFreiesFach();
        fach.einlagernPaket(p);
    }

    public ArrayList<Paket> entnehmen(int nr) throws Exception {
        ArrayList<Paket> pakete = new ArrayList<>();
        for (Fach fach: this.getFaecher()) {
            if (fach.getPaket().getSendungsnummer() == nr) {
                pakete.add(fach.getPaket());
                fach.fachLeeren();
                return pakete;
            }
        }
        throw new Exception(String.format("Kein Paket mit Nr. %s gefunden", nr));
    }

    public ArrayList<Paket> entnehmen(String empfaenger) throws Exception {
        ArrayList<Paket> pakete = new ArrayList<>();
        for (Fach fach: this.getFaecher()) {
            if (fach.getPaket().getEmpfaenger().equalsIgnoreCase(empfaenger)) {
                pakete.add(fach.getPaket());
                fach.fachLeeren();
            }
        }
        if (pakete.size() == 0) {
            throw new Exception(String.format("Kein Paket mit Nr. %s gefunden", empfaenger));
        }
        return pakete;
    }

    public ArrayList<Fach> getFaecher() {
        return this.faecher;
    }

    private Fach getFreiesFach() throws Exception {
        for (Fach fach: this.faecher) {
            if(fach.isFachfrei()) {
                return fach;
            }
        }

        throw new Exception("Es konnte kein freies Fach gefunden werden");
    }
}
