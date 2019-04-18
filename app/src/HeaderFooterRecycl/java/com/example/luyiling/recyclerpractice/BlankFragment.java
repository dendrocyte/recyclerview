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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 1.recyclerview with header and footer
 * 2.add the divisor and item's margin modification
 * */
public class BlankFragment extends Fragment {
    private HeaderFooterAdapter adapter;
    int spancount = 3;
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
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);

        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),spancount);

        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { //modify the width
            @Override
            public int getSpanSize(int position) {
                boolean isHeaderExist = setHeader(recyclerView) != null;
                boolean isFooterExist = setFooter(recyclerView) != null;
                int span = (position == 0 && isHeaderExist)
                        || (isFooterExist&& position == (initTitle().size()+1))
                        ? spancount : 1;
                Log.e(TAG, "set span size; position:"+ position+"; span:"+span);
                return span;
            }
        });
        //add header and footer
        adapter = new HeaderFooterAdapter(setHeader(recyclerView), initTitle(), setFooter(recyclerView));
        recyclerView.setAdapter(adapter);
    }


    /* set header */
    private View setHeader(RecyclerView recycler){
        return getLayoutInflater().inflate(R.layout.header, recycler, false);
    }

    /* set footer */
    private View setFooter(RecyclerView recycler){
        return getLayoutInflater().inflate(R.layout.footer, recycler, false);
    }

    private List<String> initTitle(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }

    class HeaderFooterAdapter extends RecyclerView.Adapter<HeaderFooterAdapter.Holder>{
        List<String> id;
        private final int TAB_HEADER = 1, TAB_ITEM = 2, TAB_FOOTER = 3;
        View header, footer; //for onCreatViewHolder to
        boolean isHeaderExist, isFooterExist;
        public HeaderFooterAdapter(View header, List<String> id, View footer) {
            /* items >> make the list before notifyItemInsert() because of invoking getItemCount*/
            this.id = id;
            isHeaderExist = header != null;
            isFooterExist = footer != null;
//            if (isHeaderExist) notifyItemInserted(0);
//            if (isFooterExist) notifyItemInserted(getItemCount()-1);
            this.header = header;
            this.footer = footer;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            switch (viewtype){
                case TAB_HEADER:
                    header.setTag(TAB_HEADER);
                    header.getLayoutParams().height = 500;//modified the height
                    return new Holder(header);
                case TAB_ITEM:
                      default:
                          View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
                          view.getLayoutParams().height = viewGroup.getHeight()/2;
                          view.setTag(TAB_ITEM);
                          return new Holder(view);
                case TAB_FOOTER:
                    footer.setTag(TAB_FOOTER);
                    footer.getLayoutParams().height = 500;//modified the height
                    return new Holder(footer);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Log.e(TAG,"BindViewHolder" );
            /* only bind the holder quantity which can fill the screen*/
            Log.e(TAG, "position:"+position+"; type:"+getItemViewType(position));
            switch (getItemViewType(position)){
                case TAB_HEADER:
                    holder.header_content.setText("I am Header"); //customize header and add listener
                    break;
                case TAB_FOOTER:
                    holder.footer_content.setText("I am footer"); //customize footer and add listener
                    break;
                case TAB_ITEM:
                      default:
                    int text_id = position - 1;//due to the header
                    holder.name.setText(id.get(text_id));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            int header_count = header != null ? 1:0;
            int footer_count = header != null ? 1:0;
            return header_count+footer_count+id.size();
        }

        @Override
        public int getItemViewType(int position) {
            Log.e(TAG,"GetItemViewType" );
            return (position == 0 && isHeaderExist) ? TAB_HEADER :
                    (position == (getItemCount()-1) && isFooterExist ? TAB_FOOTER : TAB_ITEM);
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView name, footer_content, header_content;
            public Holder(@NonNull View itemView) {
                super(itemView);
                int type = (int)itemView.getTag();
                switch (type){
                    case TAB_HEADER:
                        header_content = itemView.findViewById(R.id.tVheader);
                        break;
                    case TAB_FOOTER:
                        footer_content = itemView.findViewById(R.id.tVfooter);
                        break;
                    case TAB_ITEM:
                        default:
                            name = itemView.findViewById(R.id.tVitem);
                            break;
                }
            }
        }
    }
}
