package esgi.moc.pages;

import ej.microui.display.Display;
import ej.widget.composed.Button;
import ej.widget.container.List;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import esgi.moc.activities.MainActivity;
import esgi.moc.activities.SpaceInvader;

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
				SpaceInvader spaceInvader = new SpaceInvader(Display.getDefaultDisplay());
				spaceInvader.show();
			}
		});
		
		list.add(playButton);
		list.add(scoreButton);
		list.add(exitButton);
		
		setWidget(list);
		
	}
	
}
