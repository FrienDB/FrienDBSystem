/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.session;

import friendb.shared.SimpleCustomer;

/**
 *
 * @author evanguby
 */
public class CustomerSession implements Session{
    private SimpleCustomer customer;

    public CustomerSession(SimpleCustomer acct)
    {
        customer = acct;
    }
    
    public SimpleCustomer getCustomerAccount()
    {
        return customer;
    }
}
