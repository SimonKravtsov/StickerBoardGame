/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sticker.websocket;

import java.awt.BorderLayout;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author simon
 */
@ServerEndpoint("/chat")
public class chat {
    final static ConcurrentHashMap<String,Basic>_client
            =new ConcurrentHashMap<>();
    
    @OnOpen 
    public void onOpen(final Session session){
        final Basic clientConnection =session.getBasicRemote();
        final String clientId=session.getId();
        _client.put(clientId,clientConnection);
        System.out.println("INF: NEW client conneted "+clientId);
       
    }
    
    @OnMessage
    public String onMessage(final String message,final Session session) {
        try {
            final String senderId =session.getId();
            final String broadcastMessage = senderId + ">>" +message;
            for (final String key: _client.keySet()){
                _client.get(key).sendText(broadcastMessage);
            }
            
        }catch(final Throwable ex){
            System.out.println("Error: "+ex.getMessage());
        }
        return "";
//        return message;
    }
    
}
