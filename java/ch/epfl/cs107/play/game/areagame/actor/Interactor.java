package ch.epfl.cs107.play.game.areagame.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some
 * Interactable)
 * 
 * @see Interactable This interface makes sense only in the "Area Context" with
 *      Actor contained into Area Cell
 */
public interface Interactor {

	// TODO implements me #PROJECT #TUTO
	public List<DiscreteCoordinates> currentCells = new ArrayList<>();
	public List<DiscreteCoordinates> fieldOfViewCells = new ArrayList<>();

	public default List<DiscreteCoordinates> getCurrentCells() {
		return currentCells;
	}

	public List<DiscreteCoordinates> getFieldOfViewCells();

	boolean wantsCellInteraction();

	boolean wantsViewInteraction();

	void interactWith(Interactable other);

}