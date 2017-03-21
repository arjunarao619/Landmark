package landmark.arjunarao.arjunrao.com.landmark;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements OnItemClickListener {
    static final LauncherIcon[] ICONS = {
            new LauncherIcon(R.drawable.food, "Restaurant"),
            new LauncherIcon(R.drawable.atm, "ATM"),
            new LauncherIcon(R.drawable.pharmacy, "Pharmacy"),
            new LauncherIcon(R.drawable.bank, "Bank"),
            new LauncherIcon(R.drawable.cafe, "Cafe"),
            new LauncherIcon(R.drawable.clothes, "Clothing Store"),
            new LauncherIcon(R.drawable.electronics, "Electronics"),
            new LauncherIcon(R.drawable.police, "Police"),
            new LauncherIcon(R.drawable.school, "School"),
            new LauncherIcon(R.drawable.hardware, "Hardware Store"),
            new LauncherIcon(R.drawable.fire, "Fire Station"),
            new LauncherIcon(R.drawable.dentist, "Dentist"),





    };

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank,container,false);
        //some code
        GridView gridview = (GridView) view.findViewById(R.id.dashboard_grid);
        gridview.setAdapter(new ImageAdapter(view.getContext()));
        gridview.setOnItemClickListener(this);

        return view;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;


        }
    }


    static class LauncherIcon {
        final String text;
        final int imgId;


        public LauncherIcon(int imgId, String text) {
            super();
            this.imgId = imgId;
            this.text = text;

        }

    }

    static class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return ICONS.length;
        }

        @Override
        public LauncherIcon getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        static class ViewHolder {
            public ImageView icon;
            public TextView text;
        }

        // Create a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ViewHolder holder;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

                v = vi.inflate(R.layout.dashboard_icon, null);
                holder = new ViewHolder();
                holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
                holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }

            holder.icon.setImageResource(ICONS[position].imgId);
            holder.text.setText(ICONS[position].text);

            return v;
        }
    }


}
