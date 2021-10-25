package dev.tebbe.PaketstationFX;

import java.util.ArrayList;

public class Packstation {
    private final Paket[] packages = new Paket[9];

    public void paketEinlagern(Paket paket) throws PackagePresentException {
        for (int i = 0; i < this.packages.length; i++) {
            if(this.packages[i] == null) {
                this.packages[i] = paket;
                return;
            }
        }

        throw new PackagePresentException();
    }

    public ArrayList<Paket> paketEntnehmen(int position) throws PackageAbsentException {
        if(
            this.packages.length < position ||
            this.packages[position - 1] == null
        ) {
            throw new PackageAbsentException();
        }

        ArrayList<Paket> removedPackages = new ArrayList<>();
        removedPackages.add(this.packages[position - 1]);
        this.packages[position - 1] = null;

        return removedPackages;
    }

    public ArrayList<Paket> paketeEntnehmen(String recipient) throws PackageAbsentException {
        ArrayList<Paket> removedPackages = new ArrayList<>();
        for (int i = 0; i < this.packages.length; i++) {
            Paket paket = this.packages[i];
            if(
                paket != null &&
                paket.getRecipient().equalsIgnoreCase(recipient)
            ) {
                removedPackages.add(paket);
                this.packages[i] = null;
            }
        }
        if (removedPackages.size() == 0) {
            throw new PackageAbsentException();
        }
        return removedPackages;
    }

    public Paket[] paketeListen() {
        return this.packages;
    }
}
