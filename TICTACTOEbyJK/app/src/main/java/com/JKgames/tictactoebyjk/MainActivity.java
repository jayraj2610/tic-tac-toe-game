package com.JKgames.tictactoebyjk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.JKgames.tictactoebyjk.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button[][]  Buttons= new Button[3][3];
    private boolean player1turn=true;
    private int roundcounts=0;
    private int player1points=0;
    private  int player2point=0;
    private TextView player1;
    private  TextView player2;
    private Button reset_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1=(TextView)findViewById(R.id.text_view_player1);
        player2=(TextView)findViewById(R.id.text_view_player2);
        reset_button=(Button)findViewById(R.id.button_reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();

            }
        });

        Buttons[0][0]=(Button)findViewById(R.id.button_00);
        Buttons[0][1]=(Button)findViewById(R.id.button_01);
        Buttons[0][2]=(Button)findViewById(R.id.button_02);
        Buttons[1][0]=(Button)findViewById(R.id.button_10);
        Buttons[1][1]=(Button)findViewById(R.id.button_11);
        Buttons[1][2]=(Button)findViewById(R.id.button_12);
        Buttons[2][0]=(Button)findViewById(R.id.button_20);
        Buttons[2][1]=(Button)findViewById(R.id.button_21);
        Buttons[2][2]=(Button)findViewById(R.id.button_22);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                Buttons[i][j].setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View v) {
        Button b=(Button)v;
        if(!b.getText().toString().equals(""))
        {
            return;

        }

        if(player1turn)
        { b.setText("X"); }

        else
        {
            b.setText("O");
        }

        roundcounts++;

        if(checkwin())
        {
            if(player1turn)
            {  player1wins();}
            else
            { player2wins();}
        }
        else
        {
            if(roundcounts==9)
            {
                draw();

            }
            else
            {
                player1turn=!player1turn;

            }

        }


    }

    private void draw() {
        Toast.makeText(this,"it's draw....",Toast.LENGTH_SHORT).show();
        updatepointsText();
        resetboard();




    }

    private void player2wins() {
        player2point++;
        Toast.makeText(this,"player 2 [O] wins ....",Toast.LENGTH_SHORT).show();
        updatepointsText();
        resetboard();



    }

    private void player1wins() {
        player1points++;
        Toast.makeText(this,"player 1 [X] wins ....",Toast.LENGTH_SHORT).show();
        updatepointsText();
        resetboard();


    }

    private  void updatepointsText()
    {
        player1.setText("player1[X] :"+player1points );
        player2.setText("player2[O] :"+player2point );

    }

    private  void resetboard()
    {
        player1turn=true;
        roundcounts=0;


        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)
            {
                Buttons[i][j].setText("" );

            }
        }
    }
    private void reset()
    {
        player2point=0;
        player1points=0;
        updatepointsText();
        resetboard();
    }






    private boolean checkwin()
    {
        String[][] field=new String[3][3];

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)

                if (Buttons[i][j].getText() != "") {

                    field[i][j] = Buttons[i][j].getText().toString();
                } else {
                    field[i][j] = " ";
                }

        }
        for(int  i=0;i<3;i++)
        {
            if (field[i][0].equals(field[i][1])  &&   field[i][0].equals(field[i][2])  && !field[i][0].equals(" ") )
            {
                return true;
            }
        }

        for(int i=0;i<3;i++)
        {
            if (field[0][i].equals(field[1][i])  &&   field[0][i].equals(field[2][i])  && !field[0][i].equals(" ") )
            {
                return true;
            }
        }



        if (field[0][0].equals(field[1][1])  &&   field[0][0].equals(field[2][2])  && !field[0][0].equals(" ") )
        {
            return true;
        }

        if (field[0][2].equals(field[1][1])  &&   field[0][2].equals(field[2][0])  && !field[0][2].equals(" ") )
        {
            return true;
        }

        return false;

    }




}
