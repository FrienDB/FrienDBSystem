/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.session;

import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCustomer;
import java.util.ArrayList;

/**
 *
 * @author evanguby
 */
public class CustomerSession implements Session{
    private SimpleCustomer customer;
    private ArrayList<SimpleCircle> circles;

    public CustomerSession(SimpleCustomer acct)
    {
        customer = acct;
    }
    
    public SimpleCustomer getCustomerAccount()
    {
        return customer;
    }
    
    public ArrayList<SimpleCircle> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<SimpleCircle> scA) {
        circles = scA;
    }
}
