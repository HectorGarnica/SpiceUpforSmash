package com.example.spiceupforsmash;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinny;
    private ListView playerListView;
    private PlayerAdapter pAdapter;
    ArrayList<Player> players =new ArrayList<>();



    /////////////////////////
    //onCreate MainActivity//
    ////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner 2-8 players
        spinny = findViewById(R.id.spinny);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinny.setAdapter(adapter);
        spinny.setOnItemSelectedListener(this);

        //listView of Players
        playerListView = (ListView) findViewById(R.id.PlayerListView);

        //Button to start Play Function
        Button play = (Button) findViewById(R.id.PlayButton);

        Log.i("hectoroni", "in button click");
        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Play();
            }
        });

    }



    /////////////////////////////////////////////////////////////
    ///////////////////Spinny's Listener////////////////////////
    ///////////////////////////////////////////////////////////
    int selectedNumber;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("hectoroni", "entered");
        selectedNumber = parent.getSelectedItemPosition()+2;
        Log.i("hectoroni", "item selected: " + selectedNumber);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedNumber = 2;

    }


    //////////////////
    //Play Function//
    ////////////////
    public void Play(){

        Log.i("hectoroni", "Entered play Function");
        String playersQuirks[] = getQuirks(selectedNumber);//asks for an array of x quirks

        players =new ArrayList<>();//refresh players

        //fills players with Player objects
        for (int position = 0 ; position < selectedNumber; position++){

            String name = "Player " + (position+1);
            String quirk = playersQuirks[position];
            players.add(new Player(name, quirk));

        }

        //converts players ArrayList into ListView
        pAdapter = new PlayerAdapter(this, players);
        playerListView.setAdapter(pAdapter);


        //TODO: list filled now shuffle list
        Log.i("hectoroni","finished making list");
    }


    /////////////
    //getQuirks//
    /////////////

    //returns string array of quirks depending on # of people playing
    private String[] getQuirks(int selectedNumber) {

        int playersWQuirks =0; //if no one gets, do again
        String playersQuirks[] = new String[selectedNumber]; //Returning this
        int stageSelect = 0; //limits stage select quirk to one person
        int numOfPlayerWCpu = selectedNumber;// only 8 persons&cpus can play at a time.


            //fills playersQuirks
            for (int i = 0; i < selectedNumber; i++) {
                //create random number
                int randomQuirk = new Random().nextInt(25 - 1) + 1; //1-26 = gets a quirk about a third of the time
                String quirk = "None";

                switch (randomQuirk) {
                    case 1:
                        quirk = "Get free hit at start of match";
                        playersQuirks[i] = quirk;
                        playersWQuirks++;
                        break;
                    case 2:
                        quirk = "Choose an opponent's character";
                        playersQuirks[i] = quirk;
                        playersWQuirks++;
                        break;

                    case 3:
                        quirk = "Make an opponent use different control setup";
                        playersQuirks[i] = quirk;
                        playersWQuirks++;
                        break;
                    case 4:
                        quirk = "Choose an item to turn on";
                        playersQuirks[i] = quirk;
                        playersWQuirks++;
                        break;
                    case 5:
                        if (numOfPlayerWCpu < 8) {
                            quirk = "Team up with cpu lvl: ";
                            int cpuLevel = new Random().nextInt(10 - 1) + 1; //1-9
                            quirk += cpuLevel;//concatenate string
                            numOfPlayerWCpu++;
                            playersWQuirks++;
                        }
                        playersQuirks[i] = quirk;
                        break;
                    case 6:
                        if (stageSelect == 0){
                            quirk = "Choose Stage regardless of turn";
                            playersWQuirks++;
                            stageSelect++;
                        }
                        playersQuirks[i] = quirk;
                        break;
                    case 7:
                        int chance = new Random().nextInt(3 - 1) + 1; //1-2
                        if (chance == 1){
                            quirk = "Random Warrior: You play as random character";
                            playersWQuirks++;
                        }

                        playersQuirks[i] = quirk;

                        break;
                    case 8:
                        quirk = "Set one opponent's starting handicap to 30%";
                        playersQuirks[i] = quirk;
                        playersWQuirks++;
                        break;
                    default:
                        playersQuirks[i] = quirk;
                        break;

                }
            }
        if(playersWQuirks == 0) //do again if no one got a quirk
            return getQuirks(selectedNumber);
        return playersQuirks;
    }


}
