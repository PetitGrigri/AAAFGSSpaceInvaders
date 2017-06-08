package esgi.moc.pages;

import java.io.IOException;

import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.util.EventHandler;
import ej.style.background.SimpleImageBackground;
import ej.style.util.EditableStyle;
import ej.widget.container.Split;
import ej.widget.navigation.page.Page;
import ej.widget.navigation.stack.PageStack;
import esgi.moc.activities.MainActivity;


public class GamePage extends Displayable {
	
	Image image;

	public GamePage(Display display) {
		super(display);

		
	}

	@Override
	public void paint(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.drawImage(image, 0, 0, GraphicsContext.TOP);
	}

	@Override
	public EventHandler getController() {
		// TODO Auto-generated method stub
		return null;
	}

}


