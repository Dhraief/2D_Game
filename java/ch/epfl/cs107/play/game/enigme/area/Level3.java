package ch.epfl.cs107.play.game.enigme.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {

	public boolean begin(Window window, FileSystem fileSystem) {

		super.begin(window, fileSystem);

		Key key = new Key(this, Orientation.DOWN, new DiscreteCoordinates(1, 3));
		registerActor(key);

		Torch torch = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(7, 5), false);
		registerActor(torch);

		PressureSwitch Switch1 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4, 4));
		registerActor(Switch1);
		PressureSwitch Switch2 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5, 4));
		registerActor(Switch2);
		PressureSwitch Switch3 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6, 4));
		registerActor(Switch3);
		PressureSwitch Switch4 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5, 5));
		registerActor(Switch4);
		PressureSwitch Switch5 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4, 6));
		registerActor(Switch5);
		PressureSwitch Switch6 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5, 6));
		registerActor(Switch6);
		PressureSwitch Switch7 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6, 6));
		registerActor(Switch7);

		PressurePlate plate1 = new PressurePlate(this, Orientation.DOWN, new DiscreteCoordinates(9, 8));
		registerActor(plate1);

		Lever lever1 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(10, 5));
		registerActor(lever1);
		Lever lever2 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(9, 5));
		registerActor(lever2);
		Lever lever3 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(8, 5));
		registerActor(lever3);

		List<DiscreteCoordinates> list = new ArrayList<>();

		List<Logic> list2 = new ArrayList<>();
		list2.add(Switch1);
		list2.add(Switch2);
		list2.add(Switch3);
		list2.add(Switch4);
		list2.add(Switch5);
		list2.add(Switch6);
		list2.add(Switch7);

		MultipleAnd p = new MultipleAnd(list2);

		List<Logic> list3 = new ArrayList<>();
		list3.add(lever1);
		list3.add(new Not(lever2));
		list3.add(lever3);
		MultipleAnd p1 = new MultipleAnd(list3);

		SignalDoor door = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.UP,
				new DiscreteCoordinates(5, 9), key);

		registerActor(door);

		SignalRock r1 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(4, 8), p);
		SignalRock r2 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(5, 8), plate1);
		SignalRock r3 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6, 8), new Or(p1, torch));

		registerActor(r1);
		registerActor(r2);
		registerActor(r3);

		return true;
	}

}
