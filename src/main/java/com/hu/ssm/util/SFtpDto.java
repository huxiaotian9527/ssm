package com.hu.ssm.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import lombok.Data;

/**
 * @author hutiantian
 * @date: 2018/7/11 16:00
 * @since 1.0.0
 */
@Data
public class SFtpDto {
    private Session session;    //会话
    private Channel channel;    //连接通道
    private ChannelSftp sftp;   //sftp操作类
}
