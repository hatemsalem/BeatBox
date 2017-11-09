package com.itraters.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by hasalem on 11/9/2017.
 */

public class SoundViewModel extends BaseObservable
{
    private Sound sound;
    private BeatBox beatbox;

    public SoundViewModel(BeatBox beatbox)
    {
        this.beatbox = beatbox;
    }

    public Sound getSound()
    {
        return sound;
    }

    public void setSound(Sound sound)
    {
        this.sound = sound;
        notifyChange();
    }
    @Bindable
    public String getTitle()
    {
        return sound.getName();
    }
}
