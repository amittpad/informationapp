package amittpad.com.informationapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by AA.
 */
public class CustomDrawerAdapter extends RecyclerView.Adapter<DrawerViewHolder> {
    Context context;
    ArrayList<String> menuTitles;
    public static int selected_item = 0;


    public CustomDrawerAdapter(Context context, ArrayList<String> menuTitles) {
        this.context = context;
        this.menuTitles = menuTitles;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, null);
        DrawerViewHolder drawerViewHolder = new DrawerViewHolder(context, view);
        return drawerViewHolder;
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        if (position == selected_item) {
            holder.title.setTextColor(Color.parseColor("#CAB51C"));
        } else
            holder.title.setTextColor(Color.WHITE);
        holder.title.setText(menuTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return menuTitles.size();
    }
}
