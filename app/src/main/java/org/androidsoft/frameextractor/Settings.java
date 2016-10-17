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

/**
 * Settings
 */

public class Settings
{
    public static final int EXTRACTOR_EGL = 0;
    public static final int EXTRACTOR_FFMPEG = 1;

    public static final int FORMAT_PNG = 0;
    public static final int FORMAT_JPEG = 1;

    private int extrator;
    private int frameCount;
    private int defaultWidth;
    private int defaultHeight;
    private int imageFormat;
    private Context context;

    public int getExtrator()
    {
        return extrator;
    }

    public void setExtrator(int extrator)
    {
        this.extrator = extrator;
    }

    public int getFrameCount()
    {
        return frameCount;
    }

    public void setFrameCount(int frameCount)
    {
        this.frameCount = frameCount;
    }

    public int getDefaultWidth()
    {
        return defaultWidth;
    }

    public void setDefaultWidth(int defaultWidth)
    {
        this.defaultWidth = defaultWidth;
    }

    public int getDefaultHeight()
    {
        return defaultHeight;
    }

    public void setDefaultHeight(int defaultHeight)
    {
        this.defaultHeight = defaultHeight;
    }

    public int getImageFormat()
    {
        return imageFormat;
    }

    public void setImageFormat(int imageFormat)
    {
        this.imageFormat = imageFormat;
    }

    public String getImageExtension()
    {
        switch (imageFormat)
        {
            case FORMAT_JPEG:
                return ".jpeg";
            case FORMAT_PNG:
                return ".png";

        }
        return "";
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }
}
