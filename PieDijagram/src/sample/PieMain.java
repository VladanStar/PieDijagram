package sample;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PieMain extends Application {

    private final Pie pie = new Pie();
    private final TextField[] tfList ={new TextField("25"), new TextField("25"), new TextField("40"), new TextField("10")};

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        StackPane sp = new StackPane();
        Button update = new Button("Update");
        sp.getChildren().add(update);

        HBox box = new HBox(20);

        addFooter(box);
        AnchorPane pieCont = new AnchorPane();
        pieCont.getChildren().add(pie);

        root.setCenter(pieCont);
        root.setTop(sp);
        root.setBottom(box);
        Scene scene = new Scene(root, 600, 680);

        primaryStage.setTitle("Pie demo");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.widthProperty().addListener(new javafx.beans.value.ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pie.setWidth((double) newValue);
                pie.draw();
            }

        });
        scene.heightProperty().addListener(new javafx.beans.value.ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pie.setHeight((double) newValue);
                pie.draw();
            }

        });

        update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    double[] tmpItems = new double[]{Double.parseDouble(tfList[0].getText()), Double.parseDouble(tfList[1].getText()),
                            Double.parseDouble(tfList[2].getText()), Double.parseDouble(tfList[3].getText())};
                    if (completePercent(tmpItems)) {
                        pie.setItems(tmpItems);
                        pie.draw();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ukupan broj mora biti 100. Unesite ponovo. ");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ne smete ostaviti prazno polje. ");
                }

            }
        });

    }

    public boolean completePercent(double[] items) {
        double percent = 0.0d;
        for (int i = 0; i < items.length; i++) {
            percent += items[i];
        }
        return (percent == 100.0d);
    }

    public void addFooter(HBox box) {
        for (int i = 0; i < tfList.length; i++) {
            Circle circle = new Circle();
            circle.setFill(pie.getColors()[i]);
            circle.setRadius(20.0f);
            box.getChildren().add(circle);
            tfList[i].setMinHeight(40);
            box.getChildren().add(tfList[i]);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}