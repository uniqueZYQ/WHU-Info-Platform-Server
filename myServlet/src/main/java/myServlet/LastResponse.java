package myServlet;

public class LastResponse {
	private int id;
    private int user_id;
    private int oppo_id;
    private int last_id;
    private int code;
    private String response;
    
    public int getCode() {
    	return code;
    }
    public void setCode(int code) {
    	this.code=code;
    }
    public String getResponse() {
    	return response;
    }
    public void setResponse(String response) {
    	this.response=response;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id(){
        return user_id;
    }
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }
    public int getOppo_id(){
        return oppo_id;
    }
    public void setOppo_id(int oppo_id){
        this.oppo_id = oppo_id;
    }
    public int getLast_id() {
        return last_id;
    }
    public void setLast_id(int last_id) {
        this.last_id = last_id;
    }
}
