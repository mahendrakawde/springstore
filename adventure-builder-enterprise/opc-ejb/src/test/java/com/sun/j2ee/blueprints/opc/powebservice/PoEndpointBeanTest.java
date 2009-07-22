/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sun.j2ee.blueprints.opc.powebservice;

import com.sun.j2ee.blueprints.opc.purchaseorder.PurchaseOrder;
import com.sun.j2ee.blueprints.opc.serviceexceptions.InvalidPOException;
import org.junit.Test;

/**
 *
 * @author Marten Deinum
 */
public class PoEndpointBeanTest {

    private PoEndpointBean bean = new PoEndpointBean();
    
    /**
     * Test of submitPurchaseOrder method, of class PoEndpointBean.
     */
    @Test(expected=InvalidPOException.class)
    public void submitNullPurchaseOrder() throws Exception {
        PurchaseOrder po = null;
        bean.submitPurchaseOrder(po);
    }

    @Test(expected=InvalidPOException.class)
    public void submitInvalidPurchoseOrder() throws Exception {
        PurchaseOrder po = new PurchaseOrder();
        bean.submitPurchaseOrder(po);
    }

}
