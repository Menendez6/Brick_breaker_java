package dominio;

import java.awt.*;

public class Bola {
    int posX;
    int posY;
    int radio = 10;
    public Bola(){
        this.posX = 345;
        this.posY= 740;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getRadio(){
        return radio;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void pintar(java.awt.Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(posX, posY, radio, radio);
        g.drawOval(posX, posY, radio, radio);
    }
}
