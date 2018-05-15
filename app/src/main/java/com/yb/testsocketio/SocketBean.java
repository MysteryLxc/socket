package com.yb.testsocketio;

/**
 * Created by 12475 on 2018/5/14.
 */

public class SocketBean {
    private int action;
    private String msg;
    private UserBean user;
    private String socketId;
    private String time;
    public static final int ACTION_CONN_SUCCESS = 0;
    public static final int ACTION_LOGIN_SUCCESS = 1;
    public static final int ACTION_DISCONN = 2;
    public static final int ACTION_MSG = 3;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
