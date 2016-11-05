package com.example.android.projectuiprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

public class CreatePost extends AppCompatActivity {

    public String _TEST_userName;

    private CheckBox creditChecker, cashChecker;
    private Spinner timeSelector, cuisineSelector0, cuisineSelector1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        creditChecker = (CheckBox)findViewById(R.id.check_credit);
        cashChecker = (CheckBox)findViewById(R.id.check_cash);
        timeSelector = (Spinner)findViewById(R.id.select_orderTime);
        cuisineSelector0 = (Spinner)findViewById(R.id.select_cuisine0);
        cuisineSelector1 = (Spinner)findViewById(R.id.select_cuisine1);

        _TEST_userName = "JaveyDones";
    }

    protected void submitButton(View v){

        if(!creditChecker.isChecked() && !cashChecker.isChecked()) {
            // neither payment option selectedl
        } else {
            cuisineTypes[] cuisines  = new cuisineTypes[2];
            for(int j=0; j<cuisineTypes.numOptions.ordinal();j++){
                if(cuisineSelector0.getSelectedItemPosition() == j){
                    cuisines[0] = cuisineTypes.values()[j];
                    break;
                }
            }
            for(int j=0; j<cuisineTypes.numOptions.ordinal();j++){
                if(cuisineSelector1.getSelectedItemPosition() == j){
                    cuisines[1] = cuisineTypes.values()[j];
                    break;
                }
            }

            timeCategory timeToOrder = timeCategory.fifteen;
            for(int j=0; j<timeCategory.numOptions.ordinal();j++){
                if(cuisineSelector0.getSelectedItemPosition() == j){
                    timeToOrder = timeCategory.values()[j];
                    break;
                }
            }

            PostInfo newPost = new PostInfo(_TEST_userName,cuisines,distanceRange.near, timeToOrder, cashChecker.isChecked(), creditChecker.isChecked());

            //pass newPost to be added to database

            AppManager.instance().postCreated(newPost);

            loadBrowsePosts();
        }
    }

    protected void backButton() {
        loadHome();

    }

    protected void loadHome(){
        Intent destination = new Intent(this,Home.class);
        startActivity(destination);
    }

    protected void loadBrowsePosts(){
        Intent destination = new Intent(this,BrowsePosts.class);
        startActivity(destination);
    }
}
