package ch.epfl.cs107.play;

import ch.epfl.cs107.play.game.Game;

import ch.epfl.cs107.play.game.demo1.Demo1;
import ch.epfl.cs107.play.game.enigme.Demo2;
import ch.epfl.cs107.play.game.enigme.Enigme;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.DefaultFileSystem;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.swing.SwingWindow;

public class Play {

	private static final float ONE_SEC = 1E9f;

	public static void main(String[] args) throws Exception {
		//Demo1 game =new Demo1();
		//Demo2 game= new Demo2() ;
	   Enigme game = new Enigme();
		final FileSystem fileSystem = new ResourceFileSystem(DefaultFileSystem.INSTANCE);

		XMLTexts.initialize(fileSystem, "strings/enigme_fr.xml");

		final Window window = new SwingWindow(game.getTitle(), fileSystem, 750, 750);
		boolean on = true;
		

		try {

			if (game.begin(window, fileSystem)) {

				// Use system clock to keep track of time progression
				long currentTime = System.nanoTime();
				long lastTime;
				final float frameDuration = ONE_SEC / game.getFrameRate();
				boolean isOn=!window.isCloseRequested();
				// Run until the user try to close the window
				while (isOn) {
					
						if (!(game).gameOn())
							isOn=false;
					

					// Compute time interval
					lastTime = currentTime;
					currentTime = System.nanoTime();
					float deltaTime = (currentTime - lastTime);

					try {
						int timeDiff = Math.max(0, (int) (frameDuration - deltaTime));
						Thread.sleep((int) (timeDiff / 1E6), (int) (timeDiff % 1E6));
					} catch (InterruptedException e) {
						System.out.println("Thread sleep interrupted");
					}

					currentTime = System.nanoTime();
					deltaTime = (currentTime - lastTime) / ONE_SEC;

					// Let the game do its stuff

					Keyboard keyboard = window.getKeyboard();
					Button button = keyboard.get(Keyboard.A);

					if (button.isDown()) {
						on = !on;
					}
					if (on) {
						game.update(deltaTime);
					} else {

						Dialog text = new Dialog("PAUSE: Veuillez cliquer sur A pour continuer ", "dialog.1",
								new LevelSelector());
						text.draw(window);
					}

					// Render and update input
					window.update();
				}
			}
			game.end();

		} finally {
			// Release resources
			window.dispose();
		}
		System.exit(-1);
	}
}
