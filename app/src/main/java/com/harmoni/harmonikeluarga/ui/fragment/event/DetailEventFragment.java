package com.harmoni.harmonikeluarga.ui.fragment.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataEventItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.model.User;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
import com.harmoni.harmonikeluarga.ui.fragment.profile.ProfileFragment;
import com.medialablk.easytoast.EasyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.harmoni.harmonikeluarga.util.Constant.getCustomerId;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEventFragment extends BaseFragment {

    @BindView(R.id.img)ImageView mImageView;
    @BindView(R.id.bt_informasi)Button mButtonInformasi;
    @BindView(R.id.bt_peserta)Button mButtonPeserta;
    @BindView(R.id.bt_juara)Button mButtonJuara;
    @BindView(R.id.bt_join)Button mButtonJoin;
    @BindView(R.id.v_juara)View mViewJuara;
    String idevent;

    private FragmentManager fm;
    public static DataEventItem eventItem;

    public DetailEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_event, container, false);
        ButterKnife.bind(this, view);
        addFragment();
        getArgument();
        if (!eventItem.isWasJoin()){
            mButtonJoin.setText("Join");
        } else {
            mButtonJoin.setText("Add Post");
        }
        return view;
    }

    private void getArgument() {
        String dataEvent = getArguments().getString("data_event");
        if (dataEvent != null) {
            eventItem = new Gson().fromJson(dataEvent, DataEventItem.class);
            idevent = eventItem.getEventId();
            Log.d("DataEventID",eventItem.getEventId());
        }
        Glide.with(getActivity()).load(eventItem.getEventImage()).into(mImageView);

        if (eventItem.getEventOpenStatus().equals("open") && !eventItem.isWasJoin()){
            mButtonJoin.setVisibility(View.VISIBLE);
        }

        if (eventItem.getEventOpenStatus().equals("open")){
            mButtonJuara.setVisibility(View.GONE);
            mViewJuara.setVisibility(View.GONE);
        }
    }

    private void addFragment(){
        fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new EventInformationFragment()).commit();
    }

    @OnClick(R.id.bt_informasi)
    public void goInformasi(){
        fm.beginTransaction().replace(R.id.container, new EventInformationFragment()).commit();
        mButtonInformasi.setTextColor(getResources().getColor(R.color.new_white));
        mButtonInformasi.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonJuara.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonJuara.setBackgroundColor(getResources().getColor(R.color.new_white));

        mButtonPeserta.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonPeserta.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    @OnClick(R.id.bt_juara)
    public void goJuara(){
        fm.beginTransaction().replace(R.id.container, new EventParticipantWinnerFragment()).commit();
        mButtonJuara.setTextColor(getResources().getColor(R.color.new_white));
        mButtonJuara.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonInformasi.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonInformasi.setBackgroundColor(getResources().getColor(R.color.new_white));

        mButtonPeserta.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonPeserta.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    @OnClick(R.id.bt_peserta)
    public void goPeserta(){
        fm.beginTransaction().replace(R.id.container, new EventParticipantFragment()).commit();
        mButtonPeserta.setTextColor(getResources().getColor(R.color.new_white));
        mButtonPeserta.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mButtonJuara.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonJuara.setBackgroundColor(getResources().getColor(R.color.new_white));

        mButtonInformasi.setTextColor(getResources().getColor(R.color.colorAccent));
        mButtonInformasi.setBackgroundColor(getResources().getColor(R.color.new_white));
    }

    @OnClick(R.id.bt_join)
    public void goJoin(){
        if (mButtonJoin.getText().equals("Add Post")){
            AddPostFragment newFragment = new AddPostFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id_event", idevent);
            newFragment.setArguments(bundle);
            fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();
        } else {
            APIService apiService = new APIService();
            apiService.addJoin("join_event", eventItem.getEventId(), getCustomerId(getActivity()), new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    User user = (User) response.body();
                    if (user != null){
                        if (user.isStatus()){
                            EasyToast.info(getActivity(), user.getText());
                            mButtonJoin.setText("Add Post");
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
