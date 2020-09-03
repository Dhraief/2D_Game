package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
	Demo2Area room0 = new Room0();
	Demo2Area room1 = new Room1();
	Demo2Player perso;
	public boolean gameOn()
	{
		return true;
	}

	@Override
	public void update(float deltaTime) {

		super.update(deltaTime);
		perso.update(deltaTime);

		if (perso.getIsDoorCrossing()) {
			if (this.getCurrentArea().equals(room0)) {
				try {

					this.setCurrentArea(room1.getTitle(), false);
					perso.leaveArea();

					perso.enterArea(room1, new DiscreteCoordinates(5, 2));


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (this.getCurrentArea().equals(room1)) {
				try {
					perso.leaveArea();

					this.setCurrentArea(room0.getTitle(), false);
					perso.enterArea(room0, new DiscreteCoordinates(5, 5));


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	// TODO implements me #PROJECT #TUTO

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo2";
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements me #PROJECT #TUTO
		super.begin(window, fileSystem);
		addArea(room0);
		addArea(room1);

		try {
			setCurrentArea(room0.getTitle(), true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		room0.begin(window, fileSystem);
		room1.begin(window, fileSystem);
		perso = new Demo2Player(getCurrentArea(), new DiscreteCoordinates(4, 4));
		perso.begin(window, fileSystem);

		getCurrentArea().setViewCandidate(perso);

		return true;
	}

}