package com.sun.blueprints.test.actions;

import org.easymock.EasyMock;
import org.junit.After;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.mock.web.MockHttpServletRequest;

import com.sun.blueprints.test.AbstractJndiContextTests;
import com.sun.blueprints.test.MockHolder;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAO;
import com.sun.j2ee.blueprints.consumerwebsite.AdventureComponentManager;
import com.sun.j2ee.blueprints.consumerwebsite.AdventureKeys;
import com.sun.j2ee.blueprints.customer.dao.AccountDAO;
import com.sun.j2ee.blueprints.order.service.OrderTrackingService;
import com.sun.j2ee.blueprints.order.service.PurchaseOrderService;
import com.sun.j2ee.blueprints.signon.dao.UserDAO;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

/**
 * Base class for testing {@link HTMLAction} classes. It makes sure that
 * the environment is setup correctly. It registers a {@link AdventureComponentManager}
 * in the MockHttpSession. The AdventureComponentManager needs some JNDI
 * properties set, we also setup those. Finally we honor the {@link HTMLAction}
 * lifecycle. We first call {@link HTMLAction#doStart(javax.servlet.http.HttpServletRequest)}
 * and after the test we call 
 * {@link HTMLAction#doEnd(javax.servlet.http.HttpServletRequest, com.sun.j2ee.blueprints.waf.controller.EventResponse)}
 * .
 * 
 * We also choose to setup all the mocks here and register them with the
 * {@link MockHolder}, this due to the fact that several actions need more than
 * 1 mock.
 * 
 * @author Marten Deinum
 *
 * @see HTMLAction
 */
public abstract class AbstractActionTests extends AbstractJndiContextTests {

	protected MockHttpServletRequest request;

	protected AccountDAO accountDAO;
	protected CatalogDAO catalogDAO;
	protected UserDAO userDAO;
	protected PurchaseOrderService purchaseOrderService;
	protected OrderTrackingService orderTrackingService;
	
	protected final void onSetup() {
		request = new MockHttpServletRequest();
		AdventureComponentManager acm = new AdventureComponentManager();
		acm.init(request.getSession());
		populateRequest(request);
		getActionUnderTest().doStart(request);
		
		//Create mocks
		accountDAO = EasyMock.createMock(AccountDAO.class);
		catalogDAO = EasyMock.createMock(CatalogDAO.class);
		userDAO = EasyMock.createMock(UserDAO.class);
		purchaseOrderService = EasyMock.createMock(PurchaseOrderService.class);
		orderTrackingService = EasyMock.createMock(OrderTrackingService.class);
		//Register mocks
		MockHolder.setMock(UserDAO.class, userDAO);
		MockHolder.setMock(AccountDAO.class, accountDAO);
		MockHolder.setMock(CatalogDAO.class, catalogDAO);
		MockHolder.setMock(PurchaseOrderService.class, purchaseOrderService);
		MockHolder.setMock(OrderTrackingService.class, orderTrackingService);
		
		doOnSetup();
	}

	@Override
	protected void setupJndiContext() throws Exception {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		builder.bind(com.sun.j2ee.blueprints.catalog.dao.JNDINames.CATALOG_DAO_CLASS, "com.sun.j2ee.blueprints.catalog.dao.MockDelegatingCatalogDao");
		builder.bind(com.sun.j2ee.blueprints.customer.dao.JNDINames.ACCOUNT_DAO_CLASS, "com.sun.j2ee.blueprints.customer.dao.MockDelegatingAccountDao");
		builder.bind(com.sun.j2ee.blueprints.signon.dao.JNDINames.SIGNON_DAO_CLASS, "com.sun.j2ee.blueprints.signon.dao.MockDelegatingUserDao");
		builder.bind(com.sun.j2ee.blueprints.order.service.JNDINames.PURCHASE_ORDER_SERVICE_CLASS, "com.sun.j2ee.blueprints.purchaseorder.service.MockDelegatingOrderService");
	}
	
	@After
	public final void tearDown() {
		getActionUnderTest().doEnd(request, null);
		onTearDown();
	}
	
	protected void populateRequest(MockHttpServletRequest request2) {}

	protected final AdventureComponentManager getAdventureComponentManager() {
		return (AdventureComponentManager) request.getSession().getAttribute(AdventureKeys.COMPONENT_MANAGER);
	}
	
	protected void doOnSetup() {}
	protected void onTearDown() {}

	protected abstract HTMLAction getActionUnderTest();
	
	
}
