package esgi.moc.spaceinvaders.strategie;

import esgi.moc.spaceinvaders.GraphicalElement;

public class StrategieInvaderMove extends Strategie {

	private int changeLeftPos;
	private int changeTopPos;

	public StrategieInvaderMove(int changeLeftPos, int changeTopPos) {
		super();
		this.changeLeftPos = changeLeftPos;
		this.changeTopPos = changeTopPos;
	}


	@Override
	public void execution(int timer, GraphicalElement invader) {
		if (timer > invader.startTimer) {
			invader.posX += changeLeftPos;
			invader.posY += changeTopPos;
		}
	}

}
