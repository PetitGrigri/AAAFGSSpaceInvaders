package esgi.moc.activities;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.mwt.Desktop;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.border.SimpleRectangularBorder;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.navigation.navigator.SimpleNavigator;
import ej.widget.navigation.transition.HorizontalTransitionManager;
import esgi.moc.pages.MenuPage;

public class MainActivity implements Activity {
	
	public static SimpleNavigator simpleNav;

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
		
MicroUI.start();
		
		simpleNav = new SimpleNavigator();
		simpleNav.setTransitionManager(new HorizontalTransitionManager());
		
		Desktop desk = new Desktop();
		Panel pnl = new Panel();
		
		simpleNav.show(MenuPage.class.getName(), true);
		pnl.setWidget(simpleNav);
		
		pnl.show(desk, true);
		
		//style
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle myStyle = new EditableStyle();
		EditableStyle myStyle2 = new EditableStyle();
		
		myStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		myStyle.setForegroundColor(Colors.WHITE);
		myStyle.setBackgroundColor(Colors.BLACK);
		
		ClassSelector labelSelector = new ClassSelector("LABEL");
		
		myStyle2.setBorder(new SimpleRectangularBorder(4));
		myStyle2.setBorderColor(Colors.BLUE);
		myStyle2.setMargin(new SimpleOutline(10));
		
		ClassSelector buttonSelector = new ClassSelector("BUTTON");
		
		sts.addRule(labelSelector, myStyle);
		sts.addRule(buttonSelector, myStyle2);
		
		desk.show();
		
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
