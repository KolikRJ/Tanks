package whitebrains.pane;

import java.io.FileInputStream;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import whitebrains.icon.IconCloud;

public class FilePane extends GridPane {

	private ImageView image;
	private VBox box;
	private Pane pane;
	private Label label;
	private ProgressBar bar;

	public FilePane(String path) throws Exception {

		String type = path.toLowerCase().substring(path.length() - 3);

		pane = new Pane();
		pane.setMinSize(60, 40);
		for (String str : IconCloud.imageType()) {
			if (type.equals(str)) {
				FileInputStream stream = new FileInputStream(path);
				image = new ImageView(new Image(stream));
				image.setFitWidth(60);
				image.setFitHeight(40);
				image.setSmooth(true);
				stream.close();
				break;
			} else {
				image = new ImageView(IconCloud.getIcon(type));
				image.setScaleX(0.8);
				image.setScaleY(0.8);
				image.setSmooth(true);
			}
		}
		pane.getChildren().add(image);
		box = new VBox();
		label = new Label(path);
		label.setFont(new Font(14));
		bar = new ProgressBar();
		bar.setMinWidth(280);
		box.getChildren().addAll(label, bar);
		add(pane, 0, 0);
		add(box, 1, 0);

	}

	public void setProgress(double progress) {
		bar.setProgress(progress);
	}

}
