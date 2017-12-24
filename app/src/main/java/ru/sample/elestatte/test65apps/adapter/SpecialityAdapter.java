package ru.sample.elestatte.test65apps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * Speciality Adapter for display Speciality in spinner
 *
 * @author Shramko Alexey
 *         Date: 20.12.17
 */
public class SpecialityAdapter extends ArrayAdapter<Speciality> implements SpinnerAdapter {

    static class ViewHolder {
        @BindView(android.R.id.text1)
        TextView name;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

    public SpecialityAdapter(Context context, List<Speciality> items) {
        super(context, android.R.layout.simple_list_item_1, items);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (null == view) {
            view = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_1, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Speciality item = getItem(position);
        if (null != item) {
            holder.name.setText(item.name);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, View view, @NonNull ViewGroup parent) {
        return getView(position, view, parent);
    }

}