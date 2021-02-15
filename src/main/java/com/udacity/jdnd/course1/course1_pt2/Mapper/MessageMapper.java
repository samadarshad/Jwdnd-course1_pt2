package com.udacity.jdnd.course1.course1_pt2.Mapper;

import com.udacity.jdnd.course1.course1_pt2.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES WHERE messageId = #{messageId}")
    ChatMessage getMessage(Integer messageId);

    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getMessages();

    @Insert("INSERT INTO MESSAGES (username, messageText) VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage message);
}
