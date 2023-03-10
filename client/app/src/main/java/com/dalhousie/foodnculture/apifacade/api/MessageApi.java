package com.dalhousie.foodnculture.apifacade.api;

import com.dalhousie.foodnculture.apifacade.contract.IMessagesOperation;
import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.models.Messages;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MessageApi implements IMessagesOperation {
    private final IRequest<Messages> request;
    private String baseUrl = "/api/messages";

    public MessageApi(IRequest<Messages> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<Messages> findAll() {
        Messages[] messages = new Messages[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            messages = Mapper.mapFromJson(buffer.toString(), Messages[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(messages);
    }

    @Override
    public int save(Messages object) throws Exception {
        try {
            StringBuffer buffer = this.request.doPost(baseUrl + "/", Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Messages object) {
        try {
            StringBuffer buffer = this.request.doPut(baseUrl + "/" + object.getId(), Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Messages object) {
        return deleteById(object.getId());
    }

    @Override
    public int deleteById(Integer id) {
        try {
            StringBuffer buffer = this.request.doDelete(baseUrl + "/" + id);
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean exists(Integer id) {
        return getById(id).isPresent();
    }

    @Override
    public Optional<Messages> getById(Integer id) {
        Messages message = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            message = Mapper.mapFromJson(buffer.toString(), Messages.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(message);
    }

    @Override
    public List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2) {
        Messages[] messages = new Messages[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/chats/" + user1 + "/" + user2);
            messages = Mapper.mapFromJson(buffer.toString(), Messages[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(messages);
    }
}
