package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.impl.SubscriptionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.*;

public class SubscriptionServiceTest extends AbstractBawlFunctionalTest {


    private SubscriptionService subscriptionService;
    private SubscriptionDao subscriptionDao;

    @Before
    public void setup() {
        subscriptionService = new SubscriptionServiceImpl();
        subscriptionDao = mock(SubscriptionDao.class);
        Whitebox.setInternalState(subscriptionService, "subscriptionDao",subscriptionDao);
    }

    @Test
    public void testCreateParamIssueIdAndEmail() throws Exception {
        SubscriptionModel mockSubscriptionModel = mock(SubscriptionModel.class);
        int issueId = 10;
        int userId=10;
       subscriptionService.createSubscription(issueId, userId);
        verify(subscriptionDao,times(1)).saveAndFlush(any(SubscriptionModel.class));
    }


    @Test
    public void testDelete() throws Exception {
        subscriptionService.delete(1);
        verify(subscriptionDao,times(1)).delete(anyInt());
    }

//    @Test
//    public void testListByIssueId() throws Exception {
//        int IssueId = 2;
//        Collection <SubscriptionModel> findSubscription = subscriptionService.listByIssueId(IssueId);
//        for(SubscriptionModel model :findSubscription){
//            assertEquals(IssueId, model.getIssueId());
//        }
//    }
}