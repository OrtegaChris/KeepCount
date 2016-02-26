package keepcount.boopboop.org.keepcount;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.Vector;


public class MainActivity extends ActionBarActivity {

    private static final String MESSAGE = "MainActivity";
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView ct = (TextView) findViewById(R.id.count);
        final Deque<Integer> lastNumber = new ArrayDeque<Integer>();
        // creates a stack containing the prev values of count to undo errors

        Button ten = (Button) findViewById(R.id.plusten);
        Button five = (Button) findViewById(R.id.plusfive);
        Button one = (Button) findViewById(R.id.plusone); // one Button
        Button clr = (Button) findViewById(R.id.clr);
        Button undo = (Button) findViewById(R.id.undo);

        Button btnNScr = (Button) findViewById(R.id.ButtonNewScreen); //New Screen
        Button browserbtn = (Button) findViewById(R.id.BrowserButton);
        Button asyncbtn = (Button) findViewById(R.id.asyncButton);
        // TextView tv = (TextView) findViewById(R.id.tvMain);


        final ToggleButton negate = (ToggleButton) findViewById(R.id.negateButton);
        // add function to the negate button (ie switch the values added by each butt +1 --> -1)

        negate.setChecked(false);
//=============Class code=======================================================================================
        btnNScr.setOnClickListener(new View.OnClickListener() {  // Button for class
            @Override
            public void onClick(View v) {
                Log.i(MESSAGE, " It was clicked");
                Toast t = Toast.makeText(getApplicationContext(), "Thanks for clicking", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);


            }
        });
        browserbtn.setOnClickListener(new View.OnClickListener() {  // Button for class
            @Override
            public void onClick(View v) {
               // Log.i(MESSAGE, " It was clicked");
               // Toast t = Toast.makeText(getApplicationContext(), "Thanks for clicking", Toast.LENGTH_SHORT);
                //t.show();
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pace.edu"));
                startActivity(browse);


            }
        });
        asyncbtn.setOnClickListener(new View.OnClickListener() {  // Button for class
            @Override
            public void onClick(View v) {
                Log.i(MESSAGE, " Long Operation");
                imageDownloader img= new imageDownloader(MainActivity.this);
                img.execute("https://upload.wikimedia.org/wikipedia/commons/a/a8/Sydney_harbour_bridge_new_south_wales.jpg");


            }
        });
// ======================---Keep Count code ---========================================================================
        ct.setText(Integer.toString(count));
/*
        negate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                negate.toggle();
                // change text of button


            }
        });
*/
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 10;
                displayCount(count, ct);
                lastNumber.push(count);

            }

        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 5;
                ct.setText(Integer.toString(count));
                lastNumber.push(count);

            }

        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
                ct.setText(Integer.toString(count));
                lastNumber.push(count);

            }

        });
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNumber.push(count);
                count = 0;
                // function to completely clear by double tapping the clear button.

                Resources res = getResources();
                String msg = res.getString(R.string.emptystack);
                ct.setText(msg);


            }

        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!lastNumber.isEmpty()) {
                    count = lastNumber.pop();


                    ct.setText((Integer.toString(count)));
                } else {
                    Resources res = getResources();
                    String msg = res.getString(R.string.emptystack);
                    ct.setText(msg);
                    count = 0;
                }


            }

        });
    }

    public int displayCount(int count, TextView ct) {
        ct.setText((Integer.toString(count)));
        return 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast x = Toast.makeText(getApplicationContext(), "Settings Set", Toast.LENGTH_SHORT);
            x.show();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
