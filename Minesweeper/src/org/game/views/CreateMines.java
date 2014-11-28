package org.game.views;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class CreateMines {
	
	int row;
	int column;
	Cell[][] cells = null;
	public Dialog dialog = null;
	int totalMines = 0;
	
	public CreateMines(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public void createFrame(JDialog dialog){
		this.dialog = dialog;
		dialog.setLocationRelativeTo(null);
		dialog.setSize(new Dimension(18*column + 3, 18*row + 30));
		dialog.add(getMinesPanel());
		dialog.setVisible(true);
	}

	public JPanel getMinesPanel(){
		JPanel minesPanel = new JPanel();
//		minesPanel.setPreferredSize(new Dimension(17*column, 17*row));
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 1,1);
		minesPanel.setLayout(flowLayout);
		cells = new Cell[row][column];
		for(int i = 0; i < row; i++){
			for(int j = 0 ; j< column; j++){
				Cell cell = new Cell(i, j);
				cells[i][j] = cell;
				minesPanel.add(cell);
			}
		}
		
		for(int i = 0; i < row; i++){
			for(int j = 0 ; j< column * 0.20; j++){
				Random rowRandom = new Random();
				Cell cell = cells[i][rowRandom.nextInt(column)];
				if(!cell.hasMine()){
					cell.setMine(true);
					totalMines++;
				}
//				cell.setText("m");
			}
		}
		return minesPanel;
	}
	
	public int getSurroundedMinesCount(int rowPos, int columnPos){
		int count = 0;
		for(int i = rowPos > 0 ? rowPos - 1 : rowPos ; i <= (row-1 > rowPos ? rowPos + 1 : rowPos); i++){
			for(int j = columnPos > 0 ? columnPos - 1 : columnPos ; j <= (column -1 > columnPos ? columnPos + 1 : columnPos); j++){
				Cell cell = cells[i][j];
				if(cell.hasMine){
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean hasAllMinesFound(){
		for(int i = 0; i < row; i++){
			for(int j = 0 ; j< column; j++){
				Cell cell = cells[i][j];
				if(!cell.isOpened() && !cell.hasMine()){
					return false;
				}
			}
		}
		return true;
	}
	
	public void showAllMines(){
		for(int i = 0; i < row; i++){
			for(int j = 0 ; j< column; j++){
				Cell cell = cells[i][j];
				if(cell.hasMine()){
					cell.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
					cell.setText("*");
				}
				cell.setEnabled(false);
			}
		}
	}
	
	public void clearNearFields(int rowPos, int columnPos){
		for(int i = rowPos > 0 ? rowPos - 1 : rowPos ; i <= (row-1 > rowPos ? rowPos + 1 : rowPos); i++){
			for(int j = columnPos > 0 ? columnPos - 1 : columnPos ; j <= (column -1 > columnPos ? columnPos + 1 : columnPos); j++){
				Cell cell = cells[i][j];
				if(!cell.isOpened()){
					cell.setOpened();
					int count = Display.getMinefieldInstance().getSurroundedMinesCount(i, j);
					if(count == 0){
						clearNearFields(i, j);
					}
					cell.setText(String.valueOf(count));
					cell.setEnabled(false);
				}
			}
		}
	}
}
