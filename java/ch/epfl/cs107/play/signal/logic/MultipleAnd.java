package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class MultipleAnd extends LogicSignal {

	private List<Logic> list;

	public MultipleAnd(List<Logic> list) {
		this.list = list;
	}

	@Override
	public boolean isOn() {
		for (Logic e : list) {
			if (e == null) {
				return false;
			}
		}
		for (Logic e : list) {
			if (!e.isOn()) {
				return false;
			}
		}
		return true;
	}
}
