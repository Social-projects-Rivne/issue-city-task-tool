package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.CategoryService;
import edu.com.softserveinc.main.models.CategoryModel;

public class CategoryServiceImpl implements CategoryService {
	
	DaoImpl dao = new DaoImpl();
	
	/**
	 * Write new category to DB
	 */
	@Override
	public void addCategory(CategoryModel category) {
		dao.addInDB(category);
	}

	/**
	 * Delete category from DB
	 * @param category
	 * 
	 */
	@Override
	public void deleteCategory(CategoryModel category) {
		dao.deleteFromDB(category);
	}

	/**
	 * Change {@link CategoryModel} in DB
	 * @param category
	 */
	@Override
	public void editCategory(CategoryModel category) {
		dao.editInDB(category);
	}

	/**
	 * Load category by id
	 * @param id 
	 * @return CategoryModel
	 */
	@Override
	public CategoryModel getCategoryByID(int id) {
		return (CategoryModel) dao.getById(id, new CategoryModel());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadCategoriesList() {
		return dao.getAll(new CategoryModel());
	}
	
	/**
	 * 
	 * @param issueCategory
	 * @return true if category exists in table if else - false
	 */
	public boolean isExixis(String categoryName) {

		boolean result = false;

		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		if (session.createQuery(
						"select 'name' FROM CategoryModel" )
				.list().size() == 0) {
			result = true;
			System.out.println("category isn't exist !!!!!!!!!");
		} else {
			result = false;

			System.out.println("category exists");
		}

		return result;
	}

	public CategoryModel getCategoryByName(String name){
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM CategoryModel WHERE name = :name ");
		query.setParameter("name", name);
		 CategoryModel categoryModel = null;
		for(int i=0; i<	query.list().size(); i++){
			 categoryModel = (CategoryModel)query.list().get(i);
			 if(categoryModel.getName()==name) {
				
				 break;
			 }
		}
		return categoryModel;
	}
}
