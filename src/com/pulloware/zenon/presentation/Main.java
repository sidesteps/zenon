package com.pulloware.zenon.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;
import com.pulloware.zenon.R;
import com.pulloware.zenon.domain.AlertInterval;
import com.pulloware.zenon.infrastructure.AlertService;

public class Main extends Activity implements SeekBar.OnSeekBarChangeListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SeekBar levelSlider = (SeekBar) findViewById(R.id.levelSlider);
//        levelSlider.setProgress(1);
        levelSlider.setOnSeekBarChangeListener(this);

    }

    private void startAlerts(int level)
    {
        try
        {
            AlertInterval aInterval = new AlertInterval(level);
            Intent startIntent = new Intent(this, AlertService.class)
                .putExtra(AlertService.PARAM_ALERTNESS_LEVEL, level);
            startService(startIntent);

        } catch (Exception t)
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
        startAlerts(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}