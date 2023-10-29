package com.example.apidemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;

public class SearchRoverFragment extends Fragment {
    private View parentView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RoverViewAdapter roverViewAdapter;
    private ImageButton searchButton;
    private LinearLayout progressBarLayout;
    private String currentRover;
    private List<RoverPicture> roverPictureSearch;
    private RadioGroup roverGroup;
    private RadioGroup displayGroup;
    private static final int NUMBER_OF_SEARCH = 4;
    private int numberOfSearchCount;
    private LocalClient localClient;
    public SearchRoverFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.parentView = inflater.inflate(R.layout.search_fragment, container, false);

        this.recyclerView = (RecyclerView) this.parentView.findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );

        this.recyclerView.setLayoutManager(this.layoutManager);
        this.roverViewAdapter = new RoverViewAdapter();

        this.recyclerView.setAdapter(this.roverViewAdapter);
        this.currentRover = "Curiosity";

        this.roverPictureSearch = new ArrayList<>();

        this.localClient = new LocalClient(this.parentView.getContext());
        this.progressBarLayout = (LinearLayout) this.parentView.findViewById(R.id.progressBarLayout);

        setupGUI();


        return this.parentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

   
    private void setupGUI(){

        this.roverGroup = this.parentView.findViewById(R.id.roverGroup);
        this.displayGroup = this.parentView.findViewById(R.id.displayGroup);


        this.roverGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                setSelectRover(group.getCheckedRadioButtonId());
            }
        });

        this.displayGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                setSelectDisplay(group.getCheckedRadioButtonId());
            }
        });

        this.searchButton = (ImageButton) this.parentView.findViewById(R.id.searchButton);

        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBarLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

                roverPictureSearch.clear();

                numberOfSearchCount = 0;

                loadRoverInfo(currentRover);
            }
        });

        this.progressBarLayout.setVisibility(View.GONE);
        this.recyclerView.setVisibility(View.GONE);

        if(this.localClient.dataAlReadyExist()){

            roverViewAdapter.updateDataSet(localClient.getAllRovers());
            this.recyclerView.setVisibility(View.VISIBLE);
        }


    }


    private void setSelectRover(int selectedId){

        if(selectedId == R.id.radioCuriosity){
            this.currentRover = "Curiosity";
        }else if(selectedId == R.id.radioOpportunity){
            this.currentRover = "Opportunity";
        }else{
            this.currentRover = "Spirit";
        }
    }


    private void setSelectDisplay(int selectedId){
        if(selectedId == R.id.radioList){
            this.recyclerView.setLayoutManager(this.layoutManager);
        }
        else{
            this.recyclerView.setLayoutManager(new GridLayoutManager(this.parentView.getContext(), 2));
        }
    }


    @SuppressLint("CheckResult")
    private void loadRoverInfo(final String rover){
      RemoteClient.getService().getManifestForRover(rover, MainActivity.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new SingleObserver<RoverManifestResponse>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(RoverManifestResponse roverManifestResponse) {

                        getDataOfRover(currentRover, roverManifestResponse.getManifest().getMaxDate());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("errorapi", e.getMessage());
                    }
                });

    }


    private void getDataOfRover(final String rover, final String date){

        SingleObserver<RoverPictureResponse> errorapi = RemoteClient.getService().getRover(rover, date, MainActivity.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new SingleObserver<RoverPictureResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(RoverPictureResponse roverPictureResponse) {

                        if (numberOfSearchCount == NUMBER_OF_SEARCH) {

                            roverViewAdapter.updateDataSet(roverPictureSearch);
                            localClient.clearDatabase();

                            for (RoverPicture p : roverPictureSearch) {
                                localClient.storeRover(p);
                            }

                            progressBarLayout.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                            numberOfSearchCount = 0;

                            return;
                        }

                        String[] splitedDate = date.split("-");

                        String newDate = splitedDate[0] + "-" + splitedDate[1] + "-" + (Integer.valueOf(splitedDate[2]) - 1);

                        numberOfSearchCount++;

                        for (RoverPicture rp : roverPictureResponse.getRoverPicture()) {

                            rp.setDate(date);
                            roverPictureSearch.add(rp);
                        }

                        getDataOfRover(rover, newDate);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("errorapi", e.getMessage());
                    }
                });

    }

}
