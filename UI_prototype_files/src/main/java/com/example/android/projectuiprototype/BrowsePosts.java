package com.example.android.projectuiprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BrowsePosts extends AppCompatActivity {

    private int maxPosts = 10;
    private PostInfo[] posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_posts);
        posts = new PostInfo[maxPosts];
        _TEST_populatePosts();
        _TEST_updateContainers();
    }

    private void _TEST_populatePosts() {
        cuisineTypes[] quis0 = new cuisineTypes[1];
        cuisineTypes[] quis1 = new cuisineTypes[2];
        cuisineTypes[] quis2 = new cuisineTypes[2];

        quis0[0] = quis1[1] = cuisineTypes.Pizza;
        quis1[0] =  cuisineTypes.American;
        quis2[0] = cuisineTypes.Asian;
        quis2[1] = cuisineTypes.Other;

        posts[0] = new PostInfo("SlimJim",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[1] = new PostInfo("Queen Bea Arthur",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[2] = new PostInfo("Black Sky",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[3] = new PostInfo("WiskE TangO Foxtrot",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[4] = new PostInfo("Mk Merica Plate Agn",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[5] = new PostInfo("Snape Kild DumbLdoor",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[6] = new PostInfo("Original Roger",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[7] = new PostInfo("Alexis 898",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[8] = new PostInfo("xSepphirothx420",quis0,distanceRange.near, timeCategory.fifteen, true, true);
        posts[9] = new PostInfo("NYC 1979",quis0,distanceRange.near, timeCategory.fifteen, true, true);
    }


    private void _TEST_updateContainers() {

    }

    private void updatePost(int postNum) {


    }

    protected void backButton(View v) {
        loadHome();

    }

    protected void loadHome(){
        Intent destination = new Intent(this,Home.class);
        startActivity(destination);
    }
}
