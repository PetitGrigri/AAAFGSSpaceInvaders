package esgi.moc.spaceinvaders;

import esgi.moc.spaceinvaders.strategie.Strategie;

//42 x 48
public class GraphicalElement 
{
	public int posX, posY;
	public int startTimer;
	public Strategie strategie;
	public int score;
	

	public GraphicalElement(int poxX, int poxY, int score, Strategie strategie) {
		this.posX = poxX;
		this.posY = poxY;
		startTimer = 0;
		this.strategie = strategie;
		this.score = score;
	}
	
	public GraphicalElement(int poxX, int poxY, int score, Strategie strategie, int startTimer) {
		this.posX = poxX;
		this.posY = poxY;
		this.startTimer = startTimer;
		this.strategie = strategie;
		this.score = score;
	}
	
	public void execution(int timer) {
		strategie.execution(timer, this);
	}	
}
