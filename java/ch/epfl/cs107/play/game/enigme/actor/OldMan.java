package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class OldMan extends AreaEntity {

	private Sprite vieux;

	public OldMan(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		Vector anchor = new Vector(0.25f, 0.32f);

		vieux = new Sprite("old.man.1", 0.5f, 0.65625f, this, new RegionOfInterest(0, 0 * 21, 16, 21), anchor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Canvas canvas) {

		vieux.draw(canvas);
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
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

}
