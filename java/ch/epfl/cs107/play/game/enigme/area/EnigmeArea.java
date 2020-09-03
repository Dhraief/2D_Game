package ch.epfl.cs107.play.game.enigme.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class EnigmeArea extends Area {
	public  List<Teleporter> teleporters=new ArrayList<>();

	public boolean begin(Window window, FileSystem fileSystem) {

		super.begin(window, fileSystem);
		setBehavior(new EnigmeBehavior(window, getTitle()));
		registerActor(new Background(this));

		return true;
	}

	@Override
	public String getTitle() {
		if (this instanceof Level1) {
			return "Level1";
		} else if (this instanceof Level2) {
			return "Level2";
		} else if (this instanceof Level3) {
			return "Level3";
		}
		return "LevelSelector";

	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 15;

	}

}
