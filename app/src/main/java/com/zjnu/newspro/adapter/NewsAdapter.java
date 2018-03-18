package com.zjnu.newspro.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.zjnu.newspro.R;
import com.zjnu.newspro.activity.ArticleActivity;
import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.utils.BitmapUtils;
import com.zjnu.newspro.utils.PicassoRoundTransform;

import java.util.List;

import static com.zjnu.newspro.R.id.tv_shop_name;

/**
 * User--Hu mingzhi on 2018/3/16.
 * Created by ThinKPad
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<ArticleUserPojo.Article> datas;
    private OnItemClickListener onItemClickListener;
    private ViewHolder viewHolder;

    public NewsAdapter(Context mcontext, List<ArticleUserPojo.Article> datas) {
        this.mcontext = mcontext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(View.inflate(mcontext, R.layout.new_mode, null));
        return view;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.setData(datas.get(position));
        viewHolder.llNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, ArticleActivity.class);
                Long articleId = datas.get(position).getArticleId();
                intent.putExtra("articleId", String.valueOf(articleId));
                mcontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llNews;
        private ImageView newPic;
        private TextView tvShopName;
        private TextView tvSource;
        private TextView tvAuthor;
        private TextView tvZanNum;
        private TextView tvSee;
        private ArticleUserPojo.Article data;
        private ImageView iv_user;
        private TextView tv_down_num;
        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2018-03-16 18:54:20 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        private void findViews(View itemView) {
            llNews = (LinearLayout) itemView.findViewById(R.id.ll_news);
            newPic = (ImageView) itemView.findViewById(R.id.new_pic);
            tvShopName = (TextView) itemView.findViewById(tv_shop_name);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvZanNum = (TextView) itemView.findViewById(R.id.tv_zan_num);
            tvSee = (TextView) itemView.findViewById(R.id.tv_see);
            iv_user = itemView.findViewById(R.id.iv_user);
            tv_down_num = itemView.findViewById(R.id.tv_down_num);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.setOnItemClickListener(data);
                    }
                }
            });
        }

        public void setData(ArticleUserPojo.Article data) {
            this.data = data;
            tvShopName.setText(data.getArticleTitle());
            tvSource.setText("来自：" + data.getArticleSource());
            Picasso.with(mcontext)
                    .load(R.drawable.avatar)
                    .error(R.mipmap.ic_launcher)
                    .transform(new PicassoRoundTransform())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(iv_user);

            tvZanNum.setText(data.getArticleUp()+"");
            tvSee.setText(data.getArticleHints()+"");
            tv_down_num.setText(data.getArticleDown() + "");

            try {
                Picasso.with(mcontext)
                        .load(data.getArticleAvatar())
                        .error(R.drawable.shop_icon)
                        .transform(new Transformation() {
                            @Override
                            public Bitmap transform(Bitmap bitmap) {
                                //先对图片进行压缩
//                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                                Bitmap zoom = BitmapUtils.zoom(bitmap, 90, 90);
                                //对请求回来的Bitmap进行圆形处理
                                Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                                bitmap.recycle();//必须队更改之前的进行回收
                                return ciceBitMap;
                            }

                            @Override
                            public String key() {
                                return "";
                            }
                        }).into(newPic);

            } catch (Exception e) {
                //use default avatar
                Glide.with(mcontext)
                        .load("")
                        .placeholder(R.drawable.shop_icon)
                        .into(newPic);
            }


        }
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(ArticleUserPojo.Article data);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
