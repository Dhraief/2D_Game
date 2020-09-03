package ch.epfl.cs107.play.game.enigme.area;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);


		Door door;
		door = new Door(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.DOWN, new DiscreteCoordinates(1, 7));
		this.registerActor(door);

		for (int i = 2; i < 9; i++) {

			if (i == 2) {
				door = new Door(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.DOWN,
						new DiscreteCoordinates(i, 7));
			} else if (i == 3) {
				door = new Door(this, "Level3", new DiscreteCoordinates(5, 1), Orientation.DOWN,
						new DiscreteCoordinates(i, 7));
			} else if (i == 4) {
				door = new Door(this, "Enigme1", new DiscreteCoordinates(5, 1), Orientation.DOWN,
						new DiscreteCoordinates(i, 7));
			} else {
				door = new Door(this, "Enigme1", new DiscreteCoordinates(5, 1), Orientation.DOWN,
						new DiscreteCoordinates(i, 7));

			}
			registerActor(door);

		}

		return true;
	}

}
