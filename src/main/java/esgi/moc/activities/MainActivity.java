package esgi.moc.activities;

import ej.microui.MicroUI;
import ej.microui.display.GraphicsContext;
import ej.mwt.Desktop;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.outline.SimpleOutline;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.navigation.navigator.SimpleNavigator;
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

		//création d'un second style pour le bouton d'exit
		EditableStyle bottomStyle = new EditableStyle();
		bottomStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.BOTTOM);
		bottomStyle.setBackgroundColor(0x000000);
		bottomStyle.setForegroundColor(0xffffff);
		bottomStyle.setPadding(new SimpleOutline(12));

		//on attribue les différents styles
		sheet.addRule(new ChildCombinator(new TypeSelector(Button.class), new TypeSelector(Label.class)), bottomStyle);

		
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
