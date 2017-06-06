package esgi.moc.pages;

import ej.widget.composed.Button;
import ej.widget.container.List;
import ej.widget.navigation.page.Page;

public class MenuPage extends Page {
	
	private static List list;
	
	public MenuPage(){
		
		list = new List(true);

		Button boardButton = new Button("Play !");
		boardButton.addClassSelector("BUTTON");
		boardButton.getLabel().addClassSelector("LABEL");
		
		Button scoreButton = new Button("Scores");
		scoreButton.addClassSelector("BUTTON");
		scoreButton.getLabel().addClassSelector("LABEL");
		
		Button exitButton = new Button("Exit");
		exitButton.addClassSelector("BUTTON");
		exitButton.getLabel().addClassSelector("LABEL");
		
		list.add(boardButton);
		list.add(scoreButton);
		list.add(exitButton);
		
		setWidget(list);
		
	}
	
}
