package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

	public enum Demo2CellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		Demo2CellType(int type) {
			this.type = type;
		}

		static Demo2CellType toType(int type) {
			switch (type) {
			case -16777216:
				return WALL;
			case -65536:
				return DOOR;
			case -16776961:
				return WATER;
			case -1:
				return INDOOR_WALKABLE;
			case -14112955:
				return OUTDOOR_WALKABLE;
			default:
				return NULL;
			}
		}
	}

	public class Demo2Cell extends Cell {
		public Demo2CellType type;

		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			this.type = type;
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
		protected boolean canEnter(Interactable entity) {
			if (type == Demo2CellType.WALL || type == Demo2CellType.NULL) {
				return false;
			}

			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean takeCellSpace() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// TODO Auto-generated method stub

		}

	}

	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		Cell[][] cells2 = getCells();
		for (int x = 0; x < cells2.length; x++) {
			for (int y = 0; y < cells2[x].length; y++) {
				Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - y, x));

				cells2[x][y] = new Demo2Cell(x, y, cellType);
			}
		}
	}

}
