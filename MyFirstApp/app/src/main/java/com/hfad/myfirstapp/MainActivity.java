package com.hfad.myfirstapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {

  final static String RTSP_URL = "rtsp://192.168.0.5:8080/test.sdp";

  private MediaPlayer _mediaPlayer;
  private SurfaceHolder _surfaceHolder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set up a full-screen black window.
    Window window = getWindow();
    window.requestFeature(Window.FEATURE_NO_TITLE);
    super.onCreate(savedInstanceState);
    window.setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    window.setBackgroundDrawableResource(android.R.color.black);

    setContentView(R.layout.activity_main);

    // Configure the view that renders live video.
    SurfaceView surfaceView =
      (SurfaceView) findViewById(R.id.surfaceView);
    _surfaceHolder = surfaceView.getHolder();
    _surfaceHolder.addCallback(this);
    _surfaceHolder.setFixedSize(320, 240);
  }

  @Override
  public void surfaceChanged(
    SurfaceHolder sh, int f, int w, int h) {}

  @Override
  public void surfaceCreated(SurfaceHolder sh) {
    _mediaPlayer = new MediaPlayer();
    _mediaPlayer.setDisplay(_surfaceHolder);

    Context context = getApplicationContext();
    Uri source = Uri.parse(RTSP_URL);

    try {
      // Specify the IP camera's URL and auth headers.
      _mediaPlayer.setDataSource(context, source);

      // Begin the process of setting up a video stream.
      _mediaPlayer.setOnPreparedListener(this);
      _mediaPlayer.prepareAsync();
    }
    catch (Exception e) {}
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder sh) {
    _mediaPlayer.release();
  }

  @Override
  public void onPrepared(MediaPlayer mp) {
    _mediaPlayer.start();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
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
