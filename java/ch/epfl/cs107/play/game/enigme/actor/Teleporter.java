package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
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

public class Teleporter extends AreaEntity implements Logic{

	private DiscreteCoordinates firstCoordinates, secondCoordinates;
	private boolean gameOn= true;
	private List<DiscreteCoordinates> list = new ArrayList<>();
	private Area ownerArea;
	private Sprite sprite= new Sprite ("fire.on.1",1,1,this);

	public DiscreteCoordinates getFirstCoordinates() {
		return firstCoordinates;
	}

	protected void setFirstCoordinates(DiscreteCoordinates d) {
		this.firstCoordinates = d;
	}
	public void setGameOn()
	{
		gameOn=!gameOn;
	}
	public boolean  getGameOn()
	{
		return gameOn;
	}

	public DiscreteCoordinates getSecondCoordinates() {
		return secondCoordinates;
	}
	
	protected void setSecondCoordinates(DiscreteCoordinates d) {
		this.secondCoordinates = d;
	}

	public Teleporter(Area currentArea, DiscreteCoordinates firstCoordinates, Orientation orientation) {

		super(currentArea, orientation, firstCoordinates);
		this.list = Collections.singletonList( new DiscreteCoordinates(firstCoordinates.x, firstCoordinates.y));;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		sprite.draw(canvas);

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

		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return gameOn;
	}

}
