package ch.epfl.cs107.play.signal.logic;

import java.util.ArrayList;
import java.util.List;

public class LogicNumber extends LogicSignal {
	private float nb;
	private List<Logic> e = new ArrayList<>();
	private float nbSignal;

	public LogicNumber(float nb, List<Logic> list) {
		nbSignal = 0;
		this.nb = nb;
		this.e = list;
		for (int i = 0; i < e.size(); i++) {
			nbSignal += Math.pow(2, i) * e.get(i).getIntensity();
		}
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		if (e.size() > 12 || nb < 0 || nb > Math.pow(2, e.size())) {
			return false;
		}

		if (nbSignal == nb) {
			return true;

		} else {
			return false;
		}
	}

}
