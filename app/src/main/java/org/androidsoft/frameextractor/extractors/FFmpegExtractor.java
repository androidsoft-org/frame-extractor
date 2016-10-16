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
import android.os.Environment;

import org.androidsoft.frameextractor.ExtractEventListener;
import org.androidsoft.frameextractor.Extractor;

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
    public void extractMpegFrames(String filePath, ExtractEventListener listener) throws IOException
    {
        FFmpegMediaMetadataRetriever med = new FFmpegMediaMetadataRetriever();
        med.setDataSource(filePath);
        for (int i = 0; i < 10; i++)
        {
            String filename = Environment.getExternalStorageDirectory().getPath() + "/image" + i + ".png";
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream( filename ));
            Bitmap bmp = med.getFrameAtTime(i * 1000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, bos);
            bmp.recycle();
        }
    }
}
