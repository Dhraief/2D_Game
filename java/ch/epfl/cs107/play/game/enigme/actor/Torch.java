package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Torch extends Switcher {
	private Area currentArea;
	private DiscreteCoordinates position;
	private boolean switchState;

	public Torch(Area area, Orientation orientation, DiscreteCoordinates position, boolean b) {
		super(area, orientation, position);
		switchState = b;
	}

	@Override
	public float getIntensity(float t) {
		// TODO Auto-generated method stub
		if (switchState)
			return 1.0f;
		return 0.0f;
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
		((EnigmeInteractionVisitor) v).interactWith(this);

		// TODO Auto-generated method stub

	}

	public void changedSwitchState() {
		switchState = (!switchState);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (switchState) {
			sprite = new Sprite("torch.ground.on.1", 1, 1.f, this);
		} else {
			sprite = new Sprite("torch.ground.off", 1, 1.f, this);
		}
		sprite.draw(canvas);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return switchState;
	}

}
