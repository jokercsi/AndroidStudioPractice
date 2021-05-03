package jp.aoyama.a5817028.lgfirstandroidgui;


import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MultipleAnimationSample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_multiple_animation);

        CheckBox checkBox = (CheckBox) findViewById(R.id.check1) ;
        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : process the click event.
            }
        }) ;

    }



    public void onClick(View v) {
        // 【1】インスタンスを生成
        AnimationSet set = new AnimationSet(true);

        // 【2】基本のアニメーションを生成
        AlphaAnimation alpha = new AlphaAnimation(0.9f, 0.2f);
        RotateAnimation rotate = new RotateAnimation(0, 360, 50, 25);
        ScaleAnimation scale = new ScaleAnimation(0.1f, 1, 0.1f, 1);
        TranslateAnimation translate = new TranslateAnimation(50, 0, 200, 0);

        // 【3】生成したアニメーションを追加
        set.addAnimation(alpha);
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(translate);

        // 【4】アニメーション時間を設定して動作開始
        set.setDuration(3000);
        v.startAnimation(set);
    }

}
