package dominio;

import java.awt.*;

public class Brick {
    int x;
    int y;
    int valor;
    public static int WIDTH = 97;
    public static int HEIGHT = 46;
    Color color;

    public Brick(int x, int y){
        this.x = x;
        this.y = y;
        //this.color = this.setColor();
        this.valor = 0;
    }

    private void getColor() {
        if (valor == 1)
            this.color=new Color(0xFFFF0000, true);
        else if (valor == 2)
            this.color=new Color(0xBFBE0042, true);
        else if (valor == 3)
            this.color=new Color(0xFF4000BE, true);
        else if(valor == 4)
            this.color = new Color(0xFF0000FF, true);
        else if(valor < 0)
            this.color = Color.black;

    }

    public int getValor(){
        return valor;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public void pintar(java.awt.Graphics2D g)
    {
        getColor();
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.black);
        g.drawRect(x, y, WIDTH, HEIGHT);
    }
}
