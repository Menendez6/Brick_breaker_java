package dominio;

import dominio.Brick;

import java.awt.Graphics2D;

public class MapGenerator 
{
	public Brick map[][];
	public int col = 7;
	public int row = 13;
	public int totalBricks;

	//7 columnas
	//Dependiendo de los niveles cambiar lo valores del map
	//Crear un map con los strings de niveles como key y las matrices como values (o una matriz de bricks)
	
	public MapGenerator (int nivel) {
		//map = new int[row][col];
		map = new Brick[row][col];
		/*for(int i = 0; i<map.length; i++)
		{
			for (int j = 0; j < map[0].length; j++) {
					map[i][j] = 0;
			}
		}*/
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Brick(j * Brick.WIDTH + 10, i * Brick.HEIGHT + 10);
			}
		}
		//Nivel 1
		if (nivel == 1) {
			map[1][2].setValor(1);
			map[1][4].setValor(1);
			map[2][1].setValor(1);
			map[2][5].setValor(1);
			map[4][1].setValor(1);
			map[4][5].setValor(1);
			map[4][3].setValor(-1);
			map[5][1].setValor(1);
			map[5][5].setValor(1);
			map[8][2].setValor(1);
			map[8][4].setValor(1);
			map[7][1].setValor(1);
			map[7][5].setValor(1);
		}

			if (nivel == 2) {
				map[2][3].setValor(2);
				map[3][2].setValor(2);
				map[3][3].setValor(1);
				map[3][4].setValor(2);
				map[4][1].setValor(2);
				map[4][2].setValor(1);
				map[4][3].setValor(-1);
				map[4][4].setValor(1);
				map[4][5].setValor(2);
				map[5][2].setValor(2);
				map[5][3].setValor(1);
				map[5][4].setValor(2);
				map[6][3].setValor(2);
			}
			if (nivel == 3){
				map[1][1].setValor(2);
				map[1][2].setValor(1);
				map[1][4].setValor(1);
				map[1][5].setValor(2);
				map[2][1].setValor(1);
				map[2][2].setValor(1);
				map[2][4].setValor(3);
				map[2][5].setValor(1);
				map[4][1].setValor(3);
				map[4][2].setValor(1);
				map[4][4].setValor(2);
				map[4][5].setValor(1);
				map[5][1].setValor(1);
				map[5][2].setValor(1);
				map[5][4].setValor(1);
				map[5][5].setValor(1);
				map[7][1].setValor(1);
				map[7][2].setValor(2);
				map[7][4].setValor(-1);
				map[7][5].setValor(1);
				map[8][1].setValor(1);
				map[8][2].setValor(1);
				map[8][4].setValor(1);
				map[8][5].setValor(1);
			}
			if (nivel == 4){
				map[1][2].setValor(3);
				map[1][3].setValor(3);
				map[1][4].setValor(3);
				map[8][2].setValor(3);
				map[8][3].setValor(2);
				map[8][4].setValor(-1);

				map[3][1].setValor(3);
				map[3][2].setValor(3);
				map[3][4].setValor(4);
				map[3][5].setValor(3);
				map[6][1].setValor(4);
				map[6][2].setValor(3);
				map[6][4].setValor(3);
				map[6][5].setValor(4);

				map[4][1].setValor(3);
				map[4][5].setValor(3);
				map[5][1].setValor(3);
				map[5][5].setValor(3);

			}

			if (nivel == 5){
				for (int j = 0; j < map[0].length; j++) {
					map[0][j].setValor(1);
					map[5][j].setValor(1);
					map[2][j].setValor(2);
				}
				map[2][2].setValor(3);
				map[2][3].setValor(-1);
				map[2][4].setValor(3);
				map[3][2].setValor(1);
				map[3][3].setValor(1);
				map[3][4].setValor(1);

			}
			if (nivel == 6){
				for (int i =1; i<10;i++){
					map[i][3].setValor(-1);
					if (i>1 && i<9) {
						map[i][2].setValor(1);
						map[i][4].setValor(1);
					}
				}
				map[5][1].setValor(-1);
				map[5][2].setValor(-1);
				map[5][4].setValor(-1);
				map[5][5].setValor(-1);
			}

			if (nivel == 7){
				for (int i =1; i<9;i++){
					map[i][1].setValor(-1);
					if (i<7)
						map[8][i].setValor(-1);
				}
				map[2][4].setValor(1);
				map[3][3].setValor(1);
				map[3][4].setValor(2);
				map[3][5].setValor(1);
				map[4][2].setValor(1);
				map[4][3].setValor(2);
				map[4][4].setValor(4);
				map[4][5].setValor(2);
				map[4][6].setValor(1);
				map[5][3].setValor(1);
				map[5][4].setValor(2);
				map[5][5].setValor(1);
				map[6][4].setValor(1);
			}

			if (nivel == 8){
				for (int i = 1; i < 10; i++) {
					map[i][1].setValor(-1);
					map[i][5].setValor(-1);
				}
				map[1][2].setValor(1);
				map[1][3].setValor(2);
				map[1][4].setValor(1);
				map[2][2].setValor(2);
				map[2][4].setValor(2);
				map[3][2].setValor(2);
				map[3][4].setValor(2);
				map[4][2].setValor(2);
				map[4][4].setValor(2);
				map[5][2].setValor(3);
				map[5][4].setValor(3);
				map[6][2].setValor(3);
				map[6][3].setValor(4);
				map[6][4].setValor(3);
				map[7][2].setValor(3);
				map[7][4].setValor(3);
				map[8][2].setValor(4);
				map[8][3].setValor(4);
				map[8][4].setValor(4);
				map[9][2].setValor(-1);
				map[9][3].setValor(-1);
				map[9][4].setValor(-1);
			}

			//brickWidth = 680/col;
			//brickHeight = 600/row;
		}
	public int getTotalBricks(){
		this.totalBricks = 0;
		for(int i = 0; i<map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getValor() > 0){
					this.totalBricks ++;
				}
			}
		}
		return this.totalBricks;
	}

	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if (map[i][j].getValor() != 0)
					map[i][j].pintar(g);
			}

		}
	}

}
