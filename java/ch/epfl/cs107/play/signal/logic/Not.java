package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal {
	private Logic s;

	public Not(Logic s) {
		super();
		this.s = s;
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		if ((s != null) && !s.isOn()) {
			return true;
		}
		return false;
	}

}
