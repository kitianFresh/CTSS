package service;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import entity.*;
import sun.misc.BASE64Decoder;
import utility.Common;
public class TssService extends Common{
	private String FileDirOnServer = "D:\\files\\";//服务器文件存储路径
	public boolean addGroup(Allgroup group,String [] sid){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return false;
			}
			int gid=-1;
			Object[] params =new Object[]{group.getGleader(),group.getGsum()};
			execSQL("insert into allgroup(gleader,gsum) values(?,?)",params);
			execSQL("select gid from allgroup where sid=?",group.getGleader());
			while(rs.next()){
				gid=rs.getInt("gid");
			}
			if(gid==-1){
				return false;
			}
			int temp=group.getGsum()-1;
			for(;temp>=0;temp--){
				execSQL("insert into s_g(sid,gid) values(?,?)",sid[temp],gid);
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public int getGroupId(String sid){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return -1;
			}
			execSQL("select gid from s_g where sid=?",sid);
			int gid=-1;
			while(rs.next()){
				gid = rs.getInt("gid");
			}
			return gid;
		}catch(Exception e){
			System.out.println(e.toString());
			return -1;
		}
	}
	
	public boolean groupSelectExercise(int gid, int eid){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return false;
			}
			execSQL("select eleftcount from exercise where eid=?",eid);
			while(rs.next()){
				if(rs.getInt("eleftcount")<=0){
					return false;
				}
			}
	
			Object[] params =new Object[]{gid,eid};
			execSQL("insert into g_e (gid,eid)  values(?,?)",params);
			execSQL("update exercise set eleftcount=eleftcount-1 where eid=?",eid);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteGroupExercise(int gid, int eid){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return false;
			}
			Object[] params =new Object[]{gid,eid};
			execSQL("delete from g_e where gid=? and eid=?",params);
			execSQL("update exercise set eleftcount=eleftcount+1 where eid=?",eid);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public SelectedTopicViewOfStudent[] getSelectedTopicInfo4android(String sid){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return null;
			}
			execSQL("select distinct exercise.etitle,student.sname,student.sphone,student.sqq,allgroup.gid,exercise.eid "
					+"from student,exercise,s_g,g_e,allgroup "
					+"where s_g.sid=? and s_g.gid=allgroup.gid and allgroup.gleader=student.sid "
					+"and allgroup.gid=g_e.gid and g_e.eid=exercise.eid",sid);
	
			List<SelectedTopicViewOfStudent> stvsList = new java.util.ArrayList<SelectedTopicViewOfStudent>(); 
			while(rs.next()){
				SelectedTopicViewOfStudent stvs = new SelectedTopicViewOfStudent();
				stvs.setEtitle(rs.getString("etitle"));
				stvs.setLeadername(rs.getString("sname"));
				stvs.setLeaderphone(rs.getString("sphone"));
				stvs.setLeaderqq(rs.getString("sqq"));
				stvs.setGid(rs.getInt("gid"));
				stvs.setEid(rs.getInt("eid"));
				stvsList.add(stvs);
			}
			SelectedTopicViewOfStudent[] stvss = new SelectedTopicViewOfStudent[stvsList.size()];
		    return stvsList.toArray(stvss);
		}catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
	}
	
	public boolean addTeacher(Teacher teacher){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return false;
			}
			//teacher.setTid(userid);
			Object[] params =new Object[]{teacher.getTid(),teacher.getTname(),teacher.getToffice(),
					teacher.getTphone(),teacher.getTqq(),teacher.isTischecker()};
			execSQL("insert into teacher(tid,tname,toffice,tphone,tqq,tischecker) values(?,?,?,?,?,?)",params);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public Teacher[] getTeacherByName(String tname){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select * from teacher where tname like ?","%"+tname+"%");
			
			List<Teacher> teacherList = new java.util.ArrayList<Teacher>(); 
			while(rs.next()){
				Teacher teacher = new Teacher();//注意每一个list的元素都必须重新申请空间，要不然添加的所有元素都会被最后一个覆盖，
													//因为都是指向同一片空间的引用
				teacher.setTid(rs.getString("tid"));
				teacher.setTname(rs.getString("tname"));
				teacher.setToffice(rs.getString("toffice"));
				teacher.setTphone(rs.getString("tphone"));
				teacher.setTqq(rs.getString("tqq"));
				teacher.setTischecker(rs.getBoolean("tischecker"));
	
				teacherList.add(teacher);
			}
		    Teacher[] teachers = new Teacher[teacherList.size()];
		    return teacherList.toArray(teachers);
		}catch(Exception e){
			return null;
		}
	}
	
	public Teacher[] getTeacherByTid(String tid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select * from teacher where tid=?",tid);
			
			List<Teacher> teacherList = new java.util.ArrayList<Teacher>(); 
			while(rs.next()){
				Teacher teacher = new Teacher();//注意每一个list的元素都必须重新申请空间，要不然添加的所有元素都会被最后一个覆盖，
													//因为都是指向同一片空间的引用
				teacher.setTid(rs.getString("tid"));
				teacher.setTname(rs.getString("tname"));
				teacher.setToffice(rs.getString("toffice"));
				teacher.setTphone(rs.getString("tphone"));
				teacher.setTqq(rs.getString("tqq"));
				teacher.setTischecker(rs.getBoolean("tischecker"));
	
				teacherList.add(teacher);
			}
		    Teacher[] teachers = new Teacher[teacherList.size()];
		    return teacherList.toArray(teachers);
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean updateTeacherInfo(Teacher teacher,String oldtid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			Object[] params = new Object[]{teacher.getTid(),teacher.getTname(),teacher.getToffice(),
					teacher.getTphone(),teacher.getTqq(),teacher.isTischecker(),oldtid};
			execSQL("update teacher set tid=?, tname=?, toffice=?, tphone=?, tqq=?, tischecker=? where tid=?",params);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteTeacher(String tid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from teacher where tid = ?",tid);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteAllTeachers(){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from teacher");
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean addStudent(Student student){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return false;
			}
			//student.setSid(userid);
			Object[] params =new Object[]{student.getSid(),student.getSname(),student.getSclass(),
					student.getSphone(),student.getSqq(),student.isSisleader()};
			execSQL("insert into student(sid,sname,sclass,sphone,sqq,sisleader) values(?,?,?,?,?,?)",params);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public Student[] getStudentByName(String sname){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select * from student where sname like ?","%"+sname+"%");
			
			List<Student> studentList = new java.util.ArrayList<Student>(); 
			while(rs.next()){
				Student student = new Student();//注意每一个list的元素都必须重新申请空间，要不然添加的所有元素都会被最后一个覆盖，
													//因为都是指向同一片空间的引用
				student.setSid(rs.getString("sid"));
				student.setSname(rs.getString("sname"));
				student.setSclass(rs.getString("sclass"));
				student.setSphone(rs.getString("sphone"));
				student.setSqq(rs.getString("sqq"));
				student.setSisleader(rs.getBoolean("sisleader"));
				
				studentList.add(student);
			}
		    Student[] students = new Student[studentList.size()];
		    return studentList.toArray(students);
		}catch(Exception e){
			return null;
		}
	}
	
	public Student[] getStudentBySid(String sid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select * from student where sid=?",sid);
			
			List<Student> studentList = new java.util.ArrayList<Student>(); 
			while(rs.next()){
				Student student = new Student();//注意每一个list的元素都必须重新申请空间，要不然添加的所有元素都会被最后一个覆盖，
													//因为都是指向同一片空间的引用
				student.setSid(rs.getString("sid"));
				student.setSname(rs.getString("sname"));
				student.setSclass(rs.getString("sclass"));
				student.setSphone(rs.getString("sphone"));
				student.setSqq(rs.getString("sqq"));
				student.setSisleader(rs.getBoolean("sisleader"));
				
				studentList.add(student);
			}
		    Student[] students = new Student[studentList.size()];
		    return studentList.toArray(students);
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean updateStudentInfo(Student student,String oldsid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			Object[] params = new Object[]{student.getSid(),student.getSname(),student.getSclass(),
					student.getSphone(),student.getSqq(),student.isSisleader(),oldsid};
			execSQL("update student set sid=?, sname=?, sclass=?, sphone=?, sqq=?, sisleader=? where sid=?",params);
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteStudent(String sid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from student where sid = ?",sid);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteAllStudents(){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from student");
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean addExercise(Exercise exercise){
		try{
			String userid = (String)getProperty("login");
			if(userid ==null){
				return false;
			}
			
			execSQL("select max(eid) from exercise");
			int result=0;
			while(rs.next()){
				result = rs.getInt("max(eid)");
			}
		    //System.out.println("tttttttttttt   "+result+" ttttttttttt");
			int myeid=result+1;
		
			Object[] params1 =new Object[]{myeid,exercise.getEtitle(),exercise.getEteacher(),
					exercise.getElevel(),exercise.getEsum(),exercise.getEleftcount(),exercise.getEsummary(),"D:\\files\\"+exercise.getEfilepath()};
			execSQL("insert into exercise (eid,etitle,eteacher,elevel,esum,eleftcount,esummary,efilepath) values(?,?,?,?,?,?,?,?)",params1);
			
			Object[] params2 = new Object[]{userid,myeid};
			execSQL("insert into t_e (tid,eid) values (?,?)",params2);
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
 	public Exercise[] getExerciseByTname(String eteacher){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select * from exercise where eteacher like ?","%"+eteacher+"%");
			
			List<Exercise> exerciseList = new ArrayList<Exercise>(); 
			while(rs.next()){
				Exercise exercise = new Exercise();//注意每一个list的元素都必须重新申请空间，要不然添加的所有元素都会被最后一个覆盖，
													//因为都是指向同一片空间的引用
				exercise.setEid(rs.getInt("eid"));
				exercise.setEtitle(rs.getString("etitle"));
				exercise.setEteacher(rs.getString("eteacher"));
				exercise.setElevel(rs.getString("elevel"));
				exercise.setEsum(rs.getInt("esum"));
				exercise.setEleftcount(rs.getInt("eleftcount"));
				exercise.setEispass(rs.getBoolean("eispass"));
				exercise.setEsummary(rs.getString("esummary"));
				exercise.setEfilepath(rs.getString("efilepath"));
				
				exerciseList.add(exercise);
			}
		    Exercise[] exercises = new Exercise[exerciseList.size()];
		    return exerciseList.toArray(exercises);
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean updateExerciseInfo(Exercise exercise){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			Object[] params = new Object[]{exercise.getEtitle(),exercise.getEteacher(),
					exercise.getElevel(),exercise.getEsum(),exercise.getEleftcount(),exercise.isEispass(),exercise.getEid()};
			execSQL("update exercise set etitle=?, eteacher=?, elevel=?, esum=?, eleftcount=?, eispass=? where eid=?",params);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteExercise(int eid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from exercise where eid = ?",eid);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteAllExercises(){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from exercise");
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public TopicSelectViewOfTeacher[] getTopicSelectViewOfTeacher(String tid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select exercise.etitle,exercise.eteacher,exercise.elevel,exercise.esum,exercise.eleftcount,"
					+ "student.sname,student.sclass,student.sphone,exercise.eispass "
					+ "from teacher,exercise,student,allgroup,t_e,g_e "
					+ "where teacher.tid= ? and teacher.tid=t_e.tid and t_e.eid=exercise.eid and exercise.eid=g_e.eid and "
					+ "allgroup.gid=g_e.gid and student.sid=allgroup.gleader",tid);
			List<TopicSelectViewOfTeacher> list = new ArrayList<TopicSelectViewOfTeacher>();
			while(rs.next()){
				TopicSelectViewOfTeacher tsvt = new TopicSelectViewOfTeacher();
				
				tsvt.setEtitle(rs.getString("etitle"));
				tsvt.setEteacher(rs.getString("eteacher"));
				tsvt.setElevel(rs.getString("elevel"));
				tsvt.setEsum(rs.getInt("esum"));
				tsvt.setEleftcount(rs.getInt("eleftcount"));
				tsvt.setEispass(rs.getBoolean("eispass"));
				tsvt.setSname(rs.getString("sname"));
				tsvt.setSclass(rs.getString("sclass"));
				tsvt.setSphone(rs.getString("sphone"));
				list.add(tsvt);
			}
			TopicSelectViewOfTeacher[]  tsvts = new TopicSelectViewOfTeacher[list.size()];
			return list.toArray(tsvts);
		}catch(Exception e){
			//System.out.println("tttttttt  "+e.toString());
			return null;
		}
	}
	
	public TopicInfoViewOfStudent[] getTopicInfoViewOfStudent4android(){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select exercise.eid,exercise.etitle,exercise.eteacher,exercise.elevel,exercise.esum,exercise.eleftcount,"
					+ "exercise.esummary,exercise.efilepath,teacher.tphone "
					+ "from exercise,teacher,t_e "
					+ "where exercise.eispass=1 and exercise.eid=t_e.eid and teacher.tid=t_e.tid");
			List<TopicInfoViewOfStudent> list = new ArrayList<TopicInfoViewOfStudent>();
			while(rs.next()){
				TopicInfoViewOfStudent tivs = new TopicInfoViewOfStudent();
				
				tivs.setEid(rs.getInt("eid"));
				tivs.setEtitle(rs.getString("etitle"));
				tivs.setEteacher(rs.getString("eteacher"));
				tivs.setElevel(rs.getString("elevel"));
				tivs.setEsum(rs.getInt("esum"));
				tivs.setEleftcount(rs.getInt("eleftcount"));
				tivs.setEsummary(rs.getString("esummary"));
				tivs.setEfilepath(rs.getString("efilepath"));
				tivs.setTphone(rs.getString("tphone"));
				list.add(tivs);
				
			}
			TopicInfoViewOfStudent[]  tivss = new TopicInfoViewOfStudent[list.size()];
			return list.toArray(tivss);
		}catch(Exception e){
			System.out.println("tttttttt  "+e.toString());
			return null;
		}
	}
	
	public boolean uploadFileWithBase64Str4android(String fileBuffer,String filename){
		FileOutputStream fos = null;
		try{
			String dir = FileDirOnServer;
			byte[] buffer = new BASE64Decoder().decodeBuffer(fileBuffer);
			File destDir = new File(dir);
			if(!destDir.exists())
			destDir.mkdir();
			
			fos = new FileOutputStream(new File(destDir,filename));
			fos.write(buffer);
			fos.flush();
			fos.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
			return false;	
}

	public String downloadFileToBase64Str4android(String ServerEfilepath){
		
			FileOutputStream fos = null;
			try{
				FileInputStream fis = new FileInputStream(ServerEfilepath);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = fis.read(buffer))>=0){
					baos.write(buffer,0,count);
				}
				String fileBuffer = new String(Base64.encode(baos.toByteArray()));
				fis.close();
				return fileBuffer;
			}catch(Exception e){
				e.printStackTrace();
			}
				return null;
	}
	//注意对java实现的webservice来说，不管是使用byte[]，还是使用javax.activation.DataHandler作为WebService方法的数据类型，
	//使用wsdl2java命令生成的stub类中相应方法的类型都是javax.activation.DataHandler。
	//而象使用.net、delphi生成的stub类的相应方法类型都是byte[]。这是由于javax.activation.DataHandler类是Java特有的，
	//对于其他语言和技术来说，并不认识javax.activation.DataHandler类，因此，也只有使用最原始的byte[]了。
	public  byte[] downloadFileToByte(String ServerEfilepath){
			 File file = new File(ServerEfilepath);
			 FileInputStream fis = null;
			 byte[] buffer = null;
			 try{
				 fis = new FileInputStream(file);
			     // 创建保存要上传的图像文件内容的字节数组
			      buffer = new byte[(int) file.length()];
			     // 将图像文件的内容读取buffer数组中
			     fis.read(buffer);
			 }catch(Exception e){
				 return null;
			 }finally{
				 try {  
		                fis.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
			 }
		    return buffer; 
		}
		
	public  DataHandler downloadFileToDataHandler(String ServerEfilepath){
			DataHandler dataHandler = new DataHandler(new FileDataSource(ServerEfilepath));
			return dataHandler;
		}
	 
	/*从客户端传来的buffer（由FileInputStream(File)构造一个输入流，然后通过read方法读到buffer）*/
	public boolean uploadFileWithByte(byte[] buffer, String filename){
			String dir=FileDirOnServer;
			try{
			File file =new File(dir);
			if(!file.exists()){
				file.mkdir();
			}
			//构造文件输出流FileOutputStream
			FileOutputStream fos = new FileOutputStream(dir+filename);
			//将buffer通过write方法写入到指定文件
			fos.write(buffer);
			fos.close();
			}catch(Exception e){
				return false;
			}
			return true;
		}
		
		
	/*从客户端传来的DataHandler类，这个类由FileDataSource构造*/
	public boolean uploadFileWithDataHandler(DataHandler dataHandler,String filename){
			String dir=FileDirOnServer;
			try{
			File file =new File(dir);
			if(!file.exists()){
				file.mkdir();
			}
			FileOutputStream fos = new FileOutputStream(dir+filename);
			writeWithInputStreamToFile(dataHandler.getInputStream(),fos);
			fos.close();
			}catch(Exception e){
				return false;
			}
			return true;
		}
		
	private void writeWithInputStreamToFile(InputStream is,OutputStream os)throws Exception{
			int n=0;
			byte[] buffer = new byte[8192];
			while((n=is.read(buffer))>0){
				os.write(buffer,0,n);
			}
		}
}
