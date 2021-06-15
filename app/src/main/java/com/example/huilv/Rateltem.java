package com.example.huilv;

public class Rateltem {
    private String curname;
    private String currate;
    public int id;

    public Rateltem(){
        super();
        curname="";
        currate="";
    }
    public Rateltem(String curname,String currare){
        super();
        this.currate=currate;
        this.curname=curname;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCurname() {
        return curname;
    }
    public void setCurname(String curname) {
        this.curname = curname;
    }
    public String getCurrate() {
        return currate;
    }
    public void setCurrate(String currare) {
        this.currate = currate;
    }
}
