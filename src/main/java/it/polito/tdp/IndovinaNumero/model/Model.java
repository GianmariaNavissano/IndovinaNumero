package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false; //inGioco permette di sapere se si sta ancora giocando (è false se non hai più tentativi o indovini)
	private Set<Integer> tentativi;
	
	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	/**
	 * 0 -> tentativo corretto
	 * 1 -> tentativo troppo alto
	 * -1 -> tentativo troppo basso
	 * @param tentativo
	 * @return
	 */
	public int tentativo(int tentativo) {
		//controllo se la partita è in corso
		if(!inGioco){
			throw new IllegalStateException("La partita è già terminata");
		}
		//controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inderire un numero tra 1 e NMAX e non puoi inserire 2 volte lo stesso numero");
		}
		
		//il tentativo è valido
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		if(tentativiFatti==(TMAX-1) ) {
			this.inGioco=false;
		}
		
		if(tentativo==segreto) {
			this.inGioco=false;
			return 0;
		} else if(tentativo< segreto) {
			return -1;
		} else return 1;
	}

	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX || this.tentativi.contains(tentativo)) {
			return false;
		}
		else return true;
	}
	
	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}


}
