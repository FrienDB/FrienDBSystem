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
        @NamedQuery(name = "Advertisement.findAll", 
                query = "SELECT a FROM Advertisement a"),
        @NamedQuery(name = "Advertisement.findByEmployeeID", 
                query = "SELECT a FROM Advertisement a WHERE a.employeeID=:employeeID")
    })
    @Entity
    public class Advertisement implements Serializable{

        @Id
        private int adID;
        private int  employeeID;
        private String adType;
        private String postDate;
        private String company;
        private String item;
        private String content;
        private double price;
        private int numUnits;



        public Advertisement() {
        }

        public Advertisement(int adID, int employeeID, String adType,
                String postDate, String company, String item,
                String content, double price, int numUnits
                ){
            this.adID = adID;
            this.employeeID = employeeID;
            this.adType = adType;
            this.postDate = postDate;
            this.company = company;
            this.item = item;
            this.content = content;
            this.price = price;
            this.numUnits=numUnits;
            }
         public int getAdID() {
        return adID;
        }

        public void setAdId(int adId) {
            this.adID = adID;
        }

        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeId(int employeeID) {
            this.employeeID = employeeID;
        }
         public String getAdType() {
        return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getPostDate() {
            return postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }
        public String getCompany(){
            return company;
        }
        public void setCompany(String company) {
            this.company = company;
        }
        public String getItem(){
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }
        public String getContent(){
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public Double getPrice(){
            return price;
        }
        public void setPrice(Double price) {
            this.price = price;
        }
        public int getNumUnits(){
            return numUnits;
        }
        public void setNumUnits(int numUnits) {
            this.numUnits = numUnits;
        }
        }



