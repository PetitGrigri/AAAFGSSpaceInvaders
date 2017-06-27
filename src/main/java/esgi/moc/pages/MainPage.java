package esgi.moc.pages;


import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.composed.Button;
import ej.widget.composed.ButtonImage;
import ej.widget.container.Dock;
import ej.widget.container.List;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import esgi.moc.activities.MainActivity;

public class MainPage extends Page {
    
    Dock dock;
    List listBottom;
    Button exitButton, scoreButton;
    ButtonImage playButton;

    Page that = this;
    
    public MainPage() 
    {
        dock = new Dock();
        
        listBottom = new List();

        
        //Création de deux boutons
        playButton = new ButtonImage("/images/title.png");
        exitButton = new Button("Exit");
        scoreButton = new Button("Score");
        
        listBottom.add(exitButton);
        listBottom.add(scoreButton);

        //Ajout d'un option de click sur le bouton 1
        playButton.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {

				System.out.println("Play");
				MainActivity.navigation.show(GamePage.class.getName(), true);

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
        
        //Ajout d'une option de click sur le bouton 1 (permettant de quitter notre application)
        scoreButton.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("Score");
				MainActivity.navigation.show(ScorePage.class.getName(), true);
			}
		});
        
        dock.setCenter(playButton);
        dock.addBottom(listBottom);

        //on ajoute notre vue splitée à notre page (qui ne peut contenir qu'un seul enfant)
        this.setWidget(dock);
        
        
        
    }
}