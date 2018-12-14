package br.com.pm.clinicasaracura.controllers;

import java.util.List;

public interface SelectionController<TItem> {
	public String getTitle();
	public String getLabel();
	public String getBtnText();
	public List<TItem> getElements();
    public void selectedElement(TItem item);
}