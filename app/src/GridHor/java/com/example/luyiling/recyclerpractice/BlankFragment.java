package com.example.luyiling.recyclerpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 1.the item display and arrangement direction
 * 2.add the divisor and item's margin modification
 * */
public class BlankFragment extends Fragment {
    private mAdapter adapter;
    private View view;
    private String TAG = BlankFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView textView = view.findViewById(R.id.tVtitle);
        textView.setText((BuildConfig.VERSION_NAME.split("-"))[1]);//get the suffix from version name
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),3,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        /* we can add different decoration at the same time*/
        //add divisor
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        //modify the margin
        recyclerView.addItemDecoration(new MarginDecoation(10));//customization
        recyclerView.setHasFixedSize(true);
        adapter = new mAdapter(initTitle());
        recyclerView.setAdapter(adapter);
    }

    private List<String> initTitle(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }

    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<String> id;
        public mAdapter(List<String> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            /* modify the height
            *
            * order: recyclerView.addItemDecoration >> view.getLayoutParams().height
            * view.getLayoutParams().height = (viewGroup.getHeight()) / 2;
            * */
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Log.e(TAG,"BindViewHolder" );
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
