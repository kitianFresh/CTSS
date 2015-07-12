package util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Environment;

import entity.SelectedTopicViewOfStudent;
import entity.Student;
import entity.Teacher;
import Decoder.BASE64Decoder;

public class WebServiceUtil {
	
	public static String TssFileDir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/TssApp";
	
	private static SoapObject soapObject = null;
	private static String ServerIp = "http://192.168.1.100:8080/tss/services/";
	private static String wsdlUser = "UserService?wsdl";
	private static String wsdlTss = "TssService?wsdl";
	
	private static String UserServiceUrl = ServerIp + wsdlUser;
	private static String TssServiceUrl = ServerIp + wsdlTss;
	
	public static boolean login(String userid , String password){
		if(userid == null || password == null){
			return false;
		}
		SoapObject request = new SoapObject("http://service","login");
		request.addProperty("userid", userid); 
        request.addProperty("password",password);
        SoapObject result = callService(UserServiceUrl,request);
        //System.out.println("propertyname>>"+result.getProperty("return").toString());
        //System.out.println("index>>"+result.getProperty(0).toString());//两个都一样
        if(result!=null&& "true".equals(result.getProperty("return").toString())){
        	return true;
        }
        else{
        	return false;
        }
	}
	
	public static boolean modifyPassword(String uid,String newpw){
		if(uid == null || newpw == null){
			return false;
		}
		
		SoapObject request = new SoapObject("http://service","modifyUserPassword4android");
		request.addProperty("uid", uid); 
        request.addProperty("newpw",newpw);
        SoapObject result = callService(UserServiceUrl,request);
        //System.out.println("propertyname>>"+result.getProperty("return").toString());
        //System.out.println("index>>"+result.getProperty(0).toString());//两个都一样
        if(result!=null&& "true".equals(result.getProperty("return").toString())){
        	return true;
        }
        else{
        	return false;
        }
	}
	
	public static int getGroupId(String sid){
		SoapObject request = new SoapObject("http://service",	"getGroupId");
		request.addProperty("sid",sid);
        SoapObject result = callService(TssServiceUrl,request);
        int gid=-1;
        gid =Integer.valueOf(result.getProperty("return").toString());
        System.out.println("WebServiceUtil:"+gid);
        if(gid!=-1){
        	 return gid;
        }   
        else{
        	return -1;
        }
	}
	public  static List<Map<String, Object>> getAllExercises(){
		SoapObject request = new SoapObject("http://service",	"getTopicInfoViewOfStudent4android");
        SoapObject result = callService(TssServiceUrl,request);
        if(result!=null){
        	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        	int count = result.getPropertyCount();  //获得SoapObject对象的个数  
            for(int i=0;i<count;i++){ 
            	Map<String, Object> map = new HashMap<String, Object>();
            	map.put("E_id", ((SoapObject)result.getProperty(i)).getProperty("eid"));//"课题号："
            	map.put("E_title", ((SoapObject)result.getProperty(i)).getProperty("etitle"));//"课题："+
		        map.put("E_teacher", ((SoapObject)result.getProperty(i)).getProperty("eteacher"));//"指导老师："+ 
		        map.put("E_level", ((SoapObject)result.getProperty(i)).getProperty("elevel"));//"等级："+
		        map.put("E_sum", ((SoapObject)result.getProperty(i)).getProperty("esum"));//"人数限制："+
		        map.put("E_leftcount",((SoapObject)result.getProperty(i)).getProperty("eleftcount"));//"余量："+
		        map.put("T_phone",((SoapObject)result.getProperty(i)).getProperty("tphone"));//"老师电话："+
		        map.put("E_summary",((SoapObject)result.getProperty(i)).getProperty("esummary"));//"内容摘要："+
		        map.put("E_filepath", ((SoapObject)result.getProperty(i)).getProperty("efilepath"));//文件在服务器中的路径
		        list.add(map);
		        	        
            	/*System.out.println(((SoapObject)result.getProperty(i)).getProperty("eid"));  
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("etitle")); 
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("eteacher"));
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("elevel"));
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("esum"));
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("eleftcount")); 
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("esummary"));
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("efilepath"));
                System.out.println(((SoapObject)result.getProperty(i)).getProperty("tphone"));
                */
           }  
            return list;
        }
        else{
        	return null;
        }
	}
	
	public static boolean downloadFile(String ServerEfilepath,String Etitle){
		File f = new File(TssFileDir);
		if (!f.exists()) { 
            try { 
                //按照指定的路径创建文件夹 
                f.mkdirs(); 
            } catch (Exception e) { 
            	e.printStackTrace();
            } 
        } 
		
        String methodName = "downloadFileToBase64Str4android";  
        // 第1步：创建SoapObject对象，并指定WebService的命名空间和调用的方法名  
        SoapObject request = new SoapObject("http://service", methodName);  
        // 第2步：设置WebService方法的参数  
       
        request.addProperty("ServerEfilepath",ServerEfilepath); 
        SoapObject fileObject = callService(TssServiceUrl,request);
        
        
        try  
        {   
                // 通过getProperty方法获得Product对象的属性值  
                String fileBuffer = fileObject.getProperty("return").toString();
                byte[] buffer = new BASE64Decoder().decodeBuffer(fileBuffer);
    			
                System.out.println(fileBuffer);
                File file = new File(TssFileDir+"/"+Etitle+".pdf");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
                return true;
        
        }  
        catch (Exception e)  
        {  
        	System.out.println(e);
        }
       
        return false;
    } 
	
	public static Student getStudentInfoBySid(String sid){
		SoapObject request = new SoapObject("http://service",	"getStudentBySid");
	    // 第2步：设置WebService方法的参数  
	    request.addProperty("sid", sid); 
	     
        SoapObject result = callService(TssServiceUrl,request);
        if(result!=null){
        	int count = result.getPropertyCount();  //获得SoapObject对象的个数  
            	Student student = new Student();
            	student.setSid(((SoapObject)result.getProperty(0)).getProperty("sid").toString());
            	student.setSname(((SoapObject)result.getProperty(0)).getProperty("sname").toString());
		        student.setSclass(((SoapObject)result.getProperty(0)).getProperty("sclass").toString()); 
		        student.setSphone(((SoapObject)result.getProperty(0)).getProperty("sphone").toString());
		        student.setSqq(((SoapObject)result.getProperty(0)).getProperty("sqq").toString());
		        student.setSisleader("true".equals(((SoapObject)result.getProperty(0)).getProperty("sisleader").toString())?true:false);
		        
		        return student;
        }
        else{
        	return null;
        }
	}
	
	public static boolean updateStudentInfo(Student student){
		SoapObject request = new SoapObject("http://service",	"updateStudentInfo");
	    //设置WebService方法第一个参数student  
	     SoapObject s = new SoapObject("http://entity/xsd","Student");
	     s.addProperty("sid",student.getSid());
	     s.addProperty("sname",student.getSname());
	     s.addProperty("sclass",student.getSclass());
	     s.addProperty("sphone",student.getSphone());
	     s.addProperty("sqq",student.getSqq());
	     s.addProperty("sisleader",student.isSisleader());
	     
	     request.addProperty("student", s);
	     //设置WebService方法的第二个参数oldsid
	     request.addProperty("oldsid",student.getSid());
	    
	     SoapObject result = callService(TssServiceUrl,request);
	     if(result!=null&& "true".equals(result.getProperty("return").toString())){
	    	 return true;
	     }
	     else{
        	return false;
	     }
	}
	
	public static Teacher getTeacherInfoByTid(String tid){
		SoapObject request = new SoapObject("http://service",	"getTeacherByTid");
	    // 第2步：设置WebService方法的参数  
	    request.addProperty("tid", tid); 
	     
        SoapObject result = callService(TssServiceUrl,request);
        if(result!=null){
            	Teacher teacher = new Teacher();
            	teacher.setTid(((SoapObject)result.getProperty(0)).getProperty("tid").toString());
            	teacher.setTname(((SoapObject)result.getProperty(0)).getProperty("tname").toString());
            	teacher.setToffice(((SoapObject)result.getProperty(0)).getProperty("toffice").toString()); 
            	teacher.setTphone(((SoapObject)result.getProperty(0)).getProperty("tphone").toString());
            	teacher.setTqq(((SoapObject)result.getProperty(0)).getProperty("tqq").toString());
            	teacher.setTischecker("true".equals(((SoapObject)result.getProperty(0)).getProperty("tischecker").toString())?true:false);
		        
		        return teacher;
        }
        else{
        	return null;
        }
	}
	
	public static boolean updateTeacherInfo(Teacher teacher){
		SoapObject request = new SoapObject("http://service",	"updateTeacherInfo");
	    //设置WebService方法第一个参数teacher  
	     SoapObject t = new SoapObject("http://entity/xsd","Teacher");
	     t.addProperty("tid",teacher.getTid());
	     t.addProperty("tname",teacher.getTname());
	     t.addProperty("toffice",teacher.getToffice());
	     t.addProperty("tphone",teacher.getTphone());
	     t.addProperty("tqq",teacher.getTqq());
	     t.addProperty("tischecker",teacher.isTischecker());
	     
	     request.addProperty("teacher", t);
	     //设置WebService方法的第二个参数oldtid
	     request.addProperty("oldtid",teacher.getTid());
	    
	     SoapObject result = callService(TssServiceUrl,request);
	     if(result!=null&& "true".equals(result.getProperty("return").toString())){
	    	 return true;
	     }
	     else{
        	return false;
	     }
	}

	public static SelectedTopicViewOfStudent getSelectedTopicInfo(String sid){
		SoapObject request = new SoapObject("http://service",	"getSelectedTopicInfo4android");
		request.addProperty("sid",sid);
		SoapObject result = callService(TssServiceUrl,request);
		if(result!=null){
			SelectedTopicViewOfStudent stvs = new SelectedTopicViewOfStudent();
        	stvs.setEid(Integer.valueOf(((SoapObject)result.getProperty(0)).getProperty("eid").toString()));
        	stvs.setGid(Integer.valueOf(((SoapObject)result.getProperty(0)).getProperty("gid").toString()));
        	stvs.setEtitle(((SoapObject)result.getProperty(0)).getProperty("etitle").toString()); 
        	stvs.setLeadername(((SoapObject)result.getProperty(0)).getProperty("leadername").toString());
        	stvs.setLeaderqq(((SoapObject)result.getProperty(0)).getProperty("leaderqq").toString());
        	stvs.setLeaderphone(((SoapObject)result.getProperty(0)).getProperty("leaderphone").toString());
	        
	        return stvs;
		}
	    else{
	    	return null;
	    }
	}
	
	public static boolean selectTopic(int gid, int eid){
		SoapObject request = new SoapObject("http://service",	"groupSelectExercise");
		request.addProperty("gid",gid);
		request.addProperty("eid",eid);
		SoapObject result = callService(TssServiceUrl,request);
		
		if(result!=null&& "true".equals(result.getProperty("return").toString())){
	    	return true;
	     }
	    else{
	    	return false;
	    }
	}
	
	public static boolean undoSelect(int gid, int eid){
		SoapObject request = new SoapObject("http://service",	"deleteGroupExercise");
		request.addProperty("gid",gid);
		request.addProperty("eid",eid);
		SoapObject result = callService(TssServiceUrl,request);
		
		if(result!=null&& "true".equals(result.getProperty("return").toString())){
	    	return true;
	    }
	    else{
	    	return false;
	     }
	}
	private static SoapObject callService(String ServiceUrl, SoapObject request){
		// 第3步：创建SoapSerializationEnvelope对象，并指定WebService的版本  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
        // 设置bodyOut属性  
        envelope.bodyOut = request;
        // 第4步：创建HttpTransportSE对象，并指定WSDL文档的URL  
        HttpTransportSE ht = new HttpTransportSE(ServiceUrl);    
        try  
        {   
            // 第5步：调用WebService 
        	ht.debug = true;
            ht.call(null, envelope);
            if (envelope.getResponse() != null)  
            {  
            	
            	//System.out.println("serviceconnection>>" + ht.getServiceConnection());
            	System.out.println("request_dump>>" + ht.requestDump);
            	System.out.println("response_dump>>" +ht.responseDump );
                // 第6步：使用getResponse方法获得WebService方法的返回结果  
               soapObject = (SoapObject) envelope.bodyIn;  
               return soapObject;
               
            }  
            else {  
                return null; 
            }  
        }  
        catch (Exception e)  
        {  
        	System.out.println(e);
        	return null;
        }
	}
	
}
