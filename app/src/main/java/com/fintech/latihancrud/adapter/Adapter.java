package com.fintech.latihancrud.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fintech.latihancrud.R;
import com.fintech.latihancrud.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<Data> list;
    TextView name,email;

    public Adapter(Activity activity,  List<Data> list) {
        this.activity = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        name = view.findViewById(R.id.nameTv);
        email = view.findViewById(R.id.emailTv);

        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null && layoutInflater != null){
            view = layoutInflater.inflate(R.layout.list_users,null);
        }
        if(view != null){

            Data data = list.get(i);
            name.setText(data.getName());
            email.setText(data.getEmail());

        }
        return view;
    }
}
