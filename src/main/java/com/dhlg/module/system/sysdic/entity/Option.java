package com.dhlg.module.system.sysdic.entity;

import lombok.Data;

@Data
public class Option {
    String label;
    String value;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
