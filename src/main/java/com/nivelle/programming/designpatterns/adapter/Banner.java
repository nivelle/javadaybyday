package com.nivelle.programming.designpatterns.adapter;


public class Banner {


    public String showWithTail(String title) {

        System.out.println(title + "tail");

        return title + "tail";

    }
}
