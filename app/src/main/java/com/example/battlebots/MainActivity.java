package com.example.battlebots;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    ItemViewModel viewModel;        //viewmodel to hold data from dialog to main
    Thread myNet;       //thread for network things

    Integer x;      //current values of th bot
    Integer y;
    Integer MoveCount;
    Integer ShotCount;
    Integer HP;

    Integer PID;                //setup values, player ID, etc.
    Integer ArenaWidth;
    Integer ArenaHeight;
    Integer Team;

    Integer BulletDistance;
    Integer MyRed;      //for own player colour.
    Integer MyGreen;
    Integer MyBlue;

    TextView DisplayServerMessages;     //all elements from UI
    Button Scan;
    TextView PlayerColour;
    Button Zero;
    Button FortyFive;
    Button Ninety;
    Button OneHundredThirtyFive;
    Button OneHundredEighty;
    Button TwoHundredTwentyFive;
    Button TwoHundredSeventy;
    Button ThreeHundredFifteen;
    Button UpLeft;
    Button Up;
    Button UpRight;
    Button Left;
    Button Nothing;
    Button Right;
    Button DownLeft;
    Button Down;
    Button DownRight;

    InetAddress serverAddr;         //network stuff, needs adjusting
    Socket socket;
    Integer myport = 3012;
    String myhostname = "10.216.217.131";
    public PrintWriter out;
    public BufferedReader in;

    Boolean NothingPressed = false;
    Boolean ScanPressed = false;
    Boolean ZeroPressed = false;
    Boolean FortyFivePressed = false;
    Boolean NinetyPressed = false;
    Boolean OneHundredThirtyFivePressed = false;
    Boolean OneHundredEightyPressed = false;
    Boolean TwoHundredTwentyFivePressed = false;
    Boolean TwoHundredSeventyPressed = false;
    Boolean ThreeHundredFifteenPressed = false;

    Boolean UpLeftPressed = false;
    Boolean UpPressed = false;
    Boolean UpRightPressed = false;
    Boolean LeftPressed = false;
    Boolean RightPressed = false;
    Boolean DownLeftPressed = false;
    Boolean DownPressed = false;
    Boolean DownRightPressed = false;
    Integer LastPressed = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);     //creates viewmodel

        DialogBox dialogbox = new DialogBox();      //creates dialog box to get initial values
        dialogbox.show(getSupportFragmentManager(), "dialogbox");

        DisplayServerMessages = findViewById(R.id.DisplayServerMessages);           //links variables to their UI components
        DisplayServerMessages.setMovementMethod(new ScrollingMovementMethod());
        Scan = findViewById(R.id.Scan);
        PlayerColour = findViewById(R.id.PlayerColour);
        Zero = findViewById(R.id.Zero);
        FortyFive = findViewById(R.id.FortyFive);
        Ninety = findViewById(R.id.Ninety);
        OneHundredThirtyFive = findViewById(R.id.OneHundredThirtyFive);
        OneHundredEighty = findViewById(R.id.OneHundredEighty);
        TwoHundredTwentyFive = findViewById(R.id.TwoHundredTwentyFive);
        TwoHundredSeventy = findViewById(R.id.TwoHundredSeventy);
        ThreeHundredFifteen = findViewById(R.id.ThreeHundredFifteen);

        UpLeft = findViewById(R.id.UpLeft);
        Up = findViewById(R.id.Up);
        UpRight = findViewById(R.id.UpRight);
        Left = findViewById(R.id.Left);
        Nothing = findViewById(R.id.Nothing);
        Right = findViewById(R.id.Right);
        DownLeft = findViewById(R.id.DownLeft);
        Down = findViewById(R.id.Down);
        DownRight = findViewById(R.id.DownRight);

        MyNetwork battlebotsclient = new MyNetwork();           //sets up network thread
        myNet = new Thread(battlebotsclient);
        myNet.start();

        Nothing.setOnClickListener(new View.OnClickListener() {     //listeners for movement buttons
            @Override
            public void onClick(View view) { NothingPressed = true; }
        });

        UpLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpLeftPressed = true;
            }
        });

        Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpPressed = true;
            }
        });

        UpRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpRightPressed = true;
            }
        });

        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeftPressed = true;
            }
        });

        Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightPressed = true;
            }
        });

        DownLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownLeftPressed = true;
            }
        });

        Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownPressed = true;
            }
        });

        DownRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownRightPressed = true;
            }
        });

        Zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZeroPressed = true;
            }
        });

        FortyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { FortyFivePressed = true; }
        });

        Ninety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NinetyPressed = true;
            }
        });

        OneHundredThirtyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneHundredThirtyFivePressed = true;
            }
        });

        OneHundredEighty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneHundredEightyPressed = true;
            }
        });

        TwoHundredTwentyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwoHundredTwentyFivePressed = true;
            }
        });

        TwoHundredSeventy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwoHundredSeventyPressed = true;
            }
        });

        ThreeHundredFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {ThreeHundredFifteenPressed = true; }
        });

        Scan.setOnClickListener(new View.OnClickListener() {        //listener for scan button
            @Override
            public void onClick(View view) {
                ScanPressed = true;
            }
        });

    }

    private Handler handler = new Handler(new Handler.Callback() {      //puts messages on screen
        @Override
        public boolean handleMessage(Message msg) {
            DisplayServerMessages.append(msg.getData().getString("msg"));
            return true;
        }

    });

    public void mkmsg(String str) {         //gets messages from thread
        //handler junk, because thread can't update screen!
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("msg", str);
        msg.setData(b);
        handler.sendMessage(msg);
    }

    //ai to move to given coordinates as fast as possible?

    class MyNetwork implements Runnable {
        public Boolean loop = true;

        public void run() {

            while (viewModel.getName() == null){        //loops until dialogue box is completed
                String a;
            }

            mkmsg("host is " + myhostname + "\n");
            try {       //connects to server and setup read and write
                InetAddress serverAddr = InetAddress.getByName(myhostname);
                Socket socket = new Socket(serverAddr, myport);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                //setup bot information with server
                String message = in.readLine();
                String Chop[] = message.split(" ");
                PID = Integer.parseInt(Chop[1]);
                ArenaWidth = Integer.parseInt(Chop[2]);
                ArenaHeight = Integer.parseInt(Chop[3]);
                Team = Integer.parseInt(Chop[5]);

                mkmsg("You are on team " + Team + ", and you are player " + PID + " \n");
                out.println( viewModel.getName() + " " + viewModel.getArmor().toString() + " " + viewModel.getPower().toString() + " " + viewModel.getScan().toString());

                message = in.readLine();
                String Split[] = message.split(" ");
                BulletDistance = Integer.parseInt(Split[6]);
                MyRed = Integer.parseInt(Split[7]);
                MyGreen = Integer.parseInt(Split[8]);
                MyBlue = Integer.parseInt(Split[9]);
                String Red = String.format("%02X", MyRed);
                String Green = String.format("%02X", MyGreen);
                String Blue = String.format("%02X", MyBlue);

                PlayerColour.setBackgroundColor(Color.parseColor("#" + Red + Green + Blue));

            } catch (Exception e){
                mkmsg("Error with connection \n");

            }

            try{
                while (loop == true){
                    if (in.ready() == true) {
                        String message = in.readLine();   //reads messages
                        String Chop[] = message.split(" ");     //splits message up into words
                        if (Chop[0].equals("Info") && Chop[1].equals("PowerUp")){     //for powerups
                            if (Chop[2].equals("ArmorUp")){
                                mkmsg(message + "\n");
                            } else if (Chop[2].equals("MoveFaster")){
                                mkmsg(message + "\n");
                            } else if (Chop[2].equals("FireFaster")){
                                mkmsg(message + "\n");
                            } else if (Chop[2].equals("FireUp")){
                                mkmsg(message + "\n");
                            } else if (Chop[2].equals("FireMoveFaster")){
                                mkmsg(message + "\n");
                            } else if (Chop[2].equals("Teleport")){
                                mkmsg(message + "\n");
                            }
                        } else if (Chop[0].equals("Info")) {     //for info messages
                            if (Chop[1] == "hit"){
                                mkmsg("You've been hit by player " + Chop[3] + "\n");
                            } else if (Chop[1].equals("BadCmd")){
                                mkmsg("Command was not accepted\n");
                            } else if (Chop[1].equals("Dead")){
                                mkmsg("You died :(\n");
                                in.close();
                                out.close();
                                socket.close();
                            } else if (Chop[1].equals("GameOver")){
                                mkmsg("You Won! :)\n");
                                in.close();
                                out.close();
                                socket.close();
                            } else if (Chop[1].equals("Alive")){
                                mkmsg("A bot is dead, " + Chop[2] + " players left\n");
                            }
                        } else {        //for status messages by server
                            x = Integer.parseInt(Chop[1]);
                            y = Integer.parseInt(Chop[2]);
                            MoveCount = Integer.parseInt(Chop[3]);
                            ShotCount = Integer.parseInt(Chop[4]);
                            HP = Integer.parseInt(Chop[5]);

                            Zero.setClickable(false);
                            FortyFive.setClickable(false);
                            Ninety.setClickable(false);
                            OneHundredThirtyFive.setClickable(false);
                            OneHundredEighty.setClickable(false);
                            TwoHundredTwentyFive.setClickable(false);
                            TwoHundredSeventy.setClickable(false);
                            ThreeHundredFifteen.setClickable(false);

                            Right.setClickable(false);
                            DownRight.setClickable(false);
                            Down.setClickable(false);
                            DownLeft.setClickable(false);
                            Left.setClickable(false);
                            UpLeft.setClickable(false);
                            Up.setClickable(false);
                            UpRight.setClickable(false);

                            //prevents collisions with walls by only enabling buttons that don't move to wall and only if MoveCount = 0
                            if (x == 0 && y == 0 && MoveCount == 0){      //top left
                                Right.setClickable(true);
                                DownRight.setClickable(true);
                                Down.setClickable(true);
                            } else if (x == ArenaWidth -10 && y == 0 && MoveCount == 0){      //top right
                                Left.setClickable(true);
                                DownLeft.setClickable(true);
                                Down.setClickable(true);
                            } else if (x == ArenaWidth -10 && y == ArenaHeight -10 && MoveCount == 0){   //bottom right
                                Left.setClickable(true);
                                UpLeft.setClickable(true);
                                Up.setClickable(true);
                            } else if (x == 0 && y == ArenaHeight -10 && MoveCount == 0){      //bottom left
                                Right.setClickable(true);
                                UpRight.setClickable(true);
                                Up.setClickable(true);
                            } else if (y == 0 && MoveCount == 0){         //top wall of grid
                                Right.setClickable(true);
                                DownRight.setClickable(true);
                                Down.setClickable(true);
                                DownLeft.setClickable(true);
                                Left.setClickable(true);
                            } else if (x == 0 && MoveCount == 0){         //left wall of grid
                                Up.setClickable(true);
                                UpRight.setClickable(true);
                                Right.setClickable(true);
                                DownRight.setClickable(true);
                                Down.setClickable(true);
                            } else if (x == ArenaWidth-10 && MoveCount == 0){         //right wall of grid
                                Up.setClickable(true);
                                UpLeft.setClickable(true);
                                Left.setClickable(true);
                                DownLeft.setClickable(true);
                                Down.setClickable(true);
                            } else if (y == ArenaHeight-10 && MoveCount == 0){         //bottom wall of grid
                                Right.setClickable(true);
                                UpRight.setClickable(true);
                                Up.setClickable(true);
                                UpLeft.setClickable(true);
                                Left.setClickable(true);
                            } else if (MoveCount == 0){         //anywhere else in the grid
                                Right.setClickable(true);
                                DownRight.setClickable(true);
                                Down.setClickable(true);
                                DownLeft.setClickable(true);
                                Left.setClickable(true);
                                UpLeft.setClickable(true);
                                Up.setClickable(true);
                                UpRight.setClickable(true);
                            }

                            if (ShotCount == 0){        //enables fire buttons is shotcount is 0
                                Zero.setClickable(true);
                                FortyFive.setClickable(true);
                                Ninety.setClickable(true);
                                OneHundredThirtyFive.setClickable(true);
                                OneHundredEighty.setClickable(true);
                                TwoHundredTwentyFive.setClickable(true);
                                TwoHundredSeventy.setClickable(true);
                                ThreeHundredFifteen.setClickable(true);
                            } else{
                                Zero.setClickable(false);
                                FortyFive.setClickable(false);
                                Ninety.setClickable(false);
                                OneHundredThirtyFive.setClickable(false);
                                OneHundredEighty.setClickable(false);
                                TwoHundredTwentyFive.setClickable(false);
                                TwoHundredSeventy.setClickable(false);
                                ThreeHundredFifteen.setClickable(false);
                            }

                            Scan.setClickable(true);          //enables scan and nothing buttons
                            Nothing.setClickable(true);
                        }


                        mkmsg("Move in " + MoveCount + ". Shoot in " + ShotCount + ". HP is " + HP + "\n");
                    } else {

                    }

                    if (NothingPressed == true){
                        try {
                            //send server nothing message
                            out.println("noop");
                            mkmsg("Nothing\n");

                        } catch (Exception e){
                            mkmsg("Error with nothing\n");
                        }
                        NothingPressed = false;
                    }

                    else if (ZeroPressed == true || FortyFivePressed == true || NinetyPressed == true || OneHundredThirtyFivePressed == true || OneHundredEightyPressed == true || TwoHundredTwentyFivePressed == true || TwoHundredSeventyPressed == true || ThreeHundredFifteenPressed == true){
                        try {
                            //send server fire message

                            if (ZeroPressed == true){
                                out.println("fire 0");
                                mkmsg("Fired shot\n");
                            } else if (FortyFivePressed == true) {
                                out.println("fire 45");
                                mkmsg("Fired shot\n");
                            } else if (NinetyPressed == true) {
                                out.println("fire 90");
                                mkmsg("Fired shot\n");
                            } else if (OneHundredThirtyFivePressed == true) {
                                out.println("fire 135");
                                mkmsg("Fired shot\n");
                            } else if (OneHundredEightyPressed == true) {
                                out.println("fire 180");
                                mkmsg("Fired shot\n");
                            } else if (TwoHundredTwentyFivePressed == true) {
                                out.println("fire 225");
                                mkmsg("Fired shot\n");
                            } else if (TwoHundredSeventyPressed == true) {
                                out.println("fire 270");
                                mkmsg("Fired shot\n");
                            } else if (ThreeHundredFifteenPressed == true) {
                                out.println("fire 315");
                                mkmsg("Fired shot\n");
                            }

                        } catch (Exception e){
                            mkmsg("Error with fire \n");
                        }
                        Zero.setClickable(false);
                        FortyFive.setClickable(false);
                        Ninety.setClickable(false);
                        OneHundredThirtyFive.setClickable(false);
                        OneHundredEighty.setClickable(false);
                        TwoHundredTwentyFive.setClickable(false);
                        TwoHundredSeventy.setClickable(false);
                        ThreeHundredFifteen.setClickable(false);

                        ZeroPressed = false;
                        FortyFivePressed = false;
                        NinetyPressed = false;
                        OneHundredThirtyFivePressed = false;
                        OneHundredEightyPressed = false;
                        TwoHundredTwentyFivePressed = false;
                        TwoHundredSeventyPressed = false;
                        ThreeHundredFifteenPressed = false;


                    }

                    else if (ScanPressed == true){
                        try {
                            //send server scan message
                            out.println("scan");
                            mkmsg("Scan Results:\n");
                            //read scan message
                            Boolean CheckDone = false;
                            Done:
                            if (CheckDone == false){
                                for (String line = in.readLine(); line != null; line = in.readLine()) {
                                    String Chop[] = line.split(" ");
                                    if (Chop[1].equals("bot")){      //prints out details of bot
                                        String botteam;
                                        if (Integer.parseInt(Chop[5]) == Team){
                                            botteam = "Friend";
                                        } else{
                                            botteam = "Enemy";
                                        }
                                        mkmsg("Bot position is " + Chop[3] + " " + Chop[4] + " " + botteam + "\n");
                                    } else if (Chop[1].equals("shot")){      //prints out details of shot
                                        String shotteam;
                                        if (Integer.parseInt(Chop[5]) == Team){
                                            shotteam = "Friend";
                                        } else{
                                            shotteam = "Enemy";
                                        }
                                        mkmsg("Shot position is " + Chop[3] + " " + Chop[4] + ". Power is " + Chop[2] + " " + shotteam + "\n");
                                    } else if (Chop[1].equals("powerup")){       //prints out powerup position and details
                                        mkmsg("Powerup position is " + Chop[1] + " " + Chop[2] + " " + Chop[0] + "\n");
                                    } else if (Chop[1].equals("done")){         //checks if scan is done
                                        CheckDone = true;
                                        break Done;
                                    }
                                }
                            }
                            mkmsg("scan done\n");
                        } catch (Exception e){
                            mkmsg("Error with scan \n");
                        }
                        ScanPressed = false;
                    }

                    else if (UpLeftPressed == true || UpPressed == true || UpRightPressed == true || LeftPressed == true || RightPressed == true || DownLeftPressed == true || DownPressed == true || DownRightPressed == true){
                        try {
                            //send server move message

                            for (int i = 0; i < 5; i++) {
                                if (UpLeftPressed == true){
                                    out.println("move -1 -1");
                                } else if (UpPressed == true) {
                                    out.println("move 0 -1");
                                } else if (UpRightPressed == true) {
                                    out.println("move 1 -1");
                                } else if (LeftPressed == true) {
                                    out.println("move -1 0");
                                } else if (RightPressed == true) {
                                    out.println("move 1 0");
                                } else if (DownLeftPressed == true) {
                                    out.println("move -1 1");
                                } else if (DownPressed == true) {
                                    out.println("move 0 1");
                                } else if (DownRightPressed == true) {
                                    out.println("move 1 1");
                                }
                            }

                            UpLeftPressed = false;
                            UpPressed = false;
                            UpRightPressed = false;
                            LeftPressed = false;
                            RightPressed = false;
                            DownLeftPressed = false;
                            DownPressed = false;
                            DownRightPressed = false;

                        } catch (Exception e){
                            mkmsg("Error with moving \n");
                        }

                    }

                }
            } catch(Exception e){
                //setup error messages
            }

        }


    }


}
