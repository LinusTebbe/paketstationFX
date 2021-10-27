package dev.tebbe.PaketstationFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Fenster extends Application {

    private Presenter presenter;

    private TextField recipientInput;

    private TextArea textAreaOutput;

    private Label labelStatusMessage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.presenter = new Presenter();
        primaryStage.setTitle("Paketstation");

        StackPane root = new StackPane();
        Scene myScene = new Scene(root, 600, 400);
        BorderPane borderPane = new BorderPane();

        this.textAreaOutput = new TextArea();
        this.textAreaOutput.setEditable(false);
        this.labelStatusMessage = new Label();

        borderPane.setTop(getTopHBox());
        borderPane.setCenter(this.textAreaOutput);
        borderPane.setBottom(this.labelStatusMessage);

        root.getChildren().add(borderPane);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public HBox getTopHBox() {
        HBox hBox = new HBox();

        Label recipientLabel = new Label("Empfänger");

        this.recipientInput = new TextField();
        HBox.setHgrow(this.recipientInput, Priority.ALWAYS);

        this.recipientInput.setMaxWidth(Double.MAX_VALUE);

        hBox.getChildren().addAll(recipientLabel, this.recipientInput, getButtonVBox());
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(Double.MAX_VALUE);

        return hBox;
    }

    public VBox getButtonVBox() {
        VBox vBox = new VBox();

        Button buttonInsert = new Button("Einfügen");
        buttonInsert.setMaxWidth(Double.MAX_VALUE);
        buttonInsert.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                mouseEvent -> this.applyStyledTextToLabelStatusMessage(
                        this.presenter.handleInsert(this.recipientInput.getText())
                )
        );

        Button buttonRemove = new Button("Entnehmen");
        buttonRemove.setMaxWidth(Double.MAX_VALUE);
        buttonRemove.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                mouseEvent -> this.applyStyledTextToLabelStatusMessage(
                        this.presenter.handleRemove(this.recipientInput.getText())
                )
        );

        Button buttonList = new Button("Liste");
        buttonList.setMaxWidth(Double.MAX_VALUE);
        buttonList.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                mouseEvent -> this.textAreaOutput.setText(this.presenter.handleList().getText())
        );

        Button buttonExit = new Button("Abbrechen");
        buttonExit.setMaxWidth(Double.MAX_VALUE);
        buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Platform.exit());

        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(buttonInsert, buttonRemove, buttonList, buttonExit);

        return vBox;
    }

    private void applyStyledTextToLabelStatusMessage(StyledText styledText) {
        this.labelStatusMessage.setText(styledText.getText());
        this.labelStatusMessage.setTextFill(styledText.getColor());
    }
}