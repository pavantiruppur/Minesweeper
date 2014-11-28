package org.game.views;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Cell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean hasMine;//default false
	boolean isFlaged;//default false
	boolean isOpened;//default false
	int row;//row position of the cell
	int column;//column position of the cell
	
	public Cell(int rowPos, int columnPos) {
		this.row = rowPos;
		this.column = columnPos;
		this.setPreferredSize(new Dimension(17,17));
		setMargin(new Insets(0, 0, 0, 0));
		super.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
			      if(isFlaged){
			    	  setFlaged(false);
			    	  setText("");
			      }else {
			    	  setFlaged(true);
			    	  setText("f");
			      }
				} else if(e.getButton() == MouseEvent.BUTTON1){
					if(isFlaged){
						JOptionPane.showMessageDialog(Display.getMinefieldInstance().dialog, "Right click to unflag");
					} else if(hasMine){
						Display.getMinefieldInstance().showAllMines();
						Display.getMinefieldInstance().dialog.validate();
						int choice = JOptionPane.showConfirmDialog(Display.getMinefieldInstance().dialog, "Yor landed into mine!!! Game Over. Do you want to restart the game ?");
						if(choice == JOptionPane.YES_OPTION){
							Display.getMinefieldInstance().dialog.setVisible(false);
							Display.setCreateMines(new CreateMines(Display.getMinefieldInstance().row, Display.getMinefieldInstance().column));
							Display.getMinefieldInstance().createFrame(Display.getInstance().getDialogFrame());
						} else if(choice == JOptionPane.NO_OPTION){
							Display.getMinefieldInstance().dialog.setVisible(false);
						}
					} else{
						setOpened();
						int count = Display.getMinefieldInstance().getSurroundedMinesCount(row, column);
						if(count == 0){
							Display.getMinefieldInstance().clearNearFields(row, column);
						}
						setText(String.valueOf(count));
						setEnabled(false);
					}
				}
				if(Display.getMinefieldInstance().hasAllMinesFound()){
					Display.getMinefieldInstance().showAllMines();
					JOptionPane.showMessageDialog(Display.getMinefieldInstance().dialog, "Congrats!!! You found all the mines");
				}
			}
		});
	}
	
	public boolean hasMine() {
		return hasMine;
	}
	public void setMine(boolean hasMine) {
		this.hasMine = hasMine;
	}
	
	public boolean isFlaged() {
		return isFlaged;
	}
	public void setFlaged(boolean isFlaged) {
		this.isFlaged = isFlaged;
	}
	
	public boolean isOpened() {
		return isOpened;
	}
	public void setOpened() {
		this.isOpened = true;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
}
