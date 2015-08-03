package alizinha.c4q.nyc.onemorechance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by c4q-anthony-mcbride on 8/2/15.
 */
public class CustomAdapter extends ArrayAdapter<Office> {

    Office[] offices;

    public CustomAdapter(Context context, int resource, Office[] offices) {
        super(context, resource, offices);
        this.offices = offices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) view.findViewById(R.id.title);
        TextView addy = (TextView) view.findViewById(R.id.address);
        name.setText(offices[position].name);
        addy.setText(offices[position].address1);
        Log.w("TAG", "Called");
        return view;

    }
}
