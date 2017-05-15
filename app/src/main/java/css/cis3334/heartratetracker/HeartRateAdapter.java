package css.cis3334.heartratetracker;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Makar
 */

public class HeartRateAdapter extends ArrayAdapter<HeartRate> {

    private final Context context; //to be used to call other methods
    private HeartRateList heartRateList; //has the array list of heart rates

    /**
     *
     * @param context The activity calling this adapter
     * @param resource The xml file defining the layout for one item or row in the list
     * @param textViewResourceId the ID for a TextView in the row layout. Not used, but needed by the parent object
     * @param heartRateList The object holding the arraylist of hear rates
     */
    public HeartRateAdapter(Context context, int resource, int textViewResourceId, HeartRateList heartRateList) {
        super(context, resource, textViewResourceId, heartRateList.getList());
        this.context = context;
        this.heartRateList = heartRateList;
    }

    /**
     * This is called automatically to display each item in the list.
     *    Here you must fill the layout for one row or item in the list
     *
     * @param position index in the list that is being selected
     * @param convertView
     * @param parent The parent layout this list is in
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.heart_rate_row, null);

        //get the heart rate
        HeartRate hr = heartRateList.getHeartRate(position);

        LinearLayout hrtRateLinear = (LinearLayout) view.findViewById(R.id.llHeartRateRow);
        TextView tvPulse=(TextView) view.findViewById(R.id.textViewPulse);
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDisplayDscrpt);
        TextView tvRange = (TextView) view.findViewById(R.id.tvDisplayRange);

        //For 25 year old users:
        if (hr.getPulse() >= 176) {
            tvRange.setBackgroundResource(R.color.color_red);
            tvPulse.setTextColor(ContextCompat.getColor(context, R.color.color_red));
        }

        else if (hr.getPulse() >= 156 && hr.getPulse() <= 176) {
            tvRange.setBackgroundResource(R.color.opaque_orange);
            tvPulse.setTextColor(ContextCompat.getColor(context, R.color.opaque_orange));
        }

        else if (hr.getPulse() >= 137 && hr.getPulse() <= 156) {
            tvRange.setBackgroundResource(R.color.color_orange);
            tvPulse.setTextColor(ContextCompat.getColor(context, R.color.color_orange));
        }

        else if (hr.getPulse() >= 117 && hr.getPulse() <= 137) {
            tvRange.setBackgroundResource(R.color.translucent_orange);
            tvPulse.setTextColor(ContextCompat.getColor(context, R.color.translucent_orange));
        }

        else if (hr.getPulse() >= 98) {
            tvRange.setBackgroundResource(R.color.color_yellow);
            tvPulse.setTextColor(ContextCompat.getColor(context, R.color.color_yellow));
        }

        else{
            tvRange.setBackgroundResource(R.color.background_color);
            tvPulse.setTextColor(ContextCompat.getColor(context, R.color.color_red));
        }

        String addSpace = " ";

        tvPulse.setText(hr.getPulse().toString() + addSpace);
        tvRange.setText(hr.getRangeName().toString() + addSpace);
        tvDescription.setText(hr.getRangeDescrtiption().toString() + addSpace);

        return(view);
    }
}
