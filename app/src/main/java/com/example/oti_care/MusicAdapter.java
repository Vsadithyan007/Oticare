package com.example.oti_care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    private final Context context;
    public List<MusicList> list;
    public int playingPosition = 0;
    public final SongChangeListener songChangeListener;

    public MusicAdapter(List<MusicList> list2, Context context2) {
        this.list = list2;
        this.context = context2;
        this.songChangeListener = (SongChangeListener) context2;
    }
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_adapt, (ViewGroup) null));
    }
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MusicList list2 = this.list.get(position);
        if (list2.isPlaying()) {
            this.playingPosition = position;
            holder.rootLayout.setBackgroundResource(R.drawable.round_rect_bl);
        } else {
            holder.rootLayout.setBackgroundResource(R.drawable.round_rect);
        }
        if (!list2.getDuration().equals("00:00")) {
            holder.musicDuration.setVisibility(0);
            holder.musicDuration.setText(String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Long.valueOf(TimeUnit.
                    MILLISECONDS.toMinutes(Long.parseLong(list2.getDuration()))), Long.valueOf(TimeUnit.MILLISECONDS.
                    toSeconds(Long.parseLong(list2.getDuration())) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                    toMinutes(Long.parseLong(list2.getDuration()))))}));
        } else {
            holder.musicDuration.setVisibility(0);
        }
        holder.title.setText(list2.getTitle());
        holder.artist.setText(list2.getArtist());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MusicList) MusicAdapter.this.list.get(MusicAdapter.this.playingPosition)).setPlaying(false);
                list2.setPlaying(true);
                MusicAdapter.this.songChangeListener.onChanged(position);
                MusicAdapter.this.notifyDataSetChanged();
            }
        });
    }
    public void updateList(List<MusicList> list2) {
        this.list = list2;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView artist;
        public final TextView musicDuration;
        public final RelativeLayout rootLayout;
        public final TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.rootLayout = (RelativeLayout) itemView.findViewById(R.id.rootLayout);
            this.title = (TextView) itemView.findViewById(R.id.mtitle);
            this.artist = (TextView) itemView.findViewById(R.id.martist);
            this.musicDuration = (TextView) itemView.findViewById(R.id.mdur);
        }
    }
}
