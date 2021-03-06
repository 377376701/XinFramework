package com.xin.framework.xinframwork.http.plugins.glide.base;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.xin.framework.xinframwork.http.plugins.glide.progress.GlideProgressManager;

import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;

@GlideModule
public class GlideConfig extends AppGlideModule {

    @Override
    public void applyOptions(final Context context, final GlideBuilder builder) {
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, 1024 * 1024 * 500));
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

        Call.Factory clint = GlideProgressManager.getInstance().with(new OkHttpClient().newBuilder()).build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(clint));


    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
