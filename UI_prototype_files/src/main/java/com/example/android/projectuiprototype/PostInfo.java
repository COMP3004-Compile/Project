package com.example.android.projectuiprototype;

public class PostInfo {
    public String name;
    public cuisineTypes[] cuisines;
    public distanceRange distance;
    public timeCategory timeLefToOrder;
    public boolean cash;
    public boolean credit;

    public PostInfo(String userName, cuisineTypes[] foodTypes, distanceRange dist, timeCategory time, boolean money, boolean creditCard) {
        name = userName;
        cuisines = foodTypes;
        distance = dist;
        timeLefToOrder = time;
        cash = money;
        credit = creditCard;
    }
}
