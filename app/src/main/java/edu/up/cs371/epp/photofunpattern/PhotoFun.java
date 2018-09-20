package edu.up.cs371.epp.photofunpattern;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
        import android.widget.Button;
        import android.view.View;
import android.widget.Spinner;

/**
 *  class PhotoFun controls this photo manipulation app.
 *
 *  @author  Edward C. Epp
 *  @version November 2017
 *   https://github.com/edcepp/PhotoFunPattern
 *
 */

public class PhotoFun extends AppCompatActivity {

    // Image resources
    private Bitmap myOriginalBmp;
    private ImageView myNewImageView;

    /*
    * onCreate This constructor lays out the user interface, initializes the
    * original image and links buttons to their actions.
    *
    * @param savedInstanceState Required by parent object
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fun);

        final ImageView originalImageView =
                (ImageView) findViewById(R.id.originalImage);
        BitmapDrawable originalDrawableBmp =
                (BitmapDrawable) originalImageView.getDrawable();
        myOriginalBmp = originalDrawableBmp.getBitmap();

        myNewImageView = (ImageView) findViewById(R.id.newImage);

        Button grayFilterButton =
                (Button) findViewById(R.id.smoothFilterButton);
        grayFilterButton.setOnClickListener(new grayFilterButtonListener());
        Button brightnessFilterButton =
                (Button) findViewById(R.id.westEdgeFilterButton);
        brightnessFilterButton.setOnClickListener
                (new brightnessFilterButtonListener());

        // spinner
        final Spinner spinner = (Spinner) findViewById(R.id.image_spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.imageNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int pos, long l)
            {
                adapter.getItem(pos);
                String item = spinner.getSelectedItem().toString();
                if (item.equals("blank"))
                    originalImageView.setImageResource(R.drawable.blank);
                else if(item.equals("cheese"))
                    originalImageView.setImageResource(R.drawable.cheese);
                else if(item.equals("edcepp"))
                    originalImageView.setImageResource(R.drawable.edcepp);
                else if(item.equals("olivia"))
                    originalImageView.setImageResource(R.drawable.olivia);
                else if(item.equals("olivia25noise"))
                    originalImageView.setImageResource(R.drawable.olivia25noise);
                else if(item.equals("olivia_small"))
                    originalImageView.setImageResource(R.drawable.olivia_small);
                else if(item.equals("two"))
                    originalImageView.setImageResource(R.drawable.two);

            }
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

    }

    /*
    * class grayFilterButtonListener this inner class defines the action for
    * the gray filter button.
    */
    private class grayFilterButtonListener implements View.OnClickListener {
        public void onClick(View button) {
            GrayFilter filter = new GrayFilter();
            myNewImageView.setImageBitmap(filter.apply(myOriginalBmp));
        }
    }

    /*
    * class grayFilterButtonListener this inner class defines the action for the
    * brightness filter
    * button.
    */
    private class brightnessFilterButtonListener
            implements View.OnClickListener {
        public void onClick(View button) {
            WestEdgeFilter filter = new WestEdgeFilter();
            myNewImageView.setImageBitmap(filter.apply(myOriginalBmp));
        }
    }



}

