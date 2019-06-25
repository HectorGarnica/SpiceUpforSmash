package com.example.spiceupforsmash;


public class Player {




    String playerName;
    String playerQuirk;

    Player (String name, String quirk){
        playerName = name;
        playerQuirk = quirk;
    }

    public String getPlayerQuirk() {
        return playerQuirk;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerQuirk(String playerQuirk) {
        this.playerQuirk = playerQuirk;
    }


}
