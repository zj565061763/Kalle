/*
 * Copyright 2018 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.kalle.connect.http;

import com.yanzhenjie.kalle.Response;
import com.yanzhenjie.kalle.connect.Interceptor;

import java.io.IOException;

/**
 * Created by YanZhenjie on 2018/3/6.
 */
public class RetryInterceptor implements Interceptor
{

    private int mCount;

    public RetryInterceptor(int count)
    {
        this.mCount = count;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        try
        {
            return chain.proceed(chain.request());
        } catch (IOException e)
        {
            if (mCount > 0)
            {
                mCount--;
                return intercept(chain);
            }
            throw e;
        }
    }
}