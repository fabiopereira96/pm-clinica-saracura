package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Main {
	private static MainWindow mainWindow = new MainWindow();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(new MetalLookAndFeel());
						MainWindow.setDefaultLookAndFeelDecorated(true);
						JFrame.setDefaultLookAndFeelDecorated(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}