    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package friendb.server.entities;

    import java.io.Serializable;
    import java.util.Date;
    import javax.persistence.Entity;
    import javax.persistence.Id;
    import javax.persistence.NamedQueries;
    import javax.persistence.NamedQuery;

    /**
     *
     * @author evanguby
     */
    @NamedQueries({
        @NamedQuery(name = "Employee.findAll", 
                query = "SELECT e FROM Employee e"),
        @NamedQuery(name = "Employee.findByID", 
            query = "SELECT e FROM Employee e WHERE e.EmployeeID = :EmployeeId")
    })
    @Entity
    public class Employee implements Serializable{

        @Id
        private int  employeeID;
        private int ssn;
        private String startDate;
        private String firstName;
        private String lastName;
        private String telephone;
        private String address;
        private String city;
        private String curState;
        private int zipCode;
        private Double hourlyRate;
        private String role;
        private String password;



        public Employee() {
        }

        public Employee(int employeeID, int ssn, String startDate,
                String firstName,String lastName, String telephone,
                String address, String city, String curState,
                int zipCode, Double hourlyRate, String role
                ){
            this.employeeID = employeeID;
            this.ssn = ssn;
            this.startDate = startDate;
            this.firstName = firstName;
            this.lastName = lastName;
            this.telephone = telephone;
            this.address = address;
            this.city = city;
            this.curState = curState;
            this.zipCode = zipCode;
            this.hourlyRate = hourlyRate;
            this.role = role;
        }
        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeId(int employeeID) {
            this.employeeID = employeeID;
        }
         public String getPassword() {
            return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
         public int  getSSN() {
        return ssn;
        }

        public void setSSN(int ssn) {
            this.ssn = ssn;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
       public String getFirstName() {
        return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
         public String getCity() {
        return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
        public String getCurState() {
        return curState;
        }

        public void setCurState(String curState) {
            this.curState = curState;
        }
        

        public int getZipCode() {
            return zipCode;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }
        public double getHourlyRate() {
            return hourlyRate;
        }

        public void setHourlyRate(Double hourlyRate) {
            this.hourlyRate = hourlyRate;
        }
        public String getRole() {
        return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
        }



