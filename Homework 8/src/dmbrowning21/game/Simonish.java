package dmbrowning21.game;

import dmbrowning21.gameComponent.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import java.lang.reflect.Array;
//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
//import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

public class Simonish extends JFrame implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color[] colorArr = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
	ColorPanel[] panelArr = new ColorPanel[4];
	ScorePanel scorePanel;
	JPanel gamePanel;
	ArrayList<ColorPanel> seq = new ArrayList<ColorPanel>();
	Iterator<ColorPanel> seqItr;
	Random rand = new Random();
	public static boolean gameOver = true;
	MenuBar bar = new MenuBar();
	int sleepTime = 500;
	Color newColor;
	Thread t;
	
	Container pane;

	
	public Simonish() {
		new menuBar();
		    //new Thread(r).start();
		this.setTitle("Simonish");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		pane = this.getContentPane();
		pane.setLayout(null);
		
		pane.setPreferredSize(new Dimension(1000, 1050));
		scorePanel = new ScorePanel();
		scorePanel.setBounds(0, 0, 1000, 50);
		
		
		
		
		ImageIcon lowerLeft = new ImageIcon("data/bottomLeftEmpty.png", null);
		ImageIcon lowerRight = new ImageIcon("data/bottomRight4Empty.png", null);
		ImageIcon upperLeft = new ImageIcon("data/upperLeft2Empty.png", null);
		ImageIcon upperRight = new ImageIcon("data/upperRightEmpty.png", null);
		
		ImageIcon lowerLeftFlash = new ImageIcon("data/bottomLeftFlash.png", null);
		ImageIcon lowerRightFlash = new ImageIcon("data/bottomRightFlash.png", null);
		ImageIcon upperLeftFlash = new ImageIcon("data/upperLeftFlash.png", null);
		ImageIcon upperRightFlash = new ImageIcon("data/upperRightFlash.png", null);
		
		ImageIcon lowerLeftDark = new ImageIcon("data/bottomLeftDark.png", null);
		ImageIcon lowerRightDark = new ImageIcon("data/bottomRightDark.png", null);
		ImageIcon upperLeftDark = new ImageIcon("data/upperLeftDark.png", null);
		ImageIcon upperRightDark = new ImageIcon("data/upperRightDark.png", null);
		
		JLabel[] l = new JLabel[4];
		l[0] = new JLabel(upperLeft);
		l[1] = new JLabel (upperRight);
		l[2] = new JLabel (lowerLeft);
		l[3] = new JLabel (lowerRight);
		
		JLabel[] flash = new JLabel[4];
		
		flash[0] = new JLabel(upperLeftFlash);
		flash[1] = new JLabel (upperRightFlash);
		flash[2] = new JLabel (lowerLeftFlash);
		flash[3] = new JLabel (lowerRightFlash);
		
		JLabel[] dark = new JLabel[4];
		
		dark[0] = new JLabel(upperLeftDark);
		dark[1] = new JLabel (upperRightDark);
		dark[2] = new JLabel (lowerLeftDark);
		dark[3] = new JLabel (lowerRightDark);
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(2, 2));
		gamePanel.setBounds(0, 50, 1000, 1000);
		
		
		pane.add(scorePanel);
		pane.add(gamePanel);
		pane.add(bar);
		
		this.setJMenuBar(bar);
		
		for (int i = 0; i < panelArr.length; i++) {
			panelArr[i] = new ColorPanel(colorArr[i], l[i], flash[i], dark[i]);
			panelArr[i].addMouseListener(this);
			panelArr[i].setOpaque(false);
			gamePanel.add(panelArr[i]);
		}	
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		//this.setOpacity(0.25f);
		this.setVisible(true);
		
	}
	
	
	private void computerTurn() {
		seq.add(panelArr[rand.nextInt(panelArr.length)]);
		for (ColorPanel i : seq) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			i.pressed();
			try {
				Thread.sleep(sleepTime*2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i.released();
		}
		
		seqItr = seq.iterator();
	}
		
	
	
	private void gameOver() {
		gameOver = true;
		String msg = "Game Over";
		String name;
		if (scorePanel.getHighScore() == scorePanel.getScore()){
			msg = msg + "\nYou got the high score! \n Please Enter your name:";
			name = JOptionPane.showInputDialog(this, msg);
			scorePanel.addName(name);
		}
		else{JOptionPane.showMessageDialog(this, msg);}
		scorePanel.gamePlayed();
		for(ColorPanel i : panelArr){
			i.reset();
		}
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		    	  new Simonish();
		      }
		    });
		
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gameOver) return;
		
		ColorPanel tmp = ((ColorPanel)e.getSource());
		tmp.pressed();
		
		if (tmp != seqItr.next()) {
			gameOver();
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gameOver) return;
		
		ColorPanel tmp = ((ColorPanel)e.getSource());
		tmp.released();
		if (!seqItr.hasNext()) {
			scorePanel.incrScore();
			computerTurn();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public class CompTurn extends SwingWorker<Integer, String>{

		@Override
		protected Integer doInBackground() throws Exception {
			// TODO Auto-generated method stub
			computerTurn();
			return null;
		}
		
		
	}
	
	
	
	public class menuBar implements ActionListener{
		public menuBar(){
		bar.newGame.addActionListener(this);
		bar.highScore.addActionListener(this);
		bar.clearHighScore.addActionListener(this);
		bar.history.addActionListener(this);
		bar.exit.addActionListener(this);
		bar.easy.addActionListener(this);
		bar.medium.addActionListener(this);
		bar.hard.addActionListener(this);
		bar.expert.addActionListener(this);
		bar.about.addActionListener(this);
		bar.rules.addActionListener(this);
		bar.chooseColor.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent f) {
			// TODO Auto-generated method stub
			if (f.getSource() == bar.newGame){
				gameOver = false;
				for (ColorPanel i : panelArr) {
					i.reset();
				}
				scorePanel.reset();
				seq.clear();
				
				t = new Thread(new CompTurn());
				t.start();
				
			}
				
			if(f.getSource()==bar.exit){
				System.exit(0);
			}
			if (f.getSource() == bar.highScore){
				String[] hs = scorePanel.getHSNames();
				String c = "Top 10 Simonish Players\n";
				JOptionPane.showMessageDialog(null, hs, c,JOptionPane.INFORMATION_MESSAGE );

			}
			if(f.getSource() == bar.clearHighScore){
				
				scorePanel.clearScores();
				
			}
			if (f.getSource() == bar.history){
				JOptionPane.showMessageDialog(null, "Games Played: " +scorePanel.getGamesPlayed()+"\n");
			}
			if (f.getSource()==bar.easy){
				sleepTime = 750;
			}
			if (f.getSource()==bar.medium){
				sleepTime = 500;
			}
			if (f.getSource()==bar.hard){
				sleepTime = 300;
			}
			if (f.getSource()==bar.expert){
				sleepTime = 100;
			}
			if(f.getSource() == bar.about){
				String str = "Simonish V 0.2 \n by David Browning \n dmbrowning21.com";
				JOptionPane.showMessageDialog(null, str);
			}
			if (f.getSource() == bar.rules){
				String str =  "The rules of Simon are as follows: \n     I will proceed to light up a sequence of panels in a certain order,\n     you must repeat them back to me in that same order.\n"+
						"The game continues until you lose...and you will lose.\n\n     Are you ready?";
				Object[] options = {"Bring it on!",
				                    "No, thanks",
				                    "Go easy on me"};
				int n = JOptionPane.showOptionDialog(null,
						str	,
				    "Rules",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[2]);
				System.out.println(n);
				switch (n){
				case(0):sleepTime = 300; bar.hard.setSelected(true);f.setSource(bar.newGame);actionPerformed(f);
				break;
				case(1):JOptionPane.showMessageDialog(null, "wimp...");
				break;
				case(2):sleepTime = 750; bar.easy.setSelected(true);f.setSource(bar.newGame);actionPerformed(f);
				break;
				default:System.out.println("How did that even happen?");
				}
			}
			if(f.getSource() == bar.chooseColor){
				class ColorChooser extends JPanel implements ChangeListener{
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					protected JColorChooser tcc;
					public ColorChooser(){
						JPanel bannerPanel = new JPanel(new BorderLayout());
				        JLabel banner = new JLabel();
						bannerPanel.add(banner, BorderLayout.CENTER);
						bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));
						 
				        //Set up color chooser for setting text color
				        tcc = new JColorChooser(banner.getForeground());
				        tcc.getSelectionModel().addChangeListener(this);
				        tcc.setBorder(BorderFactory.createTitledBorder(
				                                             "Choose Text Color"));
				 
				        add(bannerPanel, BorderLayout.CENTER);
				        add(tcc, BorderLayout.PAGE_END);
				    
					}
					@Override
					public void stateChanged(ChangeEvent arg0) {
						if(tcc.getColor()!= null){
							newColor = tcc.getColor();
						}
						for(int i =0;i<panelArr.length;i++){
							panelArr[i].setOpaque(true);
		            		panelArr[i].setBackground(newColor);
		            	}
						update(getGraphics());
					}
					private void createAndShowGUI() {
				        //Create and set up the window.
				        JFrame frame = new JFrame("ColorChooser");
				        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 
				        //Create and set up the content pane.
				        JComponent newContentPane = new ColorChooser();
				        newContentPane.setOpaque(true); //content panes must be opaque
				        frame.setContentPane(newContentPane);
				 
				        //Display the window.
				        frame.pack();
				        frame.setVisible(true);
				    }
					
					
				}
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                ColorChooser cc = new ColorChooser();
		                cc.createAndShowGUI();
		            }
		        });
				
			}

		}
		
	}
	
}

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
