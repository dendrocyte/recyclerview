package com.example.luyiling.recyclerpractice;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luyiling.recyclerpractice.card.CardAdapter;
import com.example.luyiling.recyclerpractice.card.CardData;
import com.example.luyiling.recyclerpractice.group.multidata.GroupAdapter;
import com.example.luyiling.recyclerpractice.group.multidata.GroupMultiData;
import com.example.luyiling.recyclerpractice.group.variabeWH.SpannedGridLayoutManager;
import com.example.luyiling.recyclerpractice.shortcut.ShortcutAdapter;
import com.example.luyiling.recyclerpractice.shortcut.ShortcutData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;


public class BlankFragment extends Fragment {
    private String TAG = BlankFragment.class.getSimpleName();
    View view;
    private OnFragmentInteractionListener mListener;

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
        initCardAdapter();
        initShortcutAdapter();
        initGroupAdapter();
    }


    private List<CardData> initData(){
        List<CardData> data = new LinkedList<>();
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new CardData(id, "0000",null, Color.CYAN));
        }
        return data;
    }
    private List<ShortcutData> initShortcut(){
        List<ShortcutData> data = new LinkedList<>();
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new ShortcutData(id, "0000",null, null, Color.CYAN));
        }
        return data;
    }
    //make header merge into the ** view-model and ** multi-group
    private List<GroupMultiData> initAd(){
        List<GroupMultiData> data = new LinkedList<>();
        //add header
        data.add(new GroupMultiData("header", "111",null, GroupMultiData.HEADER));
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new GroupMultiData(id, "0000","http://"));
        }
        Log.e(TAG, "size:"+data.size());
        return data;
    }

    private void initCardAdapter(){
        RecyclerView cardRecyclerView = view.findViewById(R.id.cardRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),4,
                LinearLayoutManager.VERTICAL, false);
        cardRecyclerView.setLayoutManager(manager);
        cardRecyclerView.setHasFixedSize(true);
        cardRecyclerView.setNestedScrollingEnabled(false);
        CardAdapter adapter = new CardAdapter(R.layout.carditem, initData());
        cardRecyclerView.setAdapter(adapter);
    }

    private void initShortcutAdapter(){
        RecyclerView shortcutRecyclerView = view.findViewById(R.id.shortcutRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),2,
                LinearLayoutManager.HORIZONTAL, false);
        shortcutRecyclerView.setLayoutManager(manager);
        shortcutRecyclerView.setHasFixedSize(true);
        shortcutRecyclerView.setNestedScrollingEnabled(false);//減少黏著 nestscroll view
        ShortcutAdapter adapter = new ShortcutAdapter(R.layout.shortcutitem, initShortcut());
        shortcutRecyclerView.setAdapter(adapter);
    }

    //modify width or height only
    /*private void initGroupAdapter(){
        RecyclerView groupRecyclerView = view.findViewById(R.id.groupRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),4,
                LinearLayoutManager.VERTICAL, false);
        groupRecyclerView.setLayoutManager(manager);
        groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setNestedScrollingEnabled(false);
        GroupAdapter adapter = new GroupAdapter(initAd());
        groupRecyclerView.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//must after setLayoutManager(manager)
            @Override
            public int getSpanSize(int position) {//** in multi-group, which is the header
                //中間那格2,2的不規則分佈
                switch (position){
                    case 0://header
                        return 4;
                    case 1:
                        return 3;
                    case 3:
                    case 4:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
    }*/
    //for modify windth and height
    private void initGroupAdapter(){
        RecyclerView groupRecyclerView = view.findViewById(R.id.groupRecycler);
        //the order is from the top to the end
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
        groupRecyclerView.setLayoutManager(manager);
        groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setNestedScrollingEnabled(false);
        GroupAdapter adapter = new GroupAdapter(initAd());
        groupRecyclerView.setAdapter(adapter);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
