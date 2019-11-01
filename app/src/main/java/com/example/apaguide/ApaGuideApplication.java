package com.example.apaguide;

import android.app.Application;
import android.content.Context;

import com.example.apaguide.model.Example;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ApaGuideApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Realm initialize
        Realm.init(this);   // Initialize Realm
        Realm.deleteRealm(Realm.getDefaultConfiguration());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .initialData(new RealmInitialData(this))
                .build();
        Realm.setDefaultConfiguration(config);
    }
}

// Initialize Realm data
class RealmInitialData implements Realm.Transaction {
    Context context;
    RealmInitialData(Context context){
        this.context = context;
    }
    @Override
    public void execute(Realm realm) {
        try {
            InputStream is = context.getAssets().open("apa_examples.json");
            realm.createAllFromJson(Example.class, is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
