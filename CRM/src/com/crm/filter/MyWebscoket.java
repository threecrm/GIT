package com.crm.filter;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.OnMessage;
import javax.websocket.OnClose;

import javax.websocket.OnError;

public class MyWebscoket {
	private static Map<String, MyWebscoket> map=new HashMap<>();
	private Session session=null;
	private String name;
	private static int count=0;
	@OnOpen
	public void onopen(@PathParam("name")String name,Session session) {
		System.out.println(session);
		this.session=session;
		this.name=name;
		map.put(name, this);
		count++;
		System.out.println("杩炴帴寤虹珛浜�"+name);
	}
	@OnMessage
	public void onmessage(String message) {
		System.out.println(message);
		String[] split = message.split(",");
		String formName=split[0];
		String tomName=split[1];
		String content=split[2];
		if(map.containsKey(tomName)) {
			//鏉庡洓鍦ㄧ嚎
			map.get(tomName).session.getAsyncRemote().sendText(content);
		}else {
			//绂荤嚎娑堟伅
		}
		
		
	}
	@OnClose
	public void onclose() {
		System.out.println("杩炴帴閫�鍑轰簡");
		map.remove(name);
		count--;
	}
	@OnError
	public void onerror(Session session,Throwable throwable) {}
}
