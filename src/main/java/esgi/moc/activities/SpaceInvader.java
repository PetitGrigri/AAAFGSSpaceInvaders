package esgi.moc.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import esgi.moc.spaceinvaders.GraphicalElement;
import esgi.moc.spaceinvaders.strategie.Strategie;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderCol1;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderCol2;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderCol3;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderCol4;
import esgi.moc.spaceinvaders.strategie.StrategieShootSpaceShip;
import ej.bon.Timer;
import ej.bon.TimerTask;

public class SpaceInvader extends Displayable implements EventHandler {

	Image background1,background2,background3, spaceship, invader, shootSpaceShip;
	private int screenX, screenY;
	private int posBackground1, posBackground2, posBackground3;
	private int posSpaceShipY;
	
	private List<GraphicalElement> invaders = new ArrayList<GraphicalElement>();
	private List<GraphicalElement> spaceShipShoots = new ArrayList<GraphicalElement>();
	private GraphicalElement toRemove;
	
	private Timer t = new Timer();
	private int countTimer = 0;
	
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
			shootSpaceShip = Image.createImage("/images/shoot.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//

		
		//récupération des dimmensions de l'écran (non utilisé ici car on se base sur la position des barrières)
		this.screenX = Display.getDefaultDisplay().getWidth();
		this.screenY = Display.getDefaultDisplay().getHeight();

		this.posBackground1 = -screenX;
		this.posBackground2 = 0;
		this.posBackground3 = screenX;

		this.posSpaceShipY = screenY/2;
		
		invaders.add(new GraphicalElement(screenX-50, 0, new StrategieInvaderCol1()));
		invaders.add(new GraphicalElement(screenX-50, 68, new StrategieInvaderCol2()));
		invaders.add(new GraphicalElement(screenX-50, 136, new StrategieInvaderCol3()));
		invaders.add(new GraphicalElement(screenX-50, 204, new StrategieInvaderCol4()));
		invaders.add(new GraphicalElement(screenX-100, 68, new StrategieInvaderCol1()));
		invaders.add(new GraphicalElement(screenX-100, 136, new StrategieInvaderCol2()));
		invaders.add(new GraphicalElement(screenX-100, 204, new StrategieInvaderCol3()));
		invaders.add(new GraphicalElement(screenX-100, 272, new StrategieInvaderCol4()));
		invaders.add(new GraphicalElement(screenX-150, 0, new StrategieInvaderCol1()));
		invaders.add(new GraphicalElement(screenX-150, 204, new StrategieInvaderCol4()));

		t.schedule(new TimerTask() {
			@Override
			public void run() {
				
				countTimer+=30;
				posBackground1-=4;
				posBackground2-=4;
				posBackground3-=4;
				
				//gestion du shoot du vaisseau
				if (countTimer >= 600) {
					spaceShipShoots.add(new GraphicalElement(70, screenY/2, new StrategieShootSpaceShip()));
					countTimer = 0;
				}
				
				//gestion de l'affichage du background
				if (posBackground1 <= -screenX*2) {
					posBackground1=screenX;
				}
				if (posBackground2 <= -screenX*2) {
					posBackground2=screenX;;
				}
				if (posBackground3 <= -screenX*2) {
					posBackground3=screenX;
				}
				
				for (GraphicalElement invaderObject : invaders) {
					invaderObject.execution(countTimer);
				}
				
				//gestion du shoot du vaisseau
				for (GraphicalElement  shoot : spaceShipShoots) {
					shoot.execution(countTimer);
					if (shoot.poxX == 490) {
						toRemove = shoot;
					}
				}
				
				if (toRemove != null) {
					spaceShipShoots.remove(toRemove);
					toRemove = null;
					System.out.println("Shoot supprimé");
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
		
		for (GraphicalElement invaderObject : invaders) {
			g.drawImage(invader, invaderObject.poxX, invaderObject.poxY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		}
		for (GraphicalElement shoot : spaceShipShoots) {
			g.drawImage(shootSpaceShip, shoot.poxX, shoot.poxY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		}
	}

	@Override
	public EventHandler getController() {
		// TODO Auto-generated method stub
		//return null;
		return this;
	}

}
