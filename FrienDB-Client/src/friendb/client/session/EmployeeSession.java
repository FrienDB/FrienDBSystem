/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.session;

import friendb.shared.SimpleEmployee;
import friendb.shared.SimpleSales;
import java.util.List;

/**
 *
 * @author evanguby
 */
public class EmployeeSession implements Session{
    private SimpleEmployee employee;
    private List<SimpleSales> allSales;

    public EmployeeSession(SimpleEmployee acct)
    {
        employee = acct;
    }
    
    public SimpleEmployee getEmployeeAccount()
    {
        return employee;
    }
    public List<SimpleSales> getEmployeeSales() {
        return allSales;
    }

    public void setEmployeeSales(List<SimpleSales> allSales) {
        this.allSales = allSales;
    }
    
}
