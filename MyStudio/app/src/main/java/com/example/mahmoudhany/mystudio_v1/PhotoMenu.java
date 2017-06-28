package com.example.mahmoudhany.mystudio_v1;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.util.Date;


public class PhotoMenu extends ActionBarActivity {

    private File ImageFile; //used to save captured image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_menu);

        //--------------gallery button-------------------

        final Button gallery= (Button)findViewById(R.id.btn_gallery);
        gallery.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Photo"), 0);

            }


        });

        //--------------camera button-----------------------

        final Button cam=(Button)findViewById(R.id.btn_camera);
        cam.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                //to open camera
                Intent cam_intent = new Intent("android.media.action.IMAGE_CAPTURE");
                ImageFile= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),DateFormat.getDateTimeInstance().format(new Date()).toString()+".jpg");
                Uri temp= Uri.fromFile(ImageFile);
                cam_intent.putExtra(MediaStore.EXTRA_OUTPUT,temp);
                cam_intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);  //video quality works as well for capturing photos, 1 means high quality, 0 means low
                startActivityForResult(cam_intent,1);// 1 is the code I chose
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            //if it's from camera
            if (requestCode==1 && resultCode == RESULT_OK)
            {
                if(ImageFile.exists())
                {
                    Uri uri=Uri.parse("file://"+ImageFile.getAbsolutePath());
                    Intent navi= new Intent(getApplicationContext(),GalleryActivity.class);

                    navi.putExtra("source","cam");
                    navi.putExtra("captured",uri.toString());

                    startActivity(navi);
                }
            }
        else if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null)
            {
                Uri uri=data.getData();
                Intent nav= new Intent(getApplicationContext(),GalleryActivity.class);

                nav.putExtra("source","gal");
                nav.putExtra("captured",uri.toString());

                startActivity(nav);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_menu, menu);
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
