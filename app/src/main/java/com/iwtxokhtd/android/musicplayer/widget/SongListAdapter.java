package com.iwtxokhtd.android.musicplayer.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.model.Song;

import java.util.List;

/**
 * Created by tujiyue on 27/6/17.
 */

public class SongListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Song> songList;


    public SongListAdapter(Context context, List<Song> songList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.song_list, null);
            viewHolder = new ViewHolder();
            viewHolder.avatarCircle = (AvatarCircle) convertView.findViewById(R.id.avatar_list_item);
            viewHolder.artist = (TextView) convertView.findViewById(R.id.tv_artist_list_item);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title_list_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (songList.get(position).getAvatar() != null) {
            viewHolder.avatarCircle.setImageBitmap(songList.get(position).getAvatar());
        }else {
            viewHolder.avatarCircle.setImageResource(R.mipmap.blueball_72px);
        }

        viewHolder.title.setText(songList.get(position).getTitle());
        viewHolder.artist.setText(songList.get(position).getArtist());
        return convertView;
    }

    class ViewHolder {
        AvatarCircle avatarCircle;
        TextView title;
        TextView artist;
    }
}
