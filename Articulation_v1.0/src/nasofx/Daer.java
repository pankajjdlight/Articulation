/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Daer extends Application {

    BorderPane pane;
    Rectangle rect;
    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectY = new SimpleDoubleProperty();

    @Override
    public void start(Stage stage) {

        pane = new BorderPane();
        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);

        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);

        rect = getNewRectangle();
        rect.widthProperty().bind(rectX.subtract(rectinitX));
        rect.heightProperty().bind(rectY.subtract(rectinitY));
        pane.getChildren().add(rect);
        stage.show();
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {

            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                rect.setX(mouseEvent.getX());
                rect.setY(mouseEvent.getY());
                rectinitX.set(mouseEvent.getX());
                rectinitY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                rectX.set(mouseEvent.getX());
                rectY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                // Clone the rectangle
                Rectangle r = getNewRectangle();
                r.setX(rect.getX());
                r.setY(rect.getY());
                r.setWidth(rect.getWidth());
                r.setHeight(rect.getHeight());
                pane.getChildren().add(r);

                // Hide the rectangle
                rectX.set(0);
                rectY.set(0);
            }
        }
    };

    private Rectangle getNewRectangle() {
        Rectangle r = new Rectangle();
        r.setFill(Color.web("blue", 0.1));
        r.setStroke(Color.BLUE);
        r.setArcHeight(40);
        r.setArcWidth(40);
        return r;
    }

    public static void main(String[] args) {
        launch(args);
    }
}