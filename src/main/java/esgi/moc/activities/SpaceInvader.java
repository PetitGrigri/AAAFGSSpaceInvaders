package esgi.moc.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import esgi.moc.spaceinvaders.GraphicalElement;
import esgi.moc.spaceinvaders.strategie.Strategie;
import esgi.moc.spaceinvaders.strategie.StrategieExplosion;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderCol;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderCol2;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderMove;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderMove;
import esgi.moc.spaceinvaders.strategie.StrategieInvaderMove;
import esgi.moc.spaceinvaders.strategie.StrategieShootSpaceShip;
import ej.bon.Timer;
import ej.bon.TimerTask;

public class SpaceInvader extends Displayable implements EventHandler {

	Image background1,background2,background3, spaceship, invader, shootSpaceShip;
	private int screenX, screenY;
	private int posBackground1, posBackground2, posBackground3;
	private int posSpaceShipY;
	private int posSpaceShipX = 50;
	private int score = 0;
	
	private List<GraphicalElement> invaders = new ArrayList<GraphicalElement>();
	private List<GraphicalElement> spaceShipShoots = new ArrayList<GraphicalElement>();
	private List<GraphicalElement> toRemoveInvaders = new ArrayList<GraphicalElement>();
	private List<GraphicalElement> toRemoveShoots = new ArrayList<GraphicalElement>();
	
	private Timer t = new Timer();
	private int countTimer = 0;
	
	private Strategie strategieExplosion = new StrategieExplosion();
	
	//TODO TODO TODO TODO

	/**
	 * Constructeur.
	 * Permetttra d'initialiser les différentes animations (création des différentes images)
	 * @param display
	 */
	public SpaceInvader(Display display) {
		super(Display.getDefaultDisplay());
		
		//////Initialisation des images
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

		//récupération des dimmensions de l'écran (non utilisé ici car on se base sur la position des barrières)
		this.screenX = Display.getDefaultDisplay().getWidth();
		this.screenY = Display.getDefaultDisplay().getHeight();

		this.posBackground1 = -screenX;
		this.posBackground2 = 0;
		this.posBackground3 = screenX;

		this.posSpaceShipY = screenY/2;
		
		generateLevelInvadersLevel1();
		

		
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				
				countTimer+=30;
				posBackground1-=10;
				posBackground2-=10;
				posBackground3-=10;
				
				//////gestion du Tir du vaisseau
				//réalisation du Tir
				if (countTimer%300 == 0) {
					spaceShipShoots.add(new GraphicalElement(posSpaceShipX+20, posSpaceShipY, 0, new StrategieShootSpaceShip()));
				}
				if (countTimer%4800 == 0) {
					generateLevelInvadersLevel2();
				}
				
				//execution de la stratégie du shoot, et détection du shoot hors de l'écran
				for (GraphicalElement  shoot : spaceShipShoots) {
					shoot.execution(countTimer);
					
					//si  notre Tir touche un invader on supprime le Tir et l'invader
					for (GraphicalElement invaderObject : invaders) {
						if ((shoot.posX >= (invaderObject.posX -18)) && (shoot.posX <= (invaderObject.posX +18)) && (shoot.posY >= (invaderObject.posY -18)) && (shoot.posY <= (invaderObject.posY +18))) {
							toRemoveInvaders.add(invaderObject);
							toRemoveShoots.add(shoot);
							score += invaderObject.score;
						}
					}
					//si le Tir du vaisseau est sorti de l'écran : on le supprime
					if (shoot.posX >= 490) {
						toRemoveShoots.add(shoot);
					}
				}
				
				//suppression des invaders mort
				cleanGraphicalElement();

				//////gestion de l'affichage du background
				if (posBackground1 <= -screenX*2) {
					posBackground1=screenX;
				}
				if (posBackground2 <= -screenX*2) {
					posBackground2=screenX;
				}
				if (posBackground3 <= -screenX*2) {
					posBackground3=screenX;
				}
				
				//////Execution des stratégies pour les invaders, détection de la colision
				for (GraphicalElement invaderObject : invaders) {
					invaderObject.execution(countTimer);
					
					if 	(!( ((posSpaceShipX + 14)  < (invaderObject.posX - 11)) ||
							((posSpaceShipX - 14)  > (invaderObject.posX + 11)) || 
							((posSpaceShipY - 15) > (invaderObject.posY +17))  ||
							((posSpaceShipY + 15) < (invaderObject.posY-17))
							)) {
						System.out.println("BOUUUMMMMMM ! ! ! spaceship("+posSpaceShipX+" x "+posSpaceShipY+") invader("+invaderObject.posX+" x "+invaderObject.posY+")");
					}

					if (invaderObject.posX <= -10) {
						toRemoveInvaders.add(invaderObject);
					}
				}
				
				//suppression des invaders mort
				cleanGraphicalElement();
				
				//System.out.println(invaders.get(0).posX); //toRemoveInvaders.add(invaderObject);
				/////Actualisation de l'affichage
				repaint();
				
			}
		}, 0,30);
		
		
		
		
		
	}

	
	private void generateLevelInvadersLevel1() {		
		//le bloc d'invader
		invaders.add(new GraphicalElement(screenX+50, 	0, 		15, new StrategieInvaderCol(0,   68), 	0));
		invaders.add(new GraphicalElement(screenX+50, 	68, 	15, new StrategieInvaderCol(68,  136), 	0));
		invaders.add(new GraphicalElement(screenX+50, 	138, 	15, new StrategieInvaderCol(136, 204), 	0));
		invaders.add(new GraphicalElement(screenX+50, 	204, 	15, new StrategieInvaderCol(204, 272), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	68, 	15, new StrategieInvaderCol(0,   68), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	138, 	15, new StrategieInvaderCol(68,  136), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	204, 	15, new StrategieInvaderCol(136, 204), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	272, 	15, new StrategieInvaderCol(204, 272), 	0));
		invaders.add(new GraphicalElement(screenX+150, 	0, 		15, new StrategieInvaderCol(136, 204), 	0));
		invaders.add(new GraphicalElement(screenX+150, 	204, 	15, new StrategieInvaderCol(68,  136), 	0));
		
		//les invaders en mouvement
		invaders.add(new GraphicalElement(screenX+30, 	10, 	30, new StrategieInvaderMove(-4, 1), 	countTimer+1000));
		invaders.add(new GraphicalElement(screenX+30, 	screenY,30, new StrategieInvaderMove(-8, -2), countTimer+2000));
		invaders.add(new GraphicalElement(screenX+30, 	screenY/2, 	  30, new StrategieInvaderMove(-8, 0), 	countTimer+3000));
		invaders.add(new GraphicalElement(screenX+30, 	screenY,30, new StrategieInvaderMove(-3, -2), countTimer+4000));
	}


	private void generateLevelInvadersLevel2() {		
		//le bloc d'invader
		invaders.add(new GraphicalElement(screenX+50, 	0, 		25, new StrategieInvaderCol2(0,   68), 	0));
		invaders.add(new GraphicalElement(screenX+50, 	68, 	25, new StrategieInvaderCol2(68,  136), 	0));
		invaders.add(new GraphicalElement(screenX+50, 	138, 	25, new StrategieInvaderCol2(136, 204), 	0));
		invaders.add(new GraphicalElement(screenX+50, 	204, 	25, new StrategieInvaderCol2(204, 272), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	68, 	25, new StrategieInvaderCol2(0,   68), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	138, 	25, new StrategieInvaderCol2(68,  136), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	204, 	25, new StrategieInvaderCol2(136, 204), 	0));
		invaders.add(new GraphicalElement(screenX+100, 	272, 	25, new StrategieInvaderCol2(204, 272), 	0));
		invaders.add(new GraphicalElement(screenX+150, 	0, 		25, new StrategieInvaderCol2(136, 204), 	0));
		invaders.add(new GraphicalElement(screenX+150, 	204, 	25, new StrategieInvaderCol2(68,  136), 	0));
		
		//les invaders en mouvement
		invaders.add(new GraphicalElement(screenX+30, 	10, 		45, new StrategieInvaderMove(-4, 1), 	countTimer+500));
		invaders.add(new GraphicalElement(screenX+30, 	screenY,	45, new StrategieInvaderMove(-8, -2), countTimer+1000));
		invaders.add(new GraphicalElement(screenX+30, 	screenY/2, 	45, new StrategieInvaderMove(-8, 0), 	countTimer+1500));
		invaders.add(new GraphicalElement(screenX+30, 	screenY, 	45, new StrategieInvaderMove(-3, -2), countTimer+2000));
		
		invaders.add(new GraphicalElement(screenX+30, 	10, 		45, new StrategieInvaderMove(-4, 1), 	countTimer+2500));
		invaders.add(new GraphicalElement(screenX+30, 	screenY,	45, new StrategieInvaderMove(-8, -2), countTimer+3000));
		invaders.add(new GraphicalElement(screenX+30, 	screenY/2, 	45, new StrategieInvaderMove(-8, 0), 	countTimer+3500));
		invaders.add(new GraphicalElement(screenX+30, 	screenY,	45, new StrategieInvaderMove(-3, -2), countTimer+4000));
	}
	
	/**
	 * permet de gérer les évènements sur l'écran (dans ce cas précis)
	 */
	@Override
	public boolean handleEvent(int event) 
	{
		//Gestion d'un évènement de type pointer
		if (Event.getType(event) == Event.POINTER) {
			//quand on à un évènement de type de Drag, on récupère la position, on réinitialise certaines valeurs et on bloque l'animation
			if (Pointer.isDragged(event)) {
				Pointer pointer 		= (Pointer) Event.getGenerator(event);
				this.posSpaceShipY		= pointer.getY();		
			}
		}
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
	
		g.drawImage(spaceship, posSpaceShipX, posSpaceShipY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		
		for (GraphicalElement invaderObject : invaders) {
			if (invaderObject.startTimer <= countTimer) {
				g.drawImage(invader, invaderObject.posX, invaderObject.posY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
			}
		}
		for (GraphicalElement shoot : spaceShipShoots) {
			g.drawImage(shootSpaceShip, shoot.posX, shoot.posY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		}

		g.setColor(Colors.WHITE);
		g.drawString("Score : "+this.score, 0, 0, 0);
	}

	

	
	
	@Override
	public EventHandler getController() {
		// TODO Auto-generated method stub
		//return null;
		return this;
	}
	
	private void cleanGraphicalElement() {
		for (GraphicalElement  element : toRemoveInvaders) {
			invaders.remove(element);
		}
		for (GraphicalElement  element : toRemoveShoots) {
			spaceShipShoots.remove(element);
		}
		toRemoveInvaders.clear();
		toRemoveShoots.clear();
	}

}
