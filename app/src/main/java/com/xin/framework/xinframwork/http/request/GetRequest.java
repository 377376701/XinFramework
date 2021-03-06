package com.xin.framework.xinframwork.http.request;

import com.xin.framework.xinframwork.http.model.HttpMethod;
import com.xin.framework.xinframwork.http.request.base.NoBodyRequest;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description : GET请求
 * Created by xin on 2017/8/23 0023.
 */

public class GetRequest<T> extends NoBodyRequest<T, GetRequest<T>> {

    public GetRequest(String url) {
        super(url);
    }


    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public okhttp3.Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = generateRequestBuilder(requestBody);
        return requestBuilder.get().url(url).tag(tag).build();
    }


}
