package com.erdi.distanceutility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;

public class DistanceUtility {
	private static final double M_TO_KM = 1.609344;
	
	private static final Dimension FIELD_DIMENSIONS = new Dimension(400, 20);
	
	private JFrame frame = new JFrame("Distance Utility");
	private JPanel panel = new JPanel();
	
	private JTextField kmField = new JTextField();
	private JTextField milesField = new JTextField();
	
	{
		panel.setFocusable(true);
		panel.requestFocus();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		((PlainDocument) kmField.getDocument()).setDocumentFilter(new FloatFilter());
		((PlainDocument) milesField.getDocument()).setDocumentFilter(new FloatFilter());	
		
		kmField.setPreferredSize(FIELD_DIMENSIONS);
		milesField.setPreferredSize(FIELD_DIMENSIONS);
		
		kmField.setToolTipText("Kilometers");
		milesField.setToolTipText("Miles");
		
		JTextPane kmLabel = new JTextPane();
		
		
		kmLabel.setText("Kilometers");
		kmLabel.setEditable(false);
		kmLabel.setBackground(new Color(0, 0, 0, 0));
		
		kmField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			private void update() {
				try {
					milesField.setText("" + (Float.parseFloat(kmField.getText()) / M_TO_KM));
				} catch(Exception e) {}
			}
		});

		milesField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			private void update() {
				try {
					kmField.setText("" + (Float.parseFloat(milesField.getText()) * M_TO_KM));
				} catch(Exception e) {}
			}
		});
		
		panel.add(kmLabel);
		panel.add(kmField);
		
		JTextPane milesLabel = new JTextPane();
		
		milesLabel.setText("Miles");
		milesLabel.setEditable(false);
		milesLabel.setBackground(new Color(0, 0, 0, 0));
		
		panel.add(milesLabel);
		panel.add(milesField);
		
		frame.setResizable(false);
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setSize(FIELD_DIMENSIONS.width, FIELD_DIMENSIONS.height << 3);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}
