package com.fragrantmeal.EventBus;

/**
 * Created by CaoBin on 2016/4/18.
 */
public class EventBusSelect {
    public String message;
    public String actionType;

    public EventBusSelect(String actionType, String message) {
        this.actionType = actionType;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
