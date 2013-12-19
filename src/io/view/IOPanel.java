package io.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.controller.IOController;
import io.model.Game;

import javax.swing.*;

public class IOPanel extends JPanel
{
	private IOController baseController;
	private JButton saveButton;
	private JButton loadButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private JLabel gameCountLabel;

	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;

		saveButton = new JButton("save the game");
		loadButton = new JButton("load the game");
		titleField = new JTextField(15);
		titleLabel = new JLabel("gameTitle:");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking:");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("game Rules:");
		gameCountLabel = new JLabel("current game count:");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel()
	{
		this.setBackground(Color.green);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, titleField, -3, SpringLayout.NORTH, titleLabel);
		springLayout.putConstraint(SpringLayout.WEST, titleField, 0, SpringLayout.WEST, rankingField);
		springLayout.putConstraint(SpringLayout.EAST, titleField, -10, SpringLayout.WEST, gameCountLabel);
		springLayout.putConstraint(SpringLayout.WEST, loadButton, 107, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, saveButton, 107, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, rulesArea, 46, SpringLayout.SOUTH, rankingField);
		springLayout.putConstraint(SpringLayout.WEST, rulesArea, 25, SpringLayout.EAST, rulesLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, rulesArea, -18, SpringLayout.NORTH, saveButton);
		springLayout.putConstraint(SpringLayout.EAST, rulesArea, -110, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		springLayout.putConstraint(SpringLayout.WEST, rankingField, 0, SpringLayout.WEST, rulesArea);
		springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, rankingLabel);
		springLayout.putConstraint(SpringLayout.NORTH, gameCountLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, gameCountLabel, -52, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, saveButton, -6, SpringLayout.NORTH, loadButton);
		springLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 63, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, loadButton, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 97, SpringLayout.SOUTH, rankingLabel);
		springLayout.putConstraint(SpringLayout.WEST, rulesLabel, 0, SpringLayout.WEST, rankingLabel);
		springLayout.putConstraint(SpringLayout.EAST, rulesLabel, 82, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, rankingLabel, 10, SpringLayout.WEST, this);
		setLayout(springLayout);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		this.add(gameCountLabel);

	}

	private void setupLayout()
	{

	}

	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("Current game Count: " + baseController.getProjectGames().size());
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}
			}
		});

		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if (tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for (String currentRule : tempGame.getGameRules())
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);

				}
				else
				{
					JOptionPane.showMessageDialog(null, "Chick the save file make sure it is in order.");
				}
			}
		});
	}
}