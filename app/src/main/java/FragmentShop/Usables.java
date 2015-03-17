package FragmentShop;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrichuan.battlequiz.FontsOverride;
import com.example.patrichuan.battlequiz.R;

import java.util.ArrayList;

/**
 * Created by daniel on 27/01/15.
 */
public class Usables extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_usables, container,false);



        GridView gridview = (GridView) rootView.findViewById(R.id.gridviewUsables);
        ArrayList<ItemShop> itemList2=new ArrayList<>();

        //ESTO ES PROVISIONAL SE DEBERA CONSULTAR EN LA BBDD PARA RELLENAR EL ARRAYLIST <-----------------------------------------
        ItemShop primero,segundo,tercero,cuarto,quinto,sexto;
        primero = new ItemShop("SUPERGLUE X","Target player skip the next turn",R.drawable.three_c_flask,2000);
        segundo = new ItemShop("FIRE EYES","Destroy target square on the board",R.drawable.three_c_fire_eyes,4000);
        tercero = new ItemShop("BUTTON EYES","Cmon, don't buy this item...",R.drawable.three_c_button_eyes,500);
        cuarto= new ItemShop("THE SHIELD","Ignore the next spell that targets you",R.drawable.three_c_shield,3500);
        quinto = new ItemShop("I AM NOT","You can walk through one colored square",R.drawable.three_c_i_am_not,2800);
        sexto= new ItemShop("MOUSTACHE","Is there any better than a moustache?",R.drawable.three_c_moustache,1000);
        itemList2.add(primero);
        itemList2.add(segundo);
        itemList2.add(tercero);
        itemList2.add(cuarto);
        itemList2.add(quinto);
        itemList2.add(sexto);
        itemList2.add(primero);
        itemList2.add(segundo);
        itemList2.add(tercero);
        itemList2.add(cuarto);
        itemList2.add(quinto);
        itemList2.add(sexto);
        //----------------------------------------------------------------------------------------------------------------------------


        gridview.setAdapter(new ArrayAdapterGridView(getActivity(), R.layout.shop_gv_item, itemList2));



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
    }
}
