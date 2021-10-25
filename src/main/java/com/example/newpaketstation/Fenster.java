package com.example.newpaketstation;

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
        Scene myScene = new Scene(root, 300, 250);
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

    public GridPane addGridPaneButton() {

        GridPane myGridPaneButton = new GridPane();

        Button myButtonEinfuegen = new Button("Einfügen");
        myButtonEinfuegen.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            this.myLabelMessage.setText(this.presenter.handleInsert(this.myTextFieldEmpfaenger.getText()));
        });

        Button myButtonEntnehmen = new Button("Entnehmen");
        myButtonEntnehmen.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            this.myLabelMessage.setText(this.presenter.handleRemove(this.myTextFieldEmpfaenger.getText()));
        });

        Button myButtonListe = new Button("Liste");
        myButtonListe.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            this.myTextAreaListe.setText(this.presenter.handleList());
        });

        Button myButtonEnde = new Button("Abbrechen");
        myButtonEnde.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Platform.exit();
        });

        myGridPaneButton.add(myButtonEinfuegen,1,1);
        myGridPaneButton.add(myButtonEntnehmen,1,2);
        myGridPaneButton.add(myButtonListe,1,3);
        myGridPaneButton.add(myButtonEnde,1,4);

        return myGridPaneButton;
    }
}