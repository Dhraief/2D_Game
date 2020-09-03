package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {
	public abstract List<DiscreteCoordinates> getCurrentCells();

	public abstract boolean takeCellSpace();

	public abstract boolean isViewInteractable();

	public abstract boolean isCellInteractable();
	/// an AreaEntity knows its own Area

	protected Area ownerArea;
	/// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	/// Coordinate of the main Cell linked to the entity
	private DiscreteCoordinates currentMainCellCoordinates;

	protected Orientation getOrientation() {
		return orientation;
	}

	protected void setOrientation(Orientation a) {
		orientation = a;
	}

	// TODO implements me #PROJECT #TUTO

	/**
	 * Default AreaEntity constructor
	 * 
	 * @param area
	 *            (Area): Owner area. Not null
	 * @param orientation
	 *            (Orientation): Initial orientation of the entity in the Area. Not
	 *            null
	 * @param position
	 *            (DiscreteCoordinate): Initial position of the entity in the Area.
	 *            Not null
	 */
	protected void setCurrentMainCellCoordinates(DiscreteCoordinates c) {
		currentMainCellCoordinates = c;
	}

	public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {

		super(position.toVector());
		this.orientation = orientation;
		this.ownerArea = area;
		currentMainCellCoordinates = position;
		// TODO implements me #PROJECT #TUTO
	}

	/**
	 * Getter for the coordinates of the main cell occupied by the AreaEntity
	 * 
	 * @return (DiscreteCoordinates)
	 */
	public DiscreteCoordinates getCurrentMainCellCoordinates() {
		// TODO implements me #PROJECT #TUTO
		return currentMainCellCoordinates;
	}

	protected void setCurrentPosition(Vector v) {
		if (DiscreteCoordinates.isCoordinates(v)) {
			super.setCurrentPosition(v);
			v = v.round();
			currentMainCellCoordinates = new DiscreteCoordinates((int) v.x, (int) v.y);

		} else {

			super.setCurrentPosition(v);
		}

	}

	protected Area getOwnerArea() {
		return ownerArea;
	}

	protected void setOwnerArea(Area a) {
		ownerArea = a;
	}

}
