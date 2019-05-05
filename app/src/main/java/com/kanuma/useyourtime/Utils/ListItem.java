package com.kanuma.useyourtime.Utils;

public abstract class ListItem {

    public static final int TYPE_DATE =0 ;
    public static final int TYPE_GENERAL=1;
    public static final int TYPE_TOTAL=3;

    abstract public int getType();

}
