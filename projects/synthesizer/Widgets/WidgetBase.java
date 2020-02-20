package Synthesizer.Widgets;

import Synthesizer.AudioClip;
import Synthesizer.AudioComponent;
import Synthesizer.Cable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static javafx.scene.layout.BorderPane.*;


public abstract class WidgetBase {

    //private Pane pane;
    private Label waveType;
    protected BorderPane widgetBox;
    protected AudioComponent ac;
    private Circle inputJack;
    private Circle outputJack;
    private double mouseXStart;
    private double mouseYStart;
    private double xChange;
    private double yChange;
    private HBox centerbox;
    protected ArrayList<Cable> cableArray;

    //constructor
    public WidgetBase(String waveTypeName) {
        cableArray = new ArrayList<>();
        widgetBox = new BorderPane();
        widgetBox.setPrefSize(300, 50);
        waveType = new Label(waveTypeName);
        widgetBox.setTop(waveType);
        widgetBox.setAlignment(waveType, Pos.CENTER);
        widgetBox.setCenter(centerbox);
        widgetBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid outside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;" +
                "-fx-background-color: WHITE"
        );

        widgetBox.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mouseXStart = mouseEvent.getX();
                        mouseYStart = mouseEvent.getY();
                    }
                });

        widgetBox.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        xChange = mouseEvent.getX() - mouseXStart;
                        yChange = mouseEvent.getY() - mouseYStart;
                       if (!outputJack.isPressed()) {
                            widgetBox.relocate(widgetBox.getLayoutX() + xChange, widgetBox.getLayoutY() + yChange);
                            if(cableArray != null){
                            for (Cable c : cableArray){
                                c.updateLine();}
                            }
                        }
                    }
                });
    }

    protected void getInputNode(){
        inputJack = new Circle(10, Color.FIREBRICK);
        widgetBox.setLeft(inputJack);
        widgetBox.setAlignment(inputJack, Pos.CENTER_LEFT);
    }

    protected void getOutputNode(){
        outputJack = new Circle(10, Color.NAVY);
        widgetBox.setRight(outputJack);
        widgetBox.setAlignment(outputJack, Pos.CENTER_RIGHT);
    }

    protected void setDisplayGrid(Node n, Node n2){
        widgetBox.setCenter(n);
        widgetBox.setBottom(n2);
    }

    public void setAudioComponent(AudioComponent ac1){
        ac = ac1;
    }

    public AudioComponent getAudioComponent(){
        return ac;
    }

    //need getter methods for circle locations
    public Circle getInputJack(){
        return inputJack;
    }

    public Circle getOutputJack(){
        return outputJack;
    }

    public void addCable(Cable c){
        cableArray.add(c);
    }

}
