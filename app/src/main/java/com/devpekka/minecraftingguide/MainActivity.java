package com.devpekka.minecraftingguide;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView imageVShipW, imageHugeCoral, imageDungeon, imageMinecon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Glide image finder
        //
        imageVShipW = findViewById(R.id.shipWreckImage);
        imageHugeCoral = findViewById(R.id.hugeCoralImage);
        imageDungeon = findViewById(R.id.dungeonImage);
        imageMinecon = findViewById(R.id.mineconImage);
        //
        // End of image Finder

        //Glide ImageView
        //

        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/corals-shipwreck-4.jpg")
                .into(imageVShipW);

        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/big-coral-3.jpg")
                .into(imageHugeCoral);

        Glide.with(this)
                .load("https://mcpehq.com/wp-content/uploads/2017/07/spawn-village-dungeon-beneath-well-1.jpeg")
                .into(imageDungeon);

        Glide.with(this)
                .load("https://mcpehq.com/wp-content/uploads/2017/09/minecon-2017-minecon-earth-event.jpg")
                .into(imageMinecon);
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
                TextView mVersionCodes = mView.findViewById(R.id.appversion);

                mVersionCodes.setText("v" + String.valueOf(BuildConfig.VERSION_NAME));

                mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mcpedl.com/"));
                        startActivity(browserIntent);
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
        Intent shipWrecked = new Intent(MainActivity.this, ShipWrecked.class);
        startActivity(shipWrecked);
    }

    public void dungeonBeneath(View view) {
        Intent dungeonWorld = new Intent(MainActivity.this, DungeonBeneathSeed.class);
        startActivity(dungeonWorld);
    }

    public void minecon(View view) {
        Intent minecon = new Intent(MainActivity.this, Minecon.class);
        startActivity(minecon);
    }
}
