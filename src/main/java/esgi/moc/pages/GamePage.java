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

/*public class GamePage extends Page {
	
	private Split splitContainer;
	private Image image;
	
	public GamePage(){
		
		splitContainer = new Split(false,(float)0.2);
		
		try {
			image = Image.createImage("/images/invader.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EditableStyle style = new EditableStyle();
		SimpleImageBackground sim = new SimpleImageBackground()
		sim.setImage(image);
		style.setBackground(sim);
		
		splitContainer.add());
	}
	
}*/

public class GamePage extends Displayable {
	
	Image image;

	public GamePage(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
		try {
			image = Image.createImage("/images/invader.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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


