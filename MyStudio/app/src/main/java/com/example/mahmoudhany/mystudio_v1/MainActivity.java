package com.example.mahmoudhany.mystudio_v1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------about button---------------
    final Button btn_about= (Button) findViewById(R.id.btn_about);

    btn_about.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v)

        {//to navigate to the second activity
            Intent nav= new Intent(MainActivity.this,dumbAct.class);

            startActivity(nav);

        }
    });

        //-------------photo button-------------------
    final Button btn_photo=(Button)findViewById(R.id.btn_photo);

    btn_photo.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View V)
        {
            Intent photoMenu=new Intent(MainActivity.this,PhotoMenu.class);
            startActivity(photoMenu);

        }

         });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
