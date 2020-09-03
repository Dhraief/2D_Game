package ch.epfl.cs107.play.game.enigme;



import ch.epfl.cs107.play.game.Playable;

import ch.epfl.cs107.play.game.areagame.AreaGame;

import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.Level4;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import ch.epfl.cs107.play.window.Window;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the
 * notion of Player When initializing the player is added to the current area
 */
public class Enigme extends AreaGame implements Playable {
    // This is the period for which the dialogue stays visible
	private int h = 40;
	Level1 level1 = new Level1();
	Level2 level2 = new Level2();
	Level3 level3 = new Level3();
	Level4 level4 = new Level4();
	LevelSelector levelSelector = new LevelSelector();
	EnigmePlayer player;
	private boolean gameOn=true;

	/// The player is a concept of RPG games
	// TODO implements me #PROJECT

	/// Enigme implements Playable
	
	public boolean  gameOn() {
		boolean test=true;
		if (this.getCurrentArea() instanceof EnigmeArea)
		for (Teleporter ac : ((EnigmeArea)getCurrentArea()).teleporters)
	
		test=test&& ac.getGameOn();	
		return test;
		}
			
	

	

	public String getTitle() {
		return "Enigme";
	}

	@Override
	public void update(float deltaTime) {
		// TODO implements me #PROJEC
		super.update(deltaTime);
		this.getCurrentArea().setViewCandidate(player);
		
		if (player.getIsPassingDoor()) {
			try {
				player.leaveArea();
				this.setCurrentArea(player.getLastDoor().getDestinationArea(), false);
				player.enterArea(this.getAreaToString(player.getLastDoor().getDestinationArea()),
				player.getLastDoor().getArrivalCoordinates());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		this.getCurrentArea().setViewCandidate(player);
		Dialog text ;

		if (h >= 0) {
			 text = new Dialog(
					"Bienvenu dans ENIGMA soldat ! ", "dialog.3",
					getCurrentArea());
			text.draw(getWindow());
		}
		h--;

		if (getCurrentArea() instanceof Level1) {
			 text = new Dialog("L'ennemi n'est pas encore la, camarade ! ", "dialog.3",
					getCurrentArea());
			text.draw(getWindow());
		}

		if (getCurrentArea() instanceof Level2) {
			
			 text = new Dialog("Appuyer sur la touche suivante  'C'est le femininin de Il'", "dialog.3",
					getCurrentArea());
			text.draw(getWindow());
		}

		if (getCurrentArea() instanceof Level3) {

			 text = new Dialog("Illuminer, alterner et sprinter sont clefs ! ",
					"dialog.3", getCurrentArea());
			text.draw(getWindow());
		}

		if (getCurrentArea() instanceof Level4) {

			((Level4)getCurrentArea()).getText().draw(getWindow());
			 
		}
			

		}



	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements me #PROJECT
		super.begin(window, fileSystem);
		addArea(level1);
		addArea(level2);
		addArea(level3);
		addArea(levelSelector);
		addArea(level4);

		/*
		 * AudioPlayer MGP = AudioPlayer.player; AudioStream BGM; AudioData MD;
		 * 
		 * ContinuousAudioDataStream loop = null;
		 * 
		 * try {
		 * 
		 * BGM = new AudioStream(new FileInputStream(".\\res\\dialogNext.wav"));
		 * 
		 * 
		 * MD = BGM.getData(); loop = new ContinuousAudioDataStream(MD); }
		 * catch(IOException e) { System.out.println("cant find the file"); }
		 * 
		 * MGP.start(loop);
		 */

		try {
			this.setCurrentArea(levelSelector.getTitle(), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		level1.begin(window, fileSystem);
		level2.begin(window, fileSystem);
		level3.begin(window, fileSystem);
		level4.begin(window, fileSystem);
		levelSelector.begin(window, fileSystem);

		player = new EnigmePlayer(getCurrentArea(), new DiscreteCoordinates(4, 4));

		player.begin(window, fileSystem);
		levelSelector.registerActor(player);

		return true;
	}

	@Override
	public int getFrameRate() {
		return 24;
	}

}