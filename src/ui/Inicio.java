package ui;

import ui.JVentana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JPanel {
    private Image fondo;
    private JButton jugar;
    private JVentana jventana;
    private JLabel labelNombre;
    private JTextField txtNombre;
    private JLabel labelHigh;


    public Inicio(JVentana ventana){
        jventana = ventana;


        this.setPreferredSize(new Dimension(1000,800));
        this.setBackground(new Color(4, 12, 60));
        fondo = Toolkit.getDefaultToolkit().getImage("./resources/portada.jpg");
        fondo = fondo.getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);

        //Boton jugar
        this.setLayout(null);
        jugar = new JButton("PLAY NOW");
        jugar.setFont(new Font("Goudy Stout",Font.BOLD,50));
        jugar.setBackground(new Color(136, 135, 135, 38));
        jugar.setForeground(new Color(0x8114EE));
        this.add(jugar);
        jugar.setBounds(300,500,350,60);

        labelNombre = new JLabel("Player: ");
        labelNombre.setFont(new Font("Monospaced",Font.BOLD,25));
        labelNombre.setBounds(330,250,150,50);
        labelNombre.setForeground(new Color(0x78F30B));
        txtNombre = new JTextField(5);
        txtNombre.setBounds(470,260,200,30);
        txtNombre.setFont(new Font("Monospaced",Font.BOLD,25));

        labelHigh = new JLabel("Highscore" + " " + jventana.getFirst());
        labelHigh.setFont(new Font("Monospaced",Font.BOLD,25));
        labelHigh.setVerticalAlignment(SwingConstants.CENTER);
        labelHigh.setBounds(300,100,400,50);
        labelHigh.setForeground(new Color(0x78F30B));


        add(txtNombre);
        add(labelNombre);
        add(labelHigh);

        jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNombre.getText().length() == 0){
                    JOptionPane.showMessageDialog(ventana, "Introduce un nombre para jugar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else {
                    setVisible(false);
                    jventana.addPlayer(txtNombre.getText());
                    System.out.println(txtNombre.getText());
                    jventana.IniciarJuego();
                }
            }
        });

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo,0, 75,this);
    }
}
