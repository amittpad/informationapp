package amittpad.com.informationapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import amittpad.com.informationapp.Fragments.Home;
import amittpad.com.informationapp.Fragments.PrivacyPolicies;


/**
 * Created by AA.
 */
public class DrawerViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView icon;
    String targetPackage = "amittpad.com.informationapp";

    public DrawerViewHolder(final Context context, View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (getAdapterPosition())
                {
                    case 0:
                        CustomDrawerAdapter.selected_item = getAdapterPosition();
                        MainActivity.customDrawerAdapter.notifyDataSetChanged();
                        ((MainActivity)context).loadFragment(new Home(), false);
                        break;
                    case 1:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Information App");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + targetPackage);
                        context.startActivity(Intent.createChooser(shareIntent, "Share App Using"));
                        break;
                    case 2:
                       /* Intent gmailIntent = new Intent();
                        gmailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                        gmailIntent.setData(Uri.parse("mailto:youremail@gmail.com"));
                        gmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Of Informational App");
                        try {
                            context.startActivity(gmailIntent);
                        } catch(ActivityNotFoundException ex) {
                            // handle error
                        }*/

                        ////////////////////////////////////////////////////////
                        Intent send = new Intent(Intent.ACTION_SENDTO);
                        String uriText = "mailto:" + Uri.encode("youremail@gmail.com") +
                                "?subject=" + Uri.encode("Feedback Of Informational App") ;
                        Uri uri = Uri.parse(uriText);

                        send.setData(uri);
                        context.startActivity(Intent.createChooser(send, "Send mail..."));
                        break;
                    case 3:
                        CustomDrawerAdapter.selected_item = getAdapterPosition();
                        MainActivity.customDrawerAdapter.notifyDataSetChanged();
                        ((MainActivity)context).loadFragment(new PrivacyPolicies(), false);
                        break;

                }
                MainActivity.drawerLayout.closeDrawers();

            }
        });
    }
}
