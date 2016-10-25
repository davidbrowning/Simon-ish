package dmbrowning21.notes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SoundDemo {
	
	boolean internet = true;

    private MidiChannel midi;
    private JPanel pressPanel;
    private JLabel imageLabel;
    private JLabel noteLabel;
    private JTextField noteField;
    private JLabel instrumentLabel;
    private JTextField instrumentField;
    private ImageIcon penguin1;
    private ImageIcon penguin2;
    private JButton colorButton;
    Color panelColor = Color.RED;

    public SoundDemo() {

        JFrame frame = new JFrame("Sound Fun");
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
        
        pressPanel = new JPanel();
        pressPanel.setBackground(panelColor);
        pressPanel.setPreferredSize(new Dimension(300, 300));
        pane.add(pressPanel, BorderLayout.CENTER);
        pressPanel.addMouseListener(new noteListener());
        pressPanel.setLayout(new GridLayout(1, 1));

        if (internet) {
        try {
				penguin1 = new ImageIcon(new URL("https://openclipart.org/image/200px/svg_to_png/27563/lemmling-Little-penguin-5.png"));
				penguin2 = new ImageIcon(new URL("https://openclipart.org/image/200px/svg_to_png/61207/flat-lemmling-Cartoon-penguin.png"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {   
	        penguin1 = new ImageIcon("data/lemmling-Little-penguin-5.png", null);
			penguin2 = new ImageIcon("data/flat-lemmling-Cartoon-penguin.png", null);
        }
		
		imageLabel = new JLabel(penguin1);
		pressPanel.add(imageLabel);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3));
        
        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        controlPanel.add(notePanel);
        
        JPanel instrumentPanel = new JPanel();
        instrumentPanel.setLayout(new BoxLayout(instrumentPanel, BoxLayout.Y_AXIS));
        controlPanel.add(instrumentPanel);
        
        noteLabel = new JLabel("Choose Note(0-127)");
        noteField = new JTextField("60");
        notePanel.add(noteLabel);
        notePanel.add(noteField);
        
        instrumentLabel = new JLabel("Choose Instrument (0-127)");
        instrumentField = new JTextField("60");
        instrumentPanel.add(instrumentLabel);
        instrumentPanel.add(instrumentField);
        
        colorButton = new JButton("Change Color");
        colorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelColor = JColorChooser.showDialog(frame, "Choose a Color", panelColor);
				pressPanel.setBackground(panelColor);
			}});
        
        controlPanel.add(colorButton);        
        
        pane.add(controlPanel, BorderLayout.NORTH);
        

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
	        midi = synth.getChannels()[0];
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    public static void notmain(String[] args) {
        new SoundDemo();
    }
    
    private void showErrorMessage() {
    	JOptionPane.showMessageDialog(null, "Check Your Input Values!");
    }
    
    private class noteListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int noteTmp = 0;
			int instrumentTmp = 0;
			try {
				noteTmp = Integer.parseInt(noteField.getText());
				instrumentTmp = Integer.parseInt(instrumentField.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				showErrorMessage();
				return;
			}
			
			if (noteTmp < 0 || noteTmp > 127 || instrumentTmp < 0 || instrumentTmp > 127) {
				showErrorMessage();
				return;
			}
			
			midi.programChange(instrumentTmp);
			midi.noteOn(noteTmp, 150);
			
			//imageLabel.removeAll();
			imageLabel.setIcon(penguin2);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			midi.allNotesOff();
			imageLabel.removeAll();
			imageLabel.setIcon(penguin1);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

    	
    }
}