package esgi.moc.spaceinvaders.strategie;

import esgi.moc.spaceinvaders.GraphicalElement;

//une colonne : 68px
public class StrategieInvaderCol extends Strategie {

	private int top, bottom;
	
	public StrategieInvaderCol(int top, int bottom) {
		this.top 	= top;
		this.bottom = bottom;
	}

	private boolean directionDown 	= true;
	
	@Override
	public void execution(int timer, GraphicalElement invader) {
		if (timer > invader.startTimer) {
			
			if ((invader.posY >=top) && (invader.posY <bottom)) {
				invader.posY += directionDown?1:-1;
				invader.posX--;
			} else {
				directionDown = !directionDown;
				invader.posY += directionDown?1:-1;
			}
		}
	}

}
