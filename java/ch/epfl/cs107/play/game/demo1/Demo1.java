package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {
	private Actor actor1;
	private MovingRock actor2 = new MovingRock(new Vector(0.2f, 0.2f), "Hello, I'm a moving rock !");
	private Window window;
	private FileSystem fileSystem;
	private TextGraphics textBoum = new TextGraphics("BOUM!", 0.05f, Color.RED);

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.fileSystem = fileSystem;
		this.window = window;

		float radius = 0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));

		return true;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo1";
	}

	@Override
	public void update(float deltaTime) {

		actor1.draw(window);
		actor2.draw(window);
		actor2.getText().draw(window);
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		if (downArrow.isDown()) {
			actor2.update(0);
		}
		float xCerc = actor1.getPosition().getX();
		float yCerc = actor1.getPosition().getY();
		float xAct2 = actor2.getPosition().getX();
		float yAct2 = actor2.getPosition().getY();
		double disSquared = Math.pow((xCerc - xAct2), 2) + Math.pow((yCerc - yAct2), 2);
		if (disSquared < Math.pow(0.2f, 2)) {

			textBoum.draw(window);
		}
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}
	public boolean gameOn()
	{
		return true;
	}
}