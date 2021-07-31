package myServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryMyInfoServlet
 */
public class QueryMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QueryMyInfoServlet() {
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
        
        String sql = "select * from " + DBUtil.TABLE_INFO + " where owner_id= '" + id + "' order by send_date desc";
       
        List<InfoResponse> res=new ArrayList<InfoResponse>();
        try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); 
			ResultSet result;
			int i=0;
			result = statement.executeQuery(sql);
			if(result.next()){ // ƥ��ɹ�
				do {
					InfoResponse e=new InfoResponse();
					res.add(e);
					res.get(i).setAnswered(result.getInt("answered"));
					res.get(i).setCode(101);
					res.get(i).setDate(result.getString("date"));
					res.get(i).setDetail(result.getString("detail"));
					res.get(i).setFd_form(result.getInt("fd_form"));
					res.get(i).setForm(result.getInt("form"));
					res.get(i).setHelp_form(result.getInt("help_form"));
					res.get(i).setId(result.getInt("id"));
					res.get(i).setLesson(result.getString("lesson"));
					res.get(i).setOwner_id(result.getInt("owner_id"));
					res.get(i).setPicture1(result.getInt("picture1"));
					res.get(i).setPicture2(result.getInt("picture2"));
					res.get(i).setPicture3(result.getInt("picture3"));
					res.get(i).setPicture4(result.getInt("picture4"));
					res.get(i).setPlace(result.getString("place"));
					res.get(i).setPlaceId(result.getString("placeId"));
					res.get(i).setPrice(result.getDouble("price"));
					res.get(i).setResponse("��ȡ�ɹ�");
					res.get(i).setReward(result.getDouble("reward"));
					res.get(i).setScore(result.getInt("score"));
					res.get(i).setSend_date(result.getString("send_date"));
					i++;
				}
				while(result.next());
			} else { // ������
				res.get(0).setCode(102);
				res.get(0).setResponse("������Ϣ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			res.get(0).setCode(402);
			res.get(0).setResponse("��������ʧ�ܣ�������������");
		}
		 String resStr = JSONArray.fromObject(res).toString();
		 //response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // ���Զ��ַ������м��ܲ�������Ӧ�ĵ��˿ͻ��˾���Ҫ����
	        response.getWriter().append(resStr).flush();

	}

}