package ui;

import io.IOResultados;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// src,dist,resources,lib,doc Brickbreaker.jar

import javax.swing.*;


public class JVentana extends JFrame {
	//ui.Inicio init;
	private Inicio principal;
	private Gameplay gamePlay;
	HashMap<String,Integer> players;
	IOResultados IO;
	String jugador;


	public static void main(String[] args) {
		new JVentana();
	}

	public JVentana(){
		IO = new IOResultados();
		players = IO.leerResultado();
		if (players == null){
			players = new HashMap<>();
			players.put("Nadie",0);
		}

		principal = new Inicio(this);
		gamePlay = new Gameplay(this);


		setBounds(0, 0, 1000, 800);
		setTitle("dominio.Brick breaker");
		setResizable(false);
		//setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gamePlay.setVisible(false);
		add(gamePlay);
		add(principal);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();

			}
		});
		setVisible(true);

		//this.add(principal);
	}

	public void IniciarJuego(){
		this.add(gamePlay);
		System.out.println(players);
	}

	public void addPlayer(String player){
		jugador = player;
		if (players.containsKey(jugador) == false) {
			players.put(jugador,0);
		}
	}

	public void addResultado(int score){
		if (players.get(jugador) < score){
			players.put(jugador,score);
		}
		players = (HashMap<String, Integer>) sortByValue(players);
	}


	public static Map<String, Integer> sortByValue(Map<String, Integer> wordCounts) {
		return wordCounts.entrySet()
				.stream()
				.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public String getFirst(){
		if (players == null){
			return " ";
		}else{
			String firstKey = players.keySet().iterator().next();
			String firstValue = String.valueOf(players.get(firstKey));
			return firstValue + " (" + firstKey+ ")";
		}

	}
	public void close(){
		IO.escribirResultado(players);
		System.exit(0);
	}
}
