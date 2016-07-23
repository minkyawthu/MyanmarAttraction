package com.example.mkt.padcexercise5.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mkt.padcexercise5.MyanmarAttractionApp;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.adapters.AttractAdapter;
import com.example.mkt.padcexercise5.data.models.AttractModel;
import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.example.mkt.padcexercise5.events.DataEvent;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyanmarAttractionFragment extends Fragment {

    private AttractAdapter attractAdapter;
    private ControllerAttractItem onControllerAttractItem;

    private RecyclerView rvAttract;

    public MyanmarAttractionFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onControllerAttractItem = (ControllerAttractItem) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*attractAdapter = new AttractAdapter(AttractModel.getObjInstance().getAttractList(), onControllerAttractItem);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myanmar_attraction, container, false);
        rvAttract = (RecyclerView) view.findViewById(R.id.rv_attract);
        List<AttractVO> attractionList = AttractModel.getObjInstance().getAttractList();
        rvAttract.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        attractAdapter = new AttractAdapter(attractionList, onControllerAttractItem);
        rvAttract.setAdapter(attractAdapter);

        return view;

    }

    public interface ControllerAttractItem {
        void onTapAttract(AttractVO attractVO);
    }

    public void onEventMainThread(DataEvent.AttractionDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(MyanmarAttractionApp.getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<AttractVO> newAttractionList = event.getAttractionList();
        attractAdapter.setNewData(newAttractionList);
        rvAttract.setAdapter(attractAdapter);
    }
}
