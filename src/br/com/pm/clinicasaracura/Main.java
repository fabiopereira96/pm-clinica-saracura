package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Main { 
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MainWindow mainWindow = new MainWindow();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow.showFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}