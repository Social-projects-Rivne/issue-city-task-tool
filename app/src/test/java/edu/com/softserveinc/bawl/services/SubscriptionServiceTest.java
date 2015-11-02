package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.impl.SubscriptionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SubscriptionServiceTest extends AbstractBawlTest {


    private SubscriptionService subscriptionService;
    private SubscriptionDao subscriptionDao;

    @Before
    public void setup() {
        subscriptionService = new SubscriptionServiceImpl();
        subscriptionDao = mock(SubscriptionDao.class);
        Whitebox.setInternalState(subscriptionService, "subscriptionDao",subscriptionDao);
    }

    @Test
    public void testCreate() throws Exception {
        SubscriptionModel mockSubscriptionModel = mock(SubscriptionModel.class);
        subscriptionService.create(mockSubscriptionModel);
        verify(subscriptionDao,times(1)).saveAndFlush(mockSubscriptionModel);
    }

    @Test
    public void testRead() throws Exception {

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