package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
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

public class PressurePlate extends AreaEntity implements Logic, Interactable {
	private Window window;
	private FileSystem fileSystem;
	private Sprite pressurePlate;
	private boolean activated;

	private float nb;

	public void changeActivatedState() {
		if (!activated) {
			activated = true;
			nb = 60;
		}

	}

	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.activated = false;
		pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
		// TODO Auto-generated constructor stub
	}

	public void begin(Window window, FileSystem fileSystem) {
		this.fileSystem = fileSystem;
		this.window = window;
	}

	@Override
	public float getIntensity(float t) {
		if (activated) {
			return 1.0f;
		}
		return 0.0f;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		 return
		Collections.singletonList( new DiscreteCoordinates(this.getCurrentMainCellCoordinates().x,getCurrentMainCellCoordinates().y));
		
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

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		if (activated && nb != 0) {
			pressurePlate = new Sprite("GroundLightOn", 1, 1.f, this);
		} else {
			pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
		}
		pressurePlate.draw(canvas);
		if (nb > 0) {
			nb--;
		}
		if (nb == 0) {
			activated = false;
		}
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return activated;
	}

}
