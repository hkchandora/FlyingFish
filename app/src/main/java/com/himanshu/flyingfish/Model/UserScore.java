package com.himanshu.flyingfish.Model;

public class UserScore {
    String Date, Time, Id, Name, Score, Level;

    public UserScore() {
    }

    public UserScore(String date, String time, String id, String name, String score, String level) {
        Date = date;
        Time = time;
        Id = id;
        Name = name;
        Score = score;
        Level = level;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
