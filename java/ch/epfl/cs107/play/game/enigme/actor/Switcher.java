package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class Switcher extends AreaEntity implements Interactable, Logic {

	protected List<DiscreteCoordinates> currentCells = new ArrayList<>();

	protected Switcher(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		currentCells.add(position);
		// TODO Auto-generated constructor stub
	}

	protected Area currentArea;
	protected Window window;
	protected FileSystem fileSystem;
	protected DiscreteCoordinates position;
	protected Sprite sprite;

	@Override
	public float getIntensity(float t) {
		// TODO Auto-generated method stub
		if (switchState)
			return 1.0f;
		return 0.0f;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return currentCells;
	}

	public Area getCurrentArea() {
		return currentArea;

	}

	public void setCurrentArea(Area a) {
		currentArea = a;
	}

	public void setPosition(DiscreteCoordinates a) {
		position = a;
	}

	public boolean getSwitchState() {
		return switchState;
	}

	protected boolean switchState;

	public abstract boolean takeCellSpace();

	public abstract boolean isViewInteractable();

	public abstract boolean isCellInteractable();

	public abstract void acceptInteraction(AreaInteractionVisitor v);

}
