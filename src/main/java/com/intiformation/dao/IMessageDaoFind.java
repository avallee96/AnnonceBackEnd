package com.intiformation.dao;

import java.util.List;

import com.intiformation.model.Message;

public interface IMessageDaoFind {

	public List<Message> selectbyAnnonce_message(int id);
}
