package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalRock extends AreaEntity {

	private Logic s;
	private boolean nonTraversable;
	private boolean visible;
	private Sprite rock;

	public SignalRock(Area area, Orientation orientation, DiscreteCoordinates position, Logic s) {
		super(area, orientation, position);
		this.s = s;
		rock = new Sprite("rock.3", 1, 1.0f, this);
		setTravesableVisible();

	}

	private void setTravesableVisible() {
		if (s.isOn()) {
			nonTraversable = true;
			visible = false;
		} else {
			nonTraversable = false;
			visible = true;
		}
	}

	public void update(float deltaTime) {
		setTravesableVisible();
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		if (visible) {
			rock.draw(canvas);
		}
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		List<DiscreteCoordinates> list = new ArrayList<>();
		list.add(this.getCurrentMainCellCoordinates());
		return list;
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return !nonTraversable;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

}
