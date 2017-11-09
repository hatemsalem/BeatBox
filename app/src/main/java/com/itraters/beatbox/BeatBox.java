package com.itraters.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasalem on 11/8/2017.
 */

public class BeatBox
{
    private final static String TAG="BeatBox";
    private final static String SOUND_FOLDER="sample_sounds";
    List<Sound> sounds=new ArrayList<>();
    AssetManager assetManager;
    public BeatBox(Context ctx)
    {
        assetManager=ctx.getAssets();
        loadSounds();
    }
    private void loadSounds()
    {
        String[] soundNames;
        try
        {
            soundNames=assetManager.list(SOUND_FOLDER);
            Log.i(TAG,"Found "+soundNames.length+" sounds");
        }
        catch (IOException ioe)
        {
            Log.e(TAG,"Could not list assests",ioe);
            return;
        }
        for (String fileName:soundNames)
        {
            sounds.add(new Sound(SOUND_FOLDER+"/"+fileName));
        }
    }

    public List<Sound> getSounds()
    {
        return sounds;
    }
}
