package amittpad.com.informationapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amittpad.com.informationapp.Adapters.BlogsAdapter;
import amittpad.com.informationapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Tab1 extends Fragment {

    View view;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    ArrayList<BlogData> blogDatas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blogs, container, false);
        ButterKnife.bind(this, view);
        insertData();
        return view;
    }

    private void insertData() {
        blogDatas = new ArrayList<>();
        blogDatas.add(new BlogData(R.drawable.icon,"thyroid.html", "Through the hormones it produces, the thyroid gland influences almost all of the metabolic processes in your body"));
        blogDatas.add(new BlogData(R.drawable.icon,"privacy.html", "Through the hormones it produces, the thyroid gland influences almost all of the metabolic processes in your body"));
        blogDatas.add(new BlogData(R.drawable.icon,"privacy.html", "Through the hormones it produces, the thyroid gland influences almost all of the metabolic processes in your body"));

        setData();

    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(linearLayoutManager);
        BlogsAdapter blogsAdapter = new BlogsAdapter(getActivity(),blogDatas);
        recyclerview.setAdapter(blogsAdapter);
    }

}
