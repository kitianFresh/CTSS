package utility;
import java.sql.*;
import org.apache.axis2.context.*;
public class Common {
	protected ResultSet rs;
	private final String USERNAME = "root";            //定义数据库的用户名
	private final String PASSWORD = "777";           //定义数据库的密码
	private final String DRIVER = "com.mysql.jdbc.Driver";         //定义数据库的驱动信息
	private final String URL = "jdbc:mysql://localhost:3306/select_exercise?useUnicode=true&amp;characterEncoding=utf-8 ";    //定义访问数据库的地址
	
	protected Connection getConnection() throws Exception{
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		return connection;
	}
	
	protected void execSQL(String sql, Object... params) throws Exception{
		Connection connection = getConnection();
		if(connection!=null){
			PreparedStatement pstmt = connection.prepareStatement(sql);
			for(int i=0; i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			if(pstmt.execute()){
				rs = pstmt.getResultSet();
			}
		}
	}
	
	protected Object getProperty(String key){
		MessageContext mc = MessageContext.getCurrentMessageContext();
		ServiceGroupContext serviceGroupContext = mc.getServiceGroupContext();
		return serviceGroupContext.getProperty(key);
	}
	
	protected void setProperty(String key, Object value){
		MessageContext mc = MessageContext.getCurrentMessageContext();
		ServiceGroupContext serviceGroupContext = mc.getServiceGroupContext();
		serviceGroupContext .setProperty(key, value);
	}
}
