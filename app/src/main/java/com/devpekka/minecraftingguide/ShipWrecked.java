package com.devpekka.minecraftingguide;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ShipWrecked extends AppCompatActivity {
    ImageView imageViewWreckedShipp, glide1, glide2;
    ClipboardManager cM;
    ClipData cD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_wrecked);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setSubtitle("Shipwreck Seed");

        //Glide image Finder
        //
        imageViewWreckedShipp = findViewById(R.id.shipWreck);

        glide1 = findViewById(R.id.imageV1);
        glide2 = findViewById(R.id.imageV2);
        //
        //End of Glide Image Finder

        //Glide Image
        //
        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/corals-shipwreck-4.jpg")
                .into(imageViewWreckedShipp);
        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/corals-shipwreck-6.jpg")
                .into(glide1);
        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/corals-shipwreck-7.jpg")
                .into(glide2);
        //
        //End of Glide Image

        cM = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

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
        switch (item.getItemId()){
            case R.id.credit:
                final AlertDialog.Builder mBuilder =  new AlertDialog.Builder(ShipWrecked.this);
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

    public void copySeedShipWrecked(View view) {
        String text = "-1618472320";
        cD  = ClipData.newPlainText("text",text);
        cM.setPrimaryClip(cD);

        Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
    }
}
