package esgi.moc.pages;


import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.microui.display.Display;
import ej.widget.composed.Button;
import ej.widget.container.Dock;
import ej.widget.container.List;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import esgi.moc.activities.SpaceInvader;

public class MainPage extends Page {
    
    Dock dock;
    List listBottom;
    Button playButton, exitButton, scoreButton;

    public MainPage() {

        dock = new Dock();
        
        listBottom = new List();

        
        //Création de deux boutons
        playButton = new Button();
        exitButton = new Button("Exit");
        scoreButton = new Button("Score");
        
        
        listBottom.add(exitButton);
        listBottom.add(scoreButton);
        
        //Ajout d'un style personnalisé pour chaque bouton
        playButton.addClassSelector("play");
        
        
        //Ajout d'un option de click sur le bouton 1
        playButton.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				SpaceInvader spaceInvader = new SpaceInvader(Display.getDefaultDisplay());
				spaceInvader.show();
			}
		});
        
      //Ajout d'une option de click sur le bouton 1 (permettant de quitter notre application)
        exitButton.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("Exit");
				ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService(ExitHandler.class);
				if (exitHandler != null) {
					exitHandler.exit();
				}
			}
		});
        
        dock.setCenter(playButton);
        dock.addBottom(listBottom);

        //on ajoute notre vue splitée à notre page (qui ne peut contenir qu'un seul enfant)
        this.setWidget(dock);
    }
}