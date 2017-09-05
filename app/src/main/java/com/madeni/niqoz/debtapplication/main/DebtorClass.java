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

import com.madeni.niqoz.debtapplication.R;
import com.madeni.niqoz.debtapplication.adapters.CreditorAdapter;
import com.madeni.niqoz.debtapplication.adapters.DebtorAdapter;
import com.madeni.niqoz.debtapplication.adapters.getDebtors;

import java.util.ArrayList;
import java.util.List;

public class DebtorClass extends Fragment {

    View rootView;
    private List<getDebtors> DebtorsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DebtorAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
            rootView = inflater.inflate(R.layout.debtorsfragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewDebit);

        mAdapter = new DebtorAdapter(DebtorsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        previewDebitData();

        return rootView;
    }

    private void previewDebitData() {
        getDebtors cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);

        cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);

        cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);

        cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);
        cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);

        cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);
        cred= new getDebtors("Nicholas ", "8520", "456");
        DebtorsList.add(cred);



    }

}
