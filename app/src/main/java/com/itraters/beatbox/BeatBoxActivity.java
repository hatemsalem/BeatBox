package com.itraters.beatbox;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity
{

    @Override
    protected Fragment createFragment()
    {
        return BeatBoxFragment.newInstance();
    }
}
