package com.example.mahmoudhany.mystudio_v1;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class GalleryActivity extends ActionBarActivity {

    private Bitmap bitmap;         //used in getting bitmap from uri
    private Bitmap savedBitmap;    //used in saving edited photo
    private Bitmap sharedBitmap;
    private ImageView imageView;
    private PhotoClass p;
    private Intent retrieveIMG;
    private Uri retrieveURI;       //URI that holds the bitmap
    private Uri cropURI;

    //private EffectFactory posterize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //added class
        p=new PhotoClass();

        //get sent intent from PhotoMenu.java
        retrieveIMG=getIntent();


        //Get Captured photos by camera or gallery
        retrieveURI = Uri.parse(retrieveIMG.getStringExtra("captured"));
        cropURI=retrieveURI;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), retrieveURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView=(ImageView)findViewById(R.id.imgView);
        p.InitializePhoto(bitmap);
        imageView.setImageBitmap(p.GetPhoto());




              //code for listview of filters

          final ListView filters=(ListView)findViewById(R.id.effectsList);

          final ArrayAdapter<String> filtersAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

          filters.setAdapter(filtersAdapter);
      //-----------------------------------------------------------------------------------------


      //manually adding filters

          filtersAdapter.add("Original");
          filtersAdapter.add("Sepia");
          filtersAdapter.add("Colorful -ve");
          filtersAdapter.add("Colorful Contrast");
          filtersAdapter.add("Dark Contrast");
          filtersAdapter.add("Boost");
          filtersAdapter.add("EXT Boost");
          filtersAdapter.add("Bleu");
          filtersAdapter.add("Negative");
          filtersAdapter.add("Intensify");
          filtersAdapter.add("Light");
          filtersAdapter.add("Posterize");
      //setting clicking on items


          filters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                  if ( position==2) //colorful -ve
                  {

                      imageView.setImageBitmap(p.Photo_ColorNegative(p.GetPhoto()));
                  }

                  else if(position==1) //sepia filter
                  {

                      imageView.setImageBitmap(p.Photo_Sepia(p.GetPhoto()));
                  }

                  else if(position==0) //original
                  {
                      imageView.setImageBitmap(p.Photo_Original(p.GetPhoto()));
                  }

                  else if (position==3) //colorful contrast
                  {

                      imageView.setImageBitmap(p.Photo_Contrast(p.GetPhoto()));
                  }

                  else if (position==4) //black and white contrast
                  {

                      imageView.setImageBitmap(p.Photo_BWcontrast(p.GetPhoto()));
                  }

                  else if (position==5) //boost
                  {

                      imageView.setImageBitmap(p.Photo_Boost(p.GetPhoto()));
                  }

                  else if (position==6) //extreme boost
                  {

                      imageView.setImageBitmap(p.Photo_EXT_boost(p.GetPhoto()));
                  }
                  else if (position==7) //bleu
                  {

                      imageView.setImageBitmap(p.Photo_Bleu(p.GetPhoto()));
                  }

                  else if (position==8) //negative
                  {

                      imageView.setImageBitmap(p.Photo_Negative(p.GetPhoto()));

                  }

                  else if (position==9)
                  {
                      imageView.setImageBitmap(p.Photo_Intensify(p.GetPhoto()));
                  }

                  else if (position==10)
                  {
                      imageView.setImageBitmap(p.Photo_Light(p.GetPhoto()));
                  }

                  else if (position==11)
                  {
                      imageView.setImageBitmap(p.Photo_Posterize(p.GetPhoto()));
                  }

                   //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                  //added to free memory
               //   operation.recycle();
              }


          });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        menu.add("Rotate");//added for rotation
        menu.add("Crop Circle");
        menu.add("Save");
        menu.add("Share");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

         if(item.getTitle()=="Rotate") //added for rotation
         {
             imageView.setImageBitmap(p.Rotate(((BitmapDrawable)imageView.getDrawable()).getBitmap()));
         }

         else if (item.getTitle()=="Crop Circle")
         {
             Picasso.with(this).load(cropURI).transform(new CropCircleTransformation()).into(imageView);
             //p.CropCircle(((BitmapDrawable)imageView.getDrawable()).getBitmap());
             //p.clearMemory();
             //p.InitializePhoto(((BitmapDrawable)imageView.getDrawable()).getBitmap());
             //imageView.setImageBitmap(p.GetPhoto());

         }

         else if (item.getTitle()=="Share")
         {
             ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             //sharedBitmap=p.GetPhoto(); //to share thumbnail
             sharedBitmap =p.Share();     //to share original photo after editing

             sharedBitmap.compress(Bitmap.CompressFormat.JPEG, 85, bytes);
             String path = MediaStore.Images.Media.insertImage(getContentResolver(), sharedBitmap, "Edited by My Studio", null);
             Uri uri = Uri.parse(path);
             Intent intent = new Intent();
             intent.setAction(Intent.ACTION_SEND);
             intent.setType("image/*");
             intent.putExtra(Intent.EXTRA_STREAM,uri);
             startActivity(Intent.createChooser(intent, "Share via"));

         }

        else if(item.getTitle()=="Save") //done
        {
            //creating a folder for the app photos
            String folder_main = "MyStudio";

            File f = new File(Environment.getExternalStorageDirectory().getPath().toString()+"/Pictures", folder_main);
            if (!f.exists()) {
                f.mkdirs();
            }

            //saving photos
            String path = Environment.getExternalStorageDirectory().getPath().toString()+"/Pictures/MyStudio";
            OutputStream fOut = null;
            File file = new File(path, DateFormat.getDateTimeInstance().format(new Date()).toString()+".jpg"); // the File to save to
            try {
                fOut = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            savedBitmap =p.SavePhoto();
            savedBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //empty memory from saved bitmap
            if(savedBitmap !=null)
            {
                savedBitmap.recycle();
                savedBitmap =null;
            }
            Toast.makeText(this,"Done!",Toast.LENGTH_SHORT).show();


        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();



        //empty memory from bitmap
        if(bitmap!=null)
        {
            bitmap.recycle();
            bitmap=null;
        }

        if (sharedBitmap !=null)
        {
            sharedBitmap.recycle();
            sharedBitmap =null;
        }

        p.clearMemory();


    }
}
