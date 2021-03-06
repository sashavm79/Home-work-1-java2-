package circles;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {

    long lastFrameTime;
    private final MainCircles controller;
    Background background = new Background(); /* создание объекта класса Background, задающего цвет канвы */


    MainCanvas(MainCircles controller) {
        lastFrameTime = System.nanoTime();
        this.controller = controller;

        controller.mouseClick();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(background.getColor()); /* Отрисовка цвета канвы. Задается классом Background и запрашивается
        через getColor */

        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        controller.onCanvasRepainted(this, g, deltaTime);

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastFrameTime = currentTime;
        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }

}