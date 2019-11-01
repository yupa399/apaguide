package com.example.apaguide.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.apaguide.model.Example;
import com.example.apaguide.model.OptionFilter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

public class PageViewModel extends AndroidViewModel {
    private int mCategoryId;    // Category: Book, Web, Magazine, Journal
    private int mIndex;         // Reference list, In-text citing paraphrase, In-text citing direct quote
    private OptionFilter mOptionFilter;
    private MutableLiveData<List<Example>> mExample = new MutableLiveData<>();

    public PageViewModel(@NonNull Application application) {
        super(application);
        mOptionFilter = new OptionFilter();
    }

    public void setIndex(int index) {
        mIndex = index;
    }
    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public OptionFilter GetOptionFilter(){
        return mOptionFilter;
    }

    public void setOptionFiletr(OptionFilter optionFilter){
        mOptionFilter = optionFilter;
    }

    public LiveData< List<Example>> getExamples(){
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        // Build the query looking at all users:
        RealmQuery<Example> query = realm.where(Example.class);
        //query.equalTo("categoryId", mCategoryId);
        query.equalTo("categoryId", 1);     // Book demo data only
        query.equalTo("subCategoryId", mIndex);
        query.sort("sequenceNo", Sort.ASCENDING);   // Sort by seq no
        // Execute the query.
        mExample.setValue(query.findAll());
        return mExample;
    }
}
