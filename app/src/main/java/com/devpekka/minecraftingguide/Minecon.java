package com.devpekka.minecraftingguide;

import android.content.Intent;
import android.media.Image;
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

public class Minecon extends AppCompatActivity {
    ImageView mineconImage, mineconShowcase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mineconImage = findViewById(R.id.mineconImage);
        mineconShowcase = findViewById(R.id.mineconShowcase);

        Glide.with(this)
                .load("https://mcpehq.com/wp-content/uploads/2017/09/minecon-2017-minecon-earth-event.jpg")
                .into(mineconImage);
        Glide.with(this)
                .load("https://mcpehq.com/wp-content/uploads/2017/09/minecon-2017-event-768x450.jpg")
                .into(mineconShowcase);
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

                final AlertDialog.Builder mBuilder =  new AlertDialog.Builder(Minecon.this);
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
}
