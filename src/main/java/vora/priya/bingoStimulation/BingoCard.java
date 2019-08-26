package vora.priya.bingoStimulation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BingoCard {
	ArrayList<Integer> alreadyContainedValue = new ArrayList<>();
	private Random generator = new Random();
	Position[][] position = new Position[5][5];


	public BingoCard() {
	}

	public void generateTheBoard() {		
		Position[][] thePosition = new Position[5][5];

		for (int i = 0; i < thePosition.length; i++) {
			for (int j = 0; j < thePosition.length; j++) {
				int value = generator.nextInt(101 - 1 + 1) + 1;

				if (alreadyContainedValue.contains(value)) {
					j--;
				} else {
					Position p = new Position(false, value);
					p.setValue(value);
					thePosition[i][j] = p;
				
					alreadyContainedValue.add(value);
				}
			}
		}
		System.out.println("Finished...");
	
		this.setPosition(thePosition);
		
	}

	
	
	//---------------------------------------------------------------------------------------------------
	
	public ArrayList<Integer> getAlreadyContainedValue() {
		return alreadyContainedValue;
	}

	public void setAlreadyContainedValue(ArrayList<Integer> alreadyContainedValue) {
		this.alreadyContainedValue = alreadyContainedValue;
	}

	public Position[][] getPosition() {
		return position;
	}

	public void setPosition(Position[][] position) {
		this.position = position;
	}
	
}
