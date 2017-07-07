package esgi.moc.pages;

import java.util.ArrayList;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
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
    Button backButton, scoreButton;
    Button playButton;
    
    public static ArrayList<Integer> scores = new ArrayList<Integer>();
    
    public ScorePage() 
    {
    	System.out.println("Welcome on score");
    	scrollCenter = new Scroll(false, true);
    	
    	dock = new Dock();
    	
    	//liste des boutons en bas
    	listBottom = new List();
    	listCenter = new List(false);
    	
    	if (!scores.isEmpty()) {
	    	for (int a = scores.size(); a> 0; a--) {
	    		Label tempoLabelScore = new Label(((scores.size() == a)?"Dernier score : " : "")+Integer.toString(scores.get(a-1)));
	    		
		    	if ((a-1)%2 == 0)
		    		tempoLabelScore.addClassSelector("scoreOdd");
		    	else 
		    		tempoLabelScore.addClassSelector("scoreEven");
		    	
	    		listCenter.add(tempoLabelScore);
	    	}
    	} else {
    		Label tempoLabelScore = new Label("Vous n'avez pas encore de score");
    		tempoLabelScore.addClassSelector("score");
    		listCenter.add(tempoLabelScore);
    	}
    	
    	
    	scrollCenter.setWidget(listCenter);
    	
        //Création de deux boutons
        backButton = new Button("Back");
        playButton = new Button("Play");
        
        //ajout des bouton dans la liste du bas
        listBottom.add(backButton);
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
        backButton.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("Exit");
				MainActivity.navigation.show(MainPage.class.getName(), true);
			}
		});
        
        //Titre
        Label titre = new Label("Hall of Fame");
        titre.addClassSelector("scoreTitle");
        
        dock.addTop(titre);
        dock.setCenter(scrollCenter);
        dock.addBottom(listBottom);

        //on ajoute notre vue splitée à notre page (qui ne peut contenir qu'un seul enfant)
        this.setWidget(dock);
        
        
        
    }
}