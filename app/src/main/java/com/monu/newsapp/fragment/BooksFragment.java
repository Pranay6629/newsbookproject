package com.monu.newsapp.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.monu.newsapp.Books_Client;
import com.monu.newsapp.R;
import com.monu.newsapp.adapter.Recycler_Adapter;
import com.monu.newsapp.parameter.Book;
import com.monu.newsapp.parameter.Books;
import com.monu.newsapp.parameter.ImageLinks;
import com.monu.newsapp.parameter.Item;
import com.monu.newsapp.parameter.RetailPrice;
import com.monu.newsapp.parameter.SaleInfo;
import com.monu.newsapp.parameter.VolumeInfo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private static final String TAG = "RecyclerViewFragment";
    private List<Book> bookList = new ArrayList<>();
    private Recycler_Adapter adapter;
    private View rootView;
    final String API_KEY = "eaf54c76cffa486b801fec2976a21a4e";

    public BooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BooksFragment newInstance(String param1, String param2) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_books, container, false);
        rootView.setTag(TAG);
        //setRecycler();
        String q= "cancer";
        recyclerView = (RecyclerView)rootView.findViewById(R.id.booksapp_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        networkcall(q, API_KEY);
        return rootView;

    }

   /* private void setRecycler() {
        recyclerView = (RecyclerView)rootView.findViewById(R.id.booksapp_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        networkcall();
    }*/

    private void networkcall(String q, String api_key) {
        String API_BASE_URL = "https://www.googleapis.com/books/v1/";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build())
                .build();

        Books_Client booksInterface = retrofit.create(Books_Client.class);

        Call<Books> call = booksInterface.getBooks(q, api_key);

        call.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                if (response.isSuccessful()){
                    List<Item> itemList = response.body().getItems();
                    for (Item item : itemList) {
                        VolumeInfo volumeInfo = item.getVolumeInfo();
                        SaleInfo salesinfo = item.getSaleInfo();
                        RetailPrice retail_price = salesinfo.getRetailPrice();
                        String title = volumeInfo.getTitle();
                        List<String> authList = volumeInfo.getAuthors();
                        String authors= "";Integer pages = 0;
                        if(authList.size() == 0)
                            authors = "by Unknown";
                        else
                            authors = "by "+authList.get(0);
                        ImageLinks links = volumeInfo.getImageLinks();

                        String imageUrl = links.getSmallThumbnail();
                        String preViewLink = volumeInfo.getPreviewLink();
                        double rating = 0,price= 0;
                        if(volumeInfo.getAverageRating() != null){
                            rating = volumeInfo.getAverageRating();
                        }else {
                            rating = 1;
                        }

                        if(retail_price.getAmount() != null){
                            price = retail_price.getAmount();
                        }else {
                            price = 0.00;
                        }
                        if(volumeInfo.getPageCount() != 0){
                            pages = volumeInfo.getPageCount();
                        }else {
                            pages = 0;
                        }
                        bookList.add(new Book(title, authors, imageUrl, rating, price, pages, preViewLink));
                    }
                    adapter = new Recycler_Adapter(getActivity(),bookList);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getContext(), response.body()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {

            }
        });

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}