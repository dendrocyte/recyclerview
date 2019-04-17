package com.example.luyiling.recyclerpractice.gridhor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luyiling.recyclerpractice.BuildConfig;
import com.example.luyiling.recyclerpractice.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*not completed*/
public class BlankFragment extends Fragment {
    private mAdapter adapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView title = view.findViewById(R.id.tVtitle);
        title.setText((BuildConfig.VERSION_NAME.split("-"))[1]);
        RecyclerView recyclerView = view.findViewById(R.id.cardRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL); //default: vertical
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(initData());
        recyclerView.setAdapter(adapter);

    }

    private List<String> initData(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }



    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<String> id;
        public mAdapter(List<String> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.carditem, viewGroup, false);
            view.getLayoutParams().height = viewGroup.getHeight() / 3; //dynamic modify the height per carditem
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.name.setText(id.get(position));
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView name;
            public Holder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tVitem);
            }
        }
    }

}
