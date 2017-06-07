package esgi.moc.spaceinvaders.strategie;

import esgi.moc.spaceinvaders.GraphicalElement;

//une colonne : 68px
public class StrategieShootSpaceShip extends Strategie {

	private boolean directionDown = true;
	
	@Override
	public void execution(int timer, GraphicalElement shoot) {
		if ((shoot.poxX >=0) && (shoot.poxY <=490)) {
			shoot.poxX += 3;
		}
	}

}
