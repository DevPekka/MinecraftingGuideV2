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

public class HugeCoralBiome extends AppCompatActivity {
    ImageView imageViewHugeCoral, imageViewHugeCoral2, hugeThumbnail;
    ClipboardManager cMhugeCoralBiome;
    ClipData cDhugeCoralBiome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huge_coral_biome);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setSubtitle("Huge Coral Biome Seed");

        // Glide Image Finder
        //
        imageViewHugeCoral = findViewById(R.id.imageV1Coral);
        imageViewHugeCoral2 = findViewById(R.id.imageV2Coral);
        hugeThumbnail = findViewById(R.id.hugeCoralImage);
        //
        // End of Glide Image Finder


        //Glide Image
        //
        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/big-coral-3.jpg")
                .into(hugeThumbnail);

        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/big-coral-4.jpg")
                .into(imageViewHugeCoral);

        Glide.with(this)
                .load("http://mcpedl.com/wp-content/uploads/2018/04/big-coral-1.jpg")
                .into(imageViewHugeCoral2);
        //
        //End of Glide Image

        cMhugeCoralBiome = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
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

                final AlertDialog.Builder mBuilder =  new AlertDialog.Builder(HugeCoralBiome.this);
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

    public void copyHugeCoralBiome(View view) {
        String text = "-1231215285";
        cDhugeCoralBiome  = ClipData.newPlainText("text",text);
        cMhugeCoralBiome.setPrimaryClip(cDhugeCoralBiome);

        Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
    }
}
