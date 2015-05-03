/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.session;

import friendb.shared.SimpleEmployee;

/**
 *
 * @author evanguby
 */
public class EmployeeSession implements Session{
    private SimpleEmployee employee;

    public EmployeeSession(SimpleEmployee acct)
    {
        employee = acct;
    }
    
    public SimpleEmployee getEmployeeAccount()
    {
        return employee;
    }
}
