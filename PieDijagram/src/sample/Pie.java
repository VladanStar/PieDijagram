package sample;
import java.awt.BasicStroke;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Pie extends Canvas {

    Color[] colors = {Color.DARKGRAY, Color.GREENYELLOW, Color.BLUEVIOLET, Color.CRIMSON};
    double[] items = {25, 25, 40, 10};

    public Pie() {
        super();
        setWidth(600);
        setHeight(600);
        draw();
    }

    public void draw() {
        GraphicsContext gc2d = this.getGraphicsContext2D();
        clearCanvas(gc2d);
        figureDegrees();

        // Crtanje kruznice pie chart-a crnom bojom
        gc2d.strokeOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);

        double[] degrees = figureDegrees();
        int startDegree = 0;
        for (int i = 0; i < degrees.length; i++) {
            gc2d.setFill(colors[i]);
            gc2d.fillArc(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2, startDegree, degrees[i], ArcType.ROUND);
            startDegree += degrees[i];
        }
    }

    public double[] figureDegrees() {
        double[] degrees = new double[4];
        for (int i = 0; i < items.length; i++) {
            degrees[i] = (360 * items[i]) / 100;
        }
        return degrees;
    }

    public Color[] getColors() {
        return colors;
    }

    public void setItems(double[] items) {
        this.items = items;
        figureDegrees();
    }

    public void clearCanvas(GraphicsContext gc2d) {
        //Brisanje canvas-a kod ponovnog crtanja. Ukoliko ne postoji iscrtavace se pie chart preko prethodnog
        gc2d.clearRect(0, 0, getWidth(), getHeight());

    }
}