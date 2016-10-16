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

import android.os.AsyncTask;

/**
 * Asynchronous task to extract frames
 * @author Pierre LEVY
 */

public class ExtractAsyncTask extends AsyncTask<Void, String, String>
{
    private String mFilename;
    private Extractor mExtractor;
    private ExtractEventListener mListener;
    private Settings mSettings;

    ExtractAsyncTask(String filename, Extractor extractor, ExtractEventListener listener, Settings settings)
    {
        mFilename = filename;
        mExtractor = extractor;
        mListener = listener;
        mSettings = settings;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        mListener.message( "Start extraction ..." );
    }

    @Override
    protected void onProgressUpdate(String... values)
    {
        super.onProgressUpdate(values);
        mListener.message(values[0]);

    }

    @Override
    protected String doInBackground(Void... arg0)
    {
        String ret = "Extraction completed successfully !";
        try
        {

            mExtractor.extractMpegFrames(mFilename, this, mSettings);
        } catch (Exception e)
        {
            ret = "Extraction aborted on error : " + e.getMessage();
            e.printStackTrace();

        }
        return ret;

    }

    @Override
    protected void onPostExecute(String result)
    {
        mListener.message( result );
    }

    public void progressMessage(String message)
    {
        publishProgress(message);
    }
}