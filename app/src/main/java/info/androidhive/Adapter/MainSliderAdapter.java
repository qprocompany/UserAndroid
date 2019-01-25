package info.androidhive.Adapter;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

/**
 * Created by ydenn on 7/31/2018.
 */

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("http://192.168.80.63/SPHAIRA_LIVE_ADT/Promo/promo%20rawat%20inap.jpg");
                break;
            case 1:
                viewHolder.bindImageSlide("http://192.168.80.63/SPHAIRA_LIVE_ADT/Promo/promo%20jantung%20sehat.jpg");
                break;
            case 2:
                viewHolder.bindImageSlide("http://192.168.80.63/SPHAIRA_LIVE_ADT/Promo/promo%20melahirkan.jpg");
                break;
        }
    }

}
