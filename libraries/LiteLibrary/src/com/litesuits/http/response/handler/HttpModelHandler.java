package com.litesuits.http.response.handler;

import com.litesuits.android.log.Log;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;

/**
 * Handle Response on UI Thread
 * 
 * @author MaTianyu
 * 2014-2-26下午9:02:09
 */
public abstract class HttpModelHandler<Model> {
	protected abstract void onSuccess(Model data, Response res);

	protected abstract void onFailure(HttpException e, Response res);

	public HttpModelHandler<Model> handleModelData(Model json, Response res) {
		if (res != null) {
			HttpException he = res.getException();
            //
            Log.d("json",json);
			if (he != null) {
				onFailure(he, res);
			} else {
				onSuccess(json, res);
			}
		}
		return this;
	}
}
