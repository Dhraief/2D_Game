package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Collectable extends AreaEntity {
	private Sprite sprite;

	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
	}

	boolean collected = false;

	protected  void isCollected() {
		if (!collected) {
			collected = true;
			this.getOwnerArea().unregisterActor(this);
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public abstract void draw(Canvas canvas);

	@Override
	public abstract List<DiscreteCoordinates> getCurrentCells();

	@Override
	public abstract boolean takeCellSpace();

	@Override
	public abstract boolean isViewInteractable();

	@Override
	public abstract boolean isCellInteractable();

}