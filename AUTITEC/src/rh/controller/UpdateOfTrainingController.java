package rh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import model.Training;
import util.ShowMessage;
import database.dao.EmployeeDAO;
import database.dao.TrainingDAO;

public class UpdateOfTrainingController {

	private JFrame frame;

	public UpdateOfTrainingController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela?\n Os Dados não salvos serão perdidos");
		
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void fillEmployees(JComboBox<Employee> cboEmployee){
		cboEmployee.removeAllItems();
		List<Employee> list = new EmployeeDAO().getEmployees();
		list.forEach(e -> cboEmployee.addItem(e));
	}

	public void fillTraining(JComboBox<Training> cboTraining) {
		List<Training> list = new TrainingDAO().getTraining();
		list.forEach(e -> cboTraining.addItem(e));
	}

	/**
	 * Responsavel por "mapear" os atributos da classe training e transmitir para DAO realizar a atualização no banco de dador
	 * @param training é o treinamenot que deseja atualizar.
	 * 
	 */
	public void update(Training training) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	   Date date = training.getDate();
	   String duration = training.getDuration();
	   String eventType = training.getEventType();
	   int id = training.getId();
	   String motive = training.getMotive();
	   String objective = training.getObjective();
	   String period = training.getPeriod();
	   String place = training.getPlace();
	   String title = training.getTitle();
    }

	/**
	 * Metodo responsável transmitir os dados do treinamento que será deletado.
	 * @param training o treinamento que deseja que seja deletado.
	 */
	public void delete(Training training) {
	    new TrainingDAO().delete(training);
    }

	/**
	 * Persiste os dados que serão usados para cancelar um treinamento.
	 * @param training o treinamento que deseja cancelar;
	 * @param delMotive o motivo pelo qual está cancelando o treinamento.
	 */
	public void cancel(Training training, String delMotive) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("training", training);
		map.put("motive", delMotive);
		
		new TrainingDAO().cancel(map);
    }
}