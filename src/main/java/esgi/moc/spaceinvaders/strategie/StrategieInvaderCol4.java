package esgi.moc.spaceinvaders.strategie;

import esgi.moc.spaceinvaders.GraphicalElement;

//une colonne : 68px
public class StrategieInvaderCol4 extends Strategie {

	private boolean directionDown = true;
	
	@Override
	public void execution(int timer, GraphicalElement invader) {
		if ((invader.poxY >=204) && (invader.poxY <272)) {
			invader.poxY += directionDown?1:-1;
		} else {
			directionDown = !directionDown;
			invader.poxY += directionDown?1:-1;
		}
	}

}
