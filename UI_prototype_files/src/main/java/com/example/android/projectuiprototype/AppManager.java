package com.example.android.projectuiprototype;

public class AppManager {
    private boolean loggedIn;
    private boolean postCreated;
    private PostInfo myPost;

    private static AppManager appMan;

    // AppManager singleton
    // for all access to AppManager methods use AppManager.instance()
    public static AppManager instance(){

        if(appMan==null){
            appMan = new AppManager();
            appMan.postCreated = true;
            appMan.loggedIn = true;

            // above values will be initialized as false in final version
            // currently troubleshooting an issue on the create post activity
        }

        return appMan;
    }

    public boolean alreadyCreatedPost() {
        return postCreated;
    }

    public void postCreated(PostInfo post){
        myPost = post;
        postCreated = true;
    }
}
