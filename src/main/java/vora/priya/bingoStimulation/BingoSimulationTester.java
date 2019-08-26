package vora.priya.bingoStimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

public class BingoSimulationTester {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Game g = new Game();
		BingoCaller caller = new BingoCaller(g);
		
		g.makePlayers(30);
//--------------------------------------------------------------------------------		
		for (BingoPlayer p : g.getPlayers()) {
			System.out.println("Name of Subscriber: " + p.getName());
			int id = caller.subscribe(k -> p.OnPublished(k));
			
			caller.unsubscribe(y-> p.OnUnsubscribe());
			
			p.becomeAWinner(r-> caller.OnWinnerNotification(r));
			p.leave(e-> caller.OnLeaveNotification(p.getName(), p.getId()));
			//p.becomeAWinner(v-> );
			
			Position[][] thePosition = p.getBingoCard().getPosition();
			
			System.out.println(thePosition[0][0].getValue() + " " +  thePosition[0][1].getValue() + " " + thePosition[0][2].getValue() + " " + thePosition[0][3].getValue() + " " + thePosition[0][4].getValue());
			System.out.println(thePosition[1][0].getValue() + " " +  thePosition[1][1].getValue() + " " + thePosition[1][2].getValue() + " " + thePosition[1][3].getValue() + " " + thePosition[1][4].getValue());
			System.out.println(thePosition[2][0].getValue() + " " +  thePosition[2][1].getValue() + " " + thePosition[2][2].getValue() + " " + thePosition[2][3].getValue() + " " + thePosition[2][4].getValue());
			System.out.println(thePosition[3][0].getValue() + " " +  thePosition[3][1].getValue() + " " + thePosition[3][2].getValue() + " " + thePosition[3][3].getValue() + " " + thePosition[3][4].getValue());
			System.out.println(thePosition[4][0].getValue() + " " +  thePosition[4][1].getValue() + " " + thePosition[4][2].getValue() + " " + thePosition[4][3].getValue() + " " + thePosition[4][4].getValue());
	
		}

		
		System.out.println("Finished the Subscriber foreach...");

		caller.run();
		
		System.out.println("\nList of Random Numbers Called in this Game: ");
		for (int j = 0; j < caller.alreadyCalledNumber.size(); j++) {			
			System.out.println((j+1) + ". " +  caller.alreadyCalledNumber.get(j));
		}
		
		
		System.out.println("Numbers of Players Left @ End of the Game: " + caller.getHash().keySet().size());
	
	}
}
