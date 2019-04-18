package com.example.luyiling.recyclerpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*1.item display direction
* 2.different blueprint .xml but difficult to modified the width of item
* even setSpanSizeLookup() or  view.getLayoutParams().height
* 3.more easily modified different blueprint UI
* 4.repackage params with view type to be identified to present the blueprint
*/
public class BlankFragment extends Fragment {
    private mAdapter adapter;
    private View view;
    int spancount = 3;
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

        TextView title = view.findViewById(R.id.tVtitle);
        title.setText((BuildConfig.VERSION_NAME.split("-"))[1]);
        initii();
    }

    private List<String> initTitle(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }

    private void init(){
        final List<Products.Product> products = new Products().initiData(initTitle());
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
       /* ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
        params.height=100;
        recyclerView.setLayoutParams(params);
        */

        GridLayoutManager manager = new GridLayoutManager(getContext(),spancount);

//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { //modify the width of Header
//            @Override
//            public int getSpanSize(int position) {
//                int span =((position == 0)? spancount : 1);//notice this logic
//                Log.e(TAG, "set span size; position:"+ position+"; span:"+span);
//                return span;
//            }
//        });
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(products);
        recyclerView.setAdapter(adapter);
    }
    private void initii(){
        final List<Products.Product> products = new Products().initiData(initTitle());
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
       /* ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
        params.height=100;
        recyclerView.setLayoutParams(params);
        */


        SpannedGridLayoutManager manager = new SpannedGridLayoutManager(
                new SpannedGridLayoutManager.GridSpanLookup() {
                    @Override
                    public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
                        Log.e(TAG,"here qq");
                        switch (position){
                            case 0://header
                                return new SpannedGridLayoutManager.SpanInfo(4, 1);
                            case 1:
                                return new SpannedGridLayoutManager.SpanInfo(2, 2);
                            case 2:
                                return new SpannedGridLayoutManager.SpanInfo(2, 1);
                            default:
                                return new SpannedGridLayoutManager.SpanInfo(1, 1);

                        }
                    }
                },
                4, // number of columns
                1f // how big is default item
        );
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(products);
        recyclerView.setAdapter(adapter);
    }


    //getItemViewType   》 onCreateViewHolder  》Holer 》onBindViewHolder
    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<Products.Product> id;
        public mAdapter(List<Products.Product> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            View view = (viewtype == 1) ?
                    getLayoutInflater().inflate(R.layout.title, viewGroup,false):
                    getLayoutInflater().inflate(R.layout.item, viewGroup, false);
//            view.getLayoutParams().height = (viewGroup.getHeight()) / 3;

            /* will be overlap
            view.getLayoutParams().width = (viewtype == 1) ? (viewGroup.getWidth())* spancount : viewGroup.getWidth();
            * */
            view.setTag(viewtype == 1);
            Log.e(TAG,"createViewholder");
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Log.e(TAG,"BindViewHolder");
            switch (holder.getItemViewType()){
                case 1:
                    holder.brand.setText(id.get(position).name);
                    break;
                case 2:
                    holder.name.setText(id.get(position).name);
                    break;
            }
        }


        @Override
        public int getItemViewType(int position) {
            Log.e(TAG,"getItemViewType" );
            return id.get(position).viewtype;
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView name, brand;
            public Holder(@NonNull View itemView) {
                super(itemView);
                Log.e(TAG,"Holder" );
                if ((boolean)itemView.getTag()) {
                    brand = itemView.findViewById(R.id.tVbrand);
                }else {
                    name = itemView.findViewById(R.id.tVitem);
                }
            }
        }
    }

}
