package application;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.*;


//import java.awt.*;
//import java.awt.geom.Rectangle2D;

public class Sprite1
{
    private Image image;
    public double positionX;
    public double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private int speed = 1 ;
    private int start = 1;
    private int cur = 1;

    public Sprite1()
    {
        positionX = 0;
        positionY = 0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }



    public void setSpeed(int i) {
        speed = i;
    }

    public void setStart(int i) { start = i; }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x-1000;
        positionY = y;
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }
    public Image getImage() {
        return image;
    }


    public void moveNext() {
        cur++;
        if(cur < start) return;

        positionX += speed;

    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite1 s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

}