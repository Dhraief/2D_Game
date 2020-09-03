package ch.epfl.cs107.play.game.enigme.area;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);

		setBehavior(new Demo2Behavior(window, getTitle()));
		registerActor(new Background(this));

		Door door;
		door = new Door(this, "LevelSelector", new DiscreteCoordinates(1, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 0));

		registerActor(door);

		Apple apple = new Apple(this, Orientation.DOWN, new DiscreteCoordinates(5, 6));
		registerActor(apple);

		return true;
	}

}
