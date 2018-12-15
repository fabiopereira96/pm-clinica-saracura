package br.com.pm.clinicasaracura.controllers;

import java.util.List;

import br.com.pm.clinicasaracura.AgendamentoConsultaWindow;
import br.com.pm.clinicasaracura.EdicaoAgendaConsultaWindow;
import br.com.pm.clinicasaracura.SelecaoWindow;
import br.com.pm.clinicasaracura.dao.*;
import br.com.pm.clinicasaracura.entity.*;

public class SelecaoMedicoController implements SelectionController<Medico> {	
	private Especialidade espec;
	
	public SelecaoMedicoController(Especialidade espec) {
		this.espec = espec;
	}
	
	public String getTitle() {
		return "Agendamento de consulta (médico)";
	}
	
	public String getLabel() {
		return "Selecione o médico";
	}
	
	public String getBtnText() {
		return "Selecionar";
	}
	
	public List<Medico> getElements() {
		return MedicoDAO.getInstance().findAll();			
	}

	public void selectedElement(Medico m) {
		AgendamentoConsultaWindow agendamentoConsultaWindow
			= new AgendamentoConsultaWindow(m.getCrm());

		agendamentoConsultaWindow.showFrame();
	}
}