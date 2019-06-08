package com.edu.parserTest.api;

public class CurrencyNotesVM {

    private int notesOf5;
    private int notesOf10;
    private int notesOf20;
    private int notesOf50;

    public CurrencyNotesVM(int notesOf5, int notesOf10, int notesOf20, int notesOf50) {
        this.notesOf5 = notesOf5;
        this.notesOf10 = notesOf10;
        this.notesOf20 = notesOf20;
        this.notesOf50 = notesOf50;
    }

    public int getNotesOf5() {
        return notesOf5;
    }

    public void setNotesOf5(int notesOf5) {
        this.notesOf5 = notesOf5;
    }

    public int getNotesOf10() {
        return notesOf10;
    }

    public void setNotesOf10(int notesOf10) {
        this.notesOf10 = notesOf10;
    }

    public int getNotesOf20() {
        return notesOf20;
    }

    public void setNotesOf20(int notesOf20) {
        this.notesOf20 = notesOf20;
    }

    public int getNotesOf50() {
        return notesOf50;
    }

    public void setNotesOf50(int notesOf50) {
        this.notesOf50 = notesOf50;
    }

}
