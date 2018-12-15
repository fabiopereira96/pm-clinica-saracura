package br.com.pm.clinicasaracura;

import java.awt.BorderLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import br.com.pm.clinicasaracura.controllers.*;
import br.com.pm.clinicasaracura.entity.*;

import javax.swing.ImageIcon;

@SuppressWarnings("rawtypes")
public class MainWindow {

	private JFrame frame;
	
	private final static String LOGOPATH = "src/resources/logo-sized.png";

	private static SelecaoWindow especialidadesWindow
		= new SelecaoWindow<Especialidade, SelecaoEspecialidadeController>(
			new SelecaoEspecialidadeController()
		);
	
	private static SelecaoWindow selecaoTipoExameWindow
		= new SelecaoWindow<TipoExame, SelecaoTipoExameController>(
			new SelecaoTipoExameController()
		);

	private static SelecaoTipoAgendaWindow selecaoTipoAgendaWindow = new SelecaoTipoAgendaWindow();

	public MainWindow() {
		initialize();
	}
	
	private void initialize() {
		/* Setup */
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		frame = new JFrame();
		frame.getContentPane().setSize(new Dimension(500, 300));
		frame.setSize(new Dimension(500, 380));
		frame.setResizable(false);
		frame.setTitle("Clínica Saracura");
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(500, 300));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		/* Agendamento de consulta */
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

		/* Agendamento de exame de imagem */
		JButton agExameImgButton = new JButton("Agendamento de Exame de Imagem");
		agExameImgButton.setBounds(12, 229, 472, 25);
		agExameImgButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					selecaoTipoExameWindow.setVisible(true);
				} catch (Exception f) {
					System.exit(0);
				}
			}
		});
		panel.add(agExameImgButton);

		/* Atualização do cadastro de agendas */
		JButton atualizacaoButton = new JButton("Atualização do Cadastro de Agendas");
		atualizacaoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					selecaoTipoAgendaWindow.setVisible(true);
				} catch (Exception f) {
					System.exit(0);
				}
			}
		});
		atualizacaoButton.setBounds(12, 266, 472, 25);
		panel.add(atualizacaoButton);

		/* Tela principal */
		JButton exitButton = new JButton("Sair");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(12, 303, 472, 25);
		panel.add(exitButton);

		ImageIcon logo = new ImageIcon(LOGOPATH);
		JLabel saracuraLabel = new JLabel(logo);
		saracuraLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saracuraLabel.setBounds(12, 60, 472, 200);
		panel.add(saracuraLabel);
	}
	
	public void showFrame() {
		frame.setVisible(true);
	}
}
