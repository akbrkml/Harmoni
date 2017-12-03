package com.harmoni.harmonikeluarga.ui.fragment.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.harmoni.harmonikeluarga.ui.fragment.event.DetailEventFragment.eventItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventInformationFragment extends BaseFragment {

    @BindView(R.id.nameEvent)TextView mTextEvent;
    @BindView(R.id.dateTo)TextView mTextDateTo;
    @BindView(R.id.dateFrom)TextView mTextDateFrom;
    @BindView(R.id.descEvent)TextView mTextDesc;
    @BindView(R.id.labelText)TextView mTextLabelText;
    @BindView(R.id.labelImage)TextView mTextLabelImage;
    @BindView(R.id.labelAudio)TextView mTextLabelAudio;
    @BindView(R.id.labelVideo)TextView mTextLabelVideo;

    public EventInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_information, container, false);

        ButterKnife.bind(this, view);

        bindData();

        return view;
    }

    private void bindData(){
        mTextEvent.setText(eventItem.getEventName());
        mTextDateFrom.setText(eventItem.getEventStartDate());
        mTextDateTo.setText(eventItem.getEventEndDate());
        mTextDesc.setText(Html.fromHtml(eventItem.getEventDesc()));

        int size = eventItem.getEventType().size();
        String[] type = new String[size];
        for (int i=0; i < size; i++){
            type[i] = eventItem.getEventType().get(i);
        }

        if (Arrays.asList(type).contains("text")){
            mTextLabelText.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("image")){
            mTextLabelImage.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("audio")){
            mTextLabelAudio.setVisibility(View.VISIBLE);
        }
        if (Arrays.asList(type).contains("video")){
            mTextLabelVideo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
