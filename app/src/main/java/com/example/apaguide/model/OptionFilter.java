package com.example.apaguide.model;

import java.io.Serializable;

public class OptionFilter implements Serializable {
    public int authorNumber;
    public int hasDate;

    public OptionFilter(){
        this.authorNumber = 1;
        this.hasDate = 1;
    }
}
