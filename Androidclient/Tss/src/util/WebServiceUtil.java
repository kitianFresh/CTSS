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
        //System.out.println("index>>"+result.getProperty(0).toString());//������һ��
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
        //System.out.println("index>>"+result.getProperty(0).toString());//������һ��
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
        	int count = result.getPropertyCount();  //���SoapObject����ĸ���  
            for(int i=0;i<count;i++){ 
            	Map<String, Object> map = new HashMap<String, Object>();
            	map.put("E_id", ((SoapObject)result.getProperty(i)).getProperty("eid"));//"����ţ�"
            	map.put("E_title", ((SoapObject)result.getProperty(i)).getProperty("etitle"));//"���⣺"+
		        map.put("E_teacher", ((SoapObject)result.getProperty(i)).getProperty("eteacher"));//"ָ����ʦ��"+ 
		        map.put("E_level", ((SoapObject)result.getProperty(i)).getProperty("elevel"));//"�ȼ���"+
		        map.put("E_sum", ((SoapObject)result.getProperty(i)).getProperty("esum"));//"�������ƣ�"+
		        map.put("E_leftcount",((SoapObject)result.getProperty(i)).getProperty("eleftcount"));//"������"+
		        map.put("T_phone",((SoapObject)result.getProperty(i)).getProperty("tphone"));//"��ʦ�绰��"+
		        map.put("E_summary",((SoapObject)result.getProperty(i)).getProperty("esummary"));//"����ժҪ��"+
		        map.put("E_filepath", ((SoapObject)result.getProperty(i)).getProperty("efilepath"));//�ļ��ڷ������е�·��
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
                //����ָ����·�������ļ��� 
                f.mkdirs(); 
            } catch (Exception e) { 
            	e.printStackTrace();
            } 
        } 
		
        String methodName = "downloadFileToBase64Str4android";  
        // ��1��������SoapObject���󣬲�ָ��WebService�������ռ�͵��õķ�����  
        SoapObject request = new SoapObject("http://service", methodName);  
        // ��2��������WebService�����Ĳ���  
       
        request.addProperty("ServerEfilepath",ServerEfilepath); 
        SoapObject fileObject = callService(TssServiceUrl,request);
        
        
        try  
        {   
                // ͨ��getProperty�������Product���������ֵ  
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
	    // ��2��������WebService�����Ĳ���  
	    request.addProperty("sid", sid); 
	     
        SoapObject result = callService(TssServiceUrl,request);
        if(result!=null){
        	int count = result.getPropertyCount();  //���SoapObject����ĸ���  
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
	    //����WebService������һ������student  
	     SoapObject s = new SoapObject("http://entity/xsd","Student");
	     s.addProperty("sid",student.getSid());
	     s.addProperty("sname",student.getSname());
	     s.addProperty("sclass",student.getSclass());
	     s.addProperty("sphone",student.getSphone());
	     s.addProperty("sqq",student.getSqq());
	     s.addProperty("sisleader",student.isSisleader());
	     
	     request.addProperty("student", s);
	     //����WebService�����ĵڶ�������oldsid
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
	    // ��2��������WebService�����Ĳ���  
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
	    //����WebService������һ������teacher  
	     SoapObject t = new SoapObject("http://entity/xsd","Teacher");
	     t.addProperty("tid",teacher.getTid());
	     t.addProperty("tname",teacher.getTname());
	     t.addProperty("toffice",teacher.getToffice());
	     t.addProperty("tphone",teacher.getTphone());
	     t.addProperty("tqq",teacher.getTqq());
	     t.addProperty("tischecker",teacher.isTischecker());
	     
	     request.addProperty("teacher", t);
	     //����WebService�����ĵڶ�������oldtid
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
		// ��3��������SoapSerializationEnvelope���󣬲�ָ��WebService�İ汾  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
        // ����bodyOut����  
        envelope.bodyOut = request;
        // ��4��������HttpTransportSE���󣬲�ָ��WSDL�ĵ���URL  
        HttpTransportSE ht = new HttpTransportSE(ServiceUrl);    
        try  
        {   
            // ��5��������WebService 
        	ht.debug = true;
            ht.call(null, envelope);
            if (envelope.getResponse() != null)  
            {  
            	
            	//System.out.println("serviceconnection>>" + ht.getServiceConnection());
            	System.out.println("request_dump>>" + ht.requestDump);
            	System.out.println("response_dump>>" +ht.responseDump );
                // ��6����ʹ��getResponse�������WebService�����ķ��ؽ��  
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
