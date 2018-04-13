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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DungeonBeneathSeed extends AppCompatActivity {
    ImageView imageThumbnail, glide1;
    ClipboardManager cM;
    ClipData cD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon_beneath_seed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cM = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        imageThumbnail = findViewById(R.id.dungeonThumbnail);
        glide1 = findViewById(R.id.dungeon1);

        Glide.with(this)
                .load("https://mcpehq.com/wp-content/uploads/2017/07/spawn-village-dungeon-beneath-well-1.jpeg")
                .into(imageThumbnail);
        Glide.with(this)
                .load("https://mcpehq.com/wp-content/uploads/2017/07/spawn-village-dungeon-beneath-well-1.jpeg")
                .into(glide1);

    }

    public void copyDungeonSeed(View view) {
        String text = "-1166728700";
        cD  = ClipData.newPlainText("text",text);
        cM.setPrimaryClip(cD);

        Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
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
                final AlertDialog.Builder mBuilder =  new AlertDialog.Builder(DungeonBeneathSeed.this);
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
