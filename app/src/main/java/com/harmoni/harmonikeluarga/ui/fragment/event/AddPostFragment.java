package com.harmoni.harmonikeluarga.ui.fragment.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.harmoni.harmonikeluarga.ui.fragment.event.DetailEventFragment.eventItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends Fragment {

    @BindView(R.id.title)EditText mInputTitle;
    @BindView(R.id.desc)EditText mInputDesc;
    @BindView(R.id.bg_addimage)LinearLayout mLayoutImage;
    @BindView(R.id.bg_addaudio)LinearLayout mLayoutAudio;
    @BindView(R.id.bg_addvideo)LinearLayout mLayoutVideo;
    @BindView(R.id.imageName)TextView mImageName;
    @BindView(R.id.audioName)TextView mAudioName;
    @BindView(R.id.videoName)TextView mVideoName;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        ButterKnife.bind(this,view);

        initComponents();

        return view;
    }

    private void initComponents(){
        int size = eventItem.getEventType().size();
        String[] type = new String[size];
        for (int i = 0; i < size; i++){
            type[i] = eventItem.getEventType().get(i);
        }

        if (Arrays.asList(type).contains("image")){
            mLayoutImage.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("audio")){
            mLayoutAudio.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("video")){
            mLayoutVideo.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btAddImage)
    public void addImage(){

    }

    @OnClick(R.id.btAddAudio)
    public void addAudio(){

    }

    @OnClick(R.id.btAddVideo)
    public void addVideo(){

    }

}
