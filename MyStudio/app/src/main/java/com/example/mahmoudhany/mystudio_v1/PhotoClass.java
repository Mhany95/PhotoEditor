package com.example.mahmoudhany.mystudio_v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


/**
 * Created by Mahmoud Hany on 16-May-16.
 */
public class PhotoClass {

        private Bitmap photo;
        private Bitmap thumbnail;
        private enum editCode{original,boost,sepia,negative,bleu,extBoost,intensify,BWcontrast,Light,contrast,Posterize,color_negative};
        private editCode code;
        private boolean rotatingOriginal;
        private int rotationCounter;
        private ImageView dumbView;

        //default constructor
        PhotoClass()
        {
            this.photo=null;
            this.thumbnail=null;
            this.code=null;
            this.rotatingOriginal =false;
            this.rotationCounter=0;
            this.dumbView=null;
        }

    //Initialize photo
        public void InitializePhoto(Bitmap bmp)
        {
            this.photo=bmp;
            //this.photo=Bitmap.createScaledBitmap(bmp,bmp.getWidth(),bmp.getHeight(),true);
            thumbnail();
        }


    //Make a thumbnail of photo
        private void thumbnail()
        {
            float aspectRatio=photo.getWidth()/photo.getHeight();
            int newHeight=400; //400 is the size of the imageview
            int newWidth=(int)aspectRatio*400;
            thumbnail=Bitmap.createScaledBitmap(photo,newWidth,newHeight,true);

        }

        //Get Thumbnail
        public Bitmap GetPhoto()
        {

            return thumbnail;
        }


        //List of effects

        public Bitmap Photo_Original(Bitmap bmp)
        {
            code=editCode.original;
            return bmp;
        }

        public Bitmap Photo_Negative(Bitmap bmp)
        {
            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.red(p);
                    int b = Color.red(p);
                    int alpha = Color.alpha(p);


                    int tr =255-r;
                    int tg =255-g;
                    int tb = 255-b;


                    tmp.setPixel(i, j, Color.argb(alpha, tr, tg, tb));
                }
            }
            code=editCode.negative;
            return tmp;
        }

        public Bitmap Photo_Bleu(Bitmap bmp)
        {
            double value=0.5;

            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.red(p);
                    int b = Color.red(p);
                    int alpha = Color.alpha(p);





                    int tb = (int)(b*(1+value));


                    if(tb > 255)
                        b = 255;


                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.bleu;
            return tmp;
        }

        public Bitmap Photo_ColorNegative(Bitmap bmp)
        {
            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.green(p);
                    int b = Color.blue(p);
                    int alpha = Color.alpha(p);

                    r = 100  +  r;
                    g = 100  + g;
                    b = 100  + b;
                    alpha = 100 + alpha;
                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.color_negative;
            return tmp;
        }

        public Bitmap Photo_EXT_boost(Bitmap bmp)
        {
            double value=0.2;

            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.red(p);
                    int b = Color.red(p);
                    int alpha = Color.alpha(p);




                    int tr = (int)(r*(1+value));
                    int tg = (int)(g*(1+value));
                    int tb = (int)(b*(1+value));

                    if(tr > 255)
                        r = 255;


                    if(tg > 255)
                        g = 255;


                    if(tb > 255)
                        b = 255;


                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.extBoost;
            return tmp;
        }

        public Bitmap Photo_Boost(Bitmap bmp)
        {
            double value=0.5;

            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.red(p);
                    int b = Color.red(p);
                    int alpha = Color.alpha(p);




                    int tr = (int)(r*(1+value));
                    int tg = (int)(g*(1+value));
                    int tb = (int)(b*(1+value));

                    if(tr > 255)
                        r = 255;


                    if(tg > 255)
                        g = 255;


                    if(tb > 255)
                        b = 255;


                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.boost;
            return tmp;
        }

        public Bitmap Photo_BWcontrast(Bitmap bmp)
        {
            double value=30;
            double contrast = Math.pow((100 + value) / 100, 2);

            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.red(p);
                    int b = Color.red(p);
                    int alpha = Color.alpha(p);




                    int tr = (int)(((((r / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                    int tg = (int)(((((g / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                    int tb = (int)(((((b / 255.0) - 0.5) * contrast) + 0.5) * 255.0);

                    if(tr > 255){
                        r = 255;
                    }else{
                        r = 0;
                    }

                    if(tg > 255){
                        g = 255;
                    }else{
                        g = 0;
                    }

                    if(tb > 255){
                        b = 255;
                    }else{
                        b = 0;
                    }

                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.BWcontrast;
            return tmp;
        }

        public Bitmap Photo_Contrast(Bitmap bmp)
        {
            double value=50;
            double contrast = Math.pow((100 + value) / 100, 2);

            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.green(p);
                    int b = Color.blue(p);
                    int alpha = Color.alpha(p);



                    int tr = (int)(((((r / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                    int tg = (int)(((((g / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                    int tb = (int)(((((b / 255.0) - 0.5) * contrast) + 0.5) * 255.0);

                    if(tr > 255){
                        r = 255;
                    }else{
                        r = 0;
                    }

                    if(tg > 255){
                        g = 255;
                    }else{
                        g = 0;
                    }

                    if(tb > 255){
                        b = 255;
                    }else{
                        b = 0;
                    }

                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.contrast;
            return tmp;
        }

        public Bitmap Photo_Sepia(Bitmap bmp)
        {
            Bitmap tmp= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

            for(int i=0; i<bmp.getWidth(); i++){
                for(int j=0; j<bmp.getHeight(); j++){
                    int p = bmp.getPixel(i, j);
                    int r = Color.red(p);
                    int g = Color.green(p);
                    int b = Color.blue(p);
                    int alpha = Color.alpha(p);

                    int tr = (int)(0.393*r + 0.769*g + 0.189*b);
                    int tg = (int)(0.349*r + 0.686*g + 0.168*b);
                    int tb = (int)(0.272*r + 0.534*g + 0.131*b);

                    if(tr > 255){
                        r = 255;
                    }else{
                        r = tr;
                    }
                    if(tg > 255){
                        g = 255;
                    }else{
                        g = tg;
                    }
                    if(tb > 255){
                        b = 255;
                    }else{
                        b = tb;
                    }

                    tmp.setPixel(i, j, Color.argb(alpha, r, g, b));
                }
            }
            code=editCode.sepia;
            return tmp;
        }

        public Bitmap Photo_Posterize(Bitmap bmp)
        {

            Bitmap tmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

            int bitOffset=64; //value to posterize
            int A, R, G, B;
            int pixel;

            for(int x = 0; x < bmp.getWidth(); ++x) {
                for(int y = 0; y < bmp.getHeight(); ++y) {
                    // get pixel color
                    pixel = bmp.getPixel(x, y);
                    A = Color.alpha(pixel);
                    R = Color.red(pixel);
                    G = Color.green(pixel);
                    B = Color.blue(pixel);

                    // round-off color offset
                    R = ((R + (bitOffset / 2)) - ((R + (bitOffset / 2)) % bitOffset) - 1);
                    if(R < 0) { R = 0; }
                    G = ((G + (bitOffset / 2)) - ((G + (bitOffset / 2)) % bitOffset) - 1);
                    if(G < 0) { G = 0; }
                    B = ((B + (bitOffset / 2)) - ((B + (bitOffset / 2)) % bitOffset) - 1);
                    if(B < 0) { B = 0; }

                    // set pixel color to output bitmap
                    tmp.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }

             code=editCode.Posterize;
             return tmp;
        }

        public Bitmap Photo_Intensify(Bitmap bmp)
        {
            Bitmap tmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
            double red=0.6, green=0.6, blue=0.6;

            // color information
            int A, R, G, B;
            int pixel;
            // constant value curve
            final int    MAX_SIZE = 256;
            final double MAX_VALUE_DBL = 255.0;
            final int    MAX_VALUE_INT = 255;
            final double REVERSE = 1.0;

            // gamma arrays
            int[] gammaR = new int[MAX_SIZE];
            int[] gammaG = new int[MAX_SIZE];
            int[] gammaB = new int[MAX_SIZE];

            // setting values for every gamma channels
            for(int i = 0; i < MAX_SIZE; ++i) {
                gammaR[i] = (int)Math.min(MAX_VALUE_INT,
                        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
                gammaG[i] = (int)Math.min(MAX_VALUE_INT,
                        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
                gammaB[i] = (int)Math.min(MAX_VALUE_INT,
                        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
            }

            // apply gamma table
            for(int x = 0; x < bmp.getWidth(); ++x) {
                for(int y = 0; y < bmp.getHeight(); ++y) {
                    // get pixel color
                    pixel = bmp.getPixel(x, y);
                    A = Color.alpha(pixel);
                    // look up gamma
                    R = gammaR[Color.red(pixel)];
                    G = gammaG[Color.green(pixel)];
                    B = gammaB[Color.blue(pixel)];
                    // set new color to output bitmap
                    tmp.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }

            code=editCode.intensify;
            return tmp;
        }

        public Bitmap Photo_Light(Bitmap bmp)
        {
            Bitmap tmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
            double red=1.8, green=1.8, blue=1.8;

            // color information
            int A, R, G, B;
            int pixel;
            // constant value curve
            final int    MAX_SIZE = 256;
            final double MAX_VALUE_DBL = 255.0;
            final int    MAX_VALUE_INT = 255;
            final double REVERSE = 1.0;

            // gamma arrays
            int[] gammaR = new int[MAX_SIZE];
            int[] gammaG = new int[MAX_SIZE];
            int[] gammaB = new int[MAX_SIZE];

            // setting values for every gamma channels
            for(int i = 0; i < MAX_SIZE; ++i) {
                gammaR[i] = (int)Math.min(MAX_VALUE_INT,
                        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
                gammaG[i] = (int)Math.min(MAX_VALUE_INT,
                        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
                gammaB[i] = (int)Math.min(MAX_VALUE_INT,
                        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
            }

            // apply gamma table
            for(int x = 0; x < bmp.getWidth(); ++x) {
                for(int y = 0; y < bmp.getHeight(); ++y) {
                    // get pixel color
                    pixel = bmp.getPixel(x, y);
                    A = Color.alpha(pixel);
                    // look up gamma
                    R = gammaR[Color.red(pixel)];
                    G = gammaG[Color.green(pixel)];
                    B = gammaB[Color.blue(pixel)];
                    // set new color to output bitmap
                    tmp.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }

            code=editCode.Light;
            return tmp;
        }

        //saving photo, applying effects to the original photo, corresponding to the saved code
        public Bitmap SavePhoto()
        {
            //check rotation first
            rotatingOriginal=true;
            photo=Rotate();

            if(code==editCode.original)
            {
                //SavedPhoto=Photo_Original(photo);
                return Photo_Original(photo);
            }

            else if (code==editCode.negative)
            {
                //SavedPhoto= Photo_Negative(photo);
                return Photo_Negative(photo);
            }

            else if (code==editCode.color_negative)
            {
                //SavedPhoto= Photo_ColorNegative(photo);
                return Photo_ColorNegative(photo);
            }

            else if (code==editCode.sepia)
            {
                //SavedPhoto= Photo_Sepia(photo);
                return Photo_Sepia(photo);
            }

            else if (code==editCode.bleu)
            {
                //SavedPhoto= Photo_Bleu(photo);
                return Photo_Bleu(photo);
            }

            else if (code==editCode.boost)
            {
                //SavedPhoto= Photo_Boost(photo);
                return Photo_Boost(photo);
            }

            else if (code==editCode.BWcontrast)
            {
               // SavedPhoto= Photo_BWcontrast(photo);
                return Photo_BWcontrast(photo);
            }

            else if (code==editCode.contrast)
            {
                //SavedPhoto= Photo_Contrast(photo);
                return Photo_Contrast(photo);
            }

            else if (code==editCode.extBoost)
            {
                //SavedPhoto= Photo_EXT_boost(photo);
                return Photo_EXT_boost(photo);
            }
            else if (code==editCode.intensify)
            {
                return Photo_Intensify(photo);
            }
            else if (code==editCode.Light)
            {
                return Photo_Light(photo);
            }
            else if (code==editCode.Posterize)
            {
                return  Photo_Posterize(photo);
            }


            return photo;
        }

        //rotation used in GalleryActivity
        public Bitmap Rotate(Bitmap bmp)
        {
            Matrix matrix = new Matrix();
            matrix.setRotate(90f, 0f, 0f);


            if(!rotatingOriginal)
            {
                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                rotationCounter++;
                this.thumbnail=bmp;
                return bmp;
            }


            return photo;
        }

        //rotation used in saving
        private Bitmap Rotate()
        {

            Matrix matrix = new Matrix();
            matrix.setRotate(90f, 0f, 0f);

             if (rotatingOriginal && rotationCounter!=0)
            {
                //calculating number of rotations
                for (int i=1;i<=rotationCounter;i++)
                this.photo=Bitmap.createBitmap(this.photo,0,0,this.photo.getWidth(),this.photo.getHeight(),matrix,true);
                return photo;

            }


            return photo;
        }

        public Bitmap Share()
        {

            return SavePhoto();
        }

        public void CropCircle(Bitmap newBmp)
        {
            //first get the uri of the original photo, transform it and reinitialize the photo

            //Picasso.with(con).load(img).transform(new CropCircleTransformation()).into(dumbView);
            //this.photo=((BitmapDrawable)dumbView.getDrawable()).getBitmap();
            //this.photo=newBmp;

            //then change the thumbnail with the new cropped photo
            thumbnail();

        }

        public void clearMemory()
        {
            this.photo.recycle();
            this.photo=null;
            this.thumbnail.recycle();
            this.thumbnail=null;
        }

    }



