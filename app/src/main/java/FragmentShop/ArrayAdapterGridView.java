package FragmentShop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patrichuan.battlequiz.FontsOverride;
import com.example.patrichuan.battlequiz.R;
import java.util.ArrayList;

/**
 * Created by daniel on 20/02/15.
 */
public class ArrayAdapterGridView extends ArrayAdapter<ItemShop> {

    private final Context context;
    private final ArrayList<ItemShop> data;
    private final int layoutResourceId;

    public ArrayAdapterGridView(Context context, int layoutResourceId, ArrayList<ItemShop> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            itemView = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView1 = (TextView) itemView.findViewById(R.id.shopLabel1);
            holder.imageView1 = (ImageView) itemView.findViewById(R.id.shopIcon);
            holder.textView2 = (TextView) itemView.findViewById(R.id.shopLabel2);
            holder.textView3 = (TextView) itemView.findViewById(R.id.shopLabel3);

            FontsOverride.setTextViewFont(context, holder.textView1);
            FontsOverride.setTextViewFont(context,holder.textView2);
            FontsOverride.setTextViewFont(context,holder.textView3);

            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }

        ItemShop itemShop = data.get(position);

        holder.textView1.setText(itemShop.getTitulo());
        holder.imageView1.setImageResource(itemShop.getImagen());
        holder.textView2.setText(itemShop.getDescripcion());
        holder.textView3.setText(String.valueOf(itemShop.getDinero()));

        return itemView;
    }

    static class ViewHolder {
        TextView textView1;
        ImageView imageView1;
        TextView textView2;
        TextView textView3;
    }

}
