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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidsoft.frameextractor.extractors.EGLExtractor;
import org.androidsoft.frameextractor.extractors.FFmpegExtractor;

/**
 * VideoListFragment
 * @author Pierre LEVY
 */

public class VideoListFragment extends Fragment
{
    private VideoListAdapter mAdapter;
    private ExtractEventListener mEventListener;

    public void setExtractEventListener( ExtractEventListener listener )
    {
        mEventListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_video_list, container, false);
        ListView mVideoListView = (ListView) fragmentView.findViewById(R.id.video_list);
        mAdapter = new VideoListAdapter(getActivity());
        mVideoListView.setAdapter(mAdapter);
        mVideoListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mAdapter.getItem(position);
                if( mEventListener != null )
                {
                    mEventListener.clearMessages();
                    String filename = mAdapter.getFilePath(position);
                    mEventListener.message( "Start extraction of : " + filename);
                    startExtraction( filename );
                }

            }
        });

        return fragmentView;
    }


    private void startExtraction( String filename )
    {
//        Extractor extractor = new EGLExtractor();
        Extractor extractor = new FFmpegExtractor();
        ExtractAsyncTask task = new ExtractAsyncTask( filename , extractor , mEventListener );
        task.doInBackground();
    }


}