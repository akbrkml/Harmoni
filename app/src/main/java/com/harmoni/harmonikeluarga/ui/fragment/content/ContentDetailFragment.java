package com.harmoni.harmonikeluarga.ui.fragment.content;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.ContentChild;
import com.harmoni.harmonikeluarga.model.DataContentItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.model.DataTopicItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.ui.base.BaseFragment;
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
public class ContentDetailFragment extends BaseFragment {

    @BindView(R.id.img)ImageView mImageView;
    @BindView(R.id.title)TextView mTextTitle;
    @BindView(R.id.desc)TextView mTextDesc;
    @BindView(R.id.text)TextView mTextFav;
    @BindView(R.id.bgFavorit)RelativeLayout mBtnFavorite;

    @OnClick(R.id.bgFavorit)
    public void setFavorite(){
        addFavorite(getCustomerId(getActivity()), topicItem.getContentID());
    }

    private DataContentItem topicItem;

    public static ContentDetailFragment newInstance(){
        return new ContentDetailFragment();
    }

    public ContentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_detail, container, false);

        ButterKnife.bind(this, view);

        getArgument();

        return view;
    }

    private void getArgument() {
        String dataContent = getArguments().getString("data_content");
        if (dataContent != null) {
            topicItem = new Gson().fromJson(dataContent, DataContentItem.class);
        }
        bindData();
    }

    private void addFavorite(String customerId, String contentId){
        APIService apiService = new APIService();
        apiService.addFavorite("add_favorite", customerId, contentId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ContentChild pesan = (ContentChild) response.body();
                EasyToast.info(getActivity().getApplicationContext(), pesan.getText());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                EasyToast.error(getActivity().getApplicationContext(), "Gagal menambahkan favorite");
            }
        });
    }

    private void bindData() {
        Glide.with(getActivity()).load(topicItem.getContentImage()).into(mImageView);
        mTextTitle.setText(topicItem.getContentTitle());
        mTextDesc.setText(Html.fromHtml(topicItem.getContentDesc()));
        if (topicItem.isIsFavorite()){
            mTextFav.setTextColor(Color.WHITE);
            mTextFav.setBackgroundResource(R.color.colorPrimary);
        } else {
            mTextFav.setTextColor(R.drawable.bg_topic_text);
            mTextFav.setBackgroundResource(R.drawable.bt_favorit);
        }
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
