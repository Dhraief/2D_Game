package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door {
	private Logic signal;
	private Sprite sprite;

	public SignalDoor(Area currentArea, String destinationArea, DiscreteCoordinates arrivalCoordinates,
			Orientation orientation, DiscreteCoordinates mainCellPosition, 
			Logic signal) {
		super(currentArea, destinationArea, arrivalCoordinates, orientation, mainCellPosition);
		// TODO Auto-generated constructor stu
		this.signal = signal;
		setSprite();
	}

	public void setSprite() {
		if (signal.isOn()) {
			sprite = new Sprite("door.open.1", 1, 1.f, this);
		} else {
			sprite = new Sprite("door.close.1", 1, 1f, this);
		}
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		setSprite();
		sprite.draw(canvas);
	}

	public void update(float deltaTime) {
		setSprite();
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		if (signal.isOn()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
