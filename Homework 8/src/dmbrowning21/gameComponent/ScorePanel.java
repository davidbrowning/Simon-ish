package dmbrowning21.gameComponent;


import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int highScore;
	private int score;
	private JLabel highScoreLabel;
	private JLabel scoreLabel;
	private File highScores;
	public String[] highScoreFileContents;
	public Integer[] scores;
	private int hs = 0;
	private int gamesPlayed;
	
	public ScorePanel() {
		organizeScores();
		scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
		highScoreLabel = new JLabel("High Score: " + highScore, SwingConstants.CENTER);
		
		setLayout(new GridLayout(1, 3));
		
		getHSNames();
		highScoreLabel.setText("High Score: " + highScore);
		
		add(scoreLabel);
		add(highScoreLabel);		
	}
	
	public void gamePlayed(){
		gamesPlayed++;
	}
	
	public int getGamesPlayed(){
		return gamesPlayed;
	}
	
	public void reset() {
		score = 0;
		scoreLabel.setText("Score: " + score);
		update(scoreLabel.getGraphics());
	}
	
	public void incrScore() {
		score++;
		scoreLabel.setText("Score: " + score);
		update(scoreLabel.getGraphics());
		checkHighScore();
	}
	
	private void checkHighScore() {
		if (score > highScore) {
			highScore = score;
			highScoreLabel.setText("High Score: " + highScore);
			this.update(this.getGraphics());
		}
	}
	
	public void organizeScores(){
		Vector<String> name = new Vector<String>();
		Vector<Integer> number = new Vector<Integer>();
		highScores = new File("highScores.txt");
		try {
			highScores.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			Scanner sc = new Scanner(highScores);
			
			int x = 0;
			
			while(sc.hasNextLine()&&x<10){
				number.add(Integer.parseInt(sc.next()));
				name.add(sc.nextLine());
				x++;
			}
			sc.close();
			for(int i = 0; i < number.size();i++){
				for(int j = 1; j < number.size();j++){
					if(number.elementAt(j-1)<=number.elementAt(j)){
						Collections.swap(number, j, j-1);
						Collections.swap(name, j, j-1);
					}
				}
			}
			highScores = new File("highScores.txt");
			PrintWriter writer;
			try {
				writer = new PrintWriter(highScores);
				for(int i = 0; i < number.size();i++){
					writer.print(number.elementAt(i));
					writer.println(name.elementAt(i));
				}
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void addName(String n){
		highScores = new File("highScores.txt");
		try {
			highScores.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(highScores, true)))){
			out.println(highScore + " " + n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * highScoreFileContents;
	 * allHighScores;
	 * */
	
	public String[] getHSNames(){
		organizeScores();
		highScores = new File("highScores.txt");
		scores = new Integer[50];
		highScoreFileContents = new String[50];
		
		try {
			highScores.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			Scanner sc = new Scanner(highScores);
			
			int x = 0;
			
			while(sc.hasNextLine()){
				scores[x] = Integer.parseInt(sc.next());
				highScoreFileContents[x] = scores[x] + sc.nextLine();
				if(scores[x] > hs){
					hs = scores[x];
				}
				x++;
			}
			sc.close();
			highScore = hs;
			
			return highScoreFileContents;
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void clearScores(){
		highScore = 0;
		highScoreLabel.setText("High Score: " + highScore);
		this.update(this.getGraphics());
		highScores = new File("highScores.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(highScores);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int getScore() {
		return score;
	}
	
	public int getHighScore() {
		return highScore;
	}
}
