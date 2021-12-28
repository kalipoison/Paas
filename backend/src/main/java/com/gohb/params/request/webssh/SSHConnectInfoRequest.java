package com.gohb.params.request.webssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import org.springframework.web.socket.WebSocketSession;

/**
 * ssh连接信息
 */
public class SSHConnectInfoRequest {
    private WebSocketSession webSocketSession;
    private JSch jSch;
    private Channel channel;


    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public JSch getjSch() {
        return jSch;
    }

    public void setjSch(JSch jSch) {
        this.jSch = jSch;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
