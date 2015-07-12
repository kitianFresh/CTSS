package teacherclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import service.*;

public class TeacherMainFrame extends javax.swing.JFrame {
    private DataHandler  dataHandler;
    private String upfilename;
    private TssServiceStub.TopicSelectViewOfTeacher[] tsvts;
    TssServiceStub.Exercise exercise;
    public TeacherMainFrame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        AddInfoEPane = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        btnAddInfoEfileupload = new javax.swing.JButton();
        txtAddInfoEtitle = new javax.swing.JTextField();
        txtAddInfoEteacher = new javax.swing.JTextField();
        txtAddInfoElevel = new javax.swing.JTextField();
        txtAddInfoEsum = new javax.swing.JTextField();
        txtAddInfoEleftcount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddInfoEsummary = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnAddInfoEsave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        EPane1 = new javax.swing.JPanel();
        txtExerciseSelect = new javax.swing.JTextField();
        btnExerciseSelect = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstEInfo = new javax.swing.JList();
        jLabel22 = new javax.swing.JLabel();
        EPane2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtEtitle = new javax.swing.JTextField();
        txtEteacher = new javax.swing.JTextField();
        txtElevel = new javax.swing.JTextField();
        txtEsum = new javax.swing.JTextField();
        txtEleftcount = new javax.swing.JTextField();
        txtEispass = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSelectGroup = new javax.swing.JTextField();
        txtGroupPhone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGroupClass = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel49.setText("课题名称：");
        jLabel49.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel49.setMinimumSize(new java.awt.Dimension(60, 16));

        jLabel50.setText("指导老师：");

        jLabel51.setText("课题等级：");

        jLabel52.setText("要求人数：");

        jLabel54.setText("添加课题");

        jLabel55.setText("课题余量：");

        btnAddInfoEfileupload.setText("上传附件");
        btnAddInfoEfileupload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInfoEfileuploadActionPerformed(evt);
            }
        });

        txtAddInfoEsummary.setColumns(20);
        txtAddInfoEsummary.setRows(5);
        jScrollPane1.setViewportView(txtAddInfoEsummary);

        jLabel5.setText("课题摘要：");

        btnAddInfoEsave.setText("提交");
        btnAddInfoEsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInfoEsaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddInfoEPaneLayout = new javax.swing.GroupLayout(AddInfoEPane);
        AddInfoEPane.setLayout(AddInfoEPaneLayout);
        AddInfoEPaneLayout.setHorizontalGroup(
            AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                        .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddInfoEteacher)
                            .addComponent(txtAddInfoEtitle)))
                    .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                        .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddInfoEsum)
                            .addComponent(txtAddInfoEleftcount)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)))
                    .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                        .addComponent(btnAddInfoEfileupload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddInfoEsave))
                    .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAddInfoElevel)))
                .addContainerGap())
        );
        AddInfoEPaneLayout.setVerticalGroup(
            AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddInfoEPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(24, 24, 24)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddInfoEtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtAddInfoEteacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtAddInfoElevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddInfoEsum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtAddInfoEleftcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(AddInfoEPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddInfoEfileupload)
                    .addComponent(btnAddInfoEsave))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(AddInfoEPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 470, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddInfoEPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("老师出题", jPanel1);

        btnExerciseSelect.setText("查询");
        btnExerciseSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExerciseSelectActionPerformed(evt);
            }
        });

        lstEInfo.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEInfoValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstEInfo);

        jLabel22.setText("输入出题人工号：");

        javax.swing.GroupLayout EPane1Layout = new javax.swing.GroupLayout(EPane1);
        EPane1.setLayout(EPane1Layout);
        EPane1Layout.setHorizontalGroup(
            EPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EPane1Layout.createSequentialGroup()
                .addGroup(EPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EPane1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(EPane1Layout.createSequentialGroup()
                .addComponent(txtExerciseSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExerciseSelect)
                .addGap(18, 18, 18))
        );
        EPane1Layout.setVerticalGroup(
            EPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EPane1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExerciseSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExerciseSelect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3)
                .addGap(73, 73, 73))
        );

        jLabel31.setText("课题名称：");
        jLabel31.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel31.setMinimumSize(new java.awt.Dimension(60, 16));

        jLabel32.setText("指导老师：");

        jLabel33.setText("课题等级：");

        jLabel34.setText("要求人数：");

        jLabel35.setText("是否通过：");

        jLabel37.setText("课题余量：");

        txtEtitle.setEditable(false);

        txtEteacher.setEditable(false);

        txtElevel.setEditable(false);

        txtEsum.setEditable(false);

        txtEleftcount.setEditable(false);

        txtEispass.setEditable(false);

        jLabel1.setText("选题小组：");

        jLabel2.setText("组长电话：");

        txtSelectGroup.setEditable(false);

        txtGroupPhone.setEditable(false);

        jLabel3.setText("组长班级：");

        txtGroupClass.setEditable(false);

        javax.swing.GroupLayout EPane2Layout = new javax.swing.GroupLayout(EPane2);
        EPane2.setLayout(EPane2Layout);
        EPane2Layout.setHorizontalGroup(
            EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EPane2Layout.createSequentialGroup()
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtEtitle, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEteacher))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtElevel))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(10, 10, 10)
                        .addComponent(txtEsum))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(10, 10, 10)
                        .addComponent(txtEleftcount))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEispass))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSelectGroup))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGroupPhone))
                    .addGroup(EPane2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(txtGroupClass)))
                .addContainerGap())
        );
        EPane2Layout.setVerticalGroup(
            EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EPane2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtEteacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtElevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEsum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtEleftcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtEispass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSelectGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGroupClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGroupPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(EPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(EPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("我的被选题目", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void writeWithInputStreamToFile(InputStream is,OutputStream os)throws Exception{
	int n=0;
	byte[] buffer = new byte[8192];
        while((n=is.read(buffer))>0){
		os.write(buffer,0,n);
	}
 
    }
    
    private DataHandler openUploadDialog(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("e:/"));//设置默认打开路径
        jFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);//设置保存对话框
        //将设置好了的两种文件过滤器添加到文件选择器中来
        jFileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

                @Override
                public boolean accept(File f) {
                   return (f.isFile() && (  f.getName().endsWith(".pdf"))); // 新建一个文件类型过滤器     
                }

                @Override
                public String getDescription() {
                    return "打开pdf文件格式"; 
                }
        });   
        
        String filePath = null;
        DataHandler dataHandler = null;
        int index = jFileChooser.showDialog(null, "打开文件");
        if (index == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile(); // 得到选择的文件
            upfilename = file.getName();
            filePath = jFileChooser.getCurrentDirectory().getAbsolutePath() + file.getName();
            file = new File(filePath);     
            dataHandler = new DataHandler(new FileDataSource(file));
            System.out.println(dataHandler.getDataSource().getName()+" " +file.getName());
            return dataHandler;
        }
        else{
             return null;
        }
    }
    
    private void btnAddInfoEsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInfoEsaveActionPerformed
        exercise = new TssServiceStub.Exercise();
        if(txtAddInfoEtitle.getText()==""||txtAddInfoEteacher.getText()==""||
           txtAddInfoElevel.getText()==""||txtAddInfoEsum.getText()==""||
           txtAddInfoEleftcount.getText()==""||txtAddInfoEsummary.getText()==""||
            dataHandler==null){
            JOptionPane.showMessageDialog(this,"请先完善信息再保存！！！");
        }
        else{
            exercise.setEtitle(txtAddInfoEtitle.getText());
            exercise.setEteacher(txtAddInfoEteacher.getText());
            exercise.setElevel(txtAddInfoElevel.getText());
            exercise.setEsum(Integer.parseInt(txtAddInfoEsum.getText()));
            exercise.setEleftcount(Integer.parseInt(txtAddInfoEleftcount.getText()));
            exercise.setEsummary(txtAddInfoEsummary.getText());
            exercise.setEfilepath(upfilename);
           
                    try{
                        TssServiceStub stub1 = new TssServiceStub();
                        TssServiceStub stub2 = new TssServiceStub();
                        TssServiceStub.AddExercise addExercise = new TssServiceStub.AddExercise();
                        addExercise.setExercise(exercise);
                        TssServiceStub.UploadFileWithDataHandler upfile = new TssServiceStub.UploadFileWithDataHandler();
                        upfile.setDataHandler(dataHandler);
                        upfile.setFilename(upfilename);
                        if(stub1.addExercise(addExercise).get_return()&&stub2.uploadFileWithDataHandler(upfile).get_return())
                            JOptionPane.showMessageDialog(TeacherMainFrame.this,"提交成功！");
                        else
                            JOptionPane.showMessageDialog(TeacherMainFrame.this,"提交失败！课题可能无法写入数据库");
                    }catch(Exception e ){
                        JOptionPane.showMessageDialog(TeacherMainFrame.this,e.getMessage(),"错误！",JOptionPane.ERROR_MESSAGE);
                    }
        }
    }//GEN-LAST:event_btnAddInfoEsaveActionPerformed

    private void btnAddInfoEfileuploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInfoEfileuploadActionPerformed
        dataHandler = openUploadDialog();
        if(dataHandler != null){
            int option = JOptionPane.showConfirmDialog(null, "小苹果:上传成功！！！", "结果",
                    JOptionPane.YES_NO_OPTION);
        }
        else{
            int option = JOptionPane.showConfirmDialog(null, "小苹果:上传失败！！！请重新上传！", "结果",
                   JOptionPane.YES_NO_OPTION);
        }
    }//GEN-LAST:event_btnAddInfoEfileuploadActionPerformed

    private void btnExerciseSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExerciseSelectActionPerformed
        try{
              //查询时先清空所有详细信息
            txtEtitle.setText("");
            txtEteacher.setText("");
            txtElevel.setText("");
            txtEsum.setText("");
            txtEleftcount.setText("");
            txtEispass.setText("");
            txtSelectGroup.setText("");
            txtGroupPhone.setText("");
            txtGroupClass.setText("");
             //查询
            TssServiceStub tssServiceStub = new TssServiceStub();
            TssServiceStub.GetTopicSelectViewOfTeacher gtsvt =new TssServiceStub.GetTopicSelectViewOfTeacher();
            gtsvt.setTid(txtExerciseSelect.getText());
            tsvts=tssServiceStub.getTopicSelectViewOfTeacher(gtsvt).get_return();
            javax.swing.DefaultListModel listModel = null;
            if(!(lstEInfo.getModel() instanceof javax.swing.DefaultListModel)){
                listModel = new javax.swing.DefaultListModel();
                lstEInfo.setModel(listModel);
            }
            else{
                listModel = (javax.swing.DefaultListModel)lstEInfo.getModel(); 
            }
            listModel.removeAllElements();//先清空列表
            if(tsvts!=null){
                for(int i=0;i<tsvts.length;i++){
                   listModel.addElement(tsvts[i].getEtitle());
                }  
            }
            else{
                JOptionPane.showMessageDialog(this,"查询失败！","错误！",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
             JOptionPane.showMessageDialog(this,e.getMessage(),"错误！",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExerciseSelectActionPerformed

    private void lstEInfoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstEInfoValueChanged
        int index = lstEInfo.getSelectedIndex();
        if(index < 0){
            return;
        }
        try{
            txtEtitle.setText(tsvts[index].getEtitle());
            txtEteacher.setText(tsvts[index].getEteacher());
            txtElevel.setText(tsvts[index].getElevel());
            txtEsum.setText(tsvts[index].getEsum()+"");
            txtEleftcount.setText(tsvts[index].getEleftcount()+"");
            if(tsvts[index].getEispass()){
                txtEispass.setText("是");
            }
            else{
                txtEispass.setText("否");
            }
            txtSelectGroup.setText(tsvts[index].getSname());
            txtGroupPhone.setText(tsvts[index].getSphone());
            txtGroupClass.setText(tsvts[index].getSclass());
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_lstEInfoValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeacherMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddInfoEPane;
    private javax.swing.JPanel EPane1;
    private javax.swing.JPanel EPane2;
    private javax.swing.JButton btnAddInfoEfileupload;
    private javax.swing.JButton btnAddInfoEsave;
    private javax.swing.JButton btnExerciseSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList lstEInfo;
    private javax.swing.JTextField txtAddInfoEleftcount;
    private javax.swing.JTextField txtAddInfoElevel;
    private javax.swing.JTextField txtAddInfoEsum;
    private javax.swing.JTextArea txtAddInfoEsummary;
    private javax.swing.JTextField txtAddInfoEteacher;
    private javax.swing.JTextField txtAddInfoEtitle;
    private javax.swing.JTextField txtEispass;
    private javax.swing.JTextField txtEleftcount;
    private javax.swing.JTextField txtElevel;
    private javax.swing.JTextField txtEsum;
    private javax.swing.JTextField txtEteacher;
    private javax.swing.JTextField txtEtitle;
    private javax.swing.JTextField txtExerciseSelect;
    private javax.swing.JTextField txtGroupClass;
    private javax.swing.JTextField txtGroupPhone;
    private javax.swing.JTextField txtSelectGroup;
    // End of variables declaration//GEN-END:variables
}
