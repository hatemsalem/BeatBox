package com.itraters.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
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
    private final static int MAX_SOUNDS=5;
    private List<Sound> sounds=new ArrayList<>();
    private AssetManager assetManager;
    private SoundPool soundPool;
    public BeatBox(Context ctx)
    {
        assetManager=ctx.getAssets();
        soundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
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
            try
            {
                Sound sound = new Sound(SOUND_FOLDER + "/" + fileName);
                load(sound);
                sounds.add(sound);
            }
            catch (IOException ioe)
            {
                Log.e(TAG,"Couldn't load sound "+fileName,ioe);
            }

        }
    }

    public List<Sound> getSounds()
    {
        return sounds;
    }
    private void load(Sound sound) throws IOException
    {
        AssetFileDescriptor afd=assetManager.openFd(sound.getAsseetPath());
        int soundId=soundPool.load(afd,1);
        sound.setSoundId(soundId);
    }
    public void play(Sound sound)
    {
        Integer soundId=sound.getSoundId();
        if(soundId==null)
        {
            return;
        }
        soundPool.play(soundId,1f,1f,1,0,1f);

    }
    public void release()
    {
        soundPool.release();
    }
}
