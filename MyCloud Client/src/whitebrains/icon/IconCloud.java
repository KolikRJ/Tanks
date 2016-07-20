package whitebrains.icon;

import java.io.FileInputStream;

import javafx.scene.image.Image;

public class IconCloud {

	private static final String EXE = "PNG/exe.png";
	private static final String ISO = "PNG/iso.png";
	private static final String MP3 = "PNG/mp3.png";
	private static final String JAR = "PNG/jar.png";

	public static String[] imageType() {
		return new String[] { "png", "gif", "jpg" };
	}

	public static Image getIcon(String type) {
		Image img = null;
		try {
			switch (type) {
			case "exe":
				img = new Image(new FileInputStream(EXE));
				break;
			case "iso":
				img = new Image(new FileInputStream(ISO));
				break;
			case "mp3":
				img = new Image(new FileInputStream(MP3));
				break;
			default:
				img = new Image(new FileInputStream(JAR));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}

}
