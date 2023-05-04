package application;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.*;


//import java.awt.*;
//import java.awt.geom.Rectangle2D;

public class Sprite3
{
    public Image image;
    public double positionX;
    public double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    public double speed = 3 ;
    private int start = 1;
    private int cur = 1;
    public int dam = 0;
    public int lav = 0;
    public int verdict = 1;
    public double ex = 35;

    boolean movx = true;
    boolean movu = false;
    boolean movd = false;
    boolean sesh = false;

    public Sprite3()
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

    public boolean chk(double x, double y) {
        double l = x-ex;
        double r = x+speed-ex;
        if(positionX > l && positionX <= r) {
            return true;
        }
        return false;
    }



    public void reset() {
        lav = 0;
        dam = 0;
    }
    public void setSpeed(double i) {
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
        positionX = x;
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

        if(movx == true) {
            positionX += speed;
        } else if (movd == true) {
            positionY += speed;
        } else {
            positionY -= speed;
        }

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

    public void render(GraphicsContext gc, boolean dibo)
    {
        //System.out.println("sex");
        gc.drawImage( image, positionX, positionY );
        if(dibo == false) {
            String pointsText1 = "Strength :  " + dam;
            gc.fillText( pointsText1, 40 , 50 );
            gc.strokeText( pointsText1, 40, 50 );

            String pointsTexts2 = "Damage :  " + lav;
            gc.fillText( pointsTexts2, 40 , 80 );
            gc.strokeText( pointsTexts2, 40, 80 );
            return;
        }
        String pointsText = "Strength :  " + dam;
        gc.fillText( pointsText, positionX , positionY + 160 );
        gc.strokeText( pointsText, positionX, positionY + 160 );

        String pointsTexts = "Damage :  " + lav;
        gc.fillText( pointsTexts, positionX , positionY + 190 );
        gc.strokeText( pointsTexts, positionX, positionY + 190 );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite3 s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

}