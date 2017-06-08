package esgi.moc.activities;

import ej.style.font.FontProfile;
import ej.style.font.loader.AbstractFontLoader;

public class RobotoFontLoader extends AbstractFontLoader {

	@Override
	protected int getFontHeight(FontProfile fontProfile) {
		switch (fontProfile.getSize()) {
			case LARGE:	return 50;
			default:	return 30;
		}
	}

}
