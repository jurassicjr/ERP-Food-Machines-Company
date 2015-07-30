package objectInfraMaintenence.controller;

import database.dao.ObjectInfraDAO;
import model.ObjectInfra;

public class ObjectInfraRegisterFrameController 
{
	public void insert(ObjectInfra object)
	{
		ObjectInfraDAO objectDAO = new ObjectInfraDAO();
		objectDAO.persist(object);
	}
}
