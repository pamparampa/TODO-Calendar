package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.example.pamparampa.todocalendar.R;

/**
 * Created by Pamparampa on 2018-02-01.
 */

class CalendarRowAdapter extends BaseAdapter {
    private final Context context;
    CalendarParameters params;
    private CalendarSizesManager sizesManager;

    CalendarRowAdapter(Context context, CalendarParameters params) {
        this.context = context;
        this.params = params;
    }

    @Override
    public int getCount() {
        return 24;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderPattern viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(params.getRowViewResource(), parent, false);
            convertView.setLayoutParams(new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, params.getSizesManager().getRectHeight()));

            viewHolder = new ViewHolderPattern();
            viewHolder.rowView = convertView.findViewById(R.id.rowView);
            viewHolder.rowView.setDate(params.getFirstVisibleDate());
            viewHolder.rowView.setRowId(position);
            viewHolder.rowView.setParams(params);
            viewHolder.rowView.setSizesManager(sizesManager);           // TODO Czy to sie wykona zawsze przy zmianie rozmiarow?
            viewHolder.rowView.compose();

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolderPattern) convertView.getTag();
        }
        viewHolder.rowView.setRowId(position);

        return convertView;
    }

    public void setSizesManager(CalendarSizesManager sizesManager) {
        this.sizesManager = sizesManager;
    }

    private class ViewHolderPattern {
        CalendarWeekRowView rowView;
    }
}
