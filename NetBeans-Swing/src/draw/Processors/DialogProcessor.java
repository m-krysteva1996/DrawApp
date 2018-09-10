/**
 * DialogProcessor.java
 */

package draw.Processors;

import java.awt.*;

/**
 * Класът, който ще бъде използван при управляване на диалога.
 */
public class DialogProcessor extends DisplayProcessor {
    /**
     * Избран елемент.
     */
    private draw.Model.Shape selection;
    
    // Не е необходим в реализацията на Java
    ///**
    // * Дали в момента диалога е в състояние на "влачене" на избрания елемент.
    // */
    //private boolean isDragging;
    
    /**
     * Последна позиция на мишката при "влачене".
     * Използва се за определяне на вектора на транслация.
     */
    private Point lastLocation;

    public DialogProcessor() {
    }

    /**
     * Добавя примитив - правоъгълник на произволно място върху клиентската област.
     */
    public void AddRandomRectangle() {
        int x = 100 + (int)Math.round(Math.random()*900);
        int y = 100 + (int)Math.round(Math.random()*500);
        draw.Model.RectangleShape rect = new draw.Model.RectangleShape(new Rectangle(x,y,100,200));
        rect.setFillColor(java.awt.Color.WHITE);
        shapeList.add(rect);
    }

    /**
     * Проверява дали дадена точка е в елемента.
     * Обхожда в ред обратен на визуализацията с цел намиране на
     * "най-горния" елемент т.е. този който виждаме под мишката.
     * @param point - Указана точка.
     * @return Елемента на изображението, на който принадлежи дадената точка.
     */
    public draw.Model.Shape ContainsPoint(Point point) {
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            if (shapeList.get(i).Contains(point)) {
                shapeList.get(i).setFillColor(Color.RED);
                return shapeList.get(i);
            }
        }
        return null;
    }

    /**
     * Транслация на избраният елемент на вектор определен от <paramref name="p>p< paramref>
     * @param p - Вектор на транслация.
     */
    public void TranslateTo(Point p) {
        if ( selection != null ) {
            selection.setLocation(new Point(selection.getLocation().x + p.x - lastLocation.x, selection.getLocation().y + p.y - lastLocation.y));
            lastLocation = p;
        }
    }

    //
    
    public draw.Model.Shape getSelection() {
        return selection;
    }

    public void setSelection(draw.Model.Shape value) {
        selection = value;
    }

    //public boolean getIsDragging() {
    //    return isDragging;
    //}

    //public void setIsDragging(boolean value) {
    //    isDragging = value;
    //}

    public Point getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Point value) {
        lastLocation = value;
    }
}
