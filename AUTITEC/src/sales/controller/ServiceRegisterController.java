package sales.controller;

import java.util.HashMap;

import javax.swing.JOptionPane;

import model.OutSourcedServices;
import model.Service;
import sales.view.register.ServiceRegisterFrame;
import util.ShowMessage;
import database.dao.ServiceDAO;

public class ServiceRegisterController {

	
	private ServiceRegisterFrame frame;

	public ServiceRegisterController(ServiceRegisterFrame frame) {
		this.frame = frame;
		
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a tela de registro de serviço ?");
		if(i == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}

	public void register(Service s) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("meal", s.getMeal());
	    map.put("name", s.getName());
	    map.put("observation", s.getObservation());
	    map.put("price_I_I_I", s.getPrice_I_I_I());
	    map.put("price_I_I_II", s.getPrice_I_I_II());
	    map.put("price_I_II_I", s.getPrice_I_II_I());
	    map.put("price_I_II_II", s.getPrice_I_II_II());
	    map.put("price_I_III_I", s.getPrice_I_III_I());
	    map.put("price_I_III_II", s.getPrice_I_III_II());
	    map.put("price_II_I_I", s.getPrice_II_I_I());
	    map.put("price_II_I_II", s.getPrice_II_I_II());
	    map.put("price_II_II_I", s.getPrice_II_II_I());
	    map.put("price_II_II_II", s.getPrice_II_II_II());
	    map.put("price_II_III_I", s.getPrice_II_III_I());
	    map.put("price_II_III_II", s.getPrice_II_III_II());
	    map.put("pricePerkm", s.getPricePerKm());
	    map.put("stayValue", s.getStayValue());
	    
	    new ServiceDAO().register(map);
    }

	public void registerOutSourcedService(OutSourcedServices oss) {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("name", oss.getName());
		 map.put("observation", oss.getObservation());
		 new ServiceDAO().registerOutSourcedService(map);
    }
}
