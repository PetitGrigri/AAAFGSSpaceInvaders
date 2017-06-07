package esgi.moc.spaceinvaders;

import esgi.moc.spaceinvaders.strategie.Strategie;

//42 x 48
public class GraphicalElement 
{
	public int posX, posY;
	public int startTimer;
	public Strategie strategie;
	

	public GraphicalElement(int poxX, int poxY, Strategie strategie) {
		this.posX = poxX;
		this.posY = poxY;
		startTimer = 0;
		this.strategie = strategie;
	}
	
	public GraphicalElement(int poxX, int poxY, Strategie strategie, int startTimer) {
		this.posX = poxX;
		this.posY = poxY;
		this.startTimer = startTimer;
		this.strategie = strategie;
	}
	
	public void execution(int timer) {
		strategie.execution(timer, this);
	}	
}
