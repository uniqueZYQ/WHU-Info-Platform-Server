package myServlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
		//use post here
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
		String stdid = request.getParameter("stdid"); 
		String pwd = request.getParameter("pwd"); 
		
        String sql = "select * from " + DBUtil.TABLE_USER + " where stdid= '" + stdid + "'"+" and pwd="+"'"+pwd+"'";
       
        CommonResponse res=new CommonResponse();
        try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); 
			ResultSet result;
			
			result = statement.executeQuery(sql);
			if(result.next()){ // 匹配成功
				res.setCode(101);
				res.setResponse("登录成功");
				res.setStdid(result.getString("stdid"));
				res.setId(result.getInt("id"));
				res.setNickname(result.getString("nickname"));
				res.setRealname(result.getString("realname"));
			} else { // 不存在
				res.setCode(401);
				res.setResponse("登录失败");
				res.setStdid("");
				res.setId(0);
				res.setNickname("");
				res.setRealname("");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			res.setCode(402);
			res.setResponse("登录失败");
		}
		 String resStr = JSONObject.fromObject(res).toString();
		 //response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // 可以对字符串进行加密操作，相应的到了客户端就需要解密
	        response.getWriter().append(resStr).flush();

	}

}
