package dev.tebbe.PaketstationFX;

import javafx.scene.paint.Color;

public class StyledText {

    public static Color DEFAULT_COLOR = Color.BLACK;

    public static Color SUCCESS_COLOR = Color.LIMEGREEN;

    public static Color ERROR_COLOR = Color.RED;

    private final String text;
    private final Color color;

    public StyledText(
            String text
    ) {
        this(text, DEFAULT_COLOR);
    }

    public StyledText (
            String text,
            Color color
    ) {
        this.text = text;
        this.color = color;
    }


    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
