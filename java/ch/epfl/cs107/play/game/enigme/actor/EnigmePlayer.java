package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {

	private Door lastDoor;
	private Teleporter lastTeleporter;
	private Boolean isBeingTeleported = false;
	private final static int ANIMATION_DURATION = 8;
	private final EnigmePlayerHandler handler = new EnigmePlayerHandler();
	private Area ownerArea;
	private Sprite[] direction;
	private int bas = 0;
	private int haut = 0;
	private int droite = 0;
	private int gauche = 0;
	private Window window;
	private FileSystem fileSystem;
	private Sprite s;
	private boolean isPassingDoor;
	boolean viewInteraction;


	public void begin(Window window, FileSystem fileSystem) {
		this.fileSystem = fileSystem;
		this.window = window;

	}

	public Boolean getIsBeingTeleported() {
		return isBeingTeleported;
	}

	public void setCurrentMainCellCoordinates(DiscreteCoordinates c) {
		super.setCurrentMainCellCoordinates(c);
	}

	public List<DiscreteCoordinates> getFieldOfViewCells() {
		if (getOrientation().equals(Orientation.RIGHT)) {
			return Collections.singletonList(new DiscreteCoordinates((int) getPosition().x + 1, (int) getPosition().y));
		}
		if (getOrientation().equals(Orientation.LEFT)) {
			return Collections.singletonList(new DiscreteCoordinates((int) getPosition().x - 1, (int) getPosition().y));
		}
		if (getOrientation().equals(Orientation.DOWN)) {
			return Collections.singletonList(new DiscreteCoordinates((int) getPosition().x, (int) getPosition().y - 1));
		}
		if (getOrientation().equals(Orientation.UP)) {
			return Collections.singletonList(new DiscreteCoordinates((int) getPosition().x, (int) getPosition().y + 1));
		}

		else
			return null;
	}

	protected void setViewInteraction(boolean b) {
		viewInteraction = b;
	}

	
	public void update(float deltaTime) {
		super.update(deltaTime);
		draw(window);
		Keyboard keyboard = window.getKeyboard();

		Button button = keyboard.get(Keyboard.L);

		if (button.isDown()) {

			this.setViewInteraction(true);

		} else {
			this.setViewInteraction(false);
		}

		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button UPArrow = keyboard.get(Keyboard.UP);
		Button downArrow = keyboard.get(Keyboard.DOWN);

		if (!getIsMoving()) {
			gauche = 0;
			droite = 0;
			haut = 0;
			bas = 0;

		}

		if (leftArrow.isDown()) {
			if (this.getOrientation() == Orientation.LEFT) {
				gauche++;
				move(ANIMATION_DURATION);
			} else if (!getIsMoving()) {
				this.setOrientation(Orientation.LEFT);

			}
		}

		else if (rightArrow.isDown()) {
			if (this.getOrientation() == Orientation.RIGHT) {
				move(ANIMATION_DURATION);
				droite++;
			} else if (!getIsMoving())
				this.setOrientation(Orientation.RIGHT);
		}

		else if (UPArrow.isDown()) {
			if (this.getOrientation() == Orientation.UP) {
				move(ANIMATION_DURATION);
				haut++;
			} else if (!getIsMoving())
				this.setOrientation(Orientation.UP);

		}

		else if (downArrow.isDown()) {
			if (this.getOrientation() == Orientation.DOWN) {
				bas++;
				move(ANIMATION_DURATION);
			}

			else if (!getIsMoving())
				this.setOrientation(Orientation.DOWN);

		}
	}

	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// s = new Sprite("ghost.1", 1, 1.f, this);
		// TODO Auto-generated constructor stub
	}

	private Sprite[] spritesDOWN = new Sprite[4];
	private Sprite[] spritesUP = new Sprite[4];
	private Sprite[] spritesLEFT = new Sprite[4];
	private Sprite[] spritesRIGHT = new Sprite[4];

	public EnigmePlayer(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		Vector anchor = new Vector(0.25f, 0.32f);

		for (int i = 0; i < 4; i++)
			spritesDOWN[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(16 * 0, i * 21, 16, 21),
					anchor);

		for (int i = 0; i < 4; i++)
			spritesUP[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(16 * 2, i * 21, 16, 21),
					anchor);

		for (int i = 0; i < 4; i++)
			spritesRIGHT[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this,
					new RegionOfInterest(16 * 3, i * 21, 16, 21), anchor);

		for (int i = 0; i < 4; i++)
			spritesLEFT[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(16 * 1, i * 21, 16, 21),
					anchor);

		// s = new Sprite("ghost.1", 1, 1.f, this);

	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		this.setOwnerArea(area);
		this.setCurrentPosition(position.toVector());
		isPassingDoor = false;
		resetMotion();
	}

	public void leaveArea() {
		this.getOwnerArea().unregisterActor(this);
	}

	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {

		public void interactWith(Door door) {
			setIsPassingDoor(door);
		}

		public void interactWith(Apple apple) {

			apple.isCollected();

		}
		
		public void interactWith(Potion potion) {
			potion.isCollected();
		}

		public void interactWith(Key key) {
			key.isCollected();
		}

		public void interactWith(Teleporter tp) {
			tp.setGameOn();
			
		}

		public void interactWith(PressurePlate pressurePlate) {
			pressurePlate.changeActivatedState();
		}
		
		public void interactWith(Gold gold) {
			gold.isCollected();
		}

		public void interactWith(PressureSwitch pressureSwitch) {

			pressureSwitch.changedSwitchState();

		}

		public void interactWith(Lever lever) {
			lever.changeSwitchState();
		}

		public void interactWith(Torch torch) {
			torch.changedSwitchState();
		}

	}

	public boolean getIsPassingDoor() {

		return isPassingDoor;
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
		return false;
	}

	public Teleporter getLastTeleporter() {

		return lastTeleporter;
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

	protected void setIsPassingDoor(Door door) {
		if (this.getCurrentMainCellCoordinates().equals(door.getCurrentMainCellCoordinates())) {
			isPassingDoor = true;
			setIsLastDoor(door);
		}
	}

	public Door getLastDoor() {
		return lastDoor;
	}

	protected void setIsLastDoor(Door door) {
		lastDoor = door;
	}

	@Override
	public boolean wantsCellInteraction() {

		return true;
	}

	@Override
	public boolean wantsViewInteraction() {

		return viewInteraction;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {

		if (getOrientation().equals(Orientation.DOWN)) {

			spritesDOWN[bas % 4].draw(canvas);
		} else if (getOrientation().equals(Orientation.UP)) {
			spritesUP[haut % 4].draw(canvas);
		} else if (getOrientation().equals(Orientation.RIGHT)) {
			spritesRIGHT[droite % 4].draw(canvas);
		} else if (getOrientation().equals(Orientation.LEFT)) {
			spritesLEFT[gauche % 4].draw(canvas);
		}

	}

}
