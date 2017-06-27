package esgi.moc.pages;


import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.composed.ButtonImage;
import ej.widget.container.Dock;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import esgi.moc.activities.MainActivity;

public class ScorePage extends Page {
	
    Dock dock;
    Scroll scrollCenter;
    List listBottom, listCenter;
    Button exitButton, scoreButton;
    Button playButton;

    Page that = this;
    
    public ScorePage() 
    {
    	System.out.println("Welcome on score");
    	scrollCenter = new Scroll(false, true);
    	
    	dock = new Dock();
    	
    	//liste des boutons en bas
    	listBottom = new List();
    	listCenter = new List(false);
    	
    	listCenter.add(new Label("Test"));
    	listCenter.add(new Label("Test1"));
    	listCenter.add(new Label("Test2"));
    	listCenter.add(new Label("Test3"));
    	listCenter.add(new Label("Test"));
    	listCenter.add(new Label("Test1"));
    	listCenter.add(new Label("Test2"));
    	listCenter.add(new Label("Test3"));
    	listCenter.add(new Label("Test"));
    	listCenter.add(new Label("Test1"));
    	listCenter.add(new Label("Test2"));
    	listCenter.add(new Label("Test3"));
    	listCenter.add(new Label("Test"));
    	listCenter.add(new Label("Test1"));
    	listCenter.add(new Label("Test2"));
    	listCenter.add(new Label("Test3"));
    	listCenter.add(new Label("Test"));
    	listCenter.add(new Label("Test1"));
    	listCenter.add(new Label("Test2"));
    	listCenter.add(new Label("Test3"));
    	listCenter.add(new Label("Test"));
    	listCenter.add(new Label("Test1"));
    	listCenter.add(new Label("Test2"));
    	listCenter.add(new Label("Test3"));
    	
    	//scrollCenter.setWidget(listCenter);
    	
    	scrollCenter.setWidget(listCenter);
    	
        //Création de deux boutons
        exitButton = new Button("Exit");
        playButton = new Button("Play");
        
        //ajout des bouton dans la liste du bas
        listBottom.add(exitButton);
        listBottom.add(playButton);

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
        
        dock.setCenter(scrollCenter);
        dock.addBottom(listBottom);

        //on ajoute notre vue splitée à notre page (qui ne peut contenir qu'un seul enfant)
        this.setWidget(dock);
        
        
        
    }
}