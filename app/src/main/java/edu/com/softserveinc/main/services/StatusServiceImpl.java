package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.StatusService;
import edu.com.softserveinc.main.models.StatusModel;

public class StatusServiceImpl implements StatusService {
	
	DaoImpl dao = new DaoImpl();
	
	@Override
	public void addStatus(StatusModel status) {
		dao.addInDB(status);
	}
	
	public StatusModel getStatusByName(String name){
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		return (StatusModel) session.createQuery("FROM StatusModel WHERE name =" + name);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadStatusList() {
		return dao.getAll(new StatusModel());
	}
}
