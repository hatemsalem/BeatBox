package com.itraters.beatbox;

/**
 * Created by hasalem on 11/8/2017.
 */

public class Sound
{
    private String asseetPath;
    private String name;

    public Sound(String asseetPath)
    {
        this.asseetPath = asseetPath;
        String components[]=asseetPath.split("/");
        String fileName=components[components.length-1];
        name=fileName.replace(".wav","");
    }

    public String getAsseetPath()
    {
        return asseetPath;
    }

    public String getName()
    {
        return name;
    }
}
