package com.example.apaguide.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Example extends RealmObject {
    @PrimaryKey
    public int id;
    public int categoryId;
    public int subCategoryId;
    public int sequenceNo;
    public  String description1;
    public  String description2;
    public String example;
    public int authorNumber;
    public int hasDate;
}
