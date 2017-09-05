package com.madeni.niqoz.debtapplication.main;

/**
 * Created by niqoz on 8/12/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madeni.niqoz.debtapplication.adapters.CreditorAdapter;
import com.madeni.niqoz.debtapplication.R;
import com.madeni.niqoz.debtapplication.adapters.getCreditors;

import java.util.ArrayList;
import java.util.List;

public class CreditorClass extends Fragment {
     View rootView;
    private List<getCreditors> creditorsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CreditorAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.creditorsfragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewCredit);

        mAdapter = new CreditorAdapter(creditorsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        previewCreditData();

        return rootView;
    }

    private void previewCreditData() {
        getCreditors cred= new getCreditors("Nicholas Muturi", "8520", "456");
        creditorsList.add(cred);

        cred= new getCreditors("Nicholas Muturi", "8520", "456");
        creditorsList.add(cred);

        cred= new getCreditors("Nicholas Muturi", "8520", "456");
        creditorsList.add(cred);
    }

}

