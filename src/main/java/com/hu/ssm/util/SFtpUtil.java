package com.hu.ssm.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

/**
 * @author hutiantian
 * @date: 2018/7/11 15:16
 * @since 1.0.0
 */
@Slf4j
//@Service
public class SFtpUtil {

    private static String host_1;
    private static int port_1;
    private static String userName_1;
    private static String password_1;
    private static String host_2;
    private static int port_2;
    private static String userName_2;
    private static String password_2;

    private static int timeout;
    private static String separator;
    private static String root;

    static {
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        Properties properties = new Properties();
        try {
            // 使用properties对象加载输入流
            properties.load(SFtpUtil.class.getClassLoader().getResourceAsStream("sftp_server.properties"));
            //获取key对应的value值
            host_1 = properties.getProperty("host_1");
            port_1 = Integer.parseInt(properties.getProperty("port_1"));
            userName_1 = properties.getProperty("userName_1");
            password_1 = properties.getProperty("password_1");
            host_2 = properties.getProperty("host_2");
            port_2 = Integer.parseInt(properties.getProperty("port_2"));
            userName_2 = properties.getProperty("userName_2");
            password_2 = properties.getProperty("password_2");
            separator = properties.getProperty("separator");
            timeout= Integer.parseInt(properties.getProperty("timeout"));
            root = properties.getProperty("root");
        }catch (Exception e){
            log.error("初始化SFtpUtil失败!",e);
        }
    }

    private SFtpUtil(){};

    /**
     *  连接sftp服务器
     */
    public SFtpDto connect(int type){
        JSch jsch = new JSch();
        SFtpDto sftpDto = null;
        Session session;
        Channel channel;
        ChannelSftp sftp;
        try{
            sftpDto = new SFtpDto();
            if(type==1){
                session = jsch.getSession(userName_1, host_1, port_1);
                session.setPassword(password_1);
            }else {
                session = jsch.getSession(userName_2, host_2, port_2);
                session.setPassword(password_2);
            }
            session.setConfig("StrictHostKeyChecking", "no");           //不验证hostKey
            session.connect(timeout);
            channel = session.openChannel("sftp");
            channel.connect(timeout);
            sftp = (ChannelSftp)channel;
            sftpDto.setSession(session);
            sftpDto.setChannel(channel);
            sftpDto.setSftp(sftp);
        }catch (Exception e){
            log.error("获取sftp连接异常！",e);
        }
        return sftpDto;
    }

    /**
     * 断开连接
     */
    public void close(SFtpDto sFtpDto){
        if(sFtpDto!=null){
            Session session = sFtpDto.getSession();
            Channel channel = sFtpDto.getChannel();
            ChannelSftp sftp = sFtpDto.getSftp();
            if(sftp!=null){
                sftp.disconnect();
                sftp.exit();
            }
            if(channel!=null){
                channel.disconnect();
            }
            if(session!=null){
                session.disconnect();
            }
        }
    }

    /**
     *  创建目录，上传文件时使用
     */
    public static boolean mkDir(SFtpDto sFtpDto,String directory) throws Exception {
        boolean flag = false;
        ChannelSftp sftp = sFtpDto.getSftp();
        String[] directoryList = directory.split(separator);
        if(directoryList.length>0){
            flag = true;
            for (int i = 1; i < directoryList.length; i++) {
                if(!directoryList[i].equals("")){
                    // 判断子目录文件夹是否存在，不存在即创建
                    SftpATTRS attrs = null;
                    try {
                        attrs = sftp.stat(directoryList[i]);
                    } catch (Exception e) {
                        // do nothing
                    }
                    if (attrs == null) {
                        sftp.mkdir(directoryList[i]);
                    }
                    sftp.cd(directoryList[i]);          //进入子目录
                }
            }
        }
        return flag;
    }

    /**
     * 上传文件到SFTP服务器，目录不存在，则新建；文件已存在，则覆盖
     * @param inputStream                待上送的输入流
     * @param ftpDirectory               SFTP目录（必须以"/"开头）
     * @param fileName                  上传到服务器的文件名
     * @param type                       SFTP服务器(1-信贷，2-电子账户)
     */
    public boolean upload(InputStream inputStream, String ftpDirectory, String fileName,int type) {
        boolean flag = false;
        if(!ftpDirectory.startsWith(separator)){
            log.error("文件目录必须以"+separator+"开始！");
            return flag;
        }
        SFtpDto sFtpDto = connect(type);
        if(sFtpDto!=null){
            ChannelSftp sftp = sFtpDto.getSftp();
            try {
                mkDir(sFtpDto,ftpDirectory);            //进入指定目录
                sftp.put(inputStream,fileName);
                flag = true;
            }catch (Exception e){
                log.error("SFtpUtil upload exception!", e);
            }finally {
                try {
                    if(inputStream!=null){
                        inputStream.close();
                    }
                } catch (IOException e) {
                    log.error("InputStream close exception!", e);
                }
                close(sFtpDto);
            }
        }
        return flag;
    }

    /**
     * 从SFTP服务器下载文件
     * @param outputStream               待下载的输出流
     * @param ftpDirectory               待下载的文件所在的SFTP目录
     * @param fileName                   待下载的文件名
     * @param type                       SFTP服务器(1-信贷，2-电子账户)
     */
    public boolean downLoad(OutputStream outputStream,String ftpDirectory, String fileName,int type) {
        boolean flag = false;
        if(!ftpDirectory.startsWith(separator)){
            log.error("文件目录必须以"+separator+"开始！");
            return flag;
        }
        SFtpDto sFtpDto = connect(type);
        if(sFtpDto!=null){
            ChannelSftp sftp = sFtpDto.getSftp();
            try {
                sftp.cd(ftpDirectory);            //进入指定目录
                sftp.get(fileName, outputStream);
                flag = true;
            }catch (Exception e){
                log.error("SFtpUtil upload exception!", e);
            }finally {
                close(sFtpDto);
            }
        }
        return flag;
    }

    public static String getRoot(){
        return root;
    }


//    sFtpDto = sFtpUtil.connect(1);
//            if(sFtpDto!=null) {
//        ChannelSftp sftp = sFtpDto.getSftp();
//        sftp.cd(fileDir);            //进入指定目录
//        Vector<ChannelSftp.LsEntry> fileList = sftp.ls(fileDir); //返回目录下所有文件名称
//        for(ChannelSftp.LsEntry file : fileList){
//            String fileName = file.getFilename();
//            if(!fileName.equals(".")&&!fileName.equals("..")){
//                String type = fileName.split("-")[0];
//                //过滤掉进件已上传的图片
//                for(String s:docStr.split(",")){
//                    if(type.equals(s)){
//                        continue;
//                    }
//                }
}


