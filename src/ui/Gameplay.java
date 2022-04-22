package ui;

import dominio.Bola;
import dominio.Brick;
import dominio.MapGenerator;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.Timer;

public class Gameplay extends JPanel {
	private boolean play = false;
	private int score = 0;

	private int totalBricks;

	private Timer timer;

	private int playerX = 300;

	private int ballXdir = 0;
	private int ballYdir = 0;

	private MapGenerator map;
	//private Nivel1 nivel;
	private Bola b;
	private int numNivel = 1;

	private int posLineaX = 350;
	private int posLineaY = 700;

	private static int VELOCIDADPALA = 25;

	private int lives;
	private JVentana ventana;

	public Gameplay(JVentana jventana) {
		ventana = jventana;
		//numNivel = 8; //Para testear niveles
		map = new MapGenerator(numNivel);
		setBackground(Color.gray);
		totalBricks = map.getTotalBricks();
		lives = 3;
		//nivel = new Nivel1();
		b = new Bola();
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (play) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (playerX >= 590) {
							playerX = 590;
						} else {
							playerX += VELOCIDADPALA;
							repaint();
						}
					}

					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (playerX <= 10) {
							playerX = 10;
						} else {
							playerX -= VELOCIDADPALA;
							repaint();
						}
					}

				}
				if (!play) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (posLineaX >= 400) {
							posLineaX = 400;
						} else {
							posLineaX += 25;
						}
					}

					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (posLineaX <= 300) {
							posLineaX = 300;
						} else {
							posLineaX -= 25;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (b.getPosX() == 345 && b.getPosY() == 740) {
						play = true;
						ballXdir = (posLineaX - 350) / 25;
						ballYdir = -2;
					}
				}
			}
		});

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(6, e -> {

			updateBola();
			cambioNivel();
			gameOver();
			/*this.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										if (play) {
											if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
												if (playerX >= 590) {
													playerX = 590;
												} else {
													playerX += 1;
												}
											}

											if (e.getKeyCode() == KeyEvent.VK_LEFT) {
												if (playerX <= 10) {
													playerX = 10;
												} else {
													playerX -= 1;
												}
											}

										}
									}
								});*/


			repaint();
		});
		timer.start();
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//pintar la ralla que apunta
		if (b.getPosX() == 345 && b.getPosY() == 740) {
			if (!play) {
				g.setColor(Color.RED);
				g.drawLine(350, 745, posLineaX, posLineaY);
			}
		}

		// la bola
		b.pintar(g);


		// dibujando el mapa
		map.draw((Graphics2D) g);

		// bordes
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 10, 792);
		g.fillRect(0, 0, 692, 10);
		g.fillRect(692, 0, 10, 792);

		// ----- panel derecho --------
		g.setColor(Color.black);
		g.fillRect(700, 1, 1000, 792);
		g.setColor(Color.GREEN);
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("Score", 770, 100);

		//Vamos a centrar el resultado
		FontMetrics fm = g.getFontMetrics();
		int ancho = fm.stringWidth(String.valueOf(score));
		g.setColor(Color.blue);
		g.drawString(String.valueOf(score), 850 - ancho / 2, 170);

		//Centramos el nombre del nivel
		int anchoNivel = fm.stringWidth("Level " + numNivel);
		g.setColor(Color.green);
		g.drawString("Level " + numNivel, 850 - anchoNivel / 2, 300);

		//Vidas
		g.setColor(Color.GREEN);
		g.drawString("Lives", 770, 400);
		//Vamos a centrar el resultado
		int anchoLife = fm.stringWidth(String.valueOf(lives));
		g.setColor(Color.blue);
		g.drawString(String.valueOf(lives), 850 - anchoLife / 2, 470);

		//Controles
		if (play){
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.setColor(Color.CYAN);
			g.drawString("Muévete con",770,550);
			g.drawString("las flechas",770,600);

		}else{
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.setColor(Color.CYAN);
			g.drawString("Apunta con",770,550);
			g.drawString("las flechas",770,600);
			g.drawString("Dispara con ",770,650);
			g.drawString("el espacio ",770,700);
		}

		// la pala
		g.setColor(Color.blue);
		g.fillRect(playerX, 750, 100, 8);

	}

	public void updateBola() {
		if (play) {
			Rectangle ballRect = new Rectangle((int) b.getPosX(), b.getPosY(), b.getRadio(), b.getRadio());
			if (ballRect.intersects(new Rectangle(playerX, 750, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir--;
			} else if (ballRect.intersects(new Rectangle(playerX + 70, 750, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir++;
			} else if (ballRect.intersects(new Rectangle(playerX + 30, 750, 40, 8))) {
				ballYdir = -ballYdir;
			}

			// check map collision with the ball
			A:
			for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j].getValor() != 0) {
						//scores++;
						Brick ladrillo = map.map[i][j];

						Rectangle brickRect = new Rectangle(ladrillo.getX(), ladrillo.getY(), Brick.WIDTH, Brick.HEIGHT);
						//Rectangle ballRect = new Rectangle(b.getPosX(), ballposY, 10, 10);


						if (ballRect.intersects(brickRect)) {
							Rectangle inter = ballRect.intersection(brickRect);

							if (inter.height == 2) {
								ballYdir = -ballYdir;
							} else {
								ballXdir = -ballXdir;
							}

							int valBrick = ladrillo.getValor();
							ladrillo.setValor(valBrick - 1);
							if (ladrillo.getValor() >=0){
								score += 5;
							}

							totalBricks = map.getTotalBricks();

							break A;
						}
					}
				}
			}

			if (b.getPosX() <= 11) {
				ballXdir = -ballXdir;
			}
			if (b.getPosY() < 10) {
				ballYdir = -ballYdir;
			}
			if (b.getPosX() > 680) {
				ballXdir = -ballXdir;
			}

			b.setPosX(b.getPosX() + ballXdir);
			b.setPosY(b.getPosY() + ballYdir);


		}

	}

	public void restart() {
		b = new Bola();
		play = false;
		posLineaX = 350;
		posLineaY = 700;
		ballXdir = 0;
		ballYdir = 0;
		playerX = 300;
	}

	public void gameOver() {
		if (b.getPosY() > 770) {
			play = false;
			if (lives <= 1) {
				ventana.addResultado(score);
				lives = 0;
				ballXdir = 0;
				ballYdir = 0;
				Object[] opciones = {"Aceptar", "Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(this, "¿Quiere volver a jugar?", "Game Over",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, opciones, "Sí");
				if (eleccion == JOptionPane.YES_OPTION) {
					restart();
					lives = 3;
					score = 0;
					numNivel = 0;
					map = new MapGenerator(numNivel);
					totalBricks = map.getTotalBricks();
					repaint();
				} else {
					ventana.close();
				}

			} else {
				lives--;
				restart();
			}
		}

	}
	private void cambioNivel() {
		if (totalBricks <= 0) {
			//if si es el nivel último sacar un mensaje de que ha ganado
			if (numNivel == 8) {
				ventana.addResultado(score);
				play = false;
				ballXdir = 0;
				ballYdir = 0;
				Object[] opciones = {"Aceptar", "Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(this, "¿Quiere volver a jugar?", "Felicidades, ha ganado",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, opciones, "Sí");
				if (eleccion == JOptionPane.YES_OPTION) {

					restart();
					lives = 3;
					score = 0;
					numNivel = 0;
					map = new MapGenerator(numNivel);
					totalBricks = map.getTotalBricks();
					repaint();
				} else {
					ventana.close();
				}



			} else {
				numNivel++;
				map = new MapGenerator(numNivel);
				if (numNivel ==4 || numNivel == 7){
					lives ++;
				}

				totalBricks = map.getTotalBricks();
				restart();

			}
		}
	}
}
