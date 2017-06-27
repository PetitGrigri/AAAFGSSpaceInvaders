package esgi.moc.pages;


import ej.widget.container.Dock;
import ej.widget.navigation.page.Page;
import esgi.moc.widget.SpaceInvaderWidget;

public class GamePage extends Page {
    
	Dock dock;

    public GamePage() 
    {
    	System.out.println("Welcome on the game");
    	dock = new Dock();
    	dock.setCenter(new SpaceInvaderWidget(272, 480));
        this.setWidget(dock);
        
        
        
    }
}