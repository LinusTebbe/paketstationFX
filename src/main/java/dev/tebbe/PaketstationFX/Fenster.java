package dev.tebbe.PaketstationFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Fenster extends Application {

    private Presenter presenter;

    private TextField myTextFieldEmpfaenger;

    private TextArea myTextAreaListe;

    private Label myLabelMessage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.presenter = new Presenter();
        primaryStage.setTitle("Paketstation");

        StackPane root = new StackPane();
        Scene myScene = new Scene(root, 600, 400);
        BorderPane myWindow = new BorderPane();
        this.myTextAreaListe = new TextArea();
        this.myLabelMessage = new Label();

        myWindow.setTop(addMyBorderPane());
        myWindow.setCenter(this.myTextAreaListe);
        myWindow.setBottom(this.myLabelMessage);

        root.getChildren().add(myWindow);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public BorderPane addMyBorderPane() {

        BorderPane myBorderPaneOben = new BorderPane();
        Label myLabelEmpfaenger = new Label("Empfänger");
        this.myTextFieldEmpfaenger = new TextField();

        myBorderPaneOben.setCenter(this.myTextFieldEmpfaenger);
        myBorderPaneOben.setLeft(myLabelEmpfaenger);
        myBorderPaneOben.setRight(addGridPaneButton());

        return myBorderPaneOben;
    }

    public VBox addGridPaneButton() {
        VBox vBox = new VBox();

        Button myButtonEinfuegen = new Button("Einfügen");
        myButtonEinfuegen.setMaxWidth(Double.MAX_VALUE);
        myButtonEinfuegen.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                mouseEvent -> this.applyStyledTextToLabel(
                        this.presenter.handleInsert(this.myTextFieldEmpfaenger.getText())
                )
        );

        Button myButtonEntnehmen = new Button("Entnehmen");
        myButtonEntnehmen.setMaxWidth(Double.MAX_VALUE);
        myButtonEntnehmen.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                mouseEvent -> this.applyStyledTextToLabel(
                        this.presenter.handleRemove(this.myTextFieldEmpfaenger.getText())
                )
        );

        Button myButtonListe = new Button("Liste");
        myButtonListe.setMaxWidth(Double.MAX_VALUE);
        myButtonListe.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                mouseEvent -> this.myTextAreaListe.setText(this.presenter.handleList().getText())
        );

        Button myButtonEnde = new Button("Abbrechen");
        myButtonEnde.setMaxWidth(Double.MAX_VALUE);
        myButtonEnde.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Platform.exit());

        vBox.getChildren().addAll(myButtonEinfuegen, myButtonEntnehmen, myButtonListe, myButtonEnde);

        return vBox;
    }

    private void applyStyledTextToLabel(StyledText styledText) {
        this.myLabelMessage.setText(styledText.getText());
        this.myLabelMessage.setTextFill(styledText.getColor());
    }
}