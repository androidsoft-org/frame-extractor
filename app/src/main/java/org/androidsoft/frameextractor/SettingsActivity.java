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
        mRadioEGL = (RadioButton) findViewById( R.id.button_egl_extractor );
        mRadioEGL.setChecked( settings.getExtrator() == Application.EXTRACTOR_EGL );
        mRadioFFmpeg = (RadioButton) findViewById( R.id.button_ffmpeg_extractor );
        mRadioFFmpeg.setChecked( settings.getExtrator() == Application.EXTRACTOR_FFMPEG );
        mEditCount = (EditText) findViewById( R.id.edit_count );
        mEditCount.setText( String.valueOf( settings.getImageCount() ));
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
        settings.setImageCount( Integer.valueOf( mEditCount.getText().toString() ) );
        settings.setDefaultWidth( Integer.valueOf( mEditWidth.getText().toString() ) );
        settings.setDefaultHeight( Integer.valueOf( mEditHeight.getText().toString() ) );
        if( mRadioEGL.isChecked() )
        {
            settings.setExtrator( Application.EXTRACTOR_EGL );
        }
        else if( mRadioFFmpeg.isChecked() )
        {
            settings.setExtrator( Application.EXTRACTOR_FFMPEG );
        }

        Application.saveSettings( settings );
    }

}
