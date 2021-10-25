package dev.tebbe.PaketstationFX;

public class Paket {
    private final String recipient;

    private final int trackingNo;

    public Paket(
            String recipient,
            int trackingNo
    ) {
        this.recipient = recipient;
        this.trackingNo = trackingNo;
    }

    public String getRecipient() {
        return recipient;
    }

    public int getTrackingNo() {
        return trackingNo;
    }
}
