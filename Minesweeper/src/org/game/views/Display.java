package org.game.views;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display {
	JFrame frame = new JFrame();
	static CreateMines createMines_instance = null;
	static Display instance = null;

	public static Display getInstance(){
		if(instance == null){
			instance = new Display();
		}
		return instance;
	}
	
	public JDialog getDialogFrame(){
		return new JDialog(frame,  Dialog.ModalityType.APPLICATION_MODAL);
	}
	
	private Display() {
		// TODO Auto-generated constructor stub
	}
	
	public static CreateMines getMinefieldInstance(){
		return createMines_instance;
	}
	
	public static void setCreateMines(CreateMines createmines){
		createMines_instance = createmines;
	}
	
	public void createFrame(){
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(300,100));
		frame.setVisible(true);
		frame.add(getChoosePanel());
	}
	
	public JPanel getChoosePanel(){
		JPanel choosePanel = new JPanel();
		
		JLabel lbl = new JLabel("Choose your game level :        ");
		choosePanel.add(lbl);
		
		JButton bt10x10 = new JButton("10 X 10");
		bt10x10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createMines_instance = new CreateMines(10, 10);
				createMines_instance.createFrame(getDialogFrame());
			}
		});
		JButton bt20x20 = new JButton("20 X 20");
		bt20x20.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createMines_instance = new CreateMines(20, 20);
				createMines_instance.createFrame(getDialogFrame());
			}
		});
		JButton bt30x30 = new JButton("30 X 30");
		bt30x30.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createMines_instance = new CreateMines(30, 30);
				createMines_instance.createFrame(getDialogFrame());
			}
		});
		choosePanel.add(bt10x10);
		choosePanel.add(bt20x20);
		choosePanel.add(bt30x30);
		
		
		return choosePanel;
	}
}
