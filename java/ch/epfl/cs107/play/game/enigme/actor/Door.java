package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Door extends AreaEntity {
	private Logic signal;
	private String destinationArea;
	private DiscreteCoordinates arrivalCoordinates;
	private List<DiscreteCoordinates> list = new ArrayList<>();
	private Area ownerArea;

	public DiscreteCoordinates getArrivalCoordinates() {
		return arrivalCoordinates;
	}

	public Door(Area currentArea, String destinationArea, DiscreteCoordinates arrivalCoordinates,
			Orientation orientation, DiscreteCoordinates mainCellPosition) {

		super(currentArea, orientation, mainCellPosition);
		this.destinationArea = destinationArea;
		this.arrivalCoordinates = arrivalCoordinates;
		this.list = Collections.singletonList( mainCellPosition);
	}

	public String getDestinationArea() {
		return destinationArea;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return list;
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
		/*
		 * if (signal.getIntensity(0)==1) return true; if (signal.getIntensity(0)==0)
		 * return false;
		 */
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
