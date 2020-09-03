package ch.epfl.cs107.play.game.areagame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {
	private final Image behaviorMap;
	private final int width, height;
	private final Cell[][] cells;

	public void cellInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates coord : interactor.getCurrentCells()) {
			cells[coord.x][coord.y].cellInteractionOf(interactor);
		}
	}

	public void viewInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
			if (isInGrid(interactor.getFieldOfViewCells().get(0))) {
				cells[coord.x][coord.y].viewInteractionOf(interactor);
			}
		}
	}

	/// The behavior is an Image of size height x width
	// TODO implements me #PROJECT #TUTO

	public Image getBehaviorMap() {
		return behaviorMap;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {

		for (int i = 0; i < coordinates.size(); i++) {

			cells[coordinates.get(i).x][coordinates.get(i).y].leave(entity);

		}
	}

	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {

		for (int i = 0; i < coordinates.size(); i++) {

			cells[coordinates.get(i).x][coordinates.get(i).y].enter(entity);

		}
	}

	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < coordinates.size(); i++) {
			if (!isInGrid(coordinates.get(i))) {
				return false;
			}

			if (!cells[coordinates.get(i).x][coordinates.get(i).y].canLeave(entity)) {
				return false;
			}

		}

		return true;

	}

	public boolean isInGrid(DiscreteCoordinates coordinates) {
		return (coordinates.x <= getWidth() && coordinates.x >= 0 && coordinates.y <= getHeight()
				&& coordinates.y >= 0);
	}

	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates)

	{
		Cell cell;

		for (int i = 0; i < coordinates.size(); i++) {

			if (!isInGrid(coordinates.get(i))) {
				return false;
			}

			cell = cells[coordinates.get(i).x][coordinates.get(i).y];
			if (!cells[coordinates.get(i).x][coordinates.get(i).y].canEnter(entity)) {
				return false;
			}
			for (Interactable ac : cell.getEntities()) {
				if (ac.takeCellSpace())
					return false;
			}
		}

		return true;

	}

	public abstract class Cell implements Interactable {
		DiscreteCoordinates coordinates;
		protected Set<Interactable> entities;

		public Set<Interactable> getEntities() {
			return entities;
		}

		private void cellInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isCellInteractable()) {

					interactor.interactWith(interactable);
					interactor.interactWith(this);
				}
			}
		}

		private void viewInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isViewInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}

		@Override
		public abstract boolean takeCellSpace();

		@Override
		public abstract boolean isViewInteractable();

		@Override
		public abstract boolean isCellInteractable();

		private void enter(Interactable i) {
			entities.add(i);
		}

		private void leave(Interactable i) {
			entities.remove(i);
		}

		protected abstract boolean canEnter(Interactable entity);

		protected abstract boolean canLeave(Interactable entity);

		public Cell(int x, int y) {
			coordinates = new DiscreteCoordinates(x, y);
			entities = new HashSet<>();

		}

		@Override
		public List<DiscreteCoordinates> getCurrentCells() {
			return Collections.singletonList(new DiscreteCoordinates(coordinates.x, coordinates.y));

		}
	}

	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window
	 *            (Window): graphic context, not null
	 * @param fileName
	 *            (String): name of the file containing the behavior image, not null
	 */
	public AreaBehavior(Window window, String fileName) {
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
		// TODO implements me #PROJECT #TUTO
	}

	// TODO implements me #PROJECT #TUTO

}
