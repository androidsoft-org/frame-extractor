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

package org.androidsoft.frameextractor.extractors;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;

import org.androidsoft.frameextractor.ExtractAsyncTask;
import org.androidsoft.frameextractor.Extractor;
import org.androidsoft.frameextractor.Settings;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * FFmpegExtractor
 * @author Pierre LEVY
 */

public class FFmpegExtractor implements Extractor
{
    @Override
    public void extractMpegFrames(String filePath, ExtractAsyncTask task, Settings settings) throws IOException
    {
        task.progressMessage("Extractor FFmpeg using FFmpegMediaMetadataRetriever()");
        FFmpegMediaMetadataRetriever med = new FFmpegMediaMetadataRetriever();
        med.setDataSource(filePath);
        for (int i = 0; i < settings.getFrameCount(); i++)
        {
            String filename = String.format("ffmpeg-frame-%02d%s", i, settings.getImageExtension());
            String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath));
            task.progressMessage("Writing file: " + filepath);
            long t1 = System.currentTimeMillis();
            Bitmap bmp = med.getFrameAtTime(i * 1000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
            if (settings.getImageFormat() == Settings.FORMAT_JPEG)
            {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            } else if (settings.getImageFormat() == Settings.FORMAT_PNG)
            {
                bmp.compress(Bitmap.CompressFormat.PNG, 90, bos);
            }

            bmp.recycle();
            long t2 = System.currentTimeMillis();
            task.progressMessage("Processing time: " + (t2 - t1) + " ms");
            // hack to see immediatly created files with MTP
            MediaScannerConnection.scanFile(settings.getContext(), new String[]{filepath}, null, null);
        }
    }
}
