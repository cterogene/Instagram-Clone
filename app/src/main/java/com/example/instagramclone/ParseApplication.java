package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("wEeikZGzctyO4z7hfLn4w9UrVrvBSQRBfaRZZRek")
                .clientKey("GKTyfyuAUel4E26kSY7vCRkEpqX8rswpzqln0us8")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
