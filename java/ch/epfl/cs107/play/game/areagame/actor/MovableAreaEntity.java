package ch.epfl.cs107.play.game.areagame.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {
	/// Indicate if the actor is currently moving
	private DiscreteCoordinates targetMainCellCoordinates;

	private boolean isMoving;
	/// Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;

	/// The target cell (i.e. where the mainCell will be after the motion)

	public boolean getIsMoving() {
		return isMoving;
	}

	protected final List<DiscreteCoordinates> getLeavingCells() {
		return getCurrentCells();
	}

	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> listLeave = getLeavingCells();
		List<DiscreteCoordinates> listEnter = new ArrayList<>();
		DiscreteCoordinates newCoord;

		for (DiscreteCoordinates ac : listLeave) {
			newCoord = ac.jump(getOrientation().toVector());
			if (getOwnerArea().getAreaBehavior().getWidth() > newCoord.x
					&& getOwnerArea().getAreaBehavior().getHeight() > newCoord.y) {

				listEnter.add(newCoord);
			}
		}

		return listEnter;
	}

	// TODO implements me #PROJECT #TUTO

	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area
	 *            (Area): Owner area. Not null
	 * @param position
	 *            (Coordinate): Initial position of the entity. Not null
	 * @param orientation
	 *            (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);

		resetMotion();
		// TODO implements me #PROJECT #TUTO
	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		// TODO implements me #PROJECT #TUTO
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();

	}

	/**
	 * 
	 * @param frameForMove
	 *            (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove) {
		// TODO implements me #PROJECT #TUTO

		// if (!isMoving ||
		// getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {

		if (!isMoving || targetMainCellCoordinates.toVector().equals(getPosition())) {

			if (getOwnerArea().enterAreaCells((Interactable) this, getEnteringCells())
					&& getOwnerArea().leaveAreaCells((Interactable) this, getLeavingCells())) {

				framesForCurrentMove = (1 > framesForMove) ? 1 : framesForMove;
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);

				isMoving = true;
				return true;

			}

			return false;
		}

		return false;

		// ADD AREA CONDITIONS
	}

	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		// TODO implements me #PROJECT #TUTO*
		// if (isMoving &&
		// !getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
		if (isMoving && !targetMainCellCoordinates.toVector().equals(getPosition())) {
			Vector distance = getOrientation().toVector();

			distance = distance.mul(1.0f / framesForCurrentMove);

			setCurrentPosition(getPosition().add(distance));

		} else {
			resetMotion();
		}
	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {

		return getOrientation().toVector().mul(framesForCurrentMove);

	}
}