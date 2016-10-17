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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * SettingsActivity
 */

public class SettingsActivity extends AppCompatActivity
{
    private RadioButton mRadioEGL;
    private RadioButton mRadioFFmpeg;
    private RadioButton mRadioFormatPNG;
    private RadioButton mRadioFormatJPEG;
    private EditText mEditCount;
    private EditText mEditWidth;
    private EditText mEditHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Settings settings = Application.loadSettings();
        mRadioEGL = (RadioButton) findViewById(R.id.radio_egl_extractor);
        mRadioEGL.setChecked(settings.getExtrator() == Settings.EXTRACTOR_EGL);
        mRadioFFmpeg = (RadioButton) findViewById(R.id.radio_ffmpeg_extractor);
        mRadioFFmpeg.setChecked(settings.getExtrator() == Settings.EXTRACTOR_FFMPEG);
        mRadioFormatPNG = (RadioButton) findViewById(R.id.radio_format_png);
        mRadioFormatPNG.setChecked(settings.getExtrator() == Settings.FORMAT_PNG);
        mRadioFormatJPEG = (RadioButton) findViewById(R.id.radio_format_jpeg);
        mRadioFormatJPEG.setChecked(settings.getExtrator() == Settings.FORMAT_JPEG);
        mEditCount = (EditText) findViewById( R.id.edit_count );
        mEditCount.setText(String.valueOf(settings.getFrameCount()));
        mEditHeight = (EditText) findViewById( R.id.edit_default_height );
        mEditHeight.setText( String.valueOf( settings.getDefaultHeight() ));
        mEditWidth = (EditText) findViewById( R.id.edit_default_width );
        mEditWidth.setText( String.valueOf( settings.getDefaultWidth() ));


        Button buttonOk = (Button) findViewById( R.id.button_ok );
        buttonOk.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveSettings();
                finish();
            }
        });
        Button buttonCancel = (Button) findViewById( R.id.button_cancel );
        buttonCancel.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    private void saveSettings()
    {
        Settings settings = new Settings();
        settings.setFrameCount(Integer.valueOf(mEditCount.getText().toString()));
        settings.setDefaultWidth( Integer.valueOf( mEditWidth.getText().toString() ) );
        settings.setDefaultHeight( Integer.valueOf( mEditHeight.getText().toString() ) );
        if( mRadioEGL.isChecked() )
        {
            settings.setExtrator(Settings.EXTRACTOR_EGL);
        }
        else if( mRadioFFmpeg.isChecked() )
        {
            settings.setExtrator(Settings.EXTRACTOR_FFMPEG);
        }

        if (mRadioFormatPNG.isChecked())
        {
            settings.setImageFormat(Settings.FORMAT_PNG);
        } else if (mRadioFormatJPEG.isChecked())
        {
            settings.setImageFormat(Settings.FORMAT_JPEG);
        }

        Application.saveSettings( settings );
    }

}
