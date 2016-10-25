package dmbrowning21.gameComponent;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;


public class MenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JMenu file, settings, stats, help;
	public JMenuItem newGame, exit, chooseColor, highScore,clearHighScore, history, about, rules;
	public JRadioButtonMenuItem easy, medium, hard, expert;
	private ButtonGroup difficulty;
	public MenuBar(){
		file = new JMenu("File");
		settings = new JMenu("Settings");
		stats = new JMenu("Stats");
		help = new JMenu("Help");
		
		difficulty = new ButtonGroup();
		
		file.setMnemonic(KeyEvent.VK_A);
		settings.setMnemonic(KeyEvent.VK_A);
		stats.setMnemonic(KeyEvent.VK_A);
		help.setMnemonic(KeyEvent.VK_A);
		this.add(file);
		this.add(settings);
		this.add(stats);
		this.add(help);
		
		newGame = new JMenuItem("New Game", KeyEvent.VK_T);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,ActionEvent.ALT_MASK));
		newGame.getAccessibleContext().setAccessibleDescription("newGame");
		file.add(newGame);
		
		exit = new JMenuItem("Exit", KeyEvent.VK_T);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9,ActionEvent.ALT_MASK));
		exit.getAccessibleContext().setAccessibleDescription("exit");
		file.add(exit);
		
		chooseColor = new JMenuItem("Colors", KeyEvent.VK_T);
		chooseColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));
		chooseColor.getAccessibleContext().setAccessibleDescription("Color Picker");
		settings.add(chooseColor);
		
		easy = new JRadioButtonMenuItem("Easy");
		easy.getAccessibleContext().setAccessibleDescription("Set Sleep Time to 750");
		settings.add(easy);
		difficulty.add(easy);
		
		medium = new JRadioButtonMenuItem("Medium");
		medium.getAccessibleContext().setAccessibleDescription("Set Sleep Time to 500");
		settings.add(medium);
		medium.setSelected(true);
		difficulty.add(medium);
		
		hard = new JRadioButtonMenuItem("Hard");
		hard.getAccessibleContext().setAccessibleDescription("Set Sleep Time to 300");
		settings.add(hard);
		difficulty.add(hard);
		
		expert = new JRadioButtonMenuItem("Expert");
		expert.getAccessibleContext().setAccessibleDescription("Set Sleep Time to 100");
		settings.add(expert);
		difficulty.add(expert);
		
		highScore = new JMenuItem("High Scores", KeyEvent.VK_T);
		highScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,ActionEvent.ALT_MASK));
		highScore.getAccessibleContext().setAccessibleDescription("Display HighScore");
		stats.add(highScore);
		
		clearHighScore = new JMenuItem("Clear High Score", KeyEvent.VK_T);
		clearHighScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,ActionEvent.ALT_MASK));
		clearHighScore.getAccessibleContext().setAccessibleDescription("Display HighScore");
		stats.add(clearHighScore);
		
		history = new JMenuItem("History", KeyEvent.VK_T);
		history.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,ActionEvent.ALT_MASK));
		history.getAccessibleContext().setAccessibleDescription("History");
		stats.add(history);
		
		rules = new JMenuItem("Rules", KeyEvent.VK_T);
		rules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,ActionEvent.ALT_MASK));
		rules.getAccessibleContext().setAccessibleDescription("Display Rules");
		help.add(rules);
		
		about = new JMenuItem("about", KeyEvent.VK_T);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,ActionEvent.ALT_MASK));
		about.getAccessibleContext().setAccessibleDescription("About");
		help.add(about);
		
		
	}
}
