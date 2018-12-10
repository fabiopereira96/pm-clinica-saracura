package br.com.pm.clinicasaracura;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.pm.clinicasaracura.dao.*;
import br.com.pm.clinicasaracura.entity.*;
import java.util.*;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;

public class TiposExameWindow {

	private JFrame frame;
	private static AgendamentoExameWindow agendamentoExameWindow = new AgendamentoExameWindow();

	private int mode;

	/**
	 * Create the application.
	 */
	public TiposExameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		JButton selecionarButton,
				voltarButton;

		mode = 0;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(12, 234, 117, 25);
		voltarButton.addMouseListener(new MouseAdapter() {
		   @Override
		   public void mouseReleased(MouseEvent e) {
		    try {
		     if(mode == 1) {
		      frame.dispose();
		      initialize();
		      setVisible(true);
		     } else {
		      setVisible(false);
		      frame.dispose();
		     }
		    } catch (Exception f) {
		     System.exit(0);
		    }
		   }
		});
		frame.getContentPane().add(voltarButton);
		
		JLabel selecioneLabel = new JLabel("Selecione um tipo de exame.");
		selecioneLabel.setBounds(12, 36, 221, 15);
		frame.getContentPane().add(selecioneLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 63, 416, 159);
		frame.getContentPane().add(scrollPane_1);
		
		List<TipoExame> rows = TipoExameDAO.getInstance().findAll();

		JList tiposExameList = new JList(rows.toArray());
		tiposExameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tiposExameList);
		tiposExameList.setDragEnabled(true);

		selecionarButton = new JButton("Selecionar");
		selecionarButton.setEnabled(false);
		selecionarButton.setBounds(317, 234, 117, 25);

		selecionarButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!selecionarButton.isEnabled()) {
					return;
				}

				int idx = tiposExameList.getSelectedIndex();
				agendamentoExameWindow.setTipoExame(idx+1);
				agendamentoExameWindow.setVisible(true);
			}
		});
		frame.getContentPane().add(selecionarButton);

		tiposExameList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selecionarButton.setEnabled(true);
			}
		});
	}
	
	public void setVisible(boolean t) {
		frame.setVisible(t);
	}
}