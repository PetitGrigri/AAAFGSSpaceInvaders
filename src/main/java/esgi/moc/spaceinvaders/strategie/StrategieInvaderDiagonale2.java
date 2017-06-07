package esgi.moc.spaceinvaders.strategie;

import esgi.moc.spaceinvaders.GraphicalElement;

//une colonne : 68px
public class StrategieInvaderDiagonale2 extends Strategie {

	private boolean directionDown 	= true;
	private int changeLeftPos		= -8;
	private int changeTopPos		= -2;

	@Override
	public void execution(int timer, GraphicalElement invader) {
		if (timer > invader.startTimer) {
			invader.posX += changeLeftPos;
			invader.posY += changeTopPos;
		}
	}

}
