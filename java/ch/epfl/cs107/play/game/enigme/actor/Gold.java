package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Gold extends Collectable implements Logic, Interactable {

	private Sprite sprite;
	private Window window;
	private FileSystem fileSystem;

	public Gold(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
		sprite = new Sprite("ingot.1", 1, 1.0f, this);

	}

	/*
	 * public void begin(Window window , FileSystem fileSystem) {
	 * this.fileSystem=fileSystem; this.window = window; }
	 */

	@Override
	public float getIntensity(float t) {
		// TODO Auto-generated method stub
		if (collected) {
			return 1.0f;
		} else {
			return 0.0f;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		sprite.draw(canvas);

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
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return this.collected;
	}

}
