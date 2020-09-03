package ch.epfl.cs107.play.game.enigme.area;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {
	private Gold gold;
	private Potion potion;
	private Key key1 ;
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		List<DiscreteCoordinates> list = new ArrayList<>();
		setBehavior(new Demo2Behavior(window, "Enigme1"));
		// registerActor(new Background(this));
		for (int i = 1; i < 12; i++) {
			Teleporter rock = new Teleporter(this, new DiscreteCoordinates(i, 5), Orientation.DOWN);
			registerActor(rock);
		}
	

		OldMan vieux = new OldMan(this, Orientation.DOWN, new DiscreteCoordinates(6, 3));
		 gold = new Gold(this , Orientation.DOWN , new DiscreteCoordinates(14,12)); 
		 potion = new Potion(this, Orientation.DOWN , new DiscreteCoordinates(10,3));
		
		PressurePlate plate1 = new PressurePlate(this, Orientation.DOWN, new DiscreteCoordinates(18, 12));
		registerActor(plate1);
		SignalRock rock1 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(12, 4), potion);
		registerActor(rock1);
		 key1 = new Key (this, Orientation.DOWN, new DiscreteCoordinates(21,14));
		registerActor(key1);
		SignalDoor door = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates (4,4),
				Orientation.DOWN, new DiscreteCoordinates(23,16), key1);
		registerActor(door);


		SignalRock r2 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(23, 13), plate1);
		
		registerActor(r2);
		registerActor(potion);
		registerActor(vieux);

		registerActor(gold);

		return true;
	}
	public Dialog getText()
	{
		Dialog 	text = new Dialog(
				"Le feu est une execution, invisible est la solution! ",
				"dialog.3", this);
		if (potion.isOn())
		{ text=new Dialog(
				"Une potion, je peux m'approcher du feu?  ",
				"dialog.3", this);
			
		
		if (gold.isOn())
			text=new Dialog(
					"De l'or ! Mais, est ce la vraie richesse?  ",
					"dialog.3", this);
		if (key1.isOn())
			text=new Dialog(
					"Tout est bien qui finit bien!  ",
					"dialog.3", this);
		}
		return text;
	}

	public String getTitle() {
		return "Enigme1";
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 25;
	}

}
