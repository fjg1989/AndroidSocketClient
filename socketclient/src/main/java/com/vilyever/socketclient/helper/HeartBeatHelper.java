package com.vilyever.socketclient.helper;

import java.nio.charset.Charset;

/**
 * HeartBeatHelper
 * Created by vilyever on 2016/5/19.
 * Feature:
 */
public class HeartBeatHelper {
    final HeartBeatHelper self = this;

    public static final long DefaultHeartBeatInterval = 1000 * 30;
    public static final long NoneHeartBeatInterval = -1;
    public static final long DefaultRemoteNoReplyAliveTimeout = DefaultHeartBeatInterval * 2;
    public static final long NoneRemoteNoReplyAliveTimeout = -1;
    
    /* Constructors */
    public HeartBeatHelper(String charsetName) {
        this.charsetName = charsetName;
    }
    
    /* Public Methods */
    public HeartBeatHelper setSendString(String message) {
        if (message == null) {
            setSendData(null);
        }
        else {
            setSendData(message.getBytes(Charset.forName(getCharsetName())));
        }
        return this;
    }

    public HeartBeatHelper setReceiveString(String message) {
        if (message == null) {
            setReceiveData(null);
        }
        else {
            setReceiveData(message.getBytes(Charset.forName(getCharsetName())));
        }
        return this;
    }

    /**
     * 禁用发送心跳包
     */
    public void disableHeartBeat() {
        setSendData(null);
    }

    /**
     * 禁用自动断开
     */
    public void disableRemoteNoReplyAliveTimeout() {
        setRemoteNoReplyAliveTimeout(NoneRemoteNoReplyAliveTimeout);
    }

    /**
     * 是否发送心跳包
     * @return
     */
    public boolean shouldSendHeartBeat() {
        return getSendData() != null && getHeartBeatInterval() != NoneHeartBeatInterval;
    }

    /**
     * 是否在超过设定没有远程响应超时后自动断开
     * @return
     */
    public boolean shouldAutoDisconnectWhenRemoteNoReplyAliveTimeout() {
        return getRemoteNoReplyAliveTimeout() != NoneRemoteNoReplyAliveTimeout;
    }

    /* Properties */
    private String charsetName;
    public HeartBeatHelper setCharsetName(String charsetName) {
        this.charsetName = charsetName;
        return this;
    }
    public String getCharsetName() {
        return this.charsetName;
    }

    /**
     * 发送心跳包的数据
     */
    private byte[] sendData;
    public HeartBeatHelper setSendData(byte[] sendData) {
        this.sendData = sendData;
        return this;
    }
    public byte[] getSendData() {
        return this.sendData;
    }

    /**
     * 接收心跳包的数据，用于过滤远程心跳包
     */
    private byte[] receiveData;
    public HeartBeatHelper setReceiveData(byte[] receiveData) {
        this.receiveData = receiveData;
        return this;
    }
    public byte[] getReceiveData() {
        return this.receiveData;
    }

    /**
     * 心跳包发送间隔
     */
    private long heartBeatInterval = NoneHeartBeatInterval;
    public HeartBeatHelper setHeartBeatInterval(long heartBeatInterval) {
        if (heartBeatInterval < 0) {
            heartBeatInterval = NoneHeartBeatInterval;
        }
        this.heartBeatInterval = heartBeatInterval;
        return this;
    }
    public long getHeartBeatInterval() {
        return this.heartBeatInterval;
    }

    /**
     * 远程端在一定时间间隔没有消息后自动断开
     */
    private long remoteNoReplyAliveTimeout = NoneRemoteNoReplyAliveTimeout;
    public HeartBeatHelper setRemoteNoReplyAliveTimeout(long remoteNoReplyAliveTimeout) {
        if (remoteNoReplyAliveTimeout < 0) {
            remoteNoReplyAliveTimeout = NoneRemoteNoReplyAliveTimeout;
        }
        this.remoteNoReplyAliveTimeout = remoteNoReplyAliveTimeout;
        return this;
    }
    public long getRemoteNoReplyAliveTimeout() {
        return this.remoteNoReplyAliveTimeout;
    }

    /**
     * 记录上次发送心跳包的时间
     */
    private long lastSendHeartBeatMessageTime;
    public HeartBeatHelper setLastSendHeartBeatMessageTime(long lastSendHeartBeatMessageTime) {
        this.lastSendHeartBeatMessageTime = lastSendHeartBeatMessageTime;
        return this;
    }
    public long getLastSendHeartBeatMessageTime() {
        return this.lastSendHeartBeatMessageTime;
    }

    /**
     * 记录上次接收到消息的时间
     */
    private long lastReceiveMessageTime;
    public HeartBeatHelper setLastReceiveMessageTime(long lastReceiveMessageTime) {
        this.lastReceiveMessageTime = lastReceiveMessageTime;
        return this;
    }
    public long getLastReceiveMessageTime() {
        return this.lastReceiveMessageTime;
    }

    /* Overrides */
    
    
    /* Delegates */
    
    
    /* Private Methods */
}