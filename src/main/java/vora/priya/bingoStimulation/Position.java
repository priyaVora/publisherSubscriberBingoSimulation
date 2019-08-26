package vora.priya.bingoStimulation;

import java.util.HashMap;

public class Position {

	private boolean isCovered = false;
	private int value;
	
	public Position(boolean isCovered, int value) {
		this.isCovered = isCovered;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Position [isCovered=" + isCovered + ", value=" + value + "]";
	}

	public boolean isCovered() {
		return isCovered;
	}
	public void setCovered(boolean isCovered) {
		this.isCovered = isCovered;
	}
	public int isValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	
}
