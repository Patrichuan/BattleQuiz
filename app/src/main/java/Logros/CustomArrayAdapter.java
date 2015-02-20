package Logros;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.patrichuan.battlequiz.R;

import java.util.ArrayList;

/**
 * Created by daniel on 20/02/15.
 */
public class CustomArrayAdapter extends ArrayAdapter<Logros> {
    private final Context context;
    private final ArrayList<Logros> data;
    private final int layoutResourceId;

    public CustomArrayAdapter(Context context, int layoutResourceId, ArrayList<Logros> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView1 = (TextView)row.findViewById(R.id.label);
            holder.textView2 = (TextView)row.findViewById(R.id.label2);
            holder.textView3 = (TextView)row.findViewById(R.id.label3);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        Logros logros = data.get(position);

        holder.textView1.setText(logros.getTitulo());
        holder.textView2.setText(logros.getDescripcion());
        holder.textView3.setText(logros.getPuntuacion());

        return row;
    }

    static class ViewHolder
    {
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }
}
