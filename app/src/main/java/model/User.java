package model;

public class User {
    private String mFirstname;
    private int mScore;

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }



    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    @Override
    public String toString() {
        return  mFirstname + ":"+ mScore +" Victoires";
    }

    public User(String firstname, int score) {
        mFirstname = firstname;
        mScore = score;
    }

    public User() {
    }
}
