package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.impl.SubscriptionServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SubscriptionServiceTest extends AbstractBawlFunctionalTest {


    private SubscriptionService subscriptionService;
    private SubscriptionDao subscriptionDao;

    @Before
    public void setup() {
        subscriptionService = new SubscriptionServiceImpl();
        subscriptionDao = mock(SubscriptionDao.class);
        Whitebox.setInternalState(subscriptionService, "subscriptionDao",subscriptionDao);
    }

    @Ignore
    @Test
       public void testCreateParamSubscriptionModel() throws Exception {
        SubscriptionModel mockSubscriptionModel = mock(SubscriptionModel.class);
      //  subscriptionService.create(mockSubscriptionModel);
        verify(subscriptionDao,times(1)).saveAndFlush(mockSubscriptionModel);
    }

    @Ignore
    @Test
    public void testCreateParamIssueIdAndEmail() throws Exception {
        SubscriptionModel mockSubscriptionModel = mock(SubscriptionModel.class);
        int issueId = 10;
        String email= "admin@admin.ru" ;
      //  subscriptionService.create(issueId, email);
        verify(subscriptionDao,times(1)).saveAndFlush(any(SubscriptionModel.class));
    }

    @Test
    public void testRead() throws Exception {
        int id = 6;
        subscriptionService.read(id );
        verify(subscriptionDao,timeout(1)).findOne(id);
    }

    @Test
    public void testDelete() throws Exception {
        subscriptionService.delete(1);
        verify(subscriptionDao,times(1)).delete(anyInt());
    }

    @Test
    public void testListByIssueId() throws Exception {
        int IssueId = 2;
        Collection <SubscriptionModel> findSubscription = subscriptionService.listByIssueId(IssueId);
        for(SubscriptionModel model :findSubscription){
            assertEquals(IssueId, model.getIssueId());
        }
    }
}