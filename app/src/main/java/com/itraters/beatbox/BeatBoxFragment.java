package com.itraters.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.itraters.beatbox.databinding.FragmentBeatBoxBinding;
import com.itraters.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

/**
 * Created by hasalem on 11/8/2017.
 */

public class BeatBoxFragment extends Fragment
{
    private  BeatBox beatBox;
    public static BeatBoxFragment newInstance()
    {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        beatBox=new BeatBox(getActivity());
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(beatBox.getSounds()));
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
        return binding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder
    {
        ListItemSoundBinding binding;

        public SoundHolder(ListItemSoundBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            binding.setViewModel(new SoundViewModel(beatBox));
        }
        public void bind(Sound sound)
        {
            binding.getViewModel().setSound(sound);
            binding.executePendingBindings();
        }

    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>
    {
        private List<Sound>  sounds;

        public SoundAdapter(List<Sound> sounds)
        {
            this.sounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            ListItemSoundBinding binding=DataBindingUtil.inflate(LayoutInflater.from(getActivity()),R.layout.list_item_sound,parent,false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position)
        {
            holder.bind(sounds.get(position));
        }

        @Override
        public int getItemCount()
        {
            return sounds.size();
        }


    }

    @Override
    public void onDestroy()
    {
        beatBox.release();
        super.onDestroy();
    }
}
