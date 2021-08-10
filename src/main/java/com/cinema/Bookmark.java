package com.cinema;

public class Bookmark {
    private static Bookmark bookmark;
    private int pageNumber;
    static {
        bookmark = new Bookmark();
    }

    private Bookmark(){
    }

    public static Bookmark getInstance() {
        return bookmark;
    }
    public int getPageNumber(){
        return pageNumber;
    }
    public void setBookmarkNumber (int newNumber) {
        pageNumber = newNumber;
    }
}
