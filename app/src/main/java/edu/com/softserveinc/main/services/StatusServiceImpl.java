package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Query;
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
		Query query = session.createQuery("FROM StatusModel WHERE name = :name ");
		query.setParameter("name", name);
		StatusModel statusModel = null;
		for(int i = 0; i < query.list().size(); i++) {
			statusModel = (StatusModel)query.list().get(i);
			if(statusModel.getName() == name)
				break;
		}
		
		return statusModel;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadStatusList() {
		return dao.getAll(new StatusModel());
	}
}
