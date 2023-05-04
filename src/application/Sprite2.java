package application;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.*;


public class Sprite2
{
    private Image image;
    public double positionX;
    public double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite2()
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

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void moveNext(double x, double y, boolean ok) {

        double addX , addY;
        addX = 10;
        addY = 10;
        //if(ok == false) addX *= -1;
        x += addX;
        y += addY;
        double hudaX = 2;

        if(Math.abs(x - positionX) <= 0.001) {
            setPosition(x , y);
        }
        if(Math.abs(positionX - x) <= 0.01) {
            positionX = x;
            positionY = y;
            return;
        }
        double slope = (y-positionY)/Math.abs(x-positionX);
        //System.out.println(slope);

        if(x < positionX) hudaX *= -1;
        double hudaY = Math.abs(hudaX)*(slope);
        setPosition(hudaX+positionX, hudaY+positionY);
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

    public boolean intersects(Sprite2 s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

}