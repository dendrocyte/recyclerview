package com.example.luyiling.recyclerpractice;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luyiling.recyclerpractice.card.CardAdapter;
import com.example.luyiling.recyclerpractice.card.CardData;
import com.example.luyiling.recyclerpractice.group.GroupAdapter;
import com.example.luyiling.recyclerpractice.group.GroupData;
import com.example.luyiling.recyclerpractice.shortcut.ShortcutAdapter;
import com.example.luyiling.recyclerpractice.shortcut.ShortcutData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


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
    private List<GroupData> initAd(){
        List<GroupData> data = new LinkedList<>();
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new GroupData(id, "0000",null));
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
        GridLayoutManager manager = new GridLayoutManager(getContext(),4,
                LinearLayoutManager.VERTICAL, false);
        shortcutRecyclerView.setLayoutManager(manager);
        shortcutRecyclerView.setHasFixedSize(true);
        shortcutRecyclerView.setNestedScrollingEnabled(false);//減少黏著 nestscroll view
        ShortcutAdapter adapter = new ShortcutAdapter(R.layout.shortcutitem, initShortcut());
        shortcutRecyclerView.setAdapter(adapter);
    }

    private void initGroupAdapter(){
        RecyclerView groupRecyclerView = view.findViewById(R.id.groupRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),4,
                LinearLayoutManager.VERTICAL, false);
        groupRecyclerView.setLayoutManager(manager);
        groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setNestedScrollingEnabled(false);
        GroupAdapter adapter = new GroupAdapter(R.layout.aditem, initAd());
        groupRecyclerView.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//must after setLayoutManager(manager)
            @Override
            public int getSpanSize(int position) {
                Log.e(TAG, "here");
                //中間那格2,2的不規則分佈
                switch (position){
                    case 0:
                        return 3;
                    case 2:
                    case 3:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
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
