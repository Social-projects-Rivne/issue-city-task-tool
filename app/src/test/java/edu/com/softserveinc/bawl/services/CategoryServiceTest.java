package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.models.CategoryModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class CategoryServiceTest extends AbstractBawlTest {

@Autowired
private CategoryService service; 
	
	@Test(expected = DataAccessException.class)
	public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown(){
	   String name = "Random category name";
	   service.addCategory(new CategoryModel(name));
	   service.addCategory(new CategoryModel(name));
	}

	
}
