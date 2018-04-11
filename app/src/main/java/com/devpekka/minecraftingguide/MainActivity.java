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
    ImageView imageViewShipWrecked, imageViewHugeAtSpawn;

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
        Intent shipWrecked = new Intent(MainActivity.this, ShipWrecked.class);
        startActivity(shipWrecked);
    }

    public void downloadFile(View view) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION);
        }else{
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/MineCrafting Guide/");
            try{
                dir.mkdir();
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Cannot create folder!", Toast.LENGTH_SHORT).show();
            }

            //This is your file locationn
            new DownloadTask().execute("https://ryfol.weebly.com/uploads/4/7/6/5/47651895/sspe15_3.mcpack");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();

                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/MineCrafting Guide/");
                    try{
                        dir.mkdir();
                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(this, "Cannot create folder!", Toast.LENGTH_SHORT).show();
                    }

                    //This is your file locationn
                    new DownloadTask().execute("https://ryfol.weebly.com/uploads/4/7/6/5/47651895/sspe15_3.mcpack");

                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class DownloadTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            file_name = strings[0].substring(strings[0].lastIndexOf("/") + 1);
            try{
                InputStream inputStream = null;
                OutputStream outputStream = null;
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(strings[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                        return "Server returned HTTP" + connection.getResponseCode() + " "
                                +
                                connection.getResponseMessage();
                    }

                    int fileLength = connection.getContentLength();
                    file_size = fileLength;

                    inputStream = connection.getInputStream();
                    outputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/MineCrafting Guide/" + file_name);

                    byte data[] = new byte[4096];
                    long total = 0;
                    int count;
                    while ((count = inputStream.read(data)) != -1) {
                        if (isCancelled()){
                            return null;
                        }

                        total += count;
                        if (fileLength > 0){
                            publishProgress((int) (total * 100 / fileLength));
                        }
                        outputStream.write(data, 0, count);
                    }
                } catch (Exception e){
                    return e.toString();
                } finally {
                    try {
                        if (outputStream != null){
                            outputStream.close();
                        }
                        if (inputStream != null){
                            inputStream.close();
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }finally {

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Downloading...");
            mProgressDialog.setMessage("File size: 0 MB");
            mProgressDialog.setMessage("Please be patience while the file is dowloading...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(true);

            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Toast.makeText(MainActivity.this, "Download canclled!", Toast.LENGTH_SHORT).show();

                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/MineCrafting Guide/" + file_name);
                    try{
                        dir.delete();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(values[0]);
            mProgressDialog.setMessage("File size:" + new DecimalFormat("##.##").format(file_size / 1000000 + "MB"));

        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            mProgressDialog.dismiss();
            if (results != null){
                Toast.makeText(MainActivity.this, "Error!" + results, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Downloaded!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
