package com.example.oti_care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class audio_player extends AppCompatActivity implements SongChangeListener{
    private final List<MusicList> musicLists =new ArrayList<>();
    public int currentSongListPosition = 0;
    public TextView endTime;
    public boolean isPlaying = false;
    public MediaPlayer mediaPlayer;
    public MusicAdapter musicAdapter;
    public RecyclerView musicRecyclerView;
    public ImageView playPauseImg;
    public SeekBar playerSeekBar;
    public TextView startTime;
    public Timer timer;
    LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decodeView=getWindow().getDecorView();
        int options=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN;
        decodeView.setSystemUiVisibility(options);
        setContentView(R.layout.activity_audio_player);
        back=findViewById(R.id.backbtn);
        this.musicRecyclerView = (RecyclerView) findViewById(R.id.recycleMusicView);
        CardView playPauseCard = (CardView) findViewById(R.id.playpausecard);
        this.playPauseImg = (ImageView) findViewById(R.id.playpause);
        ImageView nextBtn = (ImageView) findViewById(R.id.nex);
        ImageView prevBtn = (ImageView) findViewById(R.id.pre);
        this.playerSeekBar = (SeekBar) findViewById(R.id.sbar);
        this.startTime = (TextView) findViewById(R.id.ctime);
        this.endTime = (TextView) findViewById(R.id.ttime);
        this.musicRecyclerView.setHasFixedSize(true);
        this.musicRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.mediaPlayer = new MediaPlayer();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(audio_player.this,home_page.class);
                startActivity(i);
            }
        });
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            getMusicFiles();
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 11);
        } else {
            getMusicFiles();
        }
        nextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int nextSongListPosition = audio_player.this.currentSongListPosition + 1;
                if (nextSongListPosition >= audio_player.this.musicLists.size()) {
                    nextSongListPosition = 0;
                }
                ((MusicList) audio_player.this.musicLists.get(audio_player.this.currentSongListPosition)).setPlaying(false);
                ((MusicList) audio_player.this.musicLists.get(nextSongListPosition)).setPlaying(true);
                audio_player.this.musicAdapter.updateList(audio_player.this.musicLists);
                audio_player.this.musicRecyclerView.scrollToPosition(nextSongListPosition);
                audio_player.this.onChanged(nextSongListPosition);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int prevSongListPosition = audio_player.this.currentSongListPosition - 1;
                if (prevSongListPosition < 0) {
                    prevSongListPosition = audio_player.this.musicLists.size() - 1;
                }
                ((MusicList) audio_player.this.musicLists.get(audio_player.this.currentSongListPosition)).setPlaying(false);
                ((MusicList) audio_player.this.musicLists.get(prevSongListPosition)).setPlaying(true);
                audio_player.this.musicAdapter.updateList(audio_player.this.musicLists);
                audio_player.this.musicRecyclerView.scrollToPosition(prevSongListPosition);
                audio_player.this.onChanged(prevSongListPosition);
            }
        });
        playPauseCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (audio_player.this.isPlaying) {
                    boolean unused = audio_player.this.isPlaying = false;
                    audio_player.this.mediaPlayer.pause();
                    audio_player.this.playPauseImg.setImageResource(R.drawable.ic_play);
                    return;
                }
                boolean unused2 = audio_player.this.isPlaying = true;
                audio_player.this.mediaPlayer.start();
                audio_player.this.playPauseImg.setImageResource(R.drawable.ic_pause);
            }
        });
        this.playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) {
                    return;
                }
                if (audio_player.this.isPlaying) {
                    audio_player.this.mediaPlayer.seekTo(progress);
                } else {
                    audio_player.this.mediaPlayer.seekTo(0);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void getMusicFiles() {
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                (String[]) null, "_data LIKE?", new String[]{"%.mp3%"}, (String) null);
        if (cursor == null) {
            Toast.makeText(this, "Something went wrong!!!", 0).show();
        } else if (!cursor.moveToNext()) {
            Toast.makeText(this, "No Music Found", 0).show();
        } else {
            while (cursor.moveToNext()) {
                String getMusicFileName = cursor.getString(cursor.getColumnIndex("title"));
                String getArtistName = cursor.getString(cursor.getColumnIndex("artist"));
                Uri musicFileUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, cursor.
                        getLong(cursor.getColumnIndex("_id")));
                String getDuration = "00:00";
                if (Build.VERSION.SDK_INT >= 29) {
                    getDuration = cursor.getString(cursor.getColumnIndex("duration"));
                }
                this.musicLists.add(new MusicList(getMusicFileName, getArtistName, getDuration, false, musicFileUri));
            }
            MusicAdapter musicAdapter2 = new MusicAdapter(this.musicLists, this);
            this.musicAdapter = musicAdapter2;
            this.musicRecyclerView.setAdapter(musicAdapter2);
        }
        cursor.close();
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length <= 0 || grantResults[0] != 0) {
            Toast.makeText(this, "Permissions Declined By User", 0).show();
        } else {
            getMusicFiles();}
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(6);
        }
    }

    public void onChanged(final int position) {
        this.currentSongListPosition = position;
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.setAudioStreamType(3);
        new Thread(new Runnable() {
            public void run() {
                try {
                    MediaPlayer access$500 = audio_player.this.mediaPlayer;
                    audio_player audioPlayer = audio_player.this;
                    access$500.setDataSource(audioPlayer, ((MusicList) audioPlayer.musicLists.get(position)).getMusicFile());
                    audio_player.this.mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(audio_player.this, "Unable to play track", 0).show();
                }
            }
        }).start();
        this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                int getTotalDuration = mp.getDuration();
                audio_player.this.endTime.setText(String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) getTotalDuration)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) getTotalDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) getTotalDuration)))}));
                boolean unused = audio_player.this.isPlaying = true;
                mp.start();
                audio_player.this.playerSeekBar.setMax(getTotalDuration);
                audio_player.this.playPauseImg.setImageResource(R.drawable.ic_pause);
            }
        });
        Timer timer2 = new Timer();
        this.timer = timer2;
        timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                audio_player.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int getCurrentDuration = audio_player.this.mediaPlayer.getCurrentPosition();
                        String generateDuration = String.format(Locale.getDefault(), "%02d:%02d",
                                new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) getCurrentDuration)),
                                Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) getCurrentDuration) - TimeUnit.MINUTES
                                .toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) getCurrentDuration)))});
                        audio_player.this.playerSeekBar.setProgress(getCurrentDuration);
                        audio_player.this.startTime.setText(generateDuration);
                    }
                });
            }
        }, 1000, 1000);
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                audio_player.this.mediaPlayer.reset();
                audio_player.this.timer.purge();
                audio_player.this.timer.cancel();
                boolean unused = audio_player.this.isPlaying = false;
                audio_player.this.playPauseImg.setImageResource(R.drawable.ic_play);
                audio_player.this.playerSeekBar.setProgress(0);
            }
        });
    }
}
