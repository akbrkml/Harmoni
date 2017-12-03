package com.harmoni.harmonikeluarga.ui.fragment.library;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.harmoni.harmonikeluarga.R;
import com.harmoni.harmonikeluarga.model.DataBookItem;
import com.harmoni.harmonikeluarga.model.DataJournalismItem;
import com.harmoni.harmonikeluarga.network.APIService;
import com.harmoni.harmonikeluarga.network.config.APIClient;
import com.harmoni.harmonikeluarga.network.config.APIInterfaces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBookFragment extends Fragment {

    @BindView(R.id.title)TextView mTextTitle;
    @BindView(R.id.penulis)TextView mTextPenulis;
    @BindView(R.id.penerbit)TextView mTextPenerbit;
    @BindView(R.id.tahun)TextView mTextTahun;
    @BindView(R.id.bahasa)TextView mTextBahasa;
    @BindView(R.id.halaman)TextView mTextHal;
    @BindView(R.id.isbn)TextView mTextIsbn;
    @BindView(R.id.category)TextView mTextCat;
    @BindView(R.id.desc)TextView mTextDesc;
    @BindView(R.id.img)ImageView mImageBook;

    private DataBookItem booksItem;


    public DetailBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_book, container, false);

        ButterKnife.bind(this, view);

        getArgument();

        return view;
    }

    private void getArgument() {
        String dataBook = getArguments().getString("data_book");
        if (dataBook != null) {
            booksItem = new Gson().fromJson(dataBook, DataBookItem.class);
        }
        bindData();
    }

    private void bindData(){
        mTextTitle.setText(booksItem.getBookTitle());
        mTextPenulis.setText(booksItem.getBookAuthor());
        mTextPenerbit.setText(booksItem.getBookPublisher());
        mTextTahun.setText(booksItem.getBookPublicationYear());
        mTextBahasa.setText(booksItem.getBookLanguage());
        mTextHal.setText(booksItem.getBookPages());
        mTextIsbn.setText(booksItem.getBookIsbn());
        mTextCat.setText(booksItem.getCatName());
        mTextDesc.setText(Html.fromHtml(booksItem.getBookDesc()));
        Glide.with(getActivity()).load(booksItem.getBookImage()).into(mImageBook);
    }

    @OnClick(R.id.download_full)
    public void downloadFull(){
        APIInterfaces apiInterface;

        apiInterface = APIClient.builder().create(APIInterfaces.class);

        apiInterface.downloadFile(booksItem.getBookFullFile()).enqueue(new Callback<ResponseBody>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("Download", "server contacted and has file");

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                            Log.d("Download", "file download was a success? " + writtenToDisk);
                            return null;
                        }
                    }.execute();
                }
                else {
                    Log.d("Download", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("Download", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    @OnClick(R.id.download_sample)
    public void downloadSample(){
        APIInterfaces apiInterface;

        apiInterface = APIClient.builder().create(APIInterfaces.class);

        apiInterface.downloadFile(booksItem.getBookSampleFile()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("Download", "server contacted and has file");

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                            Log.d("Download", "file download was a success? " + writtenToDisk);
                            return null;
                        }
                    }.execute();
                }
                else {
                    Log.d("Download", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
