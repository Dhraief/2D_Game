package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo2Player extends MovableAreaEntity {
	private final static int ANIMATION_DURATION = 8;
	private Sprite s;
	private boolean isDoorCrossing;
	Window window;
	FileSystem fileSystem;

	public void begin(Window window, FileSystem fileSystem) {
		this.fileSystem = fileSystem;
		this.window = window;
	}

	public void setCurrentMainCellCoordinates(DiscreteCoordinates c) {
		super.setCurrentMainCellCoordinates(c);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		draw(window);

		Keyboard keyboard = window.getKeyboard();
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button UPArrow = keyboard.get(Keyboard.UP);
		Button downArrow = keyboard.get(Keyboard.DOWN);
		
		if (leftArrow.isDown()) {
			if (this.getOrientation() == Orientation.LEFT) {
				move(ANIMATION_DURATION);
			} else if(!getIsMoving())
				this.setOrientation(Orientation.LEFT);
		}
		
		else if (rightArrow.isDown()) {
			if (this.getOrientation() == Orientation.RIGHT)
				move(ANIMATION_DURATION);
			else if(!getIsMoving())
				this.setOrientation(Orientation.RIGHT);
		}
	
		else if (UPArrow.isDown()) {
			if (this.getOrientation() == Orientation.UP)
				move(ANIMATION_DURATION);
			else if(!getIsMoving())
				this.setOrientation(Orientation.UP);
		}
		
		else if (downArrow.isDown()) {
			if (this.getOrientation() == Orientation.DOWN)
				move(ANIMATION_DURATION);
			else if (!getIsMoving())
				this.setOrientation(Orientation.DOWN);
		}
	}

	public Demo2Player(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		s = new Sprite("ghost.1", 1, 1.f, this);

		// TODO Auto-generated constructor stub
	}

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		s = new Sprite("ghost.1", 1, 1.f, this);

	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		this.setOwnerArea(area);
		this.setCurrentPosition(position.toVector());
		resetMotion();
	}

	public void leaveArea() {
		this.getOwnerArea().unregisterActor(this);

	}

	public boolean getIsDoorCrossing() {
		return isDoorCrossing;
	}

	public void setIsDoorCrossing() {

		Demo2Behavior ab = (Demo2Behavior) (getOwnerArea().getAreaBehavior());
		Cell[][] c = ab.getCells();

		for (DiscreteCoordinates dc : getEnteringCells()) {

			if (!(c[dc.x][dc.y] instanceof Demo2Cell)) {
				isDoorCrossing = false;
			} else {
				isDoorCrossing = ((Demo2Cell) c[dc.x][dc.y]).type == Demo2CellType.DOOR;
			}

		}
	}

	@Override
	protected boolean move(int framesForMove) {
		setIsDoorCrossing();
		super.move(framesForMove);
		return true;

	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
	}

	public void draw(Window window) {
		// TODO Auto-generated method stub
		s.draw(window);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub

	}

}