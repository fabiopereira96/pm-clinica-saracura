package br.com.pm.clinicasaracura.controllers;

public interface PaymentController<TItem> {
	public String getTitle();
	public void paymentFinished(boolean status, TItem item);
}