package com.lelaohui.limagelibrary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lelaohui.limagelibrary.test", appContext.getPackageName());
        ImageView imageView=new ImageView(appContext);
        Picasso.with(appContext).load("http://i.imgur.com/DvpvklR.png").into(imageView);

//        Picasso.with(context).load(url).into(imageView);
    }
}
