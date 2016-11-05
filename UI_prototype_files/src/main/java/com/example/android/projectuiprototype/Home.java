package com.example.android.projectuiprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
    }

    protected void feedMeButton(View v){
        if(AppManager.instance().alreadyCreatedPost()) {
            loadBrowsePosts();
        } else{
            loadCreatePost();
        }

    }

    protected void browseDirButton(View v){
        loadBrowseDir();
    }

    protected void loadBrowsePosts(){
        Intent destination = new Intent(this,BrowsePosts.class);
        startActivity(destination);
    }

    protected void loadCreatePost(){
        Intent destination = new Intent(this,CreatePost.class);
        startActivity(destination);
    }

    protected void loadBrowseDir() {
        Intent destination = new Intent(this,BrowseDirectory.class);
        startActivity(destination);
    }
}
