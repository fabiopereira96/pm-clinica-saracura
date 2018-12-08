package br.com.pm.clinicasaracura;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frame;
	private static MainWindow mainWindow = new MainWindow();
	private static EspecialidadesWindow especialidadesWindow = new EspecialidadesWindow();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setSize(new Dimension(500, 300));
		frame.setSize(new Dimension(500, 500));
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(500, 300));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton agConsultaButton = new JButton("Agendamento de Consulta");
		agConsultaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					especialidadesWindow.setVisible(true);
				} catch (Exception f) {
					System.exit(0);
				}
			}
		});
		agConsultaButton.setBounds(12, 192, 472, 25);
		panel.add(agConsultaButton);
		
		JButton agExameImgButton = new JButton("Agendamento de Exame de Imagem");
		agExameImgButton.setBounds(12, 229, 472, 25);
		panel.add(agExameImgButton);
		
		JButton atualizacaoButton = new JButton("Atualização do Cadastro de Agendas");
		atualizacaoButton.setBounds(12, 266, 472, 25);
		panel.add(atualizacaoButton);
		
		JButton exitButton = new JButton("Sair");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(12, 303, 472, 25);
		panel.add(exitButton);
		
		JLabel saracuraLabel = new JLabel("Clínica Saracura");
		saracuraLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saracuraLabel.setFont(new Font("Arial", Font.BOLD, 40));
		saracuraLabel.setBounds(12, 60, 472, 64);
		panel.add(saracuraLabel);
	}
}
