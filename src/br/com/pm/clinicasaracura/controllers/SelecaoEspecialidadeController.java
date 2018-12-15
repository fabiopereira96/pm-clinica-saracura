package br.com.pm.clinicasaracura.controllers;

import java.util.List;

import br.com.pm.clinicasaracura.EdicaoAgendaExameWindow;
import br.com.pm.clinicasaracura.SelecaoWindow;
import br.com.pm.clinicasaracura.dao.*;
import br.com.pm.clinicasaracura.entity.*;
import br.com.pm.clinicasaracura.controllers.*;

public class SelecaoEspecialidadeController implements SelectionController<Especialidade> {	
	public String getTitle() {
		return "Agendamento de consulta (especialidade)";
	}
	
	public String getLabel() {
		return "Selecione a especialidade";
	}
	
	public String getBtnText() {
		return "Selecionar";
	}
	
	public List<Especialidade> getElements() {
		return EspecialidadeDAO.getInstance().findAll();			
	}

	@SuppressWarnings("rawtypes")
	public void selectedElement(Especialidade spec) {
		SelecaoWindow selecaoMedicoWindow
			= new SelecaoWindow<Medico, SelecaoMedicoController>(
				new SelecaoMedicoController(spec)
			  );

		selecaoMedicoWindow.showFrame();
	}
}