/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.common;

import java.io.Serializable;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class Message implements Serializable {
    private long from;
    private String message;

    public Message() {
    }

    public Message(String message, long from) {
        this.message = message;
        this.from = from;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", message='" + message + '\'' +
                '}';
    }

}
