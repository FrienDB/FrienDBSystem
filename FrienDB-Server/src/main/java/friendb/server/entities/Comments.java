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
        @NamedQuery(name = "Comments.findAll", 
                query = "SELECT c FROM Comments c")
    })
    @Entity
    public class Comments implements Serializable{

        @Id
        private int commentID;
        private String content;
        private String dateCommented;
        private int author;
        private int postID;



        public Comments() {
        }

        public Comments(int commentID, String content, String dateCommented,
                int author, int postID){
            this.commentID = commentID;
            this.content = content;
            this.dateCommented = dateCommented;
            this.author = author;
            this.postID = postID;
            }
         public int getCommentID() {
        return commentID;
        }

        public void setCommentID(int commentId) {
            this.commentID = commentID;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
         public String getDateCommented() {
        return dateCommented;
        }

        public void setDateCommented(String dateCommented) {
            this.dateCommented = dateCommented;
        }

        public int getAuthor() {
            return author;
        }

        public void setAuthor(int author) {
            this.author = author;
        }
        public int getPostID() {
            return postID;
        }

        public void setPostID(int postID) {
            this.postID = postID;
        }

        }



