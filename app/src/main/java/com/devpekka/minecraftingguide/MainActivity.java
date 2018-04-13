package com.devpekka.minecraftingguide;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewShipWrecked, imageViewHugeAtSpawn, imageViewThumbnail;

    private static final int MY_PERMISSION = 1;
    ProgressDialog mProgressDialog;

    double file_size = 0;
    String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Glide image finder
        //
        imageViewShipWrecked = findViewById(R.id.imageView);
        imageViewHugeAtSpawn = findViewById(R.id.imageViewP2);
        imageViewThumbnail = findViewById(R.id.imageViewP22);
        //
        // End of image Finder

        //Glide ImageView
        //
        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/corals-shipwreck-4.jpg")
                .into(imageViewShipWrecked);

        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/big-coral-3.jpg")
                .into(imageViewHugeAtSpawn);

        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2017/01/sspe-13-1-1.jpg")
                .into(imageViewThumbnail);
        //
        // End of Glide ImageView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mymenu = getMenuInflater();
        mymenu.inflate(R.menu.menudialog, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.credit:

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                CardView mCardView = mView.findViewById(R.id.cardViews);

                mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mBuilder != null) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mcpedl.com/"));
                            startActivity(browserIntent);
                        }
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Intent Method

    public void hugeBiome(View view) {
        Intent intentCoralBiome = new Intent(MainActivity.this, HugeCoralBiome.class);
        startActivity(intentCoralBiome);
    }

    public void shipWreckCoral(View view) {
        Intent shipWrecked = new Intent(MainActivity.this, DownloadFileTest.class);
        startActivity(shipWrecked);
    }

    public void sspeLightWeight(View view) {
        Intent shaderLightWeight = new Intent(MainActivity.this, SSPELightWeightShader.class);
        startActivity(shaderLightWeight);
    }
}
