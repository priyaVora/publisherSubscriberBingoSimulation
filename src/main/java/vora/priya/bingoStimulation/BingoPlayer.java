package vora.priya.bingoStimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BingoPlayer {
	private String name;
	private int numberBeingCalled;
	private BingoCard bingoCard;
	private int id;
	private boolean winnerState = false;
	private Consumer<String> myCaller;
	private List<Consumer<Integer>> myLeave = new ArrayList<>();

	public BingoPlayer() {

	}

	public BingoPlayer(String name, BingoCard bingoCard) {
		super();
		this.name = name;
		this.bingoCard = bingoCard;
	}

	@Override
	public String toString() {
		return "BingoPlayer [name=" + name + ", bingoCard=" + bingoCard + "]";
	}

	public void becomeAWinner(Consumer<String> playerMethod) {
		this.myCaller = playerMethod;
	}

	public void OnPublished(int value) {
		System.out.println("\nPlayer " + this.getName() + " received: " + value + " as the next Bingo Number.");
		checkToCover(value);
	}

	public void OnUnsubscribe() {
		System.out.println("\nPlayer " + this.getName() + " received: a notification of Unsubscription");
	}

	public void leave(Consumer<Integer> theSubscriber) {
		this.myLeave.add(theSubscriber);
	}

	public int getNumberBeingCalled() {
		return numberBeingCalled;
	}

	public void setNumberBeingCalled(int numberBeingCalled) {
		this.numberBeingCalled = numberBeingCalled;
	}

	public void callLeave() {
		for (Consumer<Integer> s : this.myLeave) {
			s.accept(this.getId()); // Here i am passing in the value and
									// actually // calling the method
		}
	}

	public void checkToCover(int finder) {
		boolean consist = false;
		Position[][] position = this.getBingoCard().getPosition();

		for (int i = 0; i < position.length; i++) {
			for (int j = 0; j < position.length; j++) {
				int currentPositionalValue = position[i][j].getValue();
				if (currentPositionalValue == finder) {
					position[i][j].setCovered(true);
					System.out.println("UPDATED: Board");

					System.out.println(position[0][0] + " " + position[0][1] + " " + position[0][2] + " "
							+ position[0][3] + " " + position[0][4]);
					System.out.println(position[1][0] + " " + position[1][1] + " " + position[1][2] + " "
							+ position[1][3] + " " + position[1][4]);
					System.out.println(position[2][0] + " " + position[2][1] + " " + position[2][2] + " "
							+ position[2][3] + " " + position[2][4]);
					System.out.println(position[3][0] + " " + position[3][1] + " " + position[3][2] + " "
							+ position[3][3] + " " + position[3][4]);
					System.out.println(position[4][0] + " " + position[4][1] + " " + position[4][2] + " "
							+ position[4][3] + " " + position[4][4]);

				}
			}
		}

		if (consist == false) {
			System.out.println("This player does not have that value on their board...");
		}

		checkIfPlayerisWinner();
		this.getBingoCard().setPosition(position);

	}

	public void checkIfPlayerisWinner() {
		Position[][] position = this.getBingoCard().getPosition();
		if (position[0][0].isCovered() == true && position[1][1].isCovered() == true
				&& position[2][2].isCovered() == true && position[3][3].isCovered() && position[4][4].isCovered()) {
			System.out.println("HEY I AM A WINNER LEFT TO WRITE DIAGONAL WINNER......");
		} else if (position[4][0].isCovered() == true && position[3][2].isCovered() == true
				&& position[2][2].isCovered() == true && position[1][3].isCovered() == true
				&& position[0][4].isCovered() == true) {
			System.out.println("HEY I AM A WINNER RIGHT TO LEFT DIAGONAL WINNER......");
				winnerState = true;
			myCaller.accept(this.getName());
			

		} else { 
			for (int i = 0; i < position.length; i++) {
				if (position[i][0].isCovered() == true && position[i][1].isCovered() == true
						&& position[i][2].isCovered() == true && position[i][3].isCovered() == true
						&& position[i][4].isCovered() == true) {
					System.out.println("HEY I AM A Horizonal WINNER......");
						winnerState = true;
					myCaller.accept(this.getName());
					
				}

			}

			for (int i = 0; i < position.length; i++) {
				if (position[0][i].isCovered() == true && position[1][i].isCovered() == true
						&& position[2][i].isCovered() == true && position[3][i].isCovered() == true
						&& position[4][i].isCovered() == true) {
					System.out.println("HEY I AM A Vertical WINNER......");
					winnerState = true;
					myCaller.accept(this.getName());
					
				}
			}
		}
					
			
			
			if(winnerState == true) { 
				System.out.println("*******WINNER'S BOARD********");

				System.out.println(position[0][0] + " " + position[0][1] + " " + position[0][2] + " "
						+ position[0][3] + " " + position[0][4]);
				System.out.println(position[1][0] + " " + position[1][1] + " " + position[1][2] + " "
						+ position[1][3] + " " + position[1][4]);
				System.out.println(position[2][0] + " " + position[2][1] + " " + position[2][2] + " "
						+ position[2][3] + " " + position[2][4]);
				System.out.println(position[3][0] + " " + position[3][1] + " " + position[3][2] + " "
						+ position[3][3] + " " + position[3][4]);
				System.out.println(position[4][0] + " " + position[4][1] + " " + position[4][2] + " "
						+ position[4][3] + " " + position[4][4]);
			
			}
			

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BingoCard getBingoCard() {
		return bingoCard;
	}

	public void setBingoCard(BingoCard bingoCard) {
		this.bingoCard = bingoCard;
	}

}
