package amittpad.com.informationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amittpad.com.informationapp.BlogDetail;
import amittpad.com.informationapp.Fragments.BlogData;
import amittpad.com.informationapp.R;

/**
 * Created by AA
 */
public class BlogsAdapter extends RecyclerView.Adapter<BlogsViewHolder> {
    Context context;
    ArrayList<BlogData> blogDatas;


    public BlogsAdapter(Context context, ArrayList<BlogData> blogDatas) {
        this.context = context;
        this.blogDatas = blogDatas;
    }

    @Override
    public BlogsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bogs_list_items, null);
        BlogsViewHolder blogsViewHolder = new BlogsViewHolder(context, view);
        return blogsViewHolder;
    }

    @Override
    public void onBindViewHolder(BlogsViewHolder holder, final int position) {

        holder.title.setText(blogDatas.get(position).getTitle());
        holder.icon.setImageResource(blogDatas.get(position).getIcon());
        if (blogDatas.get(position).getFileName().equalsIgnoreCase(""))
            holder.viewMore.setVisibility(View.GONE);
        else
            holder.viewMore.setVisibility(View.VISIBLE);

        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BlogDetail.class);
                intent.putExtra("fileName", blogDatas.get(position).getFileName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogDatas.size();
    }
}
