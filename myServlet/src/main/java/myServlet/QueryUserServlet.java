package myServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryUserServlet
 */
public class QueryUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QueryUserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String need_picture = request.getParameter("need_picture");
		
		if(need_picture.equals("2")) {
			CommonResponse res=new CommonResponse();
			String picture_version=request.getParameter("picture_version");
			try {
				Connection connect = DBUtil.getConnect();
				Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
				ResultSet result;
			
				String sqlQuery = "select * from " + DBUtil.TABLE_USER + " where id='" + id + "'";
			
				result = statement.executeQuery(sqlQuery);
				if(result.next()){ // 已存在
					res.setId(result.getInt("id"));
					//res.setPicture(result.getBytes("picture"));
					res.setNickname(result.getString("nickname"));
					//res.setRealPicture(FileBuf);
					res.setRealname(result.getString("realname"));
					res.setStdid(result.getString("stdid"));
					res.setPicture_version(result.getInt("picture_version"));
					if(result.getInt("picture_version")==Integer.valueOf(picture_version)) {
						res.setCode(102);
						res.setResponse("无需更新");
					}else {
						res.setCode(101);
						res.setResponse("获取成功");
						res.setPicture(result.getString("picture"));
					}
				} else { // 不存在	
					res.setCode(401);
					res.setResponse("用户信息获取失败，请稍后再试");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				res.setCode(402);
				res.setResponse("操作失败，请稍后再试");
			}
			String resStr = JSONObject.fromObject(res).toString();
			//response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // 可以对字符串进行加密操作，相应的到了客户端就需要解密
	        response.getWriter().append(resStr).flush();
		}
		else{
			CommonResponse res=new CommonResponse();
			try {
				Connection connect = DBUtil.getConnect();
				Statement statement = (Statement) connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
				ResultSet result;
			
				String sqlQuery = "select * from " + DBUtil.TABLE_USER + " where id='" + id + "'";
			
				// 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0
				result = statement.executeQuery(sqlQuery); // 先查询同样的账号（比如手机号）是否存在
				if(result.next()){ // 已存在
					res.setCode(101);
					res.setResponse("用户信息获取成功");
					res.setId(result.getInt("id"));
					//res.setPicture(result.getBytes("picture"));
					res.setNickname(result.getString("nickname"));
					//res.setRealPicture(FileBuf);
					res.setRealname(result.getString("realname"));
					res.setStdid(result.getString("stdid"));
					res.setPicture_version(result.getInt("picture_version"));
				
					if(need_picture.equals("1"))
						res.setPicture(result.getString("picture"));
				} else { // 不存在	
					res.setCode(401);
					res.setResponse("用户信息获取失败，请稍后再试");
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
				res.setCode(402);
				res.setResponse("操作失败，请稍后再试");
			}
		
			String resStr = JSONObject.fromObject(res).toString();
			//response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // 可以对字符串进行加密操作，相应的到了客户端就需要解密
	        response.getWriter().append(resStr).flush();
		}
	}

}
