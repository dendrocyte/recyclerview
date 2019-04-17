package com.example.luyiling.recyclerpractice;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 1.swipe the carditem to storage or deletion */
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
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(initData());
        recyclerView.setAdapter(adapter);

    }

    //not limited to recyclerview
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return 0;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            // Row is swiped from recycler view
            // remove it from adapter
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            // Let's draw our delete view
        }
    });


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
