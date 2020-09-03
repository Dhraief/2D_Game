package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {
	public boolean isOn();

	public default float getIntensity() {
		if (isOn()) {
			return 1.0f;
		}
		return 0.0f;
	}

	@Override
	default float getIntensity(float t) {
		return getIntensity();
	}

	final Logic TRUE = new Logic() {
		@Override
		public boolean isOn() {
			return true;
		}
	};
	final Logic False = new Logic() {
		@Override
		public boolean isOn() {
			return false;
		}
	};

}
