package qinyou.com.washcar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private View view1, view2,view3;
    private ViewPager viewPager;
    private List<View> viewList;
    private ViewGroup group;//导航的小圆点图标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView(this);
    }

    private void initView(final Activity activity) {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater lf = getLayoutInflater().from(this);
        view1 = lf.inflate(R.layout.guide1, null);
        view2 = lf.inflate(R.layout.guide2, null);
        view3 = lf.inflate(R.layout.guide3, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        //group = (ViewGroup) findViewById(R.id.viewGroup);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                //return titleList.get(position);//直接用适配器来完成标题的显示，所以从上面可以看到，我们没有使用PagerTitleStrip。当然你可以使用。
                return "";
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                if(position==viewList.size()-1){
                    //当为最后一页时 注册click事件 跳转主页
                    viewList.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(GuideActivity.this,IndexActivity.class);
                            startActivity(intent);
                            finish();
                            //Toast.makeText(GuideActivity.this,"view click",Toast.LENGTH_LONG).show();

                        }
                    });
                }
                /*weibo_button=(Button) findViewById(R.id.button1);//这个需要注意，我们是在重写adapter里面实例化button组件的，如果你在onCreate()方法里这样做会报错的。
                weibo_button.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        intent=new Intent(ViewPagerDemo.this,WeiBoActivity.class);
                        startActivity(intent);
                    }
                });*/
                return viewList.get(position);
            }

        };

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                Toast.makeText(GuideActivity.this,"current Page:"+i,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setAdapter(pagerAdapter);
    }

    private void initGroupImages(){

    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
