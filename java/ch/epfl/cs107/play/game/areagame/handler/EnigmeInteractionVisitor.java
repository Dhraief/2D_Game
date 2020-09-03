package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.game.enigme.actor.Torch;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

	default void interactWith(Apple apple) {
		// by default the interaction is empty
	}

	
	default void interactWith(Potion potion) {
		
	}
	
	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		// by default the interaction is empty
	}

	default void interactWith(Door door) {
		// by default the interaction is empty
	}

	default void interactWith(Key key) {
		// by default the interaction is empty
	}

	default void interactWith(Torch torch) {
		// by default the interaction is empty
	}

	default void interactWith(PressurePlate pressurePlate) {
		// by default the interaction is empty
	}

	default void interactWith(Teleporter tp) {
		// by default the interaction is empty
	}

	default void interactWith(PressureSwitch pressureSwitch) {
		// by default the interaction is empty
	}

	default void interactWith(Lever lever) {
		// by default the interaction is empty
	}
	default void interactWith(Gold gold) {
		
	}

}