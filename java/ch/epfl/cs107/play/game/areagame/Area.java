package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */
public abstract class Area implements Playable {

	private boolean entered = false;
	private Window window;
	private FileSystem fileSystem;
	private List<Actor> actors = new LinkedList<>();
	private List<Actor> registeredActors = new LinkedList<>();
	private List<Actor> unregisteredActors = new LinkedList<>();
	private Actor viewCandidate;
	private Vector viewCenter;
	private AreaBehavior areaBehavior;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter = new HashMap<>();
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave = new HashMap<>();
	private List<Interactor> interactors = new LinkedList<>();

	public AreaBehavior getAreaBehavior() {
		return areaBehavior;

	}

	protected boolean getEntered() {
		return entered;
	}

	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity, coordinates);

			return true;
		}
		return false;
	}

	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity, coordinates);

			return true;
		}
		return false;
	}

	protected final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab;
	}

	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	/**
	 * f Add an actor to the actors list
	 * 
	 * @param a
	 *            (Actor): the actor to add, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		// TODO implements me #PROJECT #TUTO
		// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.add(a);
		if (a instanceof Interactable)
			errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());

		if (a instanceof Interactor)
			errorOccured = errorOccured || !interactors.add((Interactor) a);

		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added , " + "so remove it from where it was");
			removeActor(a, true);
		}

	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a
	 *            (Actor): the actor to remove, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {// BON
		// TODO implements me #PROJECT #TUTO

		boolean errorOccured = !actors.remove(a);
		if (a instanceof Interactable)
			errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());

		if (a instanceof Interactor)
			errorOccured = errorOccured || !interactors.remove((Interactor) a);

		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely removed , so add it from where it was");
			addActor(a, false);
		}

	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a
	 *            (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {// BON
		// TODO implements me #PROJECT #TUTO
		if (this instanceof EnigmeArea)
		{
			if (a instanceof Teleporter)
			{
				((EnigmeArea)this).teleporters.add((Teleporter) a);
			}
		}
		return registeredActors.add(a);

	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a
	 *            (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) { // BON
		// TODO implements me #PROJECT #TUTO
		return unregisteredActors.add(a);
	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		// TODO implements me #PROJECT #TUTO

		return areaBehavior.getWidth();
	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {
		// TODO implements me #PROJECT #TUTO
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */

	/// Area implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {// BON
		this.window = window;
		this.fileSystem = fileSystem;
		viewCenter = Vector.ZERO;
		viewCandidate = null;
		entered = true;

		return true;
	}

	public final void setViewCandidate(Actor a) {// BON
		this.viewCandidate = a;
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window
	 *            (Window): display context, not null
	 * @param fileSystem
	 *            (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		return true;
	}

	private final void purgeRegistration() { // Bon
		for (Actor ac : registeredActors) {
			addActor(ac, false);
		}
		for (Actor ac : unregisteredActors) {
			removeActor(ac, false);
		}

		for (Entry<Interactable, List<DiscreteCoordinates>> entree : interactablesToLeave.entrySet()) {

			Interactable c = entree.getKey();
			List<DiscreteCoordinates> cellules = entree.getValue();

			areaBehavior.leave(c, cellules);

		}

		for (Entry<Interactable, List<DiscreteCoordinates>> entree : interactablesToEnter.entrySet()) {
			Interactable c = entree.getKey();
			List<DiscreteCoordinates> cellules = entree.getValue();
			areaBehavior.enter(c, cellules);

		}

		interactablesToLeave.clear();
		interactablesToEnter.clear();

		registeredActors.clear();
		unregisteredActors.clear();

	}

	@Override
	public void update(float deltaTime) {// BON
		purgeRegistration();
		updateCamera();
		for (Actor ac : actors) {

			ac.update(deltaTime);
		}

		for (Interactor interactor : interactors) {
			if (interactor.wantsCellInteraction()) {
				// demander au gestionnaire de la grille ( AreaBehavior )
				// de mettre en place les interactions de contact
				areaBehavior.cellInteractionOf(interactor);

			}
			if (interactor.wantsViewInteraction()) {

				// demander au gestionnaire de la grille de mettre en place
				// les interactions distantes
				areaBehavior.viewInteractionOf(interactor);

			}
		}

		for (Actor ac : actors) {

			ac.draw(window);
		}

	}

	private void updateCamera() { // C BON
		// TODO implements me #PROJECT #TUTO
		if (viewCandidate != null) {
			viewCenter = viewCandidate.getPosition();
		}
		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
		window.setRelativeTransform(viewTransform);

	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend() {
		// Do nothing by default
		purgeRegistration();
	}

	@Override
	public void end() {// BON
		// TODO save the AreaState somewhere

	}

}
