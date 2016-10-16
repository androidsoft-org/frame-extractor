/* Copyright (c) 2016 Pierre LEVY androidsoft.org
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package org.androidsoft.frameextractor;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * VideoListAdapter
 */
class VideoListAdapter extends BaseAdapter
{
    private Context mContext;
    private Cursor mVideoCursor;

    VideoListAdapter(Context c)
    {
        mContext = c;
        initVideoList();
    }

    private void initVideoList()
    {
        String[] projection = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE};
        mVideoCursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
    }

    public int getCount()
    {
        return mVideoCursor.getCount();
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView tv = new TextView(mContext.getApplicationContext());
        if (convertView == null)
        {
            mVideoCursor.moveToPosition(position);
            int columnIndex = mVideoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            String text = mVideoCursor.getString(columnIndex);
            columnIndex = mVideoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
            mVideoCursor.moveToPosition(position);
            text += " - Size: " + mVideoCursor.getString(columnIndex) + " Bytes ";
            tv.setText(text);
            tv.setTextColor( Color.BLACK );
        }
        else
        {
            tv = (TextView) convertView;
        }
        return tv;
    }

    String getFilePath( int position)
    {
        int columnIndex = mVideoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        mVideoCursor.moveToPosition(position);
        return mVideoCursor.getString( columnIndex );
    }
}