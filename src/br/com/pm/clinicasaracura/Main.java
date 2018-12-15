package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

public class Main {
	private static MainWindow mainWindow = new MainWindow();

	public static void main(String[] args) {
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