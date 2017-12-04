package com.itraters.beatbox;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hasalem on 12/3/2017.
 */
public class SoundViewModelTest
{
    private BeatBox beatBox;
    private Sound sound;
    private SoundViewModel subject;
    @Before
    public void setUp() throws Exception
    {
        beatBox= mock(BeatBox.class);
        sound=new Sound("assetPath");
        subject=new SoundViewModel(beatBox);
        subject.setSound(sound);

    }
    @Test
    public  void testSoundNameAsTitle()
    {

        assertThat(subject.getTitle(),is(sound.getName()));
    }
    @Test
    public void testCallToBeatBoxPlay()
    {
        subject.onButtonClicked();
        verify(beatBox).play(sound);
    }

}