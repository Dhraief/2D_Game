package game.areagame.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	// toutes les interactions ici
	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		// by default the interaction is empty
	}

	default void interactWith(Apple apple) {
		// by default the interaction is empty
	}
}
