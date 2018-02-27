package tech.moushao.www.bean;

import java.util.List;

/**
 * Created by Moushao on 2017/8/22.
 */

public class ResponseBean {
    public String status;
    public String message = "";
    public Object data;

    public ResponseBean(String status, String message, Object cs) {
        this.status = status;
        this.message = message;
        this.data = cs;
    }
    public ResponseBean(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
