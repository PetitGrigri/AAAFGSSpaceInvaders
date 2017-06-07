package esgi.moc.spaceinvaders;

import esgi.moc.spaceinvaders.strategie.Strategie;

//42 x 48
public class GraphicalElement 
{
	public int poxX;
	public int poxY;
	public Strategie strategie;
	

	public GraphicalElement(int poxX, int poxY, Strategie strategie) {
		this.poxX = poxX;
		this.poxY = poxY;
		this.strategie = strategie;
	}
	
	public void execution(int timer) {
		strategie.execution(timer, this);
	}	
}
