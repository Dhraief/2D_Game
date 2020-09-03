package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends Switcher {

	private boolean switchState;

	public PressureSwitch(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
		switchState = false;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (switchState) {
			sprite = new Sprite("GroundLightOn", 1, 1.f, this);
		} else {
			sprite = new Sprite("GroundLightOff", 1, 1.f, this);
		}
		sprite.draw(canvas);
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub

		return true;
	}

	public void changedSwitchState() {

		switchState = (!switchState);
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
