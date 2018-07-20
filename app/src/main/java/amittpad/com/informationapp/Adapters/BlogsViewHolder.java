package amittpad.com.informationapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import amittpad.com.informationapp.R;


/**
 * Created by AA
 */
public class BlogsViewHolder extends RecyclerView.ViewHolder {
    TextView title,viewMore;
    ImageView icon;

    public BlogsViewHolder(final Context context, View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        viewMore = (TextView) itemView.findViewById(R.id.viewMore);
        icon = (ImageView) itemView.findViewById(R.id.icon);
    }
}
