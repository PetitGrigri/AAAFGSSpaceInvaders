package esgi.moc.pages;

import ej.widget.composed.Button;
import ej.widget.container.List;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import esgi.moc.activities.MainActivity;

public class MenuPage extends Page {
	
	private static List list;
	
	public MenuPage(){
		
		list = new List(true);

		Button playButton = new Button("Play !");
		playButton.addClassSelector("BUTTON");
		playButton.getLabel().addClassSelector("LABEL");
		
		Button scoreButton = new Button("Scores");
		scoreButton.addClassSelector("BUTTON");
		scoreButton.getLabel().addClassSelector("LABEL");
		
		Button exitButton = new Button("Exit");
		exitButton.addClassSelector("BUTTON");
		exitButton.getLabel().addClassSelector("LABEL");
		
		playButton.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				MainActivity.simpleNav.show(GamePage.class.getName(), false);
			}
		});
		
		list.add(playButton);
		list.add(scoreButton);
		list.add(exitButton);
		
		setWidget(list);
		
	}
	
}
