package dev.tebbe.PaketstationFX;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Presenter {

    private final Packstation packstation;

    private int nextTrackingNumber = 1;

    public Presenter() {
        this.packstation = new Packstation();
    }

    public StyledText handleList() {
        StringBuilder stringBuilder = new StringBuilder();
        String format = "%4s\t%-15s\t%-5s\n";
        stringBuilder.append(
            String.format(
                format,
                "Fach",
                "Empfänger",
                "Sendungsnummer"
            )
        );
        Paket[] pakete = this.packstation.paketeListen();
        for (int i = 0; i < pakete.length; i++) {
            Paket paket = pakete[i];
            stringBuilder.append(
                    String.format(
                        format,
                        i + 1,
                        paket != null ? paket.getRecipient() : "leer",
                        paket != null ? paket.getTrackingNo() : "-"
                    )
            );
        }

        return new StyledText(stringBuilder.toString());
    }

    public StyledText handleInsert(String recipient) {
        Paket paket = new Paket(
                recipient,
                this.nextTrackingNumber++
        );
        try {
            this.packstation.paketEinlagern(paket);
            return new StyledText("Paket wurde eingelagert.", StyledText.SUCCESS_COLOR);
        } catch (PackagePresentException e) {
            return new StyledText("Keine freien Fächer verfügbar", StyledText.ERROR_COLOR);
        }
    }

    public StyledText handleRemove(String rawInput) {
        try {
            ArrayList<Paket> packages;
            try {
                int compartment = Integer.parseInt(rawInput);
                packages = this.packstation.paketEntnehmen(compartment);
            } catch (NumberFormatException exception) {
                packages = this.packstation.paketeEntnehmen(rawInput);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Paket paket: packages) {
                stringBuilder.append(
                        String.format(
                            "Paket mit der Nr. %s von %s entnommen!\n",
                            paket.getTrackingNo(),
                            paket.getRecipient()
                        )
                );
            }
            return new StyledText(stringBuilder.toString(), StyledText.SUCCESS_COLOR);
        } catch (PackageAbsentException exception) {
            return new StyledText("Keine Pakete gefunden", StyledText.ERROR_COLOR);
        }
    }
}
