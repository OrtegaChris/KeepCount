package keepcount.boopboop.org.keepcount;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.Vector;


public class MainActivity extends ActionBarActivity {

    public int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final Deque<Integer> lastNumber = new ArrayDeque<Integer>();
        // creates a stack containing the prev values of count to undo errors

        Button ten = (Button) findViewById(R.id.plusten);
        Button five = (Button) findViewById(R.id.plusfive);
        Button one = (Button) findViewById(R.id.plusone);
        Button clr = (Button) findViewById(R.id.clr);
        Button undo = (Button) findViewById(R.id.undo);

        ToggleButton negate = (ToggleButton) findViewById(R.id.negateButton);

        final TextView ct = (TextView) findViewById(R.id.count);
        ct.setText(Integer.toString(count));
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 10;
                ct.setText(Integer.toString(count));

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

                Resources res = getResources();
                String msg = res.getString(R.string.emptystack);
                ct.setText(msg);




            }

        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!lastNumber.isEmpty()){
                    count = lastNumber.pop();


                ct.setText((Integer.toString(count)));
            }
                else
                {
                    Resources res = getResources();
                    String msg = res.getString(R.string.emptystack);
                    ct.setText(msg);
                    count = 0;
                }


            }

        });
    }

   /* @Override
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
