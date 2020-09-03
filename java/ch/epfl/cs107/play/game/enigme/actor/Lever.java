package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends Switcher {
	private final DiscreteCoordinates DESACTIVATED_POSITION;
	private boolean switchState;

	public Lever(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		DESACTIVATED_POSITION = position;
		switchState = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (switchState) {
			sprite = new Sprite("lever.small.left", 1, 1.0f, this);
		} else {
			sprite = new Sprite("lever.small.right", 1, 1.0f, this);
		}
		sprite.draw(canvas);

	}

	public void changeSwitchState() {
		switchState = !switchState;
		if (switchState) {
			position = new DiscreteCoordinates(DESACTIVATED_POSITION.x - 1, DESACTIVATED_POSITION.y);
		} else
			position = DESACTIVATED_POSITION;
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		((EnigmeInteractionVisitor) v).interactWith(this);

	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return switchState;
	}

}
