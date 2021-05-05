package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	
	List<Nerc> NercList;
	
	List<PowerOutage> POIniziali;
	List<PowerOutage> SoluzioneMigliore;
	
	int personeCoinvolteMigliore;
	
	public Model() {
		
		podao = new PowerOutageDAO();
		
		NercList = podao.getNercList();
		
	}
	
	public List<Nerc> getNercList() {
		
		return this.NercList;
		
	}
	
	public List<PowerOutage> trovaWorstCase(Nerc nerc, int anniMax, int oreMax) {
		
		SoluzioneMigliore = new ArrayList<>();
		personeCoinvolteMigliore = 0;
		
		POIniziali = podao.getPowerOutagesByNerc(nerc);
		Collections.sort(POIniziali);
		
		cerca(new ArrayList<PowerOutage>(), anniMax, oreMax);
		
		return SoluzioneMigliore;
		
	}

	private void cerca(List<PowerOutage> parziale, int anniMax, int oreMax) {
			
		if(calcolaCoinvolte(parziale) > personeCoinvolteMigliore) {
			
			personeCoinvolteMigliore = calcolaCoinvolte(parziale);
			SoluzioneMigliore = new ArrayList<PowerOutage>(parziale);
			
		}
		
		for(PowerOutage po : POIniziali) {
			
			if(!parziale.contains(po)) {
				
				parziale.add(po);
				
				if(controllaMaxAnni(parziale, anniMax) && controllaMaxOre(parziale, oreMax))
					cerca(parziale, anniMax, oreMax);
				
				
				parziale.remove(po);
				
			}
			
		}
			
	}

	public int calcolaCoinvolte(List<PowerOutage> parziale) {
		
		int result = 0;
		
		for(PowerOutage po : parziale)
			result += po.getCustomersAffected();
			
		return result;
		
	}
	
	public int calcolaOre(List<PowerOutage> parziale) {
		
		int sum = 0;
		
		for(PowerOutage po : parziale) 
			sum += po.getOutageDuration();
		
		return sum;
		
	}

	private boolean controllaMaxAnni(List<PowerOutage> parziale, int anniMax) {
		
		if(parziale.size() >= 2) {
			
			int ai = parziale.get(0).getYear();
			int af = parziale.get(parziale.size()-1).getYear();
			
			if((af-ai) > anniMax)
				return false;
			
		}
		
		return true;
		
	}
	
	private boolean controllaMaxOre(List<PowerOutage> parziale, int oreMax) {
		
		int sum = calcolaOre(parziale);
		
		if(sum > oreMax)
			return false;
		
		return true;
		
	}

}
