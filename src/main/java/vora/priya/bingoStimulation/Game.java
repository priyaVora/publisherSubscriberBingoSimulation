package vora.priya.bingoStimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	public Random generator = new Random();
	private ArrayList<Integer> alreadyAssignedIdList = new ArrayList<>();
	private List<BingoPlayer> players = new ArrayList<>();
	
	
	public Game() {

	}
	public void makePlayers(int numOfPlayersToBeCreated) throws FileNotFoundException { 
		BingoSimulationTester t = new BingoSimulationTester();
		
		for (int i = 0; i < numOfPlayersToBeCreated; i++) {
			BingoPlayer thePlayer = new BingoPlayer();
			BingoCard card = new BingoCard();
			card.generateTheBoard();
			
			thePlayer.setName(getNameFromList("firstNames.txt"));
			thePlayer.setBingoCard(card);
			
			boolean end3 = false;
			int random = (generator.nextInt(101));
			
			while (end3 == false) {
				if (alreadyAssignedIdList.contains(random)) {
					random = (generator.nextInt(101));
				} else {
					end3 = true;
				}
			}
			
		players.add(thePlayer);
		
		}
	}
	
	public String getNameFromList(String txtFileName) throws FileNotFoundException {
		String name = null;
		String[] listOfNames = nameMaker(txtFileName);

		int num = generator.nextInt(41);

		for (int i = 0; i < listOfNames.length; i++) {
			name = listOfNames[num];
		}
		return name;
	}

	public String[] nameMaker(String txtFileName) throws FileNotFoundException {
		File path = new File(txtFileName);

		Scanner file = new Scanner(path);
		String[] nameType = new String[100];
		for (int i = 0; i < nameType.length; i++) {
			nameType[i] = file.nextLine();
			nameType[i].trim().replaceAll(" ", "");
		}

		for (int i = 0; i < nameType.length; i++) {
			if (txtFileName.equalsIgnoreCase("firstnames.txt")) {
				int firstOne = generator.nextInt(4);
				if (firstOne == 0) {
					nameType[i] = "A" + nameType[i];
				} else if (firstOne == 1) {
					nameType[i] = "R" + nameType[i];
				} else if (firstOne == 2) {
					nameType[i] = "Q" + nameType[i];
				}

			} else {
				int firstOne = generator.nextInt(3);
				if (firstOne == 0) {
					nameType[i] = "S" + nameType[i];
				} else if (firstOne == 1) {
					nameType[i] = "C" + nameType[i];
				}
			}
		}

		return nameType;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------
	public List<BingoPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<BingoPlayer> players) {
		this.players = players;
	}
	public ArrayList<Integer> getAlreadyAssignedIdList() {
		return alreadyAssignedIdList;
	}
	public void setAlreadyAssignedIdList(ArrayList<Integer> alreadyAssignedIdList) {
		this.alreadyAssignedIdList = alreadyAssignedIdList;
	}
}
