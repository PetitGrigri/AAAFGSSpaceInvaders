package esgi.moc.activities;

import java.io.IOException;
import java.util.ArrayList;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.transform.ImageRotation;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.bon.Timer;
import ej.bon.TimerTask;

public class SpaceInvader extends Displayable implements EventHandler {

	Image background1,background2,background3, spaceship, invader;					//l'image de fond
	
	private int screenX;
	private int screenY;
	
	private int posBackground1;
	private int posBackground2;
	private int posBackground3;
	
	private int posSpaceShipY;
	
	private int[] posInvaders = {0, 68, 136, 204};
	
	private boolean invadersDirection = false;
	
	private Timer t = new Timer();
	
	
	//TODO TODO TODO TODO

	/**
	 * Constructeur.
	 * Permetttra d'initialiser les différentes animations (création des différentes images)
	 * @param display
	 */
	public SpaceInvader(Display display) {
		super(Display.getDefaultDisplay());
		try {
			
			//le fond de l'écran
			background1 = Image.createImage("/images/background_1.png");
			background2 = Image.createImage("/images/background_2.png");
			background3 = Image.createImage("/images/background_3.png");
			spaceship = Image.createImage("/images/spaceship.png");
			invader = Image.createImage("/images/invader.png");
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		//récupération des dimmensions de l'écran (non utilisé ici car on se base sur la position des barrières)
		this.screenX = Display.getDefaultDisplay().getWidth();
		this.screenY = Display.getDefaultDisplay().getHeight();

		this.posBackground1 = -screenX;
		this.posBackground2 = 0;
		this.posBackground3 = screenX;

		this.posSpaceShipY = screenY/2;
		
		
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				
				posBackground1-=4;
				posBackground2-=4;
				posBackground3-=4;
				
				for(int a =0; a< posInvaders.length; a++) {
					if (invadersDirection) {
						posInvaders[a]+=1;
					} else {
						posInvaders[a]-=1;
					}
				}
				
				if (posInvaders[0] >= 32) {
					invadersDirection = false;
				}
				if (posInvaders[0] <= -32) {
					invadersDirection = true;
				}
				
				
				if (posBackground1 <= -screenX*2) {
					posBackground1=screenX;
				}
				if (posBackground2 <= -screenX*2) {
					posBackground2=screenX;;
				}
				if (posBackground3 <= -screenX*2) {
					posBackground3=screenX;
				}
				
				
				
				//on demande à actualiser l'affichage
				repaint();
				
			}
		}, 0,30);
		
		
		
		
		
	}

	
	/**
	 * permet de gérer les évènements sur l'écran (dans ce cas précis)
	 */
	@Override
	public boolean handleEvent(int event) 
	{
		
		return false;
	}

	/**
	 * Méthode qui permettra d'atualiser l'affichage
	 */
	@Override
	public void paint(GraphicsContext g) 
	{

		//affichage de l'image de fond
		g.drawImage(background1, posBackground1, this.screenY/2, GraphicsContext.LEFT | GraphicsContext.VCENTER);
		g.drawImage(background2, posBackground2, this.screenY/2, GraphicsContext.LEFT | GraphicsContext.VCENTER);
		g.drawImage(background3, posBackground3, this.screenY/2, GraphicsContext.LEFT | GraphicsContext.VCENTER);
	
		g.drawImage(spaceship, 50, posSpaceShipY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		
		for(int a =0; a< posInvaders.length; a++) {
			g.drawImage(invader, screenX-50, (posInvaders[a]+64/2), GraphicsContext.HCENTER | GraphicsContext.VCENTER);
			g.drawImage(invader, screenX-100, (posInvaders[a]+64/2), GraphicsContext.HCENTER | GraphicsContext.VCENTER);
			g.drawImage(invader, screenX-150, (posInvaders[a]+64/2), GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		}
	}

	@Override
	public EventHandler getController() {
		// TODO Auto-generated method stub
		//return null;
		return this;
	}

}
