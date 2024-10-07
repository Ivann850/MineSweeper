package Project2;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class MineSweeperGUI extends JFrame implements ActionListener{
	private Grid gameGrid = new Grid(3,3,2);
	private JButton[][] gridButton = new JButton[gameGrid.getNumRows()][gameGrid.getNumColumns()];
	private JTextField[][]numBombsGrid = new JTextField [gameGrid.getNumRows()][gameGrid.getNumColumns()];
	private JTextField[][]bombsGrid = new JTextField [gameGrid.getNumRows()][gameGrid.getNumColumns()];
	static int yesNo = -2;
	int noBombCount = 0;
	GridBagConstraints gbConstraints = new GridBagConstraints();
	
	public MineSweeperGUI() {
		setLayout(new GridBagLayout());
		setTitle("Minesweeper");
		
		for(int i = 0; i < gameGrid.getNumRows(); i++) {
			for(int j = 0; j < gameGrid.getNumColumns(); j++) {
				// Creating a grid of text fields with bomb locations
				gbConstraints.gridx = i;
				gbConstraints.gridy = j;
				bombsGrid[i][j] = new JTextField();
				if(gameGrid.isBombAtLocation(i, j))
					bombsGrid[i][j].setText("BOMB!");
				bombsGrid[i][j].setVisible(false);
				add(bombsGrid[i][j], gbConstraints);
				
				//Creating a grid of text fields with the number of bombs
				gbConstraints.gridx = i;
				gbConstraints.gridy = j;
				numBombsGrid[i][j] = new JTextField();
				numBombsGrid[i][j].setText(Integer.toString(gameGrid.getCountAtLocation(i, j)));
				numBombsGrid[i][j].setVisible(false);
				add(numBombsGrid[i][j], gbConstraints);
				
				//Creating a grid of buttons
				gbConstraints.gridx = i;
				gbConstraints.gridy = j;
				gridButton[i][j] = new JButton();
				gridButton[i][j].addActionListener(this);
				add(gridButton[i][j], gbConstraints);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("3. " + yesNo);
		JButton sourceEvent = (JButton) e.getSource();
		sourceEvent.setEnabled(false);
		// Finding the button clicked
		for(int i = 0; i < gameGrid.getNumRows(); i++) {
			for(int j = 0; j < gameGrid.getNumColumns(); j++) {
				if(gridButton[i][j] == e.getSource()) {
					//Checking if a bomb is at this location
					if(gameGrid.isBombAtLocation(i, j)) {
						for(int k = 0; k < gameGrid.getNumRows(); k++) {
							for(int l = 0; l < gameGrid.getNumColumns(); l++) {
								gridButton[k][l].setEnabled(false);
								if(gameGrid.isBombAtLocation(k, l)) {
									bombsGrid[k][l].setVisible(true);
									pack();
								}
								else {
									numBombsGrid[k][l].setVisible(true);
									pack();
								}
							}
						}
						yesNo = JOptionPane.showConfirmDialog(this, "Oh no you lost!\nWould you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
						if(yesNo == 0) {
							this.dispose();
							MineSweeperGUI newFrame = new MineSweeperGUI();
							newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							newFrame.pack();
							newFrame.setVisible(true);
						}
						else
							this.dispose();
					}
					else {
						++noBombCount;
						numBombsGrid[i][j].setVisible(true);
						pack();
						
						if(noBombCount == (gameGrid.getNumRows() * gameGrid.getNumColumns()) - gameGrid.getNumBombs()) {
							for(int k = 0; k < gameGrid.getNumRows(); k++) {
								for(int l = 0; l < gameGrid.getNumColumns(); l++) {
									gridButton[k][l].setEnabled(false);
									if(gameGrid.isBombAtLocation(k, l)) {
										bombsGrid[k][l].setVisible(true);
										pack();
									}
									else {
										numBombsGrid[k][l].setVisible(true);
										pack();
									}
								}
							}
							yesNo = JOptionPane.showConfirmDialog(this, "Yayy you Won!\nWould you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
							if(yesNo == 0) {
								this.dispose();
								MineSweeperGUI newFrame = new MineSweeperGUI();
								
								newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								newFrame.pack();
								newFrame.setVisible(true);
							}
							else
								this.dispose();
						}
					}
				}
			}
		}
	}
}
