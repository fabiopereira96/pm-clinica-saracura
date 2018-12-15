package br.com.pm.clinicasaracura.controllers;

import br.com.pm.clinicasaracura.entity.*;
import br.com.pm.clinicasaracura.dao.*;

public class PgtoAgendaExameController implements PaymentController<AgendaEquipamento> {
	public String getTitle() {
		return "Pagamento (agendamento de exame)";
	}
	
	public void paymentFinished(boolean status, AgendaEquipamento item) {
		if (status) {
			AgendaEquipamentoDAO.getInstance().persist(item);
		}
	}
}
