package esgi.moc.spaceinvaders.strategie;

import esgi.moc.spaceinvaders.GraphicalElement;

//une colonne : 68px
public class StrategieInvaderCol1 extends Strategie {

	private boolean directionDown = true;
	
	@Override
	public void execution(int timer, GraphicalElement invader) {
		if ((invader.poxY >=0) && (invader.poxY <68)) {
			invader.poxY += directionDown?1:-1;
		} else {
			directionDown = !directionDown;
			invader.poxY += directionDown?1:-1;
		}
	}

}
