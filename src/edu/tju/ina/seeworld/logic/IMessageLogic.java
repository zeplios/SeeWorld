package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.exception.SeeWorldException;

public interface IMessageLogic {
	public void setReaded(Integer id) throws SeeWorldException;

	public void deleteMessage(Integer id) throws SeeWorldException;

	public Integer addMessage(String senderId, String UsertoID, String content,
			Boolean reply, Boolean conceal, String location)
			throws SeeWorldException;

	public JSONArray getMessageByPage(String senderId, String receiverId,
			Integer type, Integer offset, Integer pagesize)
			throws SeeWorldException;

	public Integer getNewMessagesNum(String receiverId) throws SeeWorldException;
	
	public JSONArray getNewMessage(String receiverId) throws SeeWorldException;
}
