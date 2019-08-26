package vora.priya.bingoStimulation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class BingoCaller {
	boolean runGame = true;
	int nextID = 0;
	int countOFRounds;
	private Game g;
	private Random generator = new Random();

	HashMap<Integer, Consumer<Integer>> hash = new HashMap<Integer, Consumer<Integer>>();
	ArrayList<Integer> alreadyCalledNumber = new ArrayList<>();
	private List<Consumer<Integer>> player = new ArrayList<>();

	public BingoCaller() { 
		
	}

	public BingoCaller(Game g) { 
		this.setG(g);
	}
	public int subscribe(Consumer<Integer> subscriberMethod) {
		this.player.add(subscriberMethod);
		hash.put(nextID, subscriberMethod);
		return nextID++;
	}

	public void unsubscribe(Consumer<Integer> subscriber) {
		this.player.remove(subscriber);
	}

	public void run() throws InterruptedException, FileNotFoundException {
		runGame = true;
		while (runGame == true) {
			boolean end3 = false;
			int random = (generator.nextInt(101));

			while (end3 == false) {
				if (alreadyCalledNumber.contains(random)) {
					random = (generator.nextInt(101));
				} else {
					end3 = true;
					alreadyCalledNumber.add(random);
				}
			}
			System.out.println("\nRandomly Generated Value: " + random);
			countOFRounds++;
			
			if (countOFRounds < 7) {
				int grabRandomKeyinHash = (int) hash.keySet().toArray()[generator.nextInt(hash.keySet().size())];
				System.out.println("REMOVE THE PLAYER..." + countOFRounds);
				hash.remove(grabRandomKeyinHash);
				
				BingoPlayer thePlayer = new BingoPlayer();
				BingoCard card = new BingoCard();
				card.generateTheBoard();
				
				thePlayer.setName(g.getNameFromList("firstNames.txt"));
				thePlayer.setBingoCard(card);
				
				g.getPlayers().add(thePlayer);
				
				System.out.println("Name of Subscriber: " + thePlayer.getName());
				int id = this.subscribe(k -> thePlayer.OnPublished(k));
				
				this.unsubscribe(y-> thePlayer.OnUnsubscribe());
				
				thePlayer.becomeAWinner(r-> this.OnWinnerNotification(r));
				thePlayer.leave(e-> this.OnLeaveNotification(thePlayer.getName(), thePlayer.getId()));
				//p.becomeAWinner(v-> );
				
				Position[][] thePosition = thePlayer.getBingoCard().getPosition();
				
				System.out.println(thePosition[0][0].getValue() + " " +  thePosition[0][1].getValue() + " " + thePosition[0][2].getValue() + " " + thePosition[0][3].getValue() + " " + thePosition[0][4].getValue());
				System.out.println(thePosition[1][0].getValue() + " " +  thePosition[1][1].getValue() + " " + thePosition[1][2].getValue() + " " + thePosition[1][3].getValue() + " " + thePosition[1][4].getValue());
				System.out.println(thePosition[2][0].getValue() + " " +  thePosition[2][1].getValue() + " " + thePosition[2][2].getValue() + " " + thePosition[2][3].getValue() + " " + thePosition[2][4].getValue());
				System.out.println(thePosition[3][0].getValue() + " " +  thePosition[3][1].getValue() + " " + thePosition[3][2].getValue() + " " + thePosition[3][3].getValue() + " " + thePosition[3][4].getValue());
				System.out.println(thePosition[4][0].getValue() + " " +  thePosition[4][1].getValue() + " " + thePosition[4][2].getValue() + " " + thePosition[4][3].getValue() + " " + thePosition[4][4].getValue());
				
				//----------------------------------------------------------------------------------------------------------------------------------
				
				BingoPlayer thePlayer1 = new BingoPlayer();
				BingoCard card1 = new BingoCard();
				card1.generateTheBoard();
				
				thePlayer1.setName(g.getNameFromList("firstNames.txt"));
				thePlayer1.setBingoCard(card1);
				
				g.getPlayers().add(thePlayer1);
				
				System.out.println("Name of Subscriber: " + thePlayer1.getName());
				int id1 = this.subscribe(k -> thePlayer1.OnPublished(k));
				
				this.unsubscribe(y-> thePlayer1.OnUnsubscribe());
				
				thePlayer1.becomeAWinner(r-> this.OnWinnerNotification(r));
				thePlayer1.leave(e-> this.OnLeaveNotification(thePlayer1.getName(), thePlayer1.getId()));
				//p.becomeAWinner(v-> );
				
				Position[][] thePosition1 = thePlayer1.getBingoCard().getPosition();
				
				System.out.println(thePosition1[0][0].getValue() + " " +  thePosition1[0][1].getValue() + " " + thePosition1[0][2].getValue() + " " + thePosition1[0][3].getValue() + " " + thePosition1[0][4].getValue());
				System.out.println(thePosition1[1][0].getValue() + " " +  thePosition1[1][1].getValue() + " " + thePosition1[1][2].getValue() + " " + thePosition1[1][3].getValue() + " " + thePosition1[1][4].getValue());
				System.out.println(thePosition1[2][0].getValue() + " " +  thePosition1[2][1].getValue() + " " + thePosition1[2][2].getValue() + " " + thePosition1[2][3].getValue() + " " + thePosition1[2][4].getValue());
				System.out.println(thePosition1[3][0].getValue() + " " +  thePosition1[3][1].getValue() + " " + thePosition1[3][2].getValue() + " " + thePosition1[3][3].getValue() + " " + thePosition1[3][4].getValue());
				System.out.println(thePosition1[4][0].getValue() + " " +  thePosition1[4][1].getValue() + " " + thePosition1[4][2].getValue() + " " + thePosition1[4][3].getValue() + " " + thePosition1[4][4].getValue());
				
				
				
				
				
				
				
				
			}

			for (Consumer<Integer> s : this.player) {
				if(runGame == false) { 
					
					break;
				}
				s.accept(random); 
			}
			// Thread.sleep(2000);
		}
	}

	public void OnWinnerNotification(String name) {
		System.out.println("\nCaller received a notification from " + name);
		runGame = false;

	}

	public void OnLeaveNotification(String name, int id) {
		System.out.println("\nCaller received a notification from " + name + "id: " + id + " to leave.");

	}

	
	//-----------------------------------------------------------------------------------------------
	public boolean isRunGame() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}

	public int getNextID() {
		return nextID;
	}

	public void setNextID(int nextID) {
		this.nextID = nextID;
	}

	public int getCountOFRounds() {
		return countOFRounds;
	}

	public void setCountOFRounds(int countOFRounds) {
		this.countOFRounds = countOFRounds;
	}

	public Random getGenerator() {
		return generator;
	}

	public void setGenerator(Random generator) {
		this.generator = generator;
	}

	public HashMap<Integer, Consumer<Integer>> getHash() {
		return hash;
	}

	public void setHash(HashMap<Integer, Consumer<Integer>> hash) {
		this.hash = hash;
	}

	public ArrayList<Integer> getAlreadyCalledNumber() {
		return alreadyCalledNumber;
	}

	public void setAlreadyCalledNumber(ArrayList<Integer> alreadyCalledNumber) {
		this.alreadyCalledNumber = alreadyCalledNumber;
	}

	public List<Consumer<Integer>> getPlayer() {
		return player;
	}

	public void setPlayer(List<Consumer<Integer>> player) {
		this.player = player;
	}
	public Game getG() {
		return g;
	}
	public void setG(Game g) {
		this.g = g;
	}
	
	

}
