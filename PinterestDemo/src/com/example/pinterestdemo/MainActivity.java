package com.example.pinterestdemo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.pinterestdemo.DrawableManager.ImageCallBack;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private LinearLayout casecade;
	private LinearLayout casecade1;
	private LinearLayout casecade2;
	private LinearLayout casecade3;
	private Display display;
	private AssetManager manager;
	private List<String> imagepaths;
	private static final String imagepath = "imgs";
	private int casecadeWidth;
	private ImageView[] igv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = this.getWindowManager().getDefaultDisplay();
        casecadeWidth = display.getWidth() / 3;
        manager = this.getAssets();
        System.out.println(manager);
        findViews();
    }
    public void findViews(){
    	casecade = (LinearLayout) findViewById(R.id.casecade);
    	casecade1 = (LinearLayout) findViewById(R.id.casecade1);
    	casecade2 = (LinearLayout) findViewById(R.id.casecade2);
    	casecade3 = (LinearLayout) findViewById(R.id.casecade3);
    	LayoutParams lp1 = casecade1.getLayoutParams();
    	lp1.width = casecadeWidth;
    	casecade1.setLayoutParams(lp1);
    	LayoutParams lp2 = casecade2.getLayoutParams();
    	lp2.width = casecadeWidth;
    	casecade1.setLayoutParams(lp2);
    	LayoutParams lp3 = casecade3.getLayoutParams();
    	lp3.width = casecadeWidth;
    	casecade1.setLayoutParams(lp3);
    	try {
			imagepaths = Arrays.asList(manager.list("imgs"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	int j = 0;
    	igv = new ImageView[imagepaths.size()];
    	for (int i = 0; i < imagepaths.size(); i++) {
    		addImgToCasecade(imagepaths.get(i), j);
    		igv[i] = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_layout, null);
    		j++;
    		if(j >= 3){
    			j = 0;
    		}
		}
    }
    private void addImgToCasecade(String filename, int j){
    	ImageView iv = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_layout, null);
    	if(j == 0){
    		casecade1.addView(iv);
    	}else if(j == 1){
    		casecade2.addView(iv);
    	}else{
    		casecade3.addView(iv);
    	}
    	String imgPath = imagepath + "/" + filename;
    	iv.setTag(imgPath);
    	Drawable drawable = DrawableManager.getInstance()
    			.fetchDrawableOnThread(imgPath, manager, new ImageCallBack() {
					@Override
					public void imageLoaded(Drawable imageDrawable, String imageUrl) {
						// TODO Auto-generated method stub
						ImageView iv = (ImageView) casecade.findViewWithTag(imageUrl);
						if(iv != null && imageDrawable != null){
							int oldwidth = imageDrawable.getIntrinsicWidth();
							int oldHeight = imageDrawable.getIntrinsicHeight();
							LayoutParams lp = (LayoutParams) iv.getLayoutParams();
							lp.height = (oldHeight * casecadeWidth) / oldwidth;
							iv.setPadding(0, 2, 0, 0);
							iv.setLayoutParams(lp);
							iv.setImageDrawable(imageDrawable);
						}
					}
				});
    	if(drawable != null){
    		int oldwidth = drawable.getIntrinsicWidth();
    		int oldHeight = drawable.getIntrinsicHeight();
    		LayoutParams lp = (LayoutParams) iv.getLayoutParams();
    		lp.height = (oldHeight * casecadeWidth) / oldwidth;
    		iv.setPadding(0, 2, 0, 0);
    		iv.setLayoutParams(lp);
    		iv.setImageDrawable(drawable);
    	}
    }
}







