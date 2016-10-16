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
import android.content.SharedPreferences;

/**
 * Application
 */

public class Application
{
    public static final int EXTRACTOR_EGL = 1;
    public static final int EXTRACTOR_FFMPEG = 2;

    private static final String PREFS_NAME = "frameextractor";
    private static final String PREF_EXTRACTOR = "extrator";
    private static final String PREF_IMAGE_COUNT = "image.count";
    private static final String PREF_DEFAULT_WIDTH = "default.width";
    private static final String PREF_DEFAULT_HEIGHT = "default.height";
    private static final int DEFAULT_EXTRACTOR = EXTRACTOR_EGL;
    private static final int DEFAULT_IMAGE_COUNT = 10;
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    private static Context mContext;

    public static void setContext( Context context )
    {
        mContext =context;
    }

    public static Settings loadSettings()
    {
        Settings settings = new Settings();
        settings.setExtrator( getPrefs().getInt( PREF_EXTRACTOR, DEFAULT_EXTRACTOR ));
        settings.setImageCount( getPrefs().getInt( PREF_IMAGE_COUNT, DEFAULT_IMAGE_COUNT ));
        settings.setDefaultWidth( getPrefs().getInt( PREF_DEFAULT_WIDTH, DEFAULT_WIDTH ));
        settings.setDefaultHeight( getPrefs().getInt( PREF_DEFAULT_HEIGHT, DEFAULT_HEIGHT ));

        return settings;
    }

    public static void saveSettings( Settings settings )
    {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt( PREF_EXTRACTOR , settings.getExtrator() );
        editor.putInt( PREF_IMAGE_COUNT , settings.getImageCount() );
        editor.putInt( PREF_DEFAULT_WIDTH , settings.getDefaultWidth() );
        editor.putInt( PREF_DEFAULT_HEIGHT , settings.getDefaultHeight() );
        editor.apply();
    }

    private static SharedPreferences getPrefs()
    {
        return mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
