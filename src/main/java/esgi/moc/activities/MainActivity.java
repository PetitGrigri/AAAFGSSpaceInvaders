package esgi.moc.activities;

import java.io.IOException;

import ej.microui.MicroUI;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.mwt.Desktop;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.background.SimpleImageBackground;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.container.Dock;
import ej.widget.container.List;
import ej.widget.navigation.TransitionManager;
import ej.widget.navigation.navigator.SimpleNavigator;
import ej.widget.navigation.page.Page;
import esgi.moc.pages.MainPage;

public class MainActivity implements Activity {
	
	public static SimpleNavigator navigation;


	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		MicroUI.start(); //toujours lancer cette commande pour avoir l'affichage sur l'écran
		
		//création de nouveaux styles
		Stylesheet sheet = StyleHelper.getStylesheet();

		//création d'un style pour les boutons du bas
		EditableStyle bottomStyle = new EditableStyle();
		bottomStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.BOTTOM);
		bottomStyle.setBackgroundColor(0x000000);
		bottomStyle.setForegroundColor(0xffffff);
		bottomStyle.setPadding(new SimpleOutline(12));
		
		//score Theme score odd
		EditableStyle labelScoreOddStyle = new EditableStyle();
		labelScoreOddStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.BOTTOM);
		labelScoreOddStyle.setBackgroundColor(0x333333);
		labelScoreOddStyle.setForegroundColor(0xeeeeee);
		labelScoreOddStyle.setPadding(new SimpleOutline(5));
		
		EditableStyle labelScoreEvenStyle = new EditableStyle();
		labelScoreEvenStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.BOTTOM);
		labelScoreEvenStyle.setBackgroundColor(0xcccccc);
		labelScoreEvenStyle.setForegroundColor(0x333333);
		labelScoreEvenStyle.setPadding(new SimpleOutline(5));

		//on attribue les différents styles
		sheet.addRule(new ChildCombinator(new TypeSelector(Button.class), new TypeSelector(Label.class)), bottomStyle);
		sheet.addRule(new ClassSelector("scoreOdd"), labelScoreOddStyle);
		sheet.addRule(new ClassSelector("scoreEven"), labelScoreEvenStyle);
		sheet.addRule(new ClassSelector("scoreTitle"), bottomStyle);
		
		//Ordre des widgets : Le Desktop contient un Panel qui contient un Navigator qui contient une page 
		//création des  widgets 
		Desktop desktop = new Desktop();
		Panel panel = new Panel();
		navigation = new SimpleNavigator();
		
		//configuration et affichage des  widgets
		navigation.show(MainPage.class.getName(), true);
		panel.setWidget(navigation);
		panel.show(desktop, true);
		desktop.show();
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

}
